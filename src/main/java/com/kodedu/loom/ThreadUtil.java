package com.kodedu.loom;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ThreadUtil {
    private static Long start = null;
    private static AtomicInteger counter = new AtomicInteger(0);
    private static AtomicReference<Long> done = new AtomicReference<>(0L);

    public static void sleepNoMessage(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(int millis) {
        System.out.println(counter.incrementAndGet() + ") Working.. " + millis + " ms.");
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

    public static void waitAll(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Thread printDoneStats(List<Future> threadList) {
        Thread thread = Thread.startVirtualThread(() -> {
            while (true) {
                final long count = threadList.stream()
                        .filter(Future::isDone)
                        .count();
                if (!Objects.equals(count, done.getAndSet(count))) {
                    System.out.println("Done: " + count + " threads");
                }
                ThreadUtil.sleepNoMessage(1000);
                if (count == threadList.size()) {
                    break;
                }
            }
        });
        return thread;
    }
}
