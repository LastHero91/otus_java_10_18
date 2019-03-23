package ru.otus;

import java.util.concurrent.TimeUnit;

public class SimpleCounters {

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
            cycleMethod(1, 10, 1);
            cycleMethod(10, 1, -1);
        }
    }

    private void cycleMethod(int start, int limit, int sign) {
        for (; sign < 0 ? start > limit : start < limit; start += sign) {
            if (flag) {
                waitMethod(start);
            } else {
                notifyMethod(start);
            }
        }
    }

    private void waitMethod(int count) {
        try {
            TimeUnit.SECONDS.sleep(1L);
            System.out.println(count);
            flag = !flag;
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void notifyMethod(int count) {
        System.out.println(count);
        flag = !flag;
        notifyAll();
    }
}
