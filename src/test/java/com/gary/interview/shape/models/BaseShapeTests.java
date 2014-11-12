package com.gary.interview.shape.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gary.interview.shape.Constants;
import com.gary.interview.shape.errors.InvalidShapeException;

public class BaseShapeTests {

    private BaseShape baseShape;

    @Before
    public void setUp() {
        baseShape = new MyShape();
    }

    @Test
    public void testCalculateArea() throws Exception {
        double area = baseShape.calculateArea(1.0, 1.0, 2.2, 2.2, 3.5, 3.5);
        Assert.assertEquals(area, 0.0, Constants.DELTA);

        area = baseShape.calculateArea(0, 0, 0, 2, 2, 0);
        Assert.assertEquals(area, 2.0, Constants.DELTA);

        area = baseShape.calculateArea(-2.0, 0, 0, -2.0, 0, 0);
        Assert.assertEquals(area, 2.0, Constants.DELTA);
    }

    @Test
    public void testCalculateDistance() throws Exception {
        double distance = baseShape.calculateDistance(112.56, 112.56, 112.56, 112.56);
        Assert.assertEquals(distance, 0.0, Constants.DELTA);

        distance = baseShape.calculateDistance(2.3, 3.5, 24.4, 3.5);
        Assert.assertEquals(distance, 22.1, Constants.DELTA);

        distance = baseShape.calculateDistance(-2.3, -3.5, -24.4, -3.5);
        Assert.assertEquals(distance, 22.1, Constants.DELTA);
    }

    class MyShape extends BaseShape {

        private static final long serialVersionUID = 1L;

        public double getArea() {
            return 0;
        }

        public boolean isInShape(double posX, double posY) {
            return false;
        }

        @Override
        String getShapeName() {
            return null;
        }

        @Override
        int getNumberOfArguments() {
            return 0;
        }

        @Override
        void parseShapeValues(String[] data) throws NumberFormatException {
            // empty
        }

        @Override
        void validateShapeProperties() throws InvalidShapeException {
            // empty
        }
    }

}
