package com.gary.interview.shape.models;

import com.gary.interview.shape.errors.InvalidShapeException;

public class Rectangle extends BaseShape {

    private static final long serialVersionUID = 1L;

    private static final int numberOfArguments = 9;

    private static final String shapeName = "rectangle";

    private Point v1, v2, v3, v4;

    public Rectangle(String rawData) throws InvalidShapeException {
        this.parse(rawData);
    }

    public double getArea() {
        return calculateArea(v1, v2, v3) + calculateArea(v1, v4, v3);
    }

    public boolean isInShape(Point point) {
        double area1 = calculateArea(point, v1, v2);
        double area2 = calculateArea(point, v2, v3);
        double area3 = calculateArea(point, v3, v4);
        double area4 = calculateArea(point, v4, v1);

        double area = getArea();

        return Math.abs(area - area1 - area2 - area3 - area4) < delta;
    }

    @Override
    public String toString() {
        return String.format("shape %d: %s with vertices at (%s, %s), (%s, %s), (%s, %s), (%s, %s)",
                getId(), getShapeName(), format(v1.x), format(v1.y), format(v2.x),
                format(v2.y), format(v3.x), format(v3.y), format(v4.x), format(v4.y));
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
        this.v4 = parsePoint(data[7], data[8]);
    }

    @Override
    void validateShapeProperties() throws InvalidShapeException {
        if (!isRectangle()) {
            throw new InvalidShapeException("not a valid " + shapeName);
        }
    }

    boolean isRectangle() {
        double distance12 = calculateDistance(v1, v2);
        double distance34 = calculateDistance(v3, v4);
        if (!(Math.abs(distance12 - distance34) < delta)) {
            return false;
        }

        double distance14 = calculateDistance(v1, v4);
        double distance23 = calculateDistance(v2, v3);
        if (!(Math.abs(distance14 - distance23) < delta)) {
            return false;
        }

        double distance13 = calculateDistance(v1, v3);
        double distance24 = calculateDistance(v2, v4);

        return Math.abs(distance13 - distance24) < delta;
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

    public Point getV4() {
        return v4;
    }
}
