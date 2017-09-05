package com.wingsofts.custombehavior.handler;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 作者：create by YW
 * 日期：2017.08.17 20:54
 * 描述：
 */

public class MessageQueue {

    private static final int MAX = 50;

    private static BlockingQueue<Message> mBlockQueue;

    public MessageQueue() {
        this.mBlockQueue = new ArrayBlockingQueue<>(MAX);
    }

    public Message next() {
        Message message = null;
        try {
            message = mBlockQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("YW", "next: " + message.what);
        return message;
    }

    public void enqueueMessage(Message msg) {
        try {
            mBlockQueue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
