package com.kodedu.loom.syncasync;

import com.kodedu.loom.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LoomStructeredApp {

    public static void main(String[] args) {

        ThreadUtil.benchmark();
        try (ExecutorService executor = Executors.newVirtualThreadExecutor();) {
            executor.submit(() -> requestService("Service 1"));
            executor.submit(() -> requestService("Service 3"));
            Future<String> future = executor.submit(() -> requestService("Service 2"));
            executor.submit(() -> requestService(future.get() + " > Service 2.1"));
        }
        ThreadUtil.benchmark();
    }

    public static String requestService(String id) {
        System.out.println("Started calling: " + id);
        ThreadUtil.sleepNoMessage(5000);
        return id;
    }
}
