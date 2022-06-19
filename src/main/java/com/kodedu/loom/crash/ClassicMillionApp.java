package com.kodedu.loom.crash;

import com.kodedu.loom.ThreadUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClassicMillionApp {
    public static void main(String[] args) {

        List<Thread> threadList = IntStream.range(1, 1_000_000)
                .mapToObj(i -> new Thread(() -> {
                    ThreadUtil.sleep(i);
                }))
                .peek(Thread::start)
                .collect(Collectors.toList());

        ThreadUtil.waitAll(threadList);

    }
}
