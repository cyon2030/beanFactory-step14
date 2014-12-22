package com.coupang.beanfactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by coupang on 2014. 12. 20..
 */
public class SimpleBeanFactory implements BeanFactory {
    //bean 정보저장
    private Map<String ,BeanDifinition> beanDifinitionList = new LinkedHashMap<String, BeanDifinition>();
    //propertyfile 읽어오기
    private BeanPropertyfileLoder beanPropertyfileLoder = new BeanPropertyfileLoder();

    SingletonGroup singletonGroup = SingletonGroup.getInstance();

    private void beanDefinitionLoaders(String classPathes){
        if(classPathes.indexOf("properties")>=0) {
            BeanDifinition[] loadBeans = beanPropertyfileLoder.loadBeans(classPathes);
            for (BeanDifinition beanDifinition : loadBeans) {
                beanDifinitionList.put(beanDifinition.getBeanName(), beanDifinition);
            }//for
        }//if
        if(classPathes.indexOf("xml")>=0){
            //xml file loader
        }//if
    }

    public SimpleBeanFactory(String classPathes){
        beanDefinitionLoaders(classPathes);
    }

    @Override
    public <T> T getInstance(Class<T> type) throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
        T instance = null;


        if(isSingleton(type.getSimpleName().toLowerCase())) {
            if((singletonGroup.getInstance(type.getSimpleName().toLowerCase())) != null){

                return (T) singletonGroup.getInstance(type.getSimpleName().toLowerCase());
            }
            else {

                Constructor<T> declaredConstructor = type.getDeclaredConstructor();
                declaredConstructor.setAccessible(true);
                instance = declaredConstructor.newInstance();
                singletonGroup.putInstance(instance);
                return instance;
            }//if else
        }//if

        if(isPrototype(type.getSimpleName().toLowerCase())){

            Constructor<T> declaredConstructor = type.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            instance = declaredConstructor.newInstance();
        }//if
        return instance;
    }

    @Override
    public Object getInstance(String beanName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Object instance = null;

        if(isPrototype(beanName)){
            Class cls = Class.forName(beanDifinitionList.get(beanName).getClassPath());
            Constructor con = cls.getDeclaredConstructor();
            con.setAccessible(true);
            instance = con.newInstance();
            return instance;
        }//if

        if(isSingleton(beanName)) {

            if((singletonGroup.getInstance(beanName) != null)){

                return (Object) singletonGroup.getInstance(beanName);
            }
            else {
                Class cls = Class.forName(beanDifinitionList.get(beanName).getClassPath());
                Constructor con = cls.getDeclaredConstructor();
                con.setAccessible(true);
                instance = con.newInstance();
                return instance;
            }//if else
        }//if
        return instance;
    }

    @Override
    public boolean isPrototype(String beanName) {
        BeanDifinition beanDifinition = beanDifinitionList.get(beanName);
        if("PROTOTYPE".equals(beanDifinition.getScope())){
            return true;
        }//if
        return false;
    }

    @Override
    public boolean isSingleton(String beanName) {
        BeanDifinition beanDifinition = beanDifinitionList.get(beanName);
        if("SINGLETON".equals(beanDifinition.getScope())) {
            return true;
        }//if
        return false;
    }
}
