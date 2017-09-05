package com.yw.observer.subject;

/**
 * 作者：create by YW
 * 日期：2017.04.28 11:57
 * 描述：发布者1 测试的设计主题 Watched Subject
 */
public class Watched extends ConcreteSubject {

    private String mData;

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        this.mData = data;
        setChanged(); //设置data改变 - true
        notifyObservers(); //刷新缓存
    }
}
