package com.youmai.okhttp3.okhttp3client;

import com.youmai.okhttp3.listener.DisposeDataHandle;
import com.youmai.okhttp3.response.CommonFileCallback;
import com.youmai.okhttp3.response.CommonJsonCallback;
import com.youmai.okhttp3.ssl.HttpsUtils;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/10/21.
 * @function 用来发送get, post请求的工具类
 */
public class CommonOkHttpClient {

    private static final int TIME_OUT = 30; //响应超时时间

    private static OkHttpClient mOkHttpClient;

    static {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;//允许通过
            }
        });

        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.followRedirects(true);//允许重定向
        //okHttpBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory());//https://

        mOkHttpClient = okHttpBuilder.build();
    }


    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }


    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    //差个文件的封装请求
    public static Call downloadFile(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;
    }

}


