package com.gary.interview.shape.app;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gary.interview.shape.errors.InvalidFilePathException;
import com.gary.interview.shape.errors.InvalidShapeException;
import com.gary.interview.shape.models.BaseShape;
import com.gary.interview.shape.models.Shape;
import com.gary.interview.shape.repositories.ShapeRepository;

@Component
class ShapeLoader {

    @Autowired
    private ShapeRepository shapeRepository;

    @Autowired
    private ShapeFactory shapeFactory;

    void loadShapes(String shapeFilesDir) throws InvalidFilePathException {
        File directory = validate(shapeFilesDir);

        Collection<File> files = FileUtils.listFiles(directory, new ShapeFileFilter(), null);

        for (File file : files) {
            try {
                int loadedCnt = loadShapes(file);

                System.out.printf("%10d shape(s) were loaded from file: %s\n", loadedCnt, file.getAbsolutePath());
            } catch (IOException e) {
                System.out.printf("Error reading file: '%s', skipped...\n", file.getAbsolutePath());
            }
        }
    }

    private File validate(String shapeFilesLocation) throws InvalidFilePathException {
        if (shapeFilesLocation == null || shapeFilesLocation.trim().isEmpty()) {
            throw new InvalidFilePathException("File location cannot be null or empty(trimmed): " + shapeFilesLocation);
        }

        File directory = new File(shapeFilesLocation);

        if (!directory.exists()) {
            throw new InvalidFilePathException("File location does not exist: " + shapeFilesLocation);
        }

        if (!directory.isDirectory()) {
            throw new InvalidFilePathException("File location is not a directory: " + shapeFilesLocation);
        }

        if (!directory.canRead()) {
            throw new InvalidFilePathException("File location is not readable: " + shapeFilesLocation);
        }

        return directory;
    }

    private int loadShapes(File dataFile) throws IOException {
        int count = 0;

        LineIterator it = FileUtils.lineIterator(dataFile);

        try {
            while (it.hasNext()) {
                try {
                    Shape shape = shapeFactory.parseShape(it.nextLine());

                    shapeRepository.addShape((BaseShape) shape);

                    count++;
                } catch (InvalidShapeException e) {
                    // ignore, invalid line will be skipped silently
                }
            }
        } finally {
            it.close();
        }

        return count;
    }

    private class ShapeFileFilter implements IOFileFilter {

        public boolean accept(File file) {
            return file.isFile() && file.canRead() && file.getName().toLowerCase().endsWith(".txt");
        }

        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(".txt");
        }
    }
}
