package com.gary.interview.shape.errors;

import org.junit.Assert;
import org.junit.Test;

public class InvalidFilePathExceptionTests {

    @Test
    public void testCounstructor() {
        InvalidFilePathException error = new InvalidFilePathException("error message");
        Assert.assertNotNull(error);
        Assert.assertTrue(error instanceof Exception);
        Assert.assertEquals("error message", error.getMessage());
    }
}
