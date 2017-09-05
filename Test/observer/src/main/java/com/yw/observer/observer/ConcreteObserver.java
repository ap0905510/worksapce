package com.yw.observer.observer;

import android.util.Log;

import com.yw.observer.interfaces.Observer;
import com.yw.observer.interfaces.Subject;
import com.yw.observer.subject.Watched;

/**
 * 作者：create by YW
 * 日期：2017.04.28 10:21
 * 描述：观察者1 ConcreteObserver
 */
public class ConcreteObserver implements Observer {

    public ConcreteObserver(Watched w) {
        w.attach(this); //把观察者添加进订阅者列表
    }

    @Override
    public void update(Subject sub, Object obj) {
        Log.e("YW", "ConcreteSubject: " + ((Watched)sub).getData());
    }

}
