package com.yw.permission.permission.phonemodel;

import android.content.Context;
import android.content.Intent;

import com.yw.permission.R;
import com.yw.permission.permission.RomInfoUtils;

/**
 * Author:  Kevin Feng
 * Email:   597415099@qq.com
 * Date:    2016-10-25 10:16
 * Description:
 */
public class MeizuPhoneModel implements IPhoneModel {

    private int version;

    public MeizuPhoneModel() {
        version = RomInfoUtils.getMeizuRomVersion();
    }

    @Override
    public int windowPerm(Context context) {
        Intent it = new Intent();

        it.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        it.putExtra("packageName", context.getPackageName());
        context.startActivity(it);
        switch (version) {
            case RomInfoUtils.MEIZU:
                return R.layout.hx_activity_perm_meizu_window;
            case RomInfoUtils.MEIZU_NOTE:
                return R.layout.hx_activity_perm_meizu_note_window;
        }
        return -1;
    }

    @Override
    public int protectPerm(Context context) {
        Intent it = new Intent();
        try {
            switch (version) {
                case RomInfoUtils.MEIZU:
                    it.setClassName("com.meizu.safe", "com.meizu.safe.permission.SmartBGActivity");
                    context.startActivity(it);
                    return R.layout.hx_activity_perm_meizu_protect;
                case RomInfoUtils.MEIZU_NOTE:
                    it.setClassName("com.meizu.safe", "com.meizu.safe.powerui.AppPowerManagerActivity");
                    context.startActivity(it);
                    return R.layout.hx_activity_perm_meizu_note_protect;
            }
        } catch (Exception e) {
            try {
                it.setClassName("com.meizu.safe", "com.meizu.safe.powerui.PowerAppPermissionActivity");
                context.startActivity(it);
                return R.layout.hx_activity_perm_meizu_note_protect;
            }catch (Exception ex) {
                return -1;
            }
        }
        return -1;
    }

    @Override
    public int dialPerm(Context context) {
        Intent it = new Intent();
        it.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        it.putExtra("packageName", context.getPackageName());
        context.startActivity(it);
        switch (version) {
            case RomInfoUtils.MEIZU:
                return R.layout.hx_activity_perm_meizu_dial;
            case RomInfoUtils.MEIZU_NOTE:
                return R.layout.hx_activity_perm_meizu_note_dial;
        }

        return -1;
    }

    @Override
    public int contactsPerm(Context context) {
        Intent it = new Intent();
        it.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        it.putExtra("packageName", context.getPackageName());
        context.startActivity(it);
        switch (version) {
            case RomInfoUtils.MEIZU:
                return R.layout.hx_activity_perm_meizu_contacts;
            case RomInfoUtils.MEIZU_NOTE:
                return R.layout.hx_activity_perm_meizu_note_contacts;
        }

        return -1;
    }

    @Override
    public int accessibilityPerm(Context context) {
        Intent it = new Intent(Intent.ACTION_MAIN);
        it.setClassName("com.android.settings", "com.android.settings.Settings$AccessibilitySettingsActivity");
        context.startActivity(it);
        return -1;
    }
}
