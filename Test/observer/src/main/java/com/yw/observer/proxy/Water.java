package com.yw.observer.proxy;

import android.util.Log;

/**
 * 作者：create by YW
 * 日期：2017.04.28 16:14
 * 描述：
 */
public class Water implements Drink {

    @Override
    public void drink() {
        Log.e("YW", "drink water");
    }

}
