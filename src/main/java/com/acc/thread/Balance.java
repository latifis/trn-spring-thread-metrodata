package com.acc.thread;

public class Balance {
    private Long value;

    public Balance(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    // DEADLOCK
//    public static void transfer(Balance from, Balance to, Long value) throws InterruptedException {
//        synchronized (from) {
//            Thread.sleep(1_000L);
//            synchronized (to) {
//                Thread.sleep(1_000L);
//                from.setValue(from.getValue() - value);
//                to.setValue(to.getValue() + value);
//            }
//        }
//    }

    public static void transfer(Balance from, Balance to, Long value) throws InterruptedException {
        synchronized (from) {
            Thread.sleep(1_000);
            from.setValue(from.getValue() - value);
        }
        synchronized (to) {
            Thread.sleep(1_000);
            to.setValue(to.getValue() + value);
        }
    }
}