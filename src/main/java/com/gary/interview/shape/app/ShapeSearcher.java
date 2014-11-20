package com.gary.interview.shape.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gary.interview.shape.models.Point;
import com.gary.interview.shape.models.Shape;
import com.gary.interview.shape.repositories.ShapeRepository;

@Component
class ShapeSearcher {

    @Autowired
    private ShapeRepository shapeRepository;

    private Point point;

    List<Shape> search(Point point, int threads) {
        this.point = point;
        threads = (threads > 0 ? threads : 1);

        int totalShapes = shapeRepository.getSize();
        
        ExecutorService threadPool = Executors.newFixedThreadPool(threads);

        List<Future<List<Shape>>> threadResults = new ArrayList<Future<List<Shape>>>();
        int shapesInAThread = totalShapes / threads;
        for (int i = 0; i < threads; i++) {
            int from = i * shapesInAThread;
            int to = (i == (threads - 1) ? totalShapes : from + shapesInAThread);

            SearchWorker worker = new SearchWorker(from, to);
            threadResults.add(threadPool.submit(worker));
        }

        List<Shape> results = new ArrayList<Shape>();
        for (Future<List<Shape>> result : threadResults) {
            try {
                results.addAll(result.get());
            } catch (Exception e) {
                // ignore
            }
        }

        threadPool.shutdown();

        return results;
    }

    private class SearchWorker implements Callable<List<Shape>> {
        private int from;
        private int to;

        SearchWorker(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public List<Shape> call() throws Exception {
            List<Shape> result = new ArrayList<Shape>();

            for (int i = from; i < to; i++) {
                Shape shape = shapeRepository.get(i);
                if (shape.isInShape(point)) {
                    result.add(shape);
                }
            }
            return result;
        }

    }
}
