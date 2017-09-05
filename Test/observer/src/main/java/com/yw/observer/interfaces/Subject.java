package com.yw.observer.interfaces;

/**
 * 作者：create by YW
 * 日期：2017.04.28 10:29
 * 描述：
 */
public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}
