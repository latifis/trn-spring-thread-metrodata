package com.acc.thread;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceTest {

    private Random random = new Random();

    @Test
    void testCompletionService() throws InterruptedException {

        ExecutorService thread = Executors.newFixedThreadPool(10);
        CompletionService<String> completionService = new ExecutorCompletionService<String>(thread);

        // Submit Task
        Executors.newSingleThreadExecutor().submit(() -> {
            for (int i = 0; i < 100; i++) {
                final int index = i;
                completionService.submit(()->{
                    Thread.sleep(random.nextInt(2000));
                    return "Task-"+index;
                });
            }
        });

        // Poll Task
        Executors.newSingleThreadExecutor().execute(()->{
            while (true){
                try {
                    Future<String> future = completionService.poll(5, TimeUnit.SECONDS);
                    if (future == null) {
                        break;
                    }else {
                        System.out.println(future.get());
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.awaitTermination(1, TimeUnit.DAYS);
    }
}
