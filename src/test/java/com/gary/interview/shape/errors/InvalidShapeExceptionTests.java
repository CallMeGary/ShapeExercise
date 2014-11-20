package com.gary.interview.shape.errors;

import org.junit.Assert;
import org.junit.Test;

public class InvalidShapeExceptionTests {

    @Test
    public void testCounstructor_String() {
        InvalidShapeException error = new InvalidShapeException("error message");
        Assert.assertNotNull(error);
        Assert.assertTrue(error instanceof Exception);
        Assert.assertEquals("error message", error.getMessage());
    }
    
    @Test
    public void testCounstructor_String_Throwable() {
        Throwable cause = new Exception();
        InvalidShapeException error = new InvalidShapeException("error message", cause);
        Assert.assertNotNull(error);
        Assert.assertTrue(error instanceof Exception);
        Assert.assertEquals("error message", error.getMessage());
        Assert.assertEquals(cause, error.getCause());
    }
}
