package com.yw.permission.permission.phonemodel;

import android.content.Context;
import android.content.Intent;

import com.yw.permission.R;
import com.yw.permission.permission.RomInfoUtils;

/**
 * Author:  Kevin Feng
 * Email:   597415099@qq.com
 * Date:    2016-10-24 10:52
 * Description:
 */
public class OppoPhoneModel implements IPhoneModel {

    private int index = 3;

    private boolean isPlus = false;

    public OppoPhoneModel() {
        String version = RomInfoUtils.getOppoRomVersion();
        if (version.startsWith("2")) {
            index = 2;
        } else if (version.startsWith("3")) {
            index  = 3;
            if (version.length() < 5) {
                isPlus = true;
            } else {
                isPlus = false;
            }
        }
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int windowPerm(Context context) {
        Intent it = new Intent();
        try {
            switch (index) {
                case 2:
                    it.setClassName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity");
                    context.startActivity(it);
                    return R.layout.hx_activity_perm_oppo2_window;
                case 3:
                    if (!isPlus) {
                        it.setClassName("com.coloros.safecenter", "com.coloros.safecenter.permission.floatwindow.FloatWindowListActivity");
                        context.startActivity(it);
                        return R.layout.hx_activity_perm_oppo3_window;
                    } else {
                        it.setClassName("com.coloros.safecenter", "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity");
                        context.startActivity(it);
                        return R.layout.hx_activity_perm_oppo3_window;
                    }
            }
        }catch (Exception e) {
            try {
                it.setClassName("com.coloros.safecenter", "com.coloros.safecenter.sysfloatwindow.FloatWindowListActivity");
                context.startActivity(it);
                return R.layout.hx_activity_perm_oppo3_window;
            }catch (Exception ex) {
                return -1;
            }
        }

        return -1;
    }

    @Override
    public int protectPerm(Context context) {
        Intent it = new Intent();
        switch (index) {
            case 2:
                it.setClassName("com.color.safecenter", "com.color.safecenter.permission.startup.StartupAppListActivity");
                context.startActivity(it);
                return R.layout.hx_activity_perm_oppo2_protect;
            case 3:
                it.setClassName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity");
                context.startActivity(it);
                break;
        }
        return -1;
    }

    @Override
    public int dialPerm(Context context) {
        Intent it = new Intent();
        switch (index) {
            case 2:
                it.setClassName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity");
                context.startActivity(it);
                return R.layout.hx_activity_perm_oppo2_dial;
            case 3:
                it.setClassName("com.coloros.safecenter", "com.coloros.safecenter.permission.PermissionManagerActivity");
                context.startActivity(it);
                return R.layout.hx_activity_perm_oppo3_dial;
        }
        return -1;
    }

    @Override
    public int contactsPerm(Context context) {
        Intent it = new Intent();
        switch (index) {
            case 2:
                it.setClassName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity");
                context.startActivity(it);
                return R.layout.hx_activity_perm_oppo2_contacts;
            case 3:
                it.setClassName("com.coloros.safecenter", "com.coloros.safecenter.permission.PermissionManagerActivity");
                context.startActivity(it);
                return R.layout.hx_activity_perm_oppo3_contacts;
        }
        return -1;
    }

    @Override
    public int accessibilityPerm(Context context) {
        Intent it = new Intent(Intent.ACTION_MAIN);
        it.setClassName("com.android.settings", "com.android.settings.Settings$AccessibilitySettingsActivity");
        context.startActivity(it);
        return 0;
    }
}
