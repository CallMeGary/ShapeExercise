package com.gary.interview.shape.app;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gary.interview.shape.Helper;
import com.gary.interview.shape.errors.InvalidShapeException;
import com.gary.interview.shape.models.Circle;
import com.gary.interview.shape.models.Point;
import com.gary.interview.shape.models.Shape;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class ShapeFactoryTests {

    @Autowired
    private ShapeFactory shapeFactory;

    @Test(expected = InvalidShapeException.class)
    public void testParseShape_NullArgument() throws Exception {
        shapeFactory.parseShape(null);
    }

    @Test(expected = InvalidShapeException.class)
    public void testParseShape_EmptyArgument() throws Exception {
        shapeFactory.parseShape(" ");
    }

    @Test(expected = InvalidShapeException.class)
    public void testParseShape_WrongCommand() throws Exception {
        shapeFactory.parseShape("wrong 1.0 2.0");
    }

    @Test(expected = InvalidShapeException.class)
    public void testParseShape_WrongArguments() throws Exception {
        shapeFactory.parseShape("circle 1.0 2.0");
    }

    @Test
    public void testParseShape() throws Exception {
        Shape shape = shapeFactory.parseShape("circle 1.0 2.0 5.5");
        Assert.assertTrue(shape instanceof Circle);

        Circle circle = (Circle) shape;
        Helper.assertPointEquals(new Point(1.0, 2.0), circle.getCenter());
        Helper.assertDoubleEquals(5.5, circle.getRadius());
    }
}
