package com.gary.interview.shape.models;

import org.junit.Assert;
import org.junit.Test;

import com.gary.interview.shape.Constants;
import com.gary.interview.shape.errors.InvalidShapeException;

public class DonutTests {

    private Donut donut;

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NullPrameter() throws Exception {
        donut = new Donut(null);
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_EmptyPrameter() throws Exception {
        donut = new Donut(" ");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_WrongCommand() throws Exception {
        donut = new Donut("wrong 1.0 3.0 3.4");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_TooManyArguments() throws Exception {
        donut = new Donut("donut 1.0 3.0 3.4 4.5 5.6");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_TooFewArguments() throws Exception {
        donut = new Donut("donut 1.0 3.0 3.4");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_InvalidNumberFormat() throws Exception {
        donut = new Donut("donut 1.0 3.0 2.0 abc");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_InvalidInnerRadiusValue() throws Exception {
        donut = new Donut("donut 1.0 3.0 -1.0 2.4");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_InvalidOuterRadiusValue() throws Exception {
        donut = new Donut("donut -1.0 3.0 1.0 -2.4");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_InvalidRadiusValues() throws Exception {
        donut = new Donut("donut -1.0 3.0 1.6 1.5");
    }

    @Test
    public void testConstructor_Accuracy() throws Exception {
        donut = new Donut("donut -1.3 -3.5 12.5 15.8");

        Assert.assertEquals(-1.3, donut.getCenterX(), Constants.DELTA);
        Assert.assertEquals(-3.5, donut.getCenterY(), Constants.DELTA);
        Assert.assertEquals(12.5, donut.getInnerRadius(), Constants.DELTA);
        Assert.assertEquals(15.8, donut.getOuterRadius(), Constants.DELTA);
    }

    @Test
    public void testGetArea() throws Exception {
        donut = new Donut("donut -1.3 -3.5 12.5 15.8");

        Assert.assertEquals(Math.PI * (15.8 * 15.8 - 12.5 * 12.5), donut.getArea(), Constants.DELTA);
    }

    @Test
    public void testIsInShape() throws Exception {
        donut = new Donut("donut 0 0 10 20");

        Assert.assertFalse(donut.isInShape(5.0, 5.0));
        Assert.assertFalse(donut.isInShape(7.0, 7.0));
        Assert.assertTrue(donut.isInShape(7.1, 7.1));
        Assert.assertTrue(donut.isInShape(10, 10));
        Assert.assertTrue(donut.isInShape(14.1, 14.1));
        Assert.assertFalse(donut.isInShape(15.1, 15.1));
        Assert.assertFalse(donut.isInShape(20, 20));
    }

}
