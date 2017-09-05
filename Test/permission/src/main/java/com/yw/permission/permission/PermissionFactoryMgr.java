package com.yw.permission.permission;

import android.os.Build;

import com.yw.permission.permission.phonemodel.HuaWeiPhoneModel;
import com.yw.permission.permission.phonemodel.IPhoneModel;
import com.yw.permission.permission.phonemodel.LetVPhoneModel;
import com.yw.permission.permission.phonemodel.MeizuPhoneModel;
import com.yw.permission.permission.phonemodel.OppoPhoneModel;
import com.yw.permission.permission.phonemodel.ViVoPhoneModel;
import com.yw.permission.permission.phonemodel.XiaomiPhoneModel;

/**
 * Author:  Kevin Feng
 * Email:   597415099@qq.com
 * Date:    2016-10-22 16:37
 * Description:
 */
public class PermissionFactoryMgr {

    public static IPhoneModel getPhoneModel() {
        String manufacturer = Build.MANUFACTURER.toLowerCase();
        if (manufacturer.contains("vivo")) { //vivo
            return new ViVoPhoneModel();
        } else if (manufacturer.contains("oppo")) { //oppo
            return new OppoPhoneModel();
        } else if (manufacturer.contains("huawei")) { //huawei
            return new HuaWeiPhoneModel();
        } else if (manufacturer.contains("meizu")) { //meizu
            return new MeizuPhoneModel();
        } else if (manufacturer.contains("xiaomi")) { //xiaomi
            return new XiaomiPhoneModel();
        } else if (manufacturer.contains("letv")) {
            return new LetVPhoneModel();
        }
        return null;
    }

}
