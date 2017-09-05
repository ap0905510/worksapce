package com.yw.permission.permission.phonemodel;

import android.content.Context;
import android.content.Intent;

/**
 * 作者：create by YW
 * 日期：2017.03.15 15:54
 * 描述：
 */
public class LetVPhoneModel implements IPhoneModel {

    public LetVPhoneModel() {

    }

    @Override
    public int windowPerm(Context context) {
        Intent it = new Intent(Intent.ACTION_MAIN);
        //it.setClassName("com.letv.android.supermanager", "com.letv.android.supermanager.activity.PermissionManagerActivity");
        //it.setClassName("com.android.packageinstaller", "com.android.packageinstaller.permission.ui.ManagePermissionsActivity");
        //it.setClassName("com.android.settings", "com.letv.leui.settings.accesscontrol.LeUIAccessControlIntroductionActivity");

        it.setClassName("com.letv.android.supermanager", "com.letv.android.supermanager.activity.SuperManagerActivity");
        it.putExtra("extra_pkgname", context.getPackageName());
        context.startActivity(it);
        return -1;
    }

    @Override
    public int protectPerm(Context context) {
        Intent it = new Intent();
        it.setClassName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity");
        it.putExtra("extra_pkgname", context.getPackageName());
        context.startActivity(it);
        return -1;
    }

    @Override
    public int dialPerm(Context context) {

        Intent it = new Intent();
        it.setClassName("com.letv.android.supermanager", "com.letv.android.supermanager.activity.SuperManagerActivity");
        it.putExtra("extra_pkgname", context.getPackageName());
        context.startActivity(it);
        return -1;
    }

    @Override
    public int contactsPerm(Context context) {
        Intent it = new Intent();
        it.setClassName("com.letv.android.supermanager", "com.letv.android.supermanager.activity.SuperManagerActivity");
        it.putExtra("extra_pkgname", context.getPackageName());
        context.startActivity(it);
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
