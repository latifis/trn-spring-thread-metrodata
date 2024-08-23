package com.acc.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class ThreadPoolTest {

    @Test
    void createThreadPool() throws InterruptedException {

        int minThreads = 10;
        int maxThreads = 100;
        int alive = 1;
        TimeUnit aliveTime = TimeUnit.MINUTES;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1000);

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(minThreads, maxThreads, alive, aliveTime, queue);

        Runnable runnable = () ->{
            try {
                Thread.sleep(2000);
                System.out.println("Runnable from thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        threadPool.execute(runnable);

        // Threadpool run as Asycn maka perlu Thread.sleep untuk melihat hasilnya.
        Thread.sleep(3000);
    }

    @Test
    void threadPoolShutdown() throws InterruptedException {

//        int numberOfCores = Runtime.getRuntime().availableProcessors();
//        int optimalThreadCount = numberOfCores + 1;
//        System.out.println(optimalThreadCount);

        int minThreads = 10;
        int maxThreads = 100;
        int alive = 3;
        TimeUnit aliveTime = TimeUnit.MINUTES;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(900);

        ThreadPoolExecutor thread = new ThreadPoolExecutor(minThreads, maxThreads, alive, aliveTime, queue);

        for (int i = 0; i < 1000; i++) {
//            final int task = i;
            Runnable runnable = () -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Task from thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    System.out.println("lll");
                }
            };
            thread.execute(runnable);
        }

        // thread.shutdown();
        thread.shutdownNow();
        thread.awaitTermination(1,TimeUnit.HOURS);
    }

    @Test
    void rejected() throws InterruptedException {

        int minThreads = 10;
        int maxThreads = 50;
        int alive = 1;
        TimeUnit aliveTime = TimeUnit.MINUTES;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);

        RejectedExecutionHandler executionHandler = (r, e) -> {
            System.out.println("Task "+ r+ " rejected");
        };

        ThreadPoolExecutor thread = new ThreadPoolExecutor(minThreads, maxThreads, alive, aliveTime, queue, executionHandler);

        for (int i = 0; i < 1000; i++) {
            final int task = i;
            Runnable runnable = () -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Task " + task + " from thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            thread.execute(runnable);
        }

        thread.shutdown();
        thread.awaitTermination(1,TimeUnit.DAYS);
    }

    @Test
    void executorServiceFix() throws InterruptedException {

        ExecutorService threadExecutor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            threadExecutor.execute(() ->{
                try {
                    Thread.sleep(1000);
                    System.out.println("Runnable in thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        threadExecutor.shutdown();
        threadExecutor.awaitTermination(1, TimeUnit.HOURS);

    }
}
