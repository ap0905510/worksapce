package com.yw.permission.permission.phonemodel;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.yw.permission.R;
import com.yw.permission.permission.RomInfoUtils;

/**
 * Author:  Kevin Feng
 * Email:   597415099@qq.com
 * Date:    2016-10-25 14:45
 * Description:
 */
public class XiaomiPhoneModel implements IPhoneModel {

    private int index = -1;

    public XiaomiPhoneModel() {
        int version = RomInfoUtils.getMiUiRomVersion();
        if (version >= 8) {
            index = 8;
        } else {
            index = 7;
        }
    }

    @Override
    public int windowPerm(Context context) {
        Intent it = new Intent();

        switch (index) {
            case 8:
                it.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
                break;
            default:
                it.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                break;
        }

        it.putExtra("extra_pkgname", context.getPackageName());
        context.startActivity(it);
        return R.layout.hx_activity_perm_xiaomi_window;
    }

    @Override
    public int protectPerm(Context context) {
        Intent it = new Intent();
        it.setClassName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
        it.putExtra("extra_pkgname", context.getPackageName());
        context.startActivity(it);
        return -1;
    }

    @Override
    public int dialPerm(Context context) {
        Intent it = new Intent();
        it.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        it.putExtra("extra_pkgname", context.getPackageName());
        context.startActivity(it);
        return R.layout.hx_activity_perm_xiaomi_dial;
    }

    @Override
    public int contactsPerm(Context context) {
        Intent it = new Intent();
        it.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        it.putExtra("extra_pkgname", context.getPackageName());
        context.startActivity(it);
        return R.layout.hx_activity_perm_xiaomi_contacts;
    }

    @Override
    public int accessibilityPerm(Context context) {
        Intent it = new Intent(Intent.ACTION_MAIN);
        it.setClassName("com.android.settings", "com.android.settings.Settings$AccessibilitySettingsActivity");
        context.startActivity(it);
        return -1;
    }
}
