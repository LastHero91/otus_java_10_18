package ru.otus;

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
        long timer = System.currentTimeMillis();
        for (int idx = 0; idx < loopCounter; idx++) {
            int local = size;
            for (int i = 0; i < local; i++) {
                Integer value = i;
                list.add(value);
                if (i % 15 == 0) {
                    list.set(i, null);
                }
                if(timer+300*1000<System.currentTimeMillis()){
                    System.out.println("loopCounter:"+loopCounter);
                    System.out.println("i:"+i);
                    break;
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