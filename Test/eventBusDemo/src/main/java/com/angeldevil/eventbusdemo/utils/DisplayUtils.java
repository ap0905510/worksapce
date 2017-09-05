package com.angeldevil.eventbusdemo.utils;

import android.content.Context;

/**
 * 作者：create by YW
 * 日期：2016.11.21 15:09
 * 描述：
 */

public class DisplayUtils {

    public static int getScreenWidth(Context context) {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        return screenWidth;
    }

    public static int getScreenHeight(Context context) {
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        return heightPixels;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
