package com.acc.thread;

import org.junit.jupiter.api.Test;

public class ThreadComunicationTest {

    String message = null;

    @Test
    void manual() throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            while (message.equals(null)){
                //wait
            }
            System.out.println(message);
        });

        Thread thread2 = new Thread(()->{
            message = "Field!!";
        });

        thread2.start();
        thread1.start();

        thread2.join();
        thread1.join();
    }

    @Test
    void waitNotify() throws InterruptedException {
        final Object lock = new Object();  // untuk network

        Thread thread1 = new Thread(() -> {
            synchronized (lock){
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock){
                message = "Notify Communication";
                lock.notify();
            }
        });

        // Make sure Thread 1 Wait() Start first before notify
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
