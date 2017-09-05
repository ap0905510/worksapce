package com.youmai.okhttp3.listener;

/**
 * Created by Administrator on 2016/10/21.
 * 处理不同的响应：json, 文件等等分类
 */
public class DisposeDataHandle {

    public DisposeDataListener mListener;
    public Class<?> mClazz;
    public String mSource;

    public DisposeDataHandle(DisposeDataListener listener) {
        this.mListener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener, Class<?> clazz) {
        this.mListener = listener;
        this.mClazz = clazz;
    }

    public DisposeDataHandle(DisposeDataListener listener, String source) {
        this.mListener = listener;
        this.mSource = source;
    }

    public DisposeDataListener getListener() {
        return mListener;
    }

    public void setListener(DisposeDataListener mListener) {
        this.mListener = mListener;
    }

    public Class<?> getClazz() {
        return mClazz;
    }

    public void setClazz(Class<?> mClazz) {
        this.mClazz = mClazz;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String mSource) {
        this.mSource = mSource;
    }

}
