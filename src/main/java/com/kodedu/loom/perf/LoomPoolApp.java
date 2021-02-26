package com.kodedu.loom.perf;

import com.kodedu.loom.ThreadUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LoomPoolApp {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newVirtualThreadExecutor();

        ThreadUtil.benchmark();

        List<Future> threadList = IntStream.iterate(10000, i -> i != 0, i -> --i)
                .mapToObj(i -> {
                    Runnable runnable = () -> {
                        ThreadUtil.sleep(i);
                    };
                    return runnable;
                })
                .map(executor::submit)
                .collect(Collectors.toList());

        ThreadUtil.waitAll(threadList);

        ThreadUtil.benchmark();
    }
}
