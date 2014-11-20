package com.gary.interview.shape.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gary.interview.shape.errors.InvalidFilePathException;
import com.gary.interview.shape.errors.InvalidShapeException;
import com.gary.interview.shape.models.BaseShape;
import com.gary.interview.shape.models.Point;
import com.gary.interview.shape.models.Shape;
import com.gary.interview.shape.repositories.ShapeRepository;

public class AppConsole {
    private final static String exitCmd = "exit";
    private final static String helpCmd = "help";
    private final static String queryCmd = "-?\\d+(\\.\\d+)?\\s-?\\d+(\\.\\d+)?";

    @Autowired
    private ShapeFactory shapeFactory;

    @Autowired
    private ShapeSearcher shapeSeacher;

    @Autowired
    private ShapeLoader shapeLoader;

    @Autowired
    private ShapeRepository shapeRepository;

    public static void main(String args[]) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("appContext.xml");
        AppConsole app = (AppConsole) appContext.getBean("appConsole");

        System.out.printf("\n\nThe Shape Game Application is starting ....\n");

        app.loadShapes();
        app.printWelcome();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                app.printPrompt();
                String line = br.readLine().trim();

                while (!line.equals(exitCmd)) {
                    if (line.isEmpty()) {
                        // do nothing
                    } else if (line.equals(helpCmd)) {
                        app.printUsage();
                    } else if (line.matches(queryCmd)) {
                        app.searchShapes(line);
                    } else {
                        app.addShape(line);
                    }

                    app.printPrompt();
                    line = br.readLine();
                }

                System.out.printf("\nGoodbye! Remember to have fun...");
                System.exit(0);
            } finally {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void loadShapes() {
        System.out.printf("...\n");
        System.out.printf("Started to load shapes from files at location: %s\n", shapeFilesDir);

        try {
            shapeLoader.loadShapes(shapeFilesDir);
        } catch (InvalidFilePathException e) {
            System.out.println(e.getMessage());
        }

        System.out.printf("Completed loading shapes from files.\n");
        System.out.printf("...\n");
    }

    private void searchShapes(String line) {
        String data[] = line.split("\\s");

        double x = Double.parseDouble(data[0]);
        double y = Double.parseDouble(data[1]);
        double totalArea = 0.0;

        Point point = new Point(x, y);

        NumberFormat fmt = NumberFormat.getInstance();

        System.out.printf("Searching for shapes that include (%s, %s) ...\n", fmt.format(x), fmt.format(y));

        List<Shape> result = shapeSeacher.search(point, searchThreads);
        for (Shape shape : result) {
            if (shape.isInShape(point)) {
                double area = shape.getArea();
                totalArea += area;
                System.out.printf("\t%s, area: %s\n", shape.toString(), fmt.format(area));
            }
        }
        System.out.printf("Found %d shape(s) that include (%s, %s), total area is %s\n",
                result.size(), fmt.format(x), fmt.format(y), fmt.format(totalArea));
    }

    private void addShape(String line) {
        try {
            Shape shape = shapeFactory.parseShape(line);

            shapeRepository.addShape((BaseShape) shape);

            System.out.printf("%s\n", shape.toString());
        } catch (InvalidShapeException e) {
            System.out.printf("%s\n", e.getMessage());
        }
    }

    private void printPrompt() {
        System.out.print(prompt);
    }

    private void printWelcome() {
        for (String msg : welcomeMessages) {
            System.out.printf("%s\n", msg);
        }
    }

    private void printUsage() {
        for (String usage : commandUsages) {
            System.out.printf("%s\n", usage);
        }
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public List<String> getWelcomeMessages() {
        return welcomeMessages;
    }

    public void setWelcomeMessages(List<String> welcomeMessages) {
        this.welcomeMessages = welcomeMessages;
    }

    public List<String> getCommandUsages() {
        return commandUsages;
    }

    public void setCommandUsages(List<String> commandUsages) {
        this.commandUsages = commandUsages;
    }

    public String getShapeFilesDir() {
        return shapeFilesDir;
    }

    public void setShapeFilesDir(String shapeFilesDir) {
        this.shapeFilesDir = shapeFilesDir;
    }

    public int getSearchThreads() {
        return searchThreads;
    }

    public void setSearchThreads(int searchThreads) {
        this.searchThreads = searchThreads;
    }

    private String prompt;

    private List<String> welcomeMessages;

    private List<String> commandUsages;

    private int searchThreads;

    private String shapeFilesDir;
}
