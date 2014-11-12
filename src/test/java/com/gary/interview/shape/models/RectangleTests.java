package com.gary.interview.shape.models;

import org.junit.Assert;
import org.junit.Test;

import com.gary.interview.shape.Constants;
import com.gary.interview.shape.errors.InvalidShapeException;

public class RectangleTests {

    private Rectangle rectangle;

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NullPrameter() throws Exception {
        rectangle = new Rectangle(null);
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_EmptyPrameter() throws Exception {
        rectangle = new Rectangle(" ");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_WrongCommand() throws Exception {
        rectangle = new Rectangle("wrong 0 0 2 0 2 4 0 4");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_TooManyArguments() throws Exception {
        rectangle = new Rectangle("rectangle 0 0 2 0 2 4 0 4 5");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_TooFewArguments() throws Exception {
        rectangle = new Rectangle("rectangle 0 0 2 0 2 4 0");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_InvalidNumberFormat() throws Exception {
        rectangle = new Rectangle("rectangle 0 0 ab 0 2 4 0 4");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NotARectangle01() throws Exception {
        rectangle = new Rectangle("rectangle 0 0 2 0 2 5 0 4");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NotARectangle02() throws Exception {
        rectangle = new Rectangle("rectangle 0 0 2 -1 2 5 0 4");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NotARectangle03() throws Exception {
        rectangle = new Rectangle("rectangle -2 0 1 0 2 3 -1 3");
    }

    @Test
    public void testConstructor_Accuracy() throws Exception {
        rectangle = new Rectangle("rectangle 0 0 2 0 2 3 0 3");

        Assert.assertEquals(0.0, rectangle.getVerticeX1(), Constants.DELTA);
        Assert.assertEquals(0.0, rectangle.getVerticeY1(), Constants.DELTA);
        Assert.assertEquals(2.0, rectangle.getVerticeX2(), Constants.DELTA);
        Assert.assertEquals(0.0, rectangle.getVerticeY2(), Constants.DELTA);
        Assert.assertEquals(2.0, rectangle.getVerticeX3(), Constants.DELTA);
        Assert.assertEquals(3.0, rectangle.getVerticeY3(), Constants.DELTA);
        Assert.assertEquals(0.0, rectangle.getVerticeX4(), Constants.DELTA);
        Assert.assertEquals(3.0, rectangle.getVerticeY4(), Constants.DELTA);
    }

    @Test
    public void testGetArea() throws Exception {
        rectangle = new Rectangle("rectangle -1.0 0 2 0 2 4 -1 4");

        Assert.assertEquals(12.0, rectangle.getArea(), Constants.DELTA);
    }

    @Test
    public void testIsInShape() throws Exception {
        rectangle = new Rectangle("rectangle -1.0 0 2 0 2 4 -1 4");

        Assert.assertTrue(rectangle.isInShape(0.0, 0.0));
        Assert.assertTrue(rectangle.isInShape(-1.0, 0.0));
        Assert.assertTrue(rectangle.isInShape(1.0, 1.0));

        Assert.assertFalse(rectangle.isInShape(4.0, 4.0));
        Assert.assertFalse(rectangle.isInShape(-2.5, 0));
    }

}
