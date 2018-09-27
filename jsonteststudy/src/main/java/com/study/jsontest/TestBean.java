package com.study.jsontest;

import java.io.Serializable;

/**
 * @author :created  by  zhangrongzhao on  2018/09/2018/9/28
 */
public class TestBean implements Serializable {
    private  String  name ;
    private  String  sex ;
    private  int age;

    public TestBean(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
