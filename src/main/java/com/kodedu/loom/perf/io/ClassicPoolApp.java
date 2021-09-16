package com.kodedu.loom.perf.io;

import com.kodedu.loom.ThreadUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Classic pools are not scalable for I/O (for blocking ops)
 */
public class ClassicPoolApp {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(8);

        ThreadUtil.benchmark();

        List<Future> threadList = IntStream.iterate(10000, i -> i != 0, i -> --i)
                .mapToObj(i -> {
                    Runnable runnable = () -> {
                        ThreadUtil.sleep(i); // blocking ops
                    };
                    return runnable;
                })
                .map(executor::submit)
                .collect(Collectors.toList());

        Thread thread = ThreadUtil.printDoneStats(threadList);
        ThreadUtil.waitAll(executor);
        ThreadUtil.waitAll(thread);

        ThreadUtil.benchmark();
    }
}
