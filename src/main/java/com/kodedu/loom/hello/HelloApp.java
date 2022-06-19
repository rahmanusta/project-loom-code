package com.kodedu.loom.hello;

import com.kodedu.loom.ThreadUtil;

public class HelloApp {

    public static void main(String[] args) {

        Thread virtualThread1 = Thread.startVirtualThread(() -> {
            System.out.println("Hello world 1");
//            Thread.dumpStack();
        });

        Thread virtualThread2 = Thread.ofVirtual().start(() -> {
            System.out.println("Hello world 2");
        });

        ThreadUtil.waitAll(virtualThread1, virtualThread2);


    }
}
