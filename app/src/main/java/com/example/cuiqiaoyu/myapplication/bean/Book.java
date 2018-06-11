package com.example.cuiqiaoyu.myapplication.bean;

/**
 * Created by cuiqiaoyu on 2018/6/10.
 */

public class Book {
    private int mIconid;
    private String mName;

    public Book(String mName,int mIconid) {
        this.mIconid = mIconid;
        this.mName = mName;
    }

    public int getmIconid() {
        return mIconid;
    }

    public void setmIconid(int mIconid) {
        this.mIconid = mIconid;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
