package com.acc.thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FutureTest {

    @Test
    void futureTest() throws ExecutionException, InterruptedException {
        ExecutorService thread = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "Hello";
        };

        Future<String> future = thread.submit(callable);

        while (!future.isDone()) {
            System.out.println("Waiting for future");
            Thread.sleep(1000);
        }

        String value = future.get();
        System.out.println(value);

    }

    @Test
    void invokeAllTest() throws InterruptedException, ExecutionException {
        ExecutorService thread = Executors.newFixedThreadPool(10);

        List<Callable<String>> callable = IntStream.range(1,11).mapToObj(value -> new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(value*500L);
                return String.valueOf(value);
            }
        }).collect(Collectors.toList());

        List<Future<String>> futures = thread.invokeAll(callable);

        for (Future<String> future: futures){
            System.out.println(future.get());
        }
    }

    @Test
    void invokeAnyTest() throws InterruptedException, ExecutionException {
        ExecutorService thread = Executors.newFixedThreadPool(10);

        List<Callable<String>> callable = IntStream.range(1,11).mapToObj(value -> new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(value*500L);
                return String.valueOf(value);
            }
        }).collect(Collectors.toList());

        String s = thread.invokeAny(callable);
        System.out.println(s);
    }
}
