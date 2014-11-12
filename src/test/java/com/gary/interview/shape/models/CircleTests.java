package com.gary.interview.shape.models;

import org.junit.Assert;
import org.junit.Test;

import com.gary.interview.shape.Constants;
import com.gary.interview.shape.errors.InvalidShapeException;

public class CircleTests {

    private Circle circle;

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NullPrameter() throws Exception {
        circle = new Circle(null);
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_EmptyPrameter() throws Exception {
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

        Assert.assertEquals(-1.3, circle.getCenterX(), Constants.DELTA);
        Assert.assertEquals(3.5, circle.getCenterY(), Constants.DELTA);
        Assert.assertEquals(12.9, circle.getRadius(), Constants.DELTA);
    }

    @Test
    public void testGetArea() throws Exception {
        circle = new Circle("circle -1.3 3.5 12.9");

        Assert.assertEquals(Math.PI * 12.9 * 12.9, circle.getArea(), Constants.DELTA);
    }

    @Test
    public void testIsInShape() throws Exception {
        circle = new Circle("circle 0 0 10");

        Assert.assertTrue(circle.isInShape(5.0, 5.0));
        Assert.assertTrue(circle.isInShape(7.0, 7.0));
        Assert.assertFalse(circle.isInShape(7.1, 7.1));
        Assert.assertFalse(circle.isInShape(8, 8));
    }

}
