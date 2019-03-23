package ru.otus;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounters {
    private boolean flag;
    private ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new LockCounters().go();
    }

    private void go() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        startCallable(executorService, true);
        startCallable(executorService, false);
    }

    private void startCallable(ExecutorService executorService, boolean isUp) {
        Future<String> resultInFuture = executorService.submit(() -> print(isUp));
        try {
            resultInFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private String print(boolean isUp) {
        int start = 0;
        flag = isUp;
        while (true) {
            start = cycleMethod(start, 10, 1);
            start = cycleMethod(start, 1, -1);
        }
    }

    private int cycleMethod(int start, int limit, int sign) {
        lock.lock();
        try {
            while (sign < 0 ? start > limit : start < limit) {
                if (flag) {
                    sleepOneSecond();
                    System.out.println(start += sign);
                } else {
                    System.out.println(start);
                }
                flag = !flag;
            }
        } finally {
            lock.unlock();
        }
        return start;
    }

    private void sleepOneSecond() {
        try {
            TimeUnit.SECONDS.sleep(1l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
