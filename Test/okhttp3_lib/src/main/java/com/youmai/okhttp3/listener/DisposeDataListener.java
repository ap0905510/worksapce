package com.youmai.okhttp3.listener;

/**
 * Created by Administrator on 2016/10/21.
 * 响应处理接口
 */
public interface DisposeDataListener {
    public void onSuccess(String response);
    public void onFailure(Object response);
}
