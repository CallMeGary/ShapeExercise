package com.gary.interview.shape.models;

import java.text.NumberFormat;

import com.gary.interview.shape.errors.InvalidShapeException;

public class Circle extends BaseShape {

    private static final long serialVersionUID = 1L;

    private static final int numberOfArguments = 4;

    private static final String shapeName = "circle";

    private double centerX;

    private double centerY;

    private double radius;

    public Circle(String rawData) throws InvalidShapeException {
        this.parse(rawData);
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public boolean isInShape(double posX, double posY) {
        double distance = calculateDistance(posX, posY, centerX, centerY);
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
        this.centerX = Double.parseDouble(data[1]);
        this.centerY = Double.parseDouble(data[2]);
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
        NumberFormat fmt = NumberFormat.getInstance();
        return String.format("shape %d: %s with centre at (%s, %s) and radius %s",
                getId(), getShapeName(), fmt.format(centerX), fmt.format(centerY), fmt.format(radius));
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public double getRadius() {
        return radius;
    }
}
