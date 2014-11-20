package com.gary.interview.shape;

import org.junit.Assert;

import com.gary.interview.shape.models.Point;

public class Helper {
    static final double DELTA = 0.00000000001;
    
    public static void assertPointEquals(Point p1, Point p2) {
        assertDoubleEquals(p1.x, p2.x);
        assertDoubleEquals(p1.y, p2.y);
    }
    
    public static void assertDoubleEquals(double d1, double d2) {
        Assert.assertEquals(d1, d2, DELTA);
    }
}
