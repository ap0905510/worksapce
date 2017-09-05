package com.yw.httpprocessor.http;

import com.yw.httpprocessor.interfaces.ICallBack;
import com.yw.httpprocessor.interfaces.IHttpProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * 代理类
 */
public class HttpHelper implements IHttpProcessor {

    private static IHttpProcessor mIHttpProcessor;
    private static HttpHelper _instance;
    private Map<String, Object> mParams;

    private HttpHelper() {
        mParams = new HashMap<>();
    }


    public static HttpHelper obtainInstance() {
        synchronized (HttpHelper.class) {
            if (_instance == null) {
                _instance = new HttpHelper();
            }
        }
        return _instance;
    }

    public static void init(IHttpProcessor httpProcessor) {
        mIHttpProcessor = httpProcessor;
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallBack callback) {
        mIHttpProcessor.get(url, params, callback);
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callback) {
        mIHttpProcessor.post(url, params, callback);
    }

    //拼接url
    private String appendParams(String url, Map<String, Object> params) {
        return "";
    }
}
