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

import com.gary.interview.shape.models.Shape;
import com.gary.interview.shape.repositories.ShapeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class LoadTests {

    @Autowired
    private ShapeRepository shapeRepository;

    @Autowired
    private ShapeLoader shapeLoader;

    @Autowired
    private ShapeSearcher shapeSearcher;

    @Before
    public void setUp() throws Exception {
        shapeRepository.clear();
        shapeLoader.loadShapes("src/test/resources/shapeFiles/load_tests");
    }

    @Test
    public void loadTest() throws Exception {
        long timer0 = System.currentTimeMillis();

        List<Shape> result1 = shapeSearcher.search(0.1, 0.0, 1);
        long timer1 = System.currentTimeMillis();

        List<Shape> result2 = shapeSearcher.search(0.1, 0.0, 2);
        long timer2 = System.currentTimeMillis();

        List<Shape> result3 = shapeSearcher.search(0.1, 0.0, 5);
        long timer3 = System.currentTimeMillis();

        List<Shape> result4 = shapeSearcher.search(0.1, 0.0, 10);
        long timer4 = System.currentTimeMillis();

        List<Shape> result5 = shapeSearcher.search(0.1, 0.0, 20);
        long timer5 = System.currentTimeMillis();

        List<Shape> result6 = shapeSearcher.search(0.1, 0.0, 50);
        long timer6 = System.currentTimeMillis();

        List<Shape> result7 = shapeSearcher.search(0.1, 0.0, 100);
        long timer7 = System.currentTimeMillis();

        System.out.println("useThreads:   1,  elapsed time for searching: " + (timer1 - timer0));
        System.out.println("useThreads:   2,  elapsed time for searching: " + (timer2 - timer1));
        System.out.println("useThreads:   5,  elapsed time for searching: " + (timer3 - timer2));
        System.out.println("useThreads:  10,  elapsed time for searching: " + (timer4 - timer3));
        System.out.println("useThreads:  20,  elapsed time for searching: " + (timer5 - timer4));
        System.out.println("useThreads:  50,  elapsed time for searching: " + (timer6 - timer5));
        System.out.println("useThreads: 100,  elapsed time for searching: " + (timer7 - timer6));

        Assert.assertEquals(result1.size(), result2.size());
        Assert.assertEquals(result2.size(), result3.size());
        Assert.assertEquals(result3.size(), result4.size());
        Assert.assertEquals(result4.size(), result5.size());
        Assert.assertEquals(result5.size(), result6.size());
        Assert.assertEquals(result6.size(), result7.size());
    }

    @After
    public void tearDown() throws Exception {
        shapeRepository.clear();
    }
}
