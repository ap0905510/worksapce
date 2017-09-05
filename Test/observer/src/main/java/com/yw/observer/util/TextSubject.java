package com.yw.observer.util;

import java.util.Observable;

/**
 * 作者：create by YW
 * 日期：2017.04.28 15:08
 * 描述：
 */
public class TextSubject extends Observable {

    private String data;

    /**
     * 重新获得的数据
     * @return
     */
    public String retrieveData() {
        return data;
    }

    /**
     * 改变数据 并刷新发布者
     * @param data
     */
    public void changeData(String data) {
        this.data = data;
        clearChanged();
        notifyObservers();
    }
}
