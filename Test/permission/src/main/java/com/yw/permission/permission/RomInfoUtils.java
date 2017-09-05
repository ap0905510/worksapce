package com.yw.permission.permission;

import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author:  Kevin Feng
 * Email:   597415099@qq.com
 * Date:    2016-10-24 16:00
 * Description:
 */
public class RomInfoUtils {

    private static final String TAG = "RomInfoUtils";

    public static final String VIVO_PROP = "ro.vivo.os.build.display.id";
    public static final String OPPO_PROP = "ro.build.version.opporom";
    public static final String HUAWEI_PROP = "ro.build.version.emui";
    public static final String MEIZU_PROP = "ro.build.display.id";
    public static final String XIAOMI_PROP = "ro.miui.ui.version.name";
    public static final String MI_PROP = "";


    public static final int MEIZU = 1;
    public static final int MEIZU_NOTE = 2;




    public static String getVivoRomVersion() {
        String romProp = getRomProperty(VIVO_PROP);
        return romProp.replace("Funtouch OS_", "");
    }

    public static String getOppoRomVersion() {
        String romProp = getRomProperty(OPPO_PROP);
        return romProp.replace("V", "");
    }

    public static String getHuaweiRomVersion() {
        String romProp = getRomProperty(HUAWEI_PROP);
        return romProp.replace("EmotionUI_", "");
    }

    public static int getMeizuRomVersion() {
        int version = MEIZU;
        String romProp = getRomProperty(MEIZU_PROP);
        if(romProp.toLowerCase().indexOf("flyme os") != -1 || Build.MANUFACTURER.equalsIgnoreCase("meizu")) {
            version = MEIZU;
        } else if(romProp.toLowerCase().indexOf("flyme") != -1) {
            version = MEIZU_NOTE;
        }
        return version;
    }

    /**
     * 小米 ROM 权限申请
     */
    public static int getMiUiRomVersion() {
        String version = getRomProperty(XIAOMI_PROP);
        if (version != null) {
            try {
                return Integer.parseInt(version.substring(1));
            } catch (Exception e) {
                Log.e(TAG, "get miui version code error, version : " + version);
            }
        }
        return -1;
    }

    private static String getRomProperty(String prop) {
        String line = "";
        BufferedReader reader = null;
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("getprop "+prop);
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = reader.readLine();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(p != null) {
                p.destroy();
            }
        }
        return line;
    }
}
