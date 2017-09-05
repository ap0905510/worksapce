package com.yw.permission.permission.phonemodel;

import android.content.Context;
import android.content.Intent;

import com.yw.permission.R;
import com.yw.permission.permission.RomInfoUtils;

/**
 * Author:  Kevin Feng
 * Email:   597415099@qq.com
 * Date:    2016-10-22 17:01
 * Description:
 */
public class ViVoPhoneModel implements IPhoneModel {  //VIVO Y51A   vivoX7

    private String version;

    public ViVoPhoneModel() {
        version = RomInfoUtils.getVivoRomVersion();
        version.replace("Funtouch".toLowerCase(), "");
    }

    @Override
    public int windowPerm(Context context) {  //主界面展示
        Intent it = new Intent();
        it.setClassName("com.iqoo.secure", "com.iqoo.secure.MainActivity");
        context.startActivity(it);
        return R.layout.hx_activity_perm_vivo_window;
    }

    @Override
    public int protectPerm(Context context) {
        Intent it = new Intent();
        it.setClassName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity");
        context.startActivity(it);
        return R.layout.hx_activity_perm_vivo_protect;
    }

    @Override
    public int dialPerm(Context context) { //主界面展示
        Intent it = new Intent();
        it.setClassName("com.iqoo.secure", "com.iqoo.secure.MainActivity");
        context.startActivity(it);
        return R.layout.hx_activity_perm_vivo_dial;
    }

    @Override
    public int contactsPerm(Context context) {
        Intent it = new Intent();
        it.setClassName("com.iqoo.secure", "com.iqoo.secure.MainActivity");
        context.startActivity(it);
        return R.layout.hx_activity_perm_vivo_contacts;
    }

    @Override
    public int accessibilityPerm(Context context) {
        Intent it = new Intent(Intent.ACTION_MAIN);
        it.setClassName("com.android.settings", "com.android.settings.Settings$AccessibilitySettingsActivity");
        context.startActivity(it);
        return 0;
    }
}
