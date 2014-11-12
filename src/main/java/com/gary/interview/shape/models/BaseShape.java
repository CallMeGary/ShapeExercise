package com.gary.interview.shape.models;

import java.io.Serializable;

import com.gary.interview.shape.errors.InvalidShapeException;

public abstract class BaseShape implements Shape, Serializable {

    static final double delta = 0.00000000001;

    private static final long serialVersionUID = 1L;

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

    double calculateArea(double x1, double y1, double x2, double y2, double x3, double y3) {
        return Math.abs(x1 * y2 + x2 * y3 + x3 * y1 - x1 * y3 - x2 * y1 - x3 * y2) / 2.0;
    }

    double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
