package com.gary.interview.shape.models;

import org.junit.Before;
import org.junit.Test;

import com.gary.interview.shape.Helper;
import com.gary.interview.shape.errors.InvalidShapeException;

public class BaseShapeTests {

    private BaseShape baseShape;

    @Before
    public void setUp() {
        baseShape = new MyShape();
    }

    @Test
    public void testCalculateArea() throws Exception {
        double area = baseShape.calculateArea(new Point(1.0, 1.0), new Point(2.2, 2.2), new Point(3.5, 3.5));
        Helper.assertDoubleEquals(0.0, area);

        area = baseShape.calculateArea(new Point(0, 0), new Point(0, 2), new Point(2, 0));
        Helper.assertDoubleEquals(2.0, area);

        area = baseShape.calculateArea(new Point(-2.0, 0), new Point(0, -2.0), new Point(0, 0));
        Helper.assertDoubleEquals(2.0, area);
    }

    @Test
    public void testCalculateDistance() throws Exception {
        double distance = baseShape.calculateDistance(new Point(112.56, 112.56), new Point(112.56, 112.56));
        Helper.assertDoubleEquals(0.0, distance);

        distance = baseShape.calculateDistance(new Point(2.3, 3.5), new Point(24.4, 3.5));
        Helper.assertDoubleEquals(22.1, distance);

        distance = baseShape.calculateDistance(new Point(-2.3, -3.5), new Point(-24.4, -3.5));
        Helper.assertDoubleEquals(22.1, distance);
    }

    class MyShape extends BaseShape {

        private static final long serialVersionUID = 1L;

        public double getArea() {
            return 0;
        }

        public boolean isInShape(Point point) {
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
