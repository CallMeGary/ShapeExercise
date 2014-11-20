package com.gary.interview.shape.models;

import com.gary.interview.shape.errors.InvalidShapeException;

public class Triangle extends BaseShape {

    private static final long serialVersionUID = 1L;

    private static final int numberOfArguments = 7;

    private static final String shapeName = "triangle";

    private Point v1, v2, v3;

    public Triangle(String rawData) throws InvalidShapeException {
        this.parse(rawData);
    }

    public double getArea() {
        return calculateArea(v1, v2, v3);
    }

    public boolean isInShape(Point point) {
        double area1 = calculateArea(point, v2, v3);
        double area2 = calculateArea(v1, point, v3);
        double area3 = calculateArea(v1, v2, point);

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
        this.v1 = parsePoint(data[1], data[2]);
        this.v2 = parsePoint(data[3], data[4]);
        this.v3 = parsePoint(data[5], data[6]);
    }

    @Override
    void validateShapeProperties() throws InvalidShapeException {
        if (isVerticesOnSameLine()) {
            throw new InvalidShapeException("three virtices are on the same line, not a valid " + shapeName);
        }
    }

    @Override
    public String toString() {
        return String.format("shape %d: %s with vertices at (%s, %s), (%s, %s), (%s, %s)", getId(), getShapeName(),
                format(v1.x), format(v1.y), format(v2.x), format(v2.y), format(v3.x), format(v3.y));
    }

    private boolean isVerticesOnSameLine() {
        double k1 = (v2.y - v1.y) * (v3.x - v1.x);
        double k2 = (v3.y - v1.y) * (v2.x - v1.x);
        return Math.abs(k1 - k2) < delta;
    }

    public Point getV1() {
        return v1;
    }

    public Point getV2() {
        return v2;
    }

    public Point getV3() {
        return v3;
    }

}
