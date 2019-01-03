package com.k381.utils;

import com.k381.utils.testAnnotations.After;
import com.k381.utils.testAnnotations.Before;
import com.k381.utils.testAnnotations.Order;
import com.k381.utils.testAnnotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassTest {
    private List<String> strings = new ArrayList<>();

    @Before
    @Order(number = 0)
    public void initList() {
        System.out.println("Before test");
        Arrays.stream(new String[]{"Кручу","-","верчу", ","}).forEach(strings::add);
    }

    @Before
    @Order(number = 1)
    public void initList2() {
        Arrays.stream(new String[]{"запутать","хочу!"}).forEach(strings::add);
    }

    @After
    @Order(number = 0)
    public void after() {
        if(!strings.isEmpty()) {
            strings.removeAll(strings);
        }
        strings = null;
        System.out.println("After test.");
    }

    @Test
    public void test() {
        strings.add(" Получилось?)");
        System.out.println(strings);
    }

    @Test
    public void test2() {
        if(!strings.isEmpty()) {
            strings.removeAll(strings);
        }
        Arrays.stream(new String[]{"Кручусь","-","верчусь", ",", "сам ", "путаюсь("}).forEach(strings::add);
        System.out.println(strings);
    }
}
