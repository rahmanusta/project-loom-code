package com.kodedu.loom.syncasync;

import com.kodedu.loom.ThreadUtil;

public class SyncApp {

    public static void main(String[] args) {

        ThreadUtil.benchmark();
        requestService("Service 1");
        String response = requestService("Service 2");
        requestService(response + " > Service 2.1");
        requestService("Service 3");
        ThreadUtil.benchmark();
    }

    public static String requestService(String id) {
        System.out.println("Started calling: " + id);
        ThreadUtil.sleepNoMessage(5000);
        return id;
    }
}
