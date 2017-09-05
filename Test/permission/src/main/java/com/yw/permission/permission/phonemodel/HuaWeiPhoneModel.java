package com.yw.permission.permission.phonemodel;

import android.content.Context;
import android.content.Intent;
import com.yw.permission.R;
import com.yw.permission.permission.RomInfoUtils;

/**
 * Author:  Kevin Feng
 * Email:   597415099@qq.com
 * Date:    2016-10-24 16:51
 * Description:
 */
public class HuaWeiPhoneModel implements IPhoneModel {

    private int index = -1;

    public HuaWeiPhoneModel() {
        String version = RomInfoUtils.getHuaweiRomVersion();
        if (version.startsWith("3")) {
            index = 3;
        } else if (version.startsWith("4")) {
            index = 4;
        }else if (version.startsWith("5")) {
            index = 5;
        }
    }

    @Override
    public int windowPerm(Context context) {
        Intent it = new Intent();
        switch (index) {
            case 3:
            case 4:
            case 5:
                it.setClassName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity");
                context.startActivity(it);
                break;
        }
        return R.layout.hx_activity_perm_huawei_window;
    }

    @Override
    public int protectPerm(Context context) {
        Intent it = new Intent();
        switch (index) {
            case 3:
            case 4:
            case 5:
                it.setClassName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity");
                context.startActivity(it);
                break;
        }

        return R.layout.hx_activity_perm_huawei_protect;
    }

    @Override
    public int dialPerm(Context context) {
        Intent it = new Intent();
        switch (index) {
            case 3:
            case 4:
            case 5:
                it.setClassName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
                context.startActivity(it);
                break;
        }
        return R.layout.hx_activity_perm_huawei_dial;
    }

    @Override
    public int contactsPerm(Context context) {
        Intent it = new Intent();
        switch (index) {
            case 3:
            case 4:
            case 5:
                it.setClassName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
                context.startActivity(it);
                break;
        }
        return R.layout.hx_activity_perm_huawei_contacts;
    }

    @Override
    public int accessibilityPerm(Context context) {
        Intent it = new Intent(Intent.ACTION_MAIN); // Settings.ACTION_ACCESSIBILITY_SETTINGS
        switch (index) {
            case 3:
            case 4:
            case 5:
                it.setClassName("com.android.settings", "com.android.settings.Settings$AccessibilitySettingsActivity");
                context.startActivity(it);
                break;
        }
        return 0;
    }
}
