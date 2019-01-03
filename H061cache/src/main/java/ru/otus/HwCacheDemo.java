package ru.otus;

import java.util.concurrent.TimeUnit;

public class HwCacheDemo {
    public static final int COUNT = 100;
    public static final int START = 1;

    public static void main(String[] args) throws InterruptedException {
        new HwCacheDemo().demo();
    }

    private void demo() throws InterruptedException {
        HwCache cache = new MyCache();
        HwListener<Integer, Integer> listener =
                ((key, value, action) -> System.out.println("key:" + key + ", value:" + value + ", action:" + action));
        HwListener<Integer, Integer> listener2 =
                ((key, value, action) -> System.out.println(value/key));
        cache.addListener(listener);
        cache.addListener(listener2);
        for(int i=START; i<=COUNT; i++) {
            cache.put(i, i*-3);
        }
        System.gc();
        TimeUnit.SECONDS.sleep(2);
        System.gc();
        for(int i=START; i<=COUNT; i++) {
            cache.get(i);
        }
        cache.remove(START);
        cache.removeListener(listener);
        cache.removeListener(listener2);
    }
}
