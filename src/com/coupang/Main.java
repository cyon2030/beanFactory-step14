package com.coupang;

import com.coupang.beanfactory.SimpleBeanFactory;
import com.coupang.beans.Sample1;
import com.coupang.beans.Sample2;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory("/com/coupang/bean-definitions.properties");

        Sample1 instance = simpleBeanFactory.getInstance(Sample1.class);
        System.out.println(instance != null);

        Object instance2 = simpleBeanFactory.getInstance("sample2");
        System.out.println(instance2 != null);
        //싱글톤 test
        Sample1 instance3 = simpleBeanFactory.getInstance(Sample1.class);
        System.out.println(instance == instance3);


        Sample2 instance4 = simpleBeanFactory.getInstance(Sample2.class);
        System.out.println(instance2);
        System.out.println(instance4);

        Object instance5 = simpleBeanFactory.getInstance("sample1");
        System.out.println(instance);
        System.out.println(instance3);
        System.out.println(instance5);


        System.out.println(Sample2.class.isAssignableFrom(instance2.getClass()));
        System.out.println(instance2 instanceof Sample2);


    }
}
