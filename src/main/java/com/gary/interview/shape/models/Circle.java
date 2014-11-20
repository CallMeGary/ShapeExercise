package com.gary.interview.shape.models;

import com.gary.interview.shape.errors.InvalidShapeException;

public class Circle extends BaseShape {

    private static final long serialVersionUID = 1L;

    private static final int numberOfArguments = 4;

    private static final String shapeName = "circle";

    private Point center;

    private double radius;

    public Circle(String rawData) throws InvalidShapeException {
        this.parse(rawData);
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public boolean isInShape(Point point) {
        double distance = calculateDistance(point, center);
        return !(distance > radius);
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
    void parseShapeValues(String data[]) throws NumberFormatException {
        this.center = parsePoint(data[1], data[2]);
        this.radius = Double.parseDouble(data[3]);
    }

    @Override
    void validateShapeProperties() throws InvalidShapeException {
        if (!(radius > 0.0)) {
            throw new InvalidShapeException("invalid radius value: " + radius);
        }
    }

    @Override
    public String toString() {
        return String.format("shape %d: %s with centre at (%s, %s) and radius %s",
                getId(), getShapeName(), format(center.x), format(center.y), format(radius));
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

}
