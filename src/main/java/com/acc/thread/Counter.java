package com.acc.thread;

public class Counter {
    private Integer value = 0;

    public synchronized void increment() {
        value++;

//        synchronized (this){   // untuk beberapa yang ke lock aja
//            this.value++;
//        }
    }

    public Integer getValue(){
        return value;
    }
}
