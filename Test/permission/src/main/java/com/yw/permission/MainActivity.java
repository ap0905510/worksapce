package com.yw.permission;

import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yw.permission.permission.PermissionFactoryMgr;
import com.yw.permission.permission.phonemodel.IPhoneModel;
import com.yw.permission.utils.MiUiUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String PERMISSION = "/HuXin/permission/";

    private TextView tv;
    IPhoneModel iPhoneModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine);

        iPhoneModel = PermissionFactoryMgr.getPhoneModel();

        /*tv = (TextView) findViewById(R.id.tv);
        StringBuilder sb  = new StringBuilder();
        sb.append("设置\n");
        sb.append("我是第二行\n");
        sb.append("我是内容\n");
        FileUtils.writeFile(getPermissionPath() + File.separator + "permiss.txt", sb.toString());*/

        String model = Build.MODEL;
        String board = Build.BOARD;
        String manufacturer = Build.MANUFACTURER;
        String brand = Build.BRAND;
        int sdkInt = Build.VERSION.SDK_INT;
        String baseOs = Build.VERSION.BASE_OS;

    }

    public void read(View view) {
        //String content = FileUtils.readFile(getPermissionPath() + File.separator + "permiss.txt", "utf-8");
        //tv.setText(content);

        MiUiUtils.goToMiUiPermissionActivity_V8(this);
    }

    public void windowPerm(View view) {

        iPhoneModel.windowPerm(this);
    }

    public void protectPerm(View view) {
        iPhoneModel.protectPerm(this);
    }

    public void dialPerm(View view) {
        iPhoneModel.dialPerm(this);
    }

    public void contactsPerm(View view) {
        iPhoneModel.contactsPerm(this);
    }

    public void accessibilityPerm(View view) {
        iPhoneModel.accessibilityPerm(this);
    }

    private void initData(View view) {

        int id = view.getId();
        if (id == R.id.dial_permission) {

            int res;
            try {
                res = iPhoneModel.dialPerm(this);
            } catch (Exception e) {
                res = -1;
            }

        } else if (id == R.id.window_permission) {

            int res;
            try {
                res = iPhoneModel.windowPerm(this);
            } catch (Exception e) {
                res = -1;
            }

        } else if (id == R.id.protect_permission) {
            int res;
            try {
                res = iPhoneModel.protectPerm(this);
            } catch (Exception e) {
                res = -1;
            }
        } else if (id == R.id.item_ly4) {

            int res;
            try {
                res = iPhoneModel.contactsPerm(this);
            } catch (Exception e) {
                res = -1;
            }
        }
    }

    public static String getPermissionPath() {
        String path = Environment.getExternalStorageDirectory().getPath() + PERMISSION;
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        return fileDir.getAbsolutePath();
    }
}
