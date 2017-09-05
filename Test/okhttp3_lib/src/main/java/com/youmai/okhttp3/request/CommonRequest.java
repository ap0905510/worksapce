package com.youmai.okhttp3.request;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2016/10/21.
 * @function build the request: get，post, 文件上传类型,文件下载类型
 */
public class CommonRequest {

    /**
     * request the params to the url
     * @param url
     * @param params
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if(null != params) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        return new Request.Builder().url(
                urlBuilder.substring(0, urlBuilder.length() - 1)).get().build();
    }

    /**
     * create the key-value Request : post请求
     * @param url
     * @param params
     * @return
     */
    public static Request createPostRequest(String url, RequestParams params) {
        FormBody.Builder builder = new FormBody.Builder();
        if(null != params) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody body = builder.build();
        body.create(MediaType.parse("application/json; charset=utf-8"),params.toString());
        return new Request.Builder().url(url).post(body).build();
    }

    /**
     * 文件上传请求
     */
    private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");

    public static Request createMultiPostRequest(String url, RequestParams params) {

        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        requestBody.setType(MultipartBody.FORM);
        if (params != null) {

            for (Map.Entry<String, Object> entry : params.fileParams.entrySet()) {
                if (entry.getValue() instanceof File) {
                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                            RequestBody.create(FILE_TYPE, (File) entry.getValue()));
                } else if (entry.getValue() instanceof String) {
                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                            RequestBody.create(null, (String) entry.getValue()));
                }
            }
        }
        return new Request.Builder().url(url).post(requestBody.build()).build();
    }
}
