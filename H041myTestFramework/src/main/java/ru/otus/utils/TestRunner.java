package ru.otus.utils;

import ru.otus.utils.testAnnotations.After;
import ru.otus.utils.testAnnotations.Before;
import ru.otus.utils.testAnnotations.Order;
import ru.otus.utils.testAnnotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static void runner(String className) {
        try {
            Class clazz = Class.forName(className);
            List<Method> methodsWithTestAnnotations = methodsWithAnnotation(clazz, Test.class);
            List<Method> methodsWithBeforeAnnotations = methodsWithAnnotation(clazz, Before.class);
            List<Method> methodsWithAfterAnnotations = methodsWithAnnotation(clazz, After.class);

            for (Method testMethod : methodsWithTestAnnotations) {
                Object objectTest = clazz.getConstructor().newInstance();

                invokeMethodInOrder(methodsWithBeforeAnnotations, objectTest);
                invokeMethod(testMethod, objectTest);
                invokeMethodInOrder(methodsWithAfterAnnotations, objectTest);
            }
        } catch (ClassNotFoundException | InstantiationException |
                InvocationTargetException | NoSuchMethodException |
                IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static List methodsWithAnnotation(Class clazz, Class annotation) {
        Method methods[] = clazz.getMethods();
        List<Method> methodsWidth = new ArrayList<>();

        for (Method method : methods) {
            if (method.getAnnotation(annotation) != null) {
                methodsWidth.add(method);
            }
        }
        return methodsWidth;
    }

    private static void invokeMethod(Method method, Object object) {
        try {
            method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static boolean invokeMethodInOrder(List<Method> methodsWithOrderAnnotation, Object object) {
        Class order = Order.class;

        for (Method method : methodsWithOrderAnnotation) {
            if (method.getAnnotation(order) == null) {
                return false;
            }
        }
        methodsWithOrderAnnotation.sort((m, m1)->
                ((Order) m.getAnnotation(order)).number()-((Order) m1.getAnnotation(order)).number());
        for(Method method : methodsWithOrderAnnotation){
            invokeMethod(method, object);
        }
        return true;
    }
}
