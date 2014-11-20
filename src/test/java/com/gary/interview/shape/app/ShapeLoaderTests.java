package com.gary.interview.shape.app;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gary.interview.shape.Helper;
import com.gary.interview.shape.errors.InvalidFilePathException;
import com.gary.interview.shape.models.Circle;
import com.gary.interview.shape.models.Donut;
import com.gary.interview.shape.models.Point;
import com.gary.interview.shape.models.Rectangle;
import com.gary.interview.shape.models.Shape;
import com.gary.interview.shape.models.Square;
import com.gary.interview.shape.models.Triangle;
import com.gary.interview.shape.repositories.ShapeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class ShapeLoaderTests {

    @Autowired
    private ShapeRepository shapeRepository;

    @Autowired
    private ShapeLoader shapeLoader;

    @Before
    public void setUp() throws Exception {
        shapeRepository.clear();
    }

    @Test(expected = InvalidFilePathException.class)
    public void testLoadShapes_NullArgument() throws Exception {
        shapeLoader.loadShapes(null);
    }

    @Test(expected = InvalidFilePathException.class)
    public void testLoadShapes_EmptyArgument() throws Exception {
        shapeLoader.loadShapes(" ");
    }

    @Test(expected = InvalidFilePathException.class)
    public void testLoadShapes_NonExistingFilePath() throws Exception {
        shapeLoader.loadShapes("/nonExistingFilePath");
    }

    @Test(expected = InvalidFilePathException.class)
    public void testLoadShapes_NotDirectoryPath() throws Exception {
        shapeLoader.loadShapes("src/test/resources/shapes_valid.txt");
    }

    @Test
    public void testLoadShapes_Triangle() throws Exception {
        shapeLoader.loadShapes("src/test/resources/shapeFiles/triangles");

        Assert.assertEquals(1, shapeRepository.getSize());

        // triangle 0 0 0 6.6 6.6 0
        Shape shape = shapeRepository.get(0);
        Assert.assertTrue(shape instanceof Triangle);

        Triangle triangle = (Triangle) shape;
        Helper.assertPointEquals(new Point(0.0, 0.0), triangle.getV1());
        Helper.assertPointEquals(new Point(0.0, 6.6), triangle.getV2());
        Helper.assertPointEquals(new Point(6.6, 0.0), triangle.getV3());
    }

    @Test
    public void testLoadShapes_Circle() throws Exception {
        shapeLoader.loadShapes("src/test/resources/shapeFiles/circles");

        Assert.assertEquals(1, shapeRepository.getSize());

        // circle 0 0 2.5
        Shape shape = shapeRepository.get(0);
        Assert.assertTrue(shape instanceof Circle);

        Circle circle = (Circle) shape;
        Helper.assertPointEquals(new Point(0.0, 0.0), circle.getCenter());
        Helper.assertDoubleEquals(2.5, circle.getRadius());
    }

    @Test
    public void testLoadShapes_Donut() throws Exception {
        shapeLoader.loadShapes("src/test/resources/shapeFiles/donuts");

        Assert.assertEquals(1, shapeRepository.getSize());

        // donut 1.5 1.5 30 50
        Shape shape = shapeRepository.get(0);
        Assert.assertTrue(shape instanceof Donut);

        Donut donut = (Donut) shape;
        Helper.assertPointEquals(new Point(1.5, 1.5), donut.getCenter());
        Helper.assertDoubleEquals(30.0, donut.getInnerRadius());
        Helper.assertDoubleEquals(50.0, donut.getOuterRadius());
    }

    @Test
    public void testLoadShapes_Rectangle() throws Exception {
        shapeLoader.loadShapes("src/test/resources/shapeFiles/rectangles");

        Assert.assertEquals(1, shapeRepository.getSize());

        // rectangle 0 0 1 0 1 2 0 2
        Shape shape = shapeRepository.get(0);
        Assert.assertTrue(shape instanceof Rectangle);

        Rectangle rectangle = (Rectangle) shape;
        Helper.assertPointEquals(new Point(0.0, 0.0), rectangle.getV1());
        Helper.assertPointEquals(new Point(1.0, 0.0), rectangle.getV2());
        Helper.assertPointEquals(new Point(1.0, 2.0), rectangle.getV3());
        Helper.assertPointEquals(new Point(0.0, 2.0), rectangle.getV4());
    }

    @Test
    public void testLoadShapes_Square() throws Exception {
        shapeLoader.loadShapes("src/test/resources/shapeFiles/squares");

        Assert.assertEquals(1, shapeRepository.getSize());

        // square 0 0 2.5 0 2.5 2.5 0 2.5
        Shape shape = shapeRepository.get(0);
        Assert.assertTrue(shape instanceof Square);

        Square square = (Square) shape;
        Helper.assertPointEquals(new Point(0.0, 0.0), square.getV1());
        Helper.assertPointEquals(new Point(2.5, 0.0), square.getV2());
        Helper.assertPointEquals(new Point(2.5, 2.5), square.getV3());
        Helper.assertPointEquals(new Point(0.0, 2.5), square.getV4());
    }

    @After
    public void tearDown() throws Exception {
        shapeRepository.clear();
    }
}
