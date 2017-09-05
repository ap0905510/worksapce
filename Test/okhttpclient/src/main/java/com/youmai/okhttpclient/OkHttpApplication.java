package com.youmai.okhttpclient;

import android.app.Application;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

/**
 * 作者：create by YW
 * 日期：2017.02.18 13:57
 * 描述：
 */

public class OkHttpApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
    }
}
