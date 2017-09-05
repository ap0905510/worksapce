package com.yw.observer.observer;

import android.util.Log;

import com.yw.observer.interfaces.Observer;
import com.yw.observer.interfaces.Subject;
import com.yw.observer.subject.Watched;

/**
 * 作者：create by YW
 * 日期：2017.04.28 13:43
 * 描述：观察者2 Watcher
 */
public class Watcher implements Observer {

    public Watcher(Watched w) {
        w.attach(this); //把观察者添加进订阅者列表
    }

    /**
     * 刷新Subject发布的消息, 并自己监听处理
     * @param sub
     * @param obj
     */
    @Override
    public void update(Subject sub, Object obj) {
        //todo: 可以设置监听接口回调
        Log.e("YW", "Watcher: " + ((Watched)sub).getData());
    }

}
