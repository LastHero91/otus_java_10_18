package ru.otus;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Created by tully.
 * <p>
 * Updated by Sergey and LastHero91
 */
/*
-Xms256m
-Xmx256m
-verbose:gc
-Xlog:gc*:file=H051GC/logs/gc_pid_%p.log
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=H051GC/logs/dump
-XX:+UseG1GC
*/

public class GcDemo {
    public static void main(String... args) throws Exception {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        long beginTime = System.currentTimeMillis();

        int size = 6 * 1000 * 1000;
        int loopCounter = 2;
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.k381:type=Benchmark");
        Benchmark mbean = new Benchmark(loopCounter);

        mbs.registerMBean(mbean, name);
        mbean.setSize(size);
        mbean.run();

        System.out.println("time:" + (System.currentTimeMillis() - beginTime) / 1000);
    }
}