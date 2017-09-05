package com.wingsofts.custombehavior.handler;

import android.telecom.Call;
import android.util.Log;

/**
 * 作者：create by YW
 * 日期：2017.08.17 20:52
 * 描述：
 */

public class Handler {

    Looper mLooper;
    MessageQueue mQueue = new MessageQueue();
    Callback mCallback;

    public Handler(Looper looper) {
        this.mLooper = looper.myLooper();
    }

    public Handler(Looper looper, Callback callback) {
        this.mLooper = looper;
        this.mCallback = callback;
    }

    public void sendMessage(Message msg) {
        msg.target = this;
        mQueue.enqueueMessage(msg);
    }

    public void handleMessage(Message msg) {
        Log.e("YW", "handleMessage: " + msg.what);
    }

    public void dispatchMessage(Message msg) {
        Log.e("YW", "dispatchMessage: " + msg.what);
        handleMessage(msg);
    }

    public interface Callback {
        void handleMessage(Message msg);
    }

}
