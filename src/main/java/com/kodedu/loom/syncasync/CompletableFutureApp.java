package com.kodedu.loom.syncasync;

import com.kodedu.loom.ThreadUtil;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureApp {

    public static void main(String[] args) {

        ThreadUtil.benchmark();

        CompletableFuture
                .allOf(
                        CompletableFuture.runAsync(() -> requestService("Service 1")),
                        CompletableFuture.supplyAsync(() -> requestService("Service 2"))
                                .thenAcceptAsync(s -> requestService(s + " > Service 2.1")),
                        CompletableFuture.runAsync(() -> requestService("Service 3"))
                ).join();

        ThreadUtil.benchmark();
    }

    public static String requestService(String id) {
        System.out.println("Started calling: " + id);
        ThreadUtil.sleepNoMessage(5000);
        return id;
    }
}
