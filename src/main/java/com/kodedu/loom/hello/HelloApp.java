package com.kodedu.loom.hello;

import com.kodedu.loom.ThreadUtil;

public class HelloApp {

    public static void main(String[] args) {

        Thread virtualThread1 = Thread.startVirtualThread(() -> {
            System.out.println("Hello world");
//            Thread.dumpStack();
        });

        Thread virtualThread2 = Thread.builder().virtual().task(() -> {
            System.out.println("Hello world");
        }).build();
        virtualThread2.start();

        ThreadUtil.waitAll(virtualThread1, virtualThread2);


    }
}
