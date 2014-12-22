package com.coupang.beanfactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by coupang on 2014. 12. 21..
 */
public class SingletonGroup {
    private static final SingletonGroup  singletonGroup = new SingletonGroup();
    private static Map<String, Object> singletonMap = new HashMap<String, Object>();


    public void putInstance(Object object){
        singletonMap.put(object.getClass().getSimpleName().toLowerCase(),object);
    }
    public static SingletonGroup getInstance(){
        return singletonGroup;
    }
    public Object getInstance(String beanName){
        return singletonMap.get(beanName);
    }

}
