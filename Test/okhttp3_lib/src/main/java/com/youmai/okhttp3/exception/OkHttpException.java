package com.youmai.okhttp3.exception;

/**
 * Created by Administrator on 2016/10/21.
 * 异常处理类
 */
public class OkHttpException extends Exception {

    private static final long serialVersionUD = 1L;

    /**
     * return response code
     */
    private int ecode;

    /**
     * return error message
     */
    private Object emsg;

    public OkHttpException(int code, Object msg) {
        this.ecode = code;
        this.emsg = msg;
    }

    public int getEcode() {
        return ecode;
    }

    public Object getEmsg() {
        return emsg;
    }

}
