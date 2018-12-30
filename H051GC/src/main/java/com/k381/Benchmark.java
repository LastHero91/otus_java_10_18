package com.k381;

import java.util.LinkedList;
import java.util.List;

class Benchmark implements BenchmarkMBean {
    private volatile int size = 0;
    private final int loopCounter;

    public Benchmark(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    void run() {
        List<Integer> list = new LinkedList<>();
        for (int idx = 0; idx < loopCounter; idx++) {
            int local = size;
            for (int i = 0; i < local; i++) {
                Integer value = i;
                list.add(value);
                if (i % 15 == 0) {
                    list.set(i, null);
                }
            }
            //Thread.sleep(25);
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        System.out.println("new size:" + size);
        this.size = size;
    }

}