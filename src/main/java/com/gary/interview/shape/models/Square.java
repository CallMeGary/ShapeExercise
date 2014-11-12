package com.gary.interview.shape.models;

import com.gary.interview.shape.errors.InvalidShapeException;

public class Square extends Rectangle {

    private static final long serialVersionUID = 1L;

    private static final String shapeName = "square";

    public Square(String rawData) throws InvalidShapeException {
        super(rawData);
    }

    @Override
    String getShapeName() {
        return shapeName;
    }

    @Override
    void validateShapeProperties() throws InvalidShapeException {
        if (!isRectangle()) {
            throw new InvalidShapeException("not a valid " + shapeName);
        }

        double distance14 = calculateDistance(getVerticeX1(), getVerticeY1(), getVerticeX4(), getVerticeY4());
        double distance13 = calculateDistance(getVerticeX1(), getVerticeY1(), getVerticeX3(), getVerticeY3());

        if (!(Math.abs(Math.sqrt(2.0) * distance14 - distance13) < delta)) {
            throw new InvalidShapeException("not a valid " + shapeName);
        }
    }
}
