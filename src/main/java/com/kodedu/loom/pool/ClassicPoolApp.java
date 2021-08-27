package com.kodedu.loom.pool;

import com.kodedu.loom.ThreadUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Classic pools are not scalable for I/O (for blocking ops)
 */
public class ClassicPoolApp {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(8);

        List<Future> threadList = IntStream.range(1, 1_000_000)
                .mapToObj(i -> {
                    Runnable runnable = () -> {
                        int millis = ThreadLocalRandom.current().nextInt(0, 100_000);
                        ThreadUtil.sleep(millis); // I/O bounded task
                    };
                    return runnable;
                })
                .map(executor::submit)
                .collect(Collectors.toList());

        ThreadUtil.waitAll(threadList);
    }
}
