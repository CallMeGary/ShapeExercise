package com.gary.interview.shape.models;

import org.junit.Assert;
import org.junit.Test;

import com.gary.interview.shape.Constants;
import com.gary.interview.shape.errors.InvalidShapeException;

public class SquareTests {

    private Square square;

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NullParameter() throws Exception {
        square = new Square(null);
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_EmptyParameter() throws Exception {
        square = new Square(" ");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_WrongCommand() throws Exception {
        square = new Square("wrong 0 0 2 0 2 4 0 4");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_TooManyArguments() throws Exception {
        square = new Square("square 0 0 2 0 2 4 0 4 5");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_TooFewArguments() throws Exception {
        square = new Square("square 0 0 2 0 2 4 0");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_InvalidNumberFormat() throws Exception {
        square = new Square("square 0 0 ab 0 2 4 0 4");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NotASquare01() throws Exception {
        square = new Square("square 0 0 2 0 2 5 0 4");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NotASquare02() throws Exception {
        square = new Square("square 0 0 2 -1 2 5 0 4");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NotASquare03() throws Exception {
        square = new Square("square -2 0 1 0 2 3 -1 3");
    }

    @Test(expected = InvalidShapeException.class)
    public void testConstructor_NotASquare04() throws Exception {
        square = new Square("square 0 0 2 0 2 3 0 3");
    }

    @Test
    public void testConstructor_Accuracy() throws Exception {
        square = new Square("square 0 0 4 0 4 4 0 4");

        Assert.assertEquals(0.0, square.getVerticeX1(), Constants.DELTA);
        Assert.assertEquals(0.0, square.getVerticeY1(), Constants.DELTA);
        Assert.assertEquals(4.0, square.getVerticeX2(), Constants.DELTA);
        Assert.assertEquals(0.0, square.getVerticeY2(), Constants.DELTA);
        Assert.assertEquals(4.0, square.getVerticeX3(), Constants.DELTA);
        Assert.assertEquals(4.0, square.getVerticeY3(), Constants.DELTA);
        Assert.assertEquals(0.0, square.getVerticeX4(), Constants.DELTA);
        Assert.assertEquals(4.0, square.getVerticeY4(), Constants.DELTA);
    }

    @Test
    public void testGetArea() throws Exception {
        square = new Square("square 0 0 4 0 4 4 0 4");

        Assert.assertEquals(16.0, square.getArea(), Constants.DELTA);
    }

    @Test
    public void testIsInShape() throws Exception {
        square = new Square("square 0 0 4 0 4 4 0 4");

        Assert.assertTrue(square.isInShape(0.0, 0.0));
        Assert.assertTrue(square.isInShape(2.0, 3.0));
        Assert.assertTrue(square.isInShape(4.0, 1.0));

        Assert.assertFalse(square.isInShape(4.1, 4.1));
        Assert.assertFalse(square.isInShape(-2.5, 0));
    }

}
