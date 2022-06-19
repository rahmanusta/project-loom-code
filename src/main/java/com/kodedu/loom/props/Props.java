package com.kodedu.loom.props;

/**
 * @see VirtualThread#createDefaultScheduler()
 */
public class Props {

    public static void main(String[] args) {

        // Parallelism
//        String parallelismProp = System.getProperty("jdk.defaultScheduler.parallelism");
        // or else
//        int availableProcessors = Runtime.getRuntime().availableProcessors();


        // Max thread size
//        String maxPoolSizeProp = System.getProperty("jdk.defaultScheduler.maxPoolSize");
        // or else
//        int maxPoolSize = Integer.max(parallelism << 1, 128);


    }
}
