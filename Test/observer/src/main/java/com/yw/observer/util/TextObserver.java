package com.yw.observer.util;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * 作者：create by YW
 * 日期：2017.04.28 14:49
 * 描述：
 */
public class TextObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Log.e("YW", "TextObserver: " + ((TextSubject)o).retrieveData());
    }

}
