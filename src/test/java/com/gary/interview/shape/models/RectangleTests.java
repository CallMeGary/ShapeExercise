package com.gary.interview.shape.models;

import org.junit.Assert;
import org.junit.Test;

import com.gary.interview.shape.Helper;
import com.gary.interview.shape.errors.InvalidShapeException;

public class RectangleTests {

    private Rectangle rectangle;

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NullParameter() throws Exception {
        rectangle = new Rectangle(null);
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_EmptyParameter() throws Exception {
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

        Helper.assertPointEquals(new Point(0.0, 0.0), rectangle.getV1());
        Helper.assertPointEquals(new Point(2.0, 0.0), rectangle.getV2());
        Helper.assertPointEquals(new Point(2.0, 3.0), rectangle.getV3());
        Helper.assertPointEquals(new Point(0.0, 3.0), rectangle.getV4());
    }

    @Test
    public void testGetArea() throws Exception {
        rectangle = new Rectangle("rectangle -1.0 0 2 0 2 4 -1 4");

        Helper.assertDoubleEquals(12.0, rectangle.getArea());
    }

    @Test
    public void testIsInShape() throws Exception {
        rectangle = new Rectangle("rectangle -1.0 0 2 0 2 4 -1 4");

        Assert.assertTrue(rectangle.isInShape(new Point(0.0, 0.0)));
        Assert.assertTrue(rectangle.isInShape(new Point(-1.0, 0.0)));
        Assert.assertTrue(rectangle.isInShape(new Point(1.0, 1.0)));

        Assert.assertFalse(rectangle.isInShape(new Point(4.0, 4.0)));
        Assert.assertFalse(rectangle.isInShape(new Point(-2.5, 0)));
    }

}
