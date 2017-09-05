package com.youmai.okhttpclient;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;
import com.youmai.okhttp3.listener.DisposeDataHandle;
import com.youmai.okhttp3.listener.DisposeDataListener;
import com.youmai.okhttp3.listener.DisposeDownloadListener;
import com.youmai.okhttp3.okhttp3client.CommonOkHttpClient;
import com.youmai.okhttp3.request.CommonRequest;
import com.youmai.okhttp3.request.RequestParams;
import com.youmai.okhttp3.response.CommonFileCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.FileDownloadCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        Button btn_call = (Button) findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone();
            }
        });

        iv = (ImageView) findViewById(R.id.iv_img);
        /*Bitmap bitmap = BitmapFactory.decodeFile("http:///file.huxin.biz/" + 263601);
        iv.setImageBitmap(bitmap);*/

        post();
        getFile();

    }

    private void getFile() {
        String url = "http://file.huxin.biz/" + 263601;
        final File saveFile = new File(Environment.getExternalStorageDirectory(),"/263601.jpg");
        HttpRequest.download(url, saveFile, new FileDownloadCallback() {
            //开始下载
            @Override
            public void onStart() {
                super.onStart();
            }

            //下载进度
            @Override
            public void onProgress(int progress, long networkSpeed) {
                super.onProgress(progress, networkSpeed);
                Log.e(TAG, "progress = " + progress);
            }

            //下载失败
            @Override
            public void onFailure() {
                super.onFailure();
                Toast.makeText(getBaseContext(), "下载失败", Toast.LENGTH_SHORT).show();
            }

            //下载完成（下载成功）
            @Override
            public void onDone() {
                super.onDone();
                Toast.makeText(getBaseContext(), "下载成功", Toast.LENGTH_SHORT).show();
                iv.setImageBitmap(BitmapFactory.decodeFile(saveFile.getAbsolutePath()));
            }
        });
    }



    private void downloadFile() {

        RequestParams params = new RequestParams();

        Request request = CommonRequest.createMultiPostRequest("http:///file.huxin.biz/" + 263601, params);

        CommonOkHttpClient.downloadFile(request, new DisposeDataHandle(new DisposeDownloadListener() {
            @Override
            public void onProgress(int progress) {
                Log.e(TAG, "progress = " + progress);
            }

            @Override
            public void onSuccessDownload(File file) {
                Toast.makeText(MainActivity.this, "hello okhttp", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(String response) {

            }

            @Override
            public void onFailure(Object response) {

            }
        }));
    }

    private void post() {

//        Map<String, String> map = new HashMap<>();
//        RequestParams params = new RequestParams(map);
//
//        Request request = CommonRequest.createPostRequest("http:///file.huxin.biz/" + 263601, params);
//
//        CommonOkHttpClient.post(request,
//                new DisposeDataHandle(new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(String response) {
//                        Log.e(TAG, "response = " + response);
//                    }
//
//                    @Override
//                    public void onFailure(Object response) {
//                        Log.e(TAG, "response = " + response.toString());
//                    }
//                }));

        HttpRequest.post("http://file.huxin.biz/" + 263601, null, 30000, new BaseHttpRequestCallback<String>() {
            @Override
            protected void onSuccess(Headers headers, String s) {
                super.onSuccess(headers, s);
            }

            @Override
            public void onResponse(Response httpResponse, String response, Headers headers) {
                super.onResponse(httpResponse, response, headers);
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });

    }

    private static final int REQUEST_CODE = 1001;

    //打电话
    private void callPhone() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {  //检查打电话权限
            //操作权限申请处理
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, 1001);

        } else {
            doCallPhone();
        }
    }

    private void doCallPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE:
                //打电话权限回调处理
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doCallPhone();
                } else {
                    //提示用户权限未被授予
                    Toast.makeText(MainActivity.this, "电话权限未被授予", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
