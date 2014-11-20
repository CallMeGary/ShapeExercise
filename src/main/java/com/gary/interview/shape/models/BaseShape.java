package com.gary.interview.shape.models;

import java.io.Serializable;
import java.text.NumberFormat;

import com.gary.interview.shape.errors.InvalidShapeException;

public abstract class BaseShape implements Shape, Serializable {

    static final double delta = 0.00000000001;

    private static final long serialVersionUID = 1L;

    private static final NumberFormat fmt = NumberFormat.getInstance();

    abstract String getShapeName();

    abstract int getNumberOfArguments();

    abstract void parseShapeValues(String data[]) throws NumberFormatException;

    abstract void validateShapeProperties() throws InvalidShapeException;

    final void parse(String rawData) throws InvalidShapeException {
        if (rawData == null || rawData.trim().isEmpty()) {
            throw new InvalidShapeException("data cannot be null or empty(trimmed): " + rawData);
        }

        if (!rawData.startsWith(getShapeName() + " ")) {
            throw new InvalidShapeException("wrong command for " + getShapeName() + ": " + rawData);
        }

        String data[] = rawData.split("\\s");

        if (data.length < getNumberOfArguments()) {
            throw new InvalidShapeException("too few arguments: " + rawData);
        }
        if (data.length > getNumberOfArguments()) {
            throw new InvalidShapeException("too many arguments: " + rawData);
        }

        try {
            parseShapeValues(data);

            validateShapeProperties();

        } catch (NumberFormatException e) {
            throw new InvalidShapeException("invalid number format: " + rawData);
        }
    }

    double calculateArea(Point p1, Point p2, Point p3) {
        return Math.abs(p1.x * p2.y + p2.x * p3.y + p3.x * p1.y - p1.x * p3.y - p2.x * p1.y - p3.x * p2.y) / 2.0;
    }

    double calculateDistance(Point p1, Point p2) {
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }

    Point parsePoint(String xVal, String yVal) {
        return new Point(Double.parseDouble(xVal), Double.parseDouble(yVal));
    }

    String format(double number) {
        return fmt.format(number);
    }

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
