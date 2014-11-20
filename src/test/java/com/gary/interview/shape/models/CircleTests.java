package com.gary.interview.shape.models;

import org.junit.Assert;
import org.junit.Test;

import com.gary.interview.shape.Helper;
import com.gary.interview.shape.errors.InvalidShapeException;

public class CircleTests {

    private Circle circle;

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NullParameter() throws Exception {
        circle = new Circle(null);
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_EmptyParameter() throws Exception {
        circle = new Circle(" ");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_WrongCommand() throws Exception {
        circle = new Circle("wrong 1.0 3.0 3.4 4.5");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_TooManyArguments() throws Exception {
        circle = new Circle("circle 1.0 3.0 3.4 4.5");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_TooFewArguments() throws Exception {
        circle = new Circle("circle 1.0 3.0");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_InvalidNumberFormat() throws Exception {
        circle = new Circle("circle 1.0 3.0 abc");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_InvalidRadiusValue() throws Exception {
        circle = new Circle("circle 1.0 3.0 0");
    }

    @Test
    public void testConstructor_Accuracy() throws Exception {
        circle = new Circle("circle -1.3 3.5 12.9");

        Helper.assertPointEquals(new Point(-1.3, 3.5), circle.getCenter());
        Helper.assertDoubleEquals(12.9, circle.getRadius());
    }

    @Test
    public void testGetArea() throws Exception {
        circle = new Circle("circle -1.3 3.5 12.9");

        Helper.assertDoubleEquals(Math.PI * 12.9 * 12.9, circle.getArea());
    }

    @Test
    public void testIsInShape() throws Exception {
        circle = new Circle("circle 0 0 10");

        Assert.assertTrue(circle.isInShape(new Point(5.0, 5.0)));
        Assert.assertTrue(circle.isInShape(new Point(7.0, 7.0)));
        Assert.assertFalse(circle.isInShape(new Point(7.1, 7.1)));
        Assert.assertFalse(circle.isInShape(new Point(8, 8)));
    }

}
