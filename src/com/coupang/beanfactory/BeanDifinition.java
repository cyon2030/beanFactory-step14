package com.coupang.beanfactory;

/**
 * Created by coupang on 2014. 12. 21..
 */
public class BeanDifinition {

    private String beanName;
    private String classPath;
    private String scope;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }


}
