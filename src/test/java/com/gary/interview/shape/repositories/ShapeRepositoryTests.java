package com.gary.interview.shape.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.gary.interview.shape.models.Circle;

public class ShapeRepositoryTests {

    private ShapeRepository repository;

    @Before
    public void setUp() {
        repository = new ShapeRepository();
    }

    @Test
    public void testCoustructor() {
        assertNotNull(repository);
    }

    @Test
    public void testGetAllShapes() {
        assertEquals(0, repository.getSize());
    }

    @Test
    public void testAddShape() throws Exception {
        Circle circle = new Circle("circle 2.3 -5.0 25.0");
        repository.addShape(circle);

        assertNotNull(circle.getId());
        assertEquals(1L, circle.getId());

        assertEquals(1, repository.getSize());

        assertEquals(circle, repository.get(0));
    }
}
