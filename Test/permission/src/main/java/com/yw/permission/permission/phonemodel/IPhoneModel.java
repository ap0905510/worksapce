package com.yw.permission.permission.phonemodel;

import android.content.Context;


/**
 * Author:  Kevin Feng
 * Email:   597415099@qq.com
 * Date:    2016-10-22 16:57
 * Description:
 */
public interface IPhoneModel {

    int windowPerm(Context context);

    int protectPerm(Context context);

    int dialPerm(Context context);

    int contactsPerm(Context context);

    int accessibilityPerm(Context context);

}
