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

        double distance14 = calculateDistance(getV1(), getV4());
        double distance13 = calculateDistance(getV1(), getV3());

        if (!(Math.abs(Math.sqrt(2.0) * distance14 - distance13) < delta)) {
            throw new InvalidShapeException("not a valid " + shapeName);
        }
    }
}
