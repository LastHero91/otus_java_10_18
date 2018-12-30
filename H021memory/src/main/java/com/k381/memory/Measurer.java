package com.k381.memory;

import java.util.function.Supplier;

public class Measurer {

    private static final int ARRAY_SIZE = 20_000_000;

    public static long measureInt() {
        try {
            long mem = getMem();
            int array[] = new int[ARRAY_SIZE];
            long size;

            for (int i = 0; i < array.length; i++) {
                array[i] = i;
            }
            size = (getMem() - mem) / array.length;
            return size;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long measureLong() {
        try {
            long mem = getMem();
            long array[] = new long[ARRAY_SIZE];
            long size;

            for (int i = 0; i < array.length; i++) {
                array[i] = i;
            }
            size = (getMem() - mem) / array.length;
            return size;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long measureDouble() {
        try {
            long mem = getMem();
            double array[] = new double[ARRAY_SIZE];
            long size;

            for (int i = 0; i < array.length; i++) {
                array[i] = i;
            }
            size = (getMem() - mem) / array.length;
            return size;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long measureByte() {
        try {
            long mem = getMem();
            byte array[] = new byte[ARRAY_SIZE];
            long size;

            for (int i = 0; i < array.length; i++) {
                array[i] = (byte) i;
            }
            size = (getMem() - mem) / array.length;
            return size;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long measureBoolean() {
        try {
            long mem = getMem();
            boolean array[] = new boolean[ARRAY_SIZE];
            long size;

            for (int i = 0; i < array.length; i++) {
                if (i % 2 == 0)
                    array[i] = true;
            }
            size = (getMem() - mem) / array.length;
            return size;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static <T> long measure(Supplier<T> supplier) {
        try {
            Object array[] = new Object[ARRAY_SIZE];
            long size;
            long mem = getMem();
            for (int i = 0; i < array.length; i++) {
                array[i] = supplier.get();
            }
            size = (getMem() - mem) / array.length;
            return size;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static long getMem() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
