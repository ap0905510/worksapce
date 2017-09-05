package com.yw.deamontest;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by yw on 2017/8/27.
 */

public class AppUtils {

    // 查询Service是否存活
    public static boolean isServiceWork(Context context,  String serviceName) {
        boolean isWorked = false;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServices = manager.getRunningServices(128);
        if (runningServices.size() < 0) {
            return false;
        }

        for (int i = 0; i < runningServices.size(); i++) {
            String name = runningServices.get(i).service.getClassName().toString();
            if (serviceName.equals(name)) {
                isWorked = true;
                break;
            }
        }
        return isWorked;
    }

}
