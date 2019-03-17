package ru.otus;

import java.util.concurrent.TimeUnit;

public class SimpleCounters {

    private int count = 0;
    private int limit = 10;
    private boolean flag;

    public static void main(String[] args) {
        new SimpleCounters().go();
    }

    private void go() {
        Thread thread1 = new Thread(() -> this.print(true));
        Thread thread2 = new Thread(() -> this.print(false));

        thread1.start();
        thread2.start();
    }

    private synchronized void print(boolean isUp) {
        flag = isUp;
        while (true) {
            while(count < limit) {
                if (flag) {
                    waitBlock(++count);
                } else {
                    notifyBlock(count);
                }
            }
            limit = 1;
            while(count > limit) {
                if (flag) {
                    waitBlock(--count);
                } else {
                    notifyBlock(count);
                }
            }
            limit = 10;
        }
    }

    private synchronized void notifyBlock(int count) {
        System.out.println(count);
        flag = true;
        notifyAll();
    }

    private synchronized void waitBlock(int count) {
        try {
            TimeUnit.SECONDS.sleep(1L);
            System.out.println(count);
            flag = false;
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
