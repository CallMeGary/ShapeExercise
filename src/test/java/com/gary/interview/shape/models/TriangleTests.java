package com.gary.interview.shape.models;

import org.junit.Assert;
import org.junit.Test;

import com.gary.interview.shape.Helper;
import com.gary.interview.shape.errors.InvalidShapeException;

public class TriangleTests {

    private Triangle triangle;

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NullParameter() throws Exception {
        triangle = new Triangle(null);
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_EmptyParameter() throws Exception {
        triangle = new Triangle(" ");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_WrongCommand() throws Exception {
        triangle = new Triangle("wrong 0 0 2 0 2 4");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_TooManyArguments() throws Exception {
        triangle = new Triangle("triangle 0 0 2 0 2 4 0 4 5");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_TooFewArguments() throws Exception {
        triangle = new Triangle("triangle 0 0 2 0");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_InvalidNumberFormat() throws Exception {
        triangle = new Triangle("triangle 0 0 ab 0 2 4");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NotATriangle() throws Exception {
        triangle = new Triangle("triangle 1.23 1.23 -2.3 -2.3 13.7 13.7");
    }

    @Test
    public void testConstructor_Accuracy() throws Exception {
        triangle = new Triangle("triangle 0 0 4.0 0 0 4.0");

        Helper.assertPointEquals(new Point(0.0, 0.0), triangle.getV1());
        Helper.assertPointEquals(new Point(4.0, 0.0), triangle.getV2());
        Helper.assertPointEquals(new Point(0.0, 4.0), triangle.getV3());
    }

    @Test
    public void testGetArea() throws Exception {
        triangle = new Triangle("triangle 0 0 4.0 0 0 4.0");

        Helper.assertDoubleEquals(8.0, triangle.getArea());
    }

    @Test
    public void testIsInShape() throws Exception {
        triangle = new Triangle("triangle 0 0 4.0 0 0 4.0");

        Assert.assertTrue(triangle.isInShape(new Point(0.0, 0.0)));
        Assert.assertTrue(triangle.isInShape(new Point(2.0, 2.0)));
        Assert.assertTrue(triangle.isInShape(new Point(4.0, 0.0)));

        Assert.assertFalse(triangle.isInShape(new Point(4.1, 0)));
        Assert.assertFalse(triangle.isInShape(new Point(0, 4.1)));
        Assert.assertFalse(triangle.isInShape(new Point(2.001, 2.01)));
    }

}
