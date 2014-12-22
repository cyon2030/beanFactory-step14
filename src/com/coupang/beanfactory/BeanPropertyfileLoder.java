package com.coupang.beanfactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created by coupang on 2014. 12. 21..
 */
public class BeanPropertyfileLoder implements BeanDifinitionLoader{
    private String propertyPathes;

    @Override
    public BeanDifinition[] loadBeans(String propertyPathes) {
        InputStream is = getClass().getResourceAsStream(propertyPathes);
        Properties props = new Properties();
        int i = 0;
        try {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<Object> set= props.keySet();
        BeanDifinition beanDifinitionArray[] = new BeanDifinition[set.size()];
        for (Object o : set) {

            String[] tempString = props.getProperty(o.toString()).split(":");
            BeanDifinition beanDifinition = new BeanDifinition();
            beanDifinition.setBeanName(o.toString());
            beanDifinition.setScope(tempString[0]);
            beanDifinition.setClassPath(tempString[1]);
            beanDifinitionArray[i++] = beanDifinition;
        }
        return beanDifinitionArray;

    }
}
