package com.kodedu.loom.hello;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolApp {

    public static void main(String[] args) {

        try (final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> System.out.println("Hello"));
            executor.submit(() -> System.out.println("World"));
        }
        System.out.println("All done");

    }
}
