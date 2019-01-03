package ru.otus;

import ru.otus.utils.MyArrayList;

import java.util.Collections;
import java.util.List;

public class Main {
    private static final int ARRAY_SIZE = 10_000_000;

    public static void main(String[] args) {
        List<Integer> integers = new MyArrayList<>();
        Integer arrrayInts[] = new Integer[ARRAY_SIZE];
        List<Integer> integers1 = new MyArrayList<>();
        Integer arrrayInts1[] = new Integer[ARRAY_SIZE];

        for (int i = ARRAY_SIZE - 1, j = 0; i >= 0; i--, j--) {
            arrrayInts[i] = i;
            arrrayInts1[i] = j%7;
        }

        System.out.println("addAll(Collection<? super T> c, T... elements):");
        Collections.addAll(integers, arrrayInts);
        System.out.println(integers.get(ARRAY_SIZE / 2));
        Collections.addAll(integers1, arrrayInts1);
        System.out.println(integers1.get(ARRAY_SIZE / 2) + "\n");

        System.out.println("static <T> void copy(List<? super T> dest, List<? extends T> src):");
        Collections.copy(integers, integers1);
        System.out.println(integers.get(ARRAY_SIZE / 2) + "\n");

        System.out.println("static <T> void sort(List<T> list, Comparator<? super T> c):");
        Collections.sort(integers, Integer::compareTo);
        System.out.println(integers.get(ARRAY_SIZE / 2) );
    }
}
