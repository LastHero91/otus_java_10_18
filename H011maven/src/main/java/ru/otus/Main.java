package ru.otus;

import com.google.common.collect.ImmutableList;

public class Main {
    public static void main(String[] args) {
        ImmutableList<String> stringsArgs = ImmutableList.copyOf(args);
        stringsArgs.forEach(System.out::println);
    }
}
