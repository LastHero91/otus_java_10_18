package com.k381.memory;

public class Main {
    public static void main(String[] args) {
        System.out.println("int: " + Measurer.measureInt());
        System.out.println("boolean: " + Measurer.measureByte());

        //Boxing
        System.out.println("Integer: " + Measurer.measure(() -> 4));

        System.out.println("String(new char[0]): " + Measurer.measure(() -> new String(new char[0])));
        System.out.println("String(new byte[0]): " + Measurer.measure(() -> new String(new byte[0])));

        System.out.println("String(new byte[0]): " + Measurer.measure(() -> new MyClass()));
        //need to JOL test
        System.out.println("String(new byte[0]): " + Measurer.measure(() -> new MyClass2()));
    }
}

class MyClass {
    int a = 4;
    long b = 8;
    float c = 4;
}

class MyClass2 {
    int a = 4;
    long b = 8;
    //float c = 4;
}
