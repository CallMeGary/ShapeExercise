package com.gary.interview.shape.models;

import java.text.NumberFormat;

import com.gary.interview.shape.errors.InvalidShapeException;

public class Triangle extends BaseShape {

    private static final long serialVersionUID = 1L;

    private static final int numberOfArguments = 7;

    private static final String shapeName = "triangle";

    private double verticeX1;

    private double verticeY1;

    private double verticeX2;

    private double verticeY2;

    private double verticeX3;

    private double verticeY3;

    public Triangle(String rawData) throws InvalidShapeException {
        this.parse(rawData);
    }

    public double getArea() {
        return calculateArea(verticeX1, verticeY1, verticeX2, verticeY2, verticeX3, verticeY3);
    }

    public boolean isInShape(double posX, double posY) {
        double area1 = calculateArea(posX, posY, verticeX2, verticeY2, verticeX3, verticeY3);
        double area2 = calculateArea(verticeX1, verticeY1, posX, posY, verticeX3, verticeY3);
        double area3 = calculateArea(verticeX1, verticeY1, verticeX2, verticeY2, posX, posY);

        double area = getArea();

        return Math.abs(area - area1 - area2 - area3) < delta;
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
    }

    @Override
    void validateShapeProperties() throws InvalidShapeException {
        if (isVerticesOnSameLine()) {
            throw new InvalidShapeException("three virtices are on the same line, not a valid " + shapeName);
        }
    }

    @Override
    public String toString() {
        NumberFormat fmt = NumberFormat.getInstance();
        return String.format("shape %d: %s with vertices at (%s, %s), (%s, %s), (%s, %s)", getId(), getShapeName(),
                fmt.format(verticeX1), fmt.format(verticeY1), fmt.format(verticeX2), fmt.format(verticeY2),
                fmt.format(verticeX3), fmt.format(verticeY3));
    }

    private boolean isVerticesOnSameLine() {
        double k1 = (verticeY2 - verticeY1) * (verticeX3 - verticeX1);
        double k2 = (verticeY3 - verticeY1) * (verticeX2 - verticeX1);
        return Math.abs(k1 - k2) < delta;
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

}
