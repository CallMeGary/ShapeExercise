package com.gary.interview.shape.app;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gary.interview.shape.Constants;
import com.gary.interview.shape.models.Circle;
import com.gary.interview.shape.models.Shape;
import com.gary.interview.shape.repositories.ShapeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class ShapeSearcherTests {

    @Autowired
    private ShapeRepository shapeRepository;

    @Autowired
    private ShapeLoader shapeLoader;

    @Autowired
    private ShapeSearcher shapeSearcher;

    @Before
    public void setUp() throws Exception {
        shapeRepository.clear();
        shapeRepository.addShape(new Circle("circle 0 0 5.5"));
        shapeRepository.addShape(new Circle("circle 10000 2000 0.5"));
        shapeRepository.addShape(new Circle("circle 10000 2000 0.5"));
        shapeRepository.addShape(new Circle("circle 10000 2000 0.5"));
        shapeRepository.addShape(new Circle("circle 10000 2000 0.5"));
    }

    @Test
    public void testSearch_NoResult() {
        List<Shape> result = shapeSearcher.search(4.1, 4.0, 0);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testSearch_HasResult() {
        List<Shape> result = shapeSearcher.search(3.8, 3.8, 0);
        
        checkSearchResult(result);
    }

    @Test
    public void testSearch_3Thread() {
        List<Shape> result = shapeSearcher.search(3.8, 3.8, 5);
        
        checkSearchResult(result);
    }

    @Test
    public void testSearch_5Thread() {
        List<Shape> result = shapeSearcher.search(3.8, 3.8, 10);
        
        checkSearchResult(result);
    }

    @Test
    public void testSearch_10Thread() {
        List<Shape> result = shapeSearcher.search(3.8, 3.8, 10);
        
        checkSearchResult(result);
    }

    @Test
    public void testSearch_100Thread() {
        List<Shape> result = shapeSearcher.search(3.8, 3.8, 100);
        
        checkSearchResult(result);
    }

    private void checkSearchResult(List<Shape> result) {
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());

        Shape shape = result.get(0);
        Assert.assertTrue(shape instanceof Circle);

        Circle circle = (Circle) shape;
        Assert.assertEquals(0.0, circle.getCenterX(), Constants.DELTA);
        Assert.assertEquals(0.0, circle.getCenterY(), Constants.DELTA);
        Assert.assertEquals(5.5, circle.getRadius(), Constants.DELTA);
    }

    @After
    public void tearDown() throws Exception {
        shapeRepository.clear();
    }
}
