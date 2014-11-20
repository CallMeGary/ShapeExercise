package com.gary.interview.shape.models;

import java.io.Serializable;
import java.text.NumberFormat;

import com.gary.interview.shape.errors.InvalidShapeException;

public abstract class BaseShape implements Shape, Serializable {

    private static final long serialVersionUID = 1L;

    private static final NumberFormat fmt = NumberFormat.getInstance();

    static final double delta = 0.00000000001;

    abstract String getShapeName();

    abstract int getNumberOfArguments();

    abstract void parseShapeValues(String data[]) throws NumberFormatException;

    abstract void validateShapeProperties() throws InvalidShapeException;

    /**
     * Parse the shape from the command data.
     * 
     * @param commandData
     *            The command data to parse.
     * 
     * @throws InvalidShapeException
     *             If any error occurred when parsing the shape.
     */
    final void parse(String commandData) throws InvalidShapeException {
        if (commandData == null || commandData.trim().isEmpty()) {
            throw new InvalidShapeException("data cannot be null or empty(trimmed): " + commandData);
        }

        if (!commandData.startsWith(getShapeName() + " ")) {
            throw new InvalidShapeException("wrong command for " + getShapeName() + ": " + commandData);
        }

        String data[] = commandData.split("\\s");

        if (data.length < getNumberOfArguments()) {
            throw new InvalidShapeException("too few arguments: " + commandData);
        }
        if (data.length > getNumberOfArguments()) {
            throw new InvalidShapeException("too many arguments: " + commandData);
        }

        try {
            parseShapeValues(data);

            validateShapeProperties();

        } catch (NumberFormatException e) {
            throw new InvalidShapeException("invalid number format: " + commandData);
        }
    }

    /**
     * Calculate the area of the triangle formed by the specified three vertices.
     * 
     * @param p1
     *            The specified vertice p1.
     * @param p2
     *            The specified vertice p2.
     * @param p3
     *            The specified vertice p3.
     * 
     * @return The calculated area.
     */
    final double calculateArea(Point p1, Point p2, Point p3) {
        return Math.abs(p1.x * p2.y + p2.x * p3.y + p3.x * p1.y - p1.x * p3.y - p2.x * p1.y - p3.x * p2.y) / 2.0;
    }

    /**
     * Calculate the distance of the two specified vertices.
     * 
     * @param p1
     *            The specified vertice p1.
     * @param p2
     *            The specified vertice p2.
     * 
     * @return The calculated distance.
     */
    final double calculateDistance(Point p1, Point p2) {
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }

    /**
     * Parse the point from two specified string valued coordinates.
     * 
     * @param xVal
     *            The string valued x-coordinate.
     * @param yVal
     *            The string valued y-coordinate.
     * 
     * @return The parsed point.
     * @throws NumberFormatException
     *             If any error occurred when parsing number from string.
     */
    final Point parsePoint(String xVal, String yVal) {
        return new Point(Double.parseDouble(xVal), Double.parseDouble(yVal));
    }

    final String format(double number) {
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
