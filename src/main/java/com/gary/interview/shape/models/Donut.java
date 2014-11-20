package com.gary.interview.shape.models;

import com.gary.interview.shape.errors.InvalidShapeException;

public class Donut extends BaseShape {

    private static final long serialVersionUID = 1L;

    private static final int numberOfArguments = 5;

    private static final String shapeName = "donut";

    private Point center;

    private double innerRadius;

    private double outerRadius;

    public Donut(String rawData) throws InvalidShapeException {
        this.parse(rawData);
    }

    public double getArea() {
        return java.lang.Math.PI * (outerRadius * outerRadius - innerRadius * innerRadius);
    }

    public boolean isInShape(Point point) {
        double distance = calculateDistance(point, center);
        return !(distance < innerRadius || distance > outerRadius);
    }

    @Override
    int getNumberOfArguments() {
        return numberOfArguments;
    }

    @Override
    String getShapeName() {
        return shapeName;
    }

    @Override
    void parseShapeValues(String[] data) throws NumberFormatException {
        this.center = parsePoint(data[1], data[2]);
        this.innerRadius = Double.parseDouble(data[3]);
        this.outerRadius = Double.parseDouble(data[4]);
    }

    @Override
    void validateShapeProperties() throws InvalidShapeException {
        if (!(innerRadius > 0)) {
            throw new InvalidShapeException("invalid innerRadius value: " + innerRadius);
        }
        if (!(outerRadius > 0)) {
            throw new InvalidShapeException("invalid outerRadius value: " + outerRadius);
        }
        if (!(innerRadius < outerRadius)) {
            throw new InvalidShapeException(
                    "innerRadius shoud be smaller than outerRadius: " + innerRadius + ", " + outerRadius);
        }
    }

    @Override
    public String toString() {
        return String.format("shape %d: %s with center at (%s, %s), innerRadius %s, outerRadius %s",
                getId(), getShapeName(), format(center.x), format(center.y), format(innerRadius), format(outerRadius));
    }

    public Point getCenter() {
        return center;
    }

    public double getInnerRadius() {
        return innerRadius;
    }

    public double getOuterRadius() {
        return outerRadius;
    }

}
