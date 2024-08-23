package com.acc.thread;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    @Test
    void delayedJob() throws InterruptedException {

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Delayed Job");
            }
        };

        Timer timer = new Timer();
//        timer.schedule(task, 2000);
        timer.schedule(task, 5000, 1000);

        Thread.sleep(12000);
    }

}
