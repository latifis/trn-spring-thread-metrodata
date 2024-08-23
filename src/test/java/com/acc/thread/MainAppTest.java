package com.acc.thread;

import org.junit.jupiter.api.Test;

public class MainAppTest {

    @Test
    void createThread(){
        Runnable runnable = () ->{
            System.out.println("Hello run Thread: " + Thread.currentThread().getName());
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Test
    void threadSleep() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000);
                System.out.println("Hello run Thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Program Finished!");
        Thread.sleep(3_000);
    }

    @Test
    void threadJoin() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000);
                System.out.println("Hello run Thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Waiting Program Finished!");
        thread.join();
        System.out.println("Program Finished!");
    }

    @Test
    void threadInterrupt() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable: " + i);
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000);
        thread.interrupt();
        thread.join();
        System.out.println("Program Finished!");
    }

    @Test
    void threadName(){
//        Thread thread = new Thread(() -> {
//            System.out.println("Hello run Thread: " + Thread.currentThread().getName());
//        });
//        thread.setName("Rename Thread");
//        thread.start();

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        Thread thread = new Thread(runnable);
        thread.setName("The Management Users");
        thread.start();
    }

    @Test
    void threadState() throws InterruptedException {
//        Thread thread = new Thread(() -> {
//            System.out.println(Thread.currentThread().getState());
//            System.out.println("Hello run Thread: " + Thread.currentThread().getName());
//        });

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getState());
            System.out.println("Hello run Thread: " + Thread.currentThread().getName());
        };

        Thread thread = new Thread(runnable);

        System.out.println(thread.getState());
        thread.start();
        thread.join();
        System.out.println(thread.getState());
        System.out.println("Done Process!!");
    }

    @Test
    void counter() throws InterruptedException {
        Counter counter = new Counter();

        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println(counter.getValue());
    }

}
