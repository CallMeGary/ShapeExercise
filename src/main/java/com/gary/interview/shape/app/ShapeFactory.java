package com.gary.interview.shape.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gary.interview.shape.errors.InvalidShapeException;
import com.gary.interview.shape.models.Shape;

@Component
public class ShapeFactory {

    @Autowired
    private Map<String, Class<Shape>> validShapes;

    private final static String ERR_WRONG_CMD = "invalid command: '%s', use 'help' to check the usage";

    public Shape parseShape(String rawData) throws InvalidShapeException {
        checkStringBlank(rawData);

        for (String shapeName : validShapes.keySet()) {
            if (rawData.startsWith(shapeName + " ")) {
                try {
                    Class<Shape> shapeClass = validShapes.get(shapeName);
                    Constructor<Shape> shapeConstructor =
                            shapeClass.getDeclaredConstructor(new Class[] { String.class });

                    return shapeConstructor.newInstance(rawData);
                } catch (InvocationTargetException e) {
                    if (e.getTargetException() instanceof InvalidShapeException) {
                        throw (InvalidShapeException) e.getTargetException();
                    }
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        throw new InvalidShapeException(String.format(ERR_WRONG_CMD, rawData));
    }

    private void checkStringBlank(String value) throws InvalidShapeException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidShapeException("The data is null or empty(trimmed).");
        }
    }
}
