package com.youmai.okhttpclient;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v13.app.FragmentCompat;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.youmai.okhttp3.constant.Constants;

/**
 * Created by Administrator on 2016/10/21.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /***
     * 为子类提供一个权限检查方法
     * @param permissions
     * @return
     */
    public boolean hasPermission(String ... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 为子类提供一个权限请求方法
     * @param code
     * @param permissions
     */
    private void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Constants.CALL_PHONE:
                doCallPhone();
                break;
            case Constants.WRITE_EXTERNAL_CODE:
                doSDCardPermission();
                break;
        }
    }

    /**
     * 默认打电话权限处理 -- 子类复写
     */
    private void doCallPhone() {
    }

    /**
     * 默认写SD卡权限处理
     */
    private void doSDCardPermission() {
    }

}
