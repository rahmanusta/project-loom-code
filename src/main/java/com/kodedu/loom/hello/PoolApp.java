package com.kodedu.loom.hello;

import com.kodedu.loom.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolApp {

    public static void main(String[] args) {

        try (ExecutorService executorService = Executors.newVirtualThreadExecutor();) {
            executorService.submit(() -> {
                ThreadUtil.sleep(3000);
                System.out.println("first done");
            });
            executorService.submit(() -> {
                ThreadUtil.sleep(100);
                System.out.println("second done");
            });
            executorService.submit(() -> {
                ThreadUtil.sleep(5000);
                System.out.println("third done");
            });
        }

        System.out.println("All done");

    }
}
