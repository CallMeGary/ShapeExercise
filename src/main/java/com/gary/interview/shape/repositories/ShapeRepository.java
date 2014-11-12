package com.gary.interview.shape.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gary.interview.shape.models.BaseShape;
import com.gary.interview.shape.models.Shape;

@Component
public class ShapeRepository {

    private long id = 0;

    private List<Shape> dataInMemory = new ArrayList<Shape>();

    public synchronized void addShape(BaseShape shape) {
        id++;
        shape.setId(id);
        dataInMemory.add(shape);
    }
    
    public synchronized void clear() {
        id = 0;
        dataInMemory.clear();
    }

    public Shape get(int index) {
        return dataInMemory.get(index);
    }
    
    public int getSize() {
        return dataInMemory.size();
    }
}
