package com.acc.thread;

public class UserThread {
        public static void main(String[] args) {
            Thread thread = new Thread(()->{
                try {
                    Thread.sleep(3_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            System.out.println("User Thread Done!!");
        }
}
