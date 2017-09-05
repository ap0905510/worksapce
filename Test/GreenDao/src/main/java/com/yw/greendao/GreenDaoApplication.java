package com.yw.greendao;

import android.app.Application;
import com.yw.greendao.utils.GreenDBUtils;

/**
 * 作者：create by YW
 * 日期：2017.03.28 14:10
 * 描述：
 */
public class GreenDaoApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        GreenDBUtils.instance(this);
    }

}
