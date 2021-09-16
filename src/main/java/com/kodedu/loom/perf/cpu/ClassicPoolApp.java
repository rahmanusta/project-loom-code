package com.kodedu.loom.perf.cpu;

import com.kodedu.loom.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/*
Classic pools are scalable for CPU bound ops
 */
public class ClassicPoolApp {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(8);

        ThreadUtil.benchmark();

        LongAdder longAdder = new LongAdder();

        IntStream.iterate(10000, i -> i != 0, i -> --i)
                .mapToObj(i -> {
                    Runnable runnable = () -> {
                        longAdder.add(i); // non-blocking ops
                    };
                    return runnable;
                })
                .forEach(executor::submit);

        ThreadUtil.waitAll(executor);

        System.out.println("Sum: " + longAdder.sum() + " ms.");

        ThreadUtil.benchmark();
    }
}
