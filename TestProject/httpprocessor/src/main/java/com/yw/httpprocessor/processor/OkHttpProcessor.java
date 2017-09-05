package com.yw.httpprocessor.processor;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import com.yw.httpprocessor.interfaces.ICallBack;
import com.yw.httpprocessor.interfaces.IHttpProcessor;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpProcessor implements IHttpProcessor {

    public static final String TAG = "OkHttpProcessor";

    private static OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public OkHttpProcessor() {
        mHandler = new Handler();

        // 缓存路径
        int cacheSize = 50 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(new File(Environment.getExternalStorageDirectory() + "/HuXin/okhttp"), cacheSize);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        mOkHttpClient = client.cache(cache).build();
    }

    @Override
    public void get(String url, Map<String, Object> params, final ICallBack callback) {
        Log.e("YW", "Okhttp");
        Request request = new Request.Builder()
                .url(url)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailed(e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                postParams(callback, response.isSuccessful(), response);
            }
        });
    }


    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callback) {

        RequestBody requestBody = appendBody(params);

        //RequestBody requestBody = appendBigFile(params, null);

        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .header("User-Agent", "a")
                .tag(TAG)
                .build();

        mOkHttpClient.newBuilder()
                .readTimeout(5 * 1000, TimeUnit.MILLISECONDS) // readTimeout("请求超时时间" , 时间单位);
                .build()
                .newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailed(e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                postParams(callback, response.isSuccessful(), response);
            }
        });
    }

    //传入参数，返回添加头信息
    private RequestBody appendBody(Map<String, Object> params) {
        FormBody.Builder body = new FormBody.Builder();
        if(params == null || params.isEmpty()){
            return body.build();
        }
        for(Map.Entry<String, Object> entry : params.entrySet()){
                body.add(entry.getKey(),entry.getValue().toString());
        }
        return body.build();
    }

    //传入参数，返回json头
    private RequestBody appendJsonBody(Map<String, Object> params) {
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        RequestBody body = RequestBody.create(JSON, mapToJson(params));
        return body;
    }

    // 多表单提交
    protected MultipartBody appendBigFile(Map<String, Object> map, File file) {
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(file != null){
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            String fileName = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("headImage", fileName, body);
        }
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        }
        return requestBody.build();
    }

    public static String mapToJson(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return "{}";
        }
        JSONObject jsonObject = new JSONObject(map);
        String result = jsonObject.toString();
        return result;
    }

    private void postParams(final ICallBack callback, final boolean isSuccess, final Response response) {
        final String[] result = {""};
        try {
            result[0] = response.body().string();
            //响应头信息
            Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
                Log.e("YW", "header: " + headers.name(i) + ": " + headers.value(i));
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (isSuccess == true) {
                        callback.onSuccess(result[0]);
                    } else {
                        result[0] = response.message().toString();
                        callback.onFailed(result[0]);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
