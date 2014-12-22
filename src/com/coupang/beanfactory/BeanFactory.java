package com.coupang.beanfactory;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by coupang on 2014. 12. 20..
 */
public interface BeanFactory {
    //class type를 이용해서 instance생성
    public <T> T getInstance(Class<T> type)
            throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException;
    //class name 이용해서 class생성
    public Object getInstance(String beanName)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
    //prototype 확인
    public boolean isPrototype(String beanName);
    //isSingleton 확인
    public boolean isSingleton(String beanName);

}
