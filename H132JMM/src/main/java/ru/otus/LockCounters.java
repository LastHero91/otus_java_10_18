package ru.otus;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounters {
    private int count = 0;
    private int limit = 10;
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
        flag = isUp;
        while (true) {
            while(count < limit) {
                lock.lock();
                if (flag) {
                    sleepOneSecond();
                    System.out.println(++count);
                } else {
                    System.out.println(count);
                }
                flag = !flag;
                lock.unlock();
            }
            limit = 1;
            while(count > limit) {
                lock.lock();
                if (flag) {
                    sleepOneSecond();
                    System.out.println(--count);
                } else {
                    System.out.println(count);
                }
                flag = !flag;
                lock.unlock();
            }
            limit = 10;
            if(count>11){
                return "You can't never win)";
            }
        }
    }

    private void sleepOneSecond() {
        try {
            TimeUnit.SECONDS.sleep(1l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
