package com.gary.interview.shape.models;

import java.text.NumberFormat;

import com.gary.interview.shape.errors.InvalidShapeException;

public class Rectangle extends BaseShape {

    private static final long serialVersionUID = 1L;

    private static final int numberOfArguments = 9;

    private static final String shapeName = "rectangle";

    private double verticeX1;

    private double verticeY1;

    private double verticeX2;

    private double verticeY2;

    private double verticeX3;

    private double verticeY3;

    private double verticeX4;

    private double verticeY4;

    public Rectangle(String rawData) throws InvalidShapeException {
        this.parse(rawData);
    }

    public double getArea() {
        return calculateArea(verticeX1, verticeY1, verticeX2, verticeY2, verticeX3, verticeY3)
                + calculateArea(verticeX1, verticeY1, verticeX4, verticeY4, verticeX3, verticeY3);
    }

    public boolean isInShape(double posX, double posY) {
        double area1 = calculateArea(posX, posY, verticeX1, verticeY1, verticeX2, verticeY2);
        double area2 = calculateArea(posX, posY, verticeX2, verticeY2, verticeX3, verticeY3);
        double area3 = calculateArea(posX, posY, verticeX3, verticeY3, verticeX4, verticeY4);
        double area4 = calculateArea(posX, posY, verticeX4, verticeY4, verticeX1, verticeY1);

        double area = getArea();

        return Math.abs(area - area1 - area2 - area3 - area4) < delta;
    }

    @Override
    public String toString() {
        NumberFormat fmt = NumberFormat.getInstance();
        return String.format("shape %d: %s with vertices at (%s, %s), (%s, %s), (%s, %s), (%s, %s)", getId(),
                getShapeName(), fmt.format(verticeX1), fmt.format(verticeY1), fmt.format(verticeX2),
                fmt.format(verticeY2), fmt.format(verticeX3), fmt.format(verticeY3), fmt.format(verticeX4),
                fmt.format(verticeY4));
    }

    @Override
    String getShapeName() {
        return shapeName;
    }

    @Override
    int getNumberOfArguments() {
        return numberOfArguments;
    }

    @Override
    void parseShapeValues(String[] data) throws NumberFormatException {
        this.verticeX1 = Double.parseDouble(data[1]);
        this.verticeY1 = Double.parseDouble(data[2]);
        this.verticeX2 = Double.parseDouble(data[3]);
        this.verticeY2 = Double.parseDouble(data[4]);
        this.verticeX3 = Double.parseDouble(data[5]);
        this.verticeY3 = Double.parseDouble(data[6]);
        this.verticeX4 = Double.parseDouble(data[7]);
        this.verticeY4 = Double.parseDouble(data[8]);
    }

    @Override
    void validateShapeProperties() throws InvalidShapeException {
        if (!isRectangle()) {
            throw new InvalidShapeException("not a valid " + shapeName);
        }
    }

    boolean isRectangle() {
        double distance12 = calculateDistance(verticeX1, verticeY1, verticeX2, verticeY2);
        double distance34 = calculateDistance(verticeX3, verticeY3, verticeX4, verticeY4);
        if (!(Math.abs(distance12 - distance34) < delta)) {
            return false;
        }

        double distance14 = calculateDistance(verticeX1, verticeY1, verticeX4, verticeY4);
        double distance23 = calculateDistance(verticeX2, verticeY2, verticeX3, verticeY3);
        if (!(Math.abs(distance14 - distance23) < delta)) {
            return false;
        }

        double distance13 = calculateDistance(verticeX1, verticeY1, verticeX3, verticeY3);
        double distance24 = calculateDistance(verticeX2, verticeY2, verticeX4, verticeY4);

        return Math.abs(distance13 - distance24) < delta;
    }

    public double getVerticeX1() {
        return verticeX1;
    }

    public double getVerticeY1() {
        return verticeY1;
    }

    public double getVerticeX2() {
        return verticeX2;
    }

    public double getVerticeY2() {
        return verticeY2;
    }

    public double getVerticeX3() {
        return verticeX3;
    }

    public double getVerticeY3() {
        return verticeY3;
    }

    public double getVerticeX4() {
        return verticeX4;
    }

    public double getVerticeY4() {
        return verticeY4;
    }
}
