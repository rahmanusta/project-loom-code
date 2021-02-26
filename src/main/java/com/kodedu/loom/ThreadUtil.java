package com.kodedu.loom;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

public class ThreadUtil {
    private static Long start = null;

    public static void sleepNoMessage(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(int millis) {
        System.out.println("Waiting.. " + millis);
        sleepNoMessage(millis);
    }

    public static void waitAll(List<Thread> threadList) {
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void waitAll(Thread... threadList) {
        waitAll(Arrays.asList(threadList));
    }

    public static void waitAll(Collection<Future> threadList) {
        for (Future future : threadList) {
            future.join();
        }
    }

    public static void waitAll(Thread thread) {
        waitAll(Collections.singletonList(thread));
    }

    public static void benchmark() {
        if (start == null) {
            start = System.currentTimeMillis();
        } else {
            final long currentTime = System.currentTimeMillis();
            System.out.println("Completion time: " + (currentTime - start));
            start = currentTime;
        }
    }

}
