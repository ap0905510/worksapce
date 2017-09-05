package com.wingsofts.custombehavior.handler;

/**
 * 作者：create by YW
 * 日期：2017.08.17 20:54
 * 描述：
 */

public class Looper {

    private MessageQueue mQueue;

    private static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();

    public Looper() {
        mQueue = new MessageQueue();
    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("only");
        } else {
            sThreadLocal.set(new Looper());
        }
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        Looper looper = myLooper();
        MessageQueue queue = looper.mQueue;

        for (;;) {
            Message message = queue.next();
            if (message == null) {
                return;
            }
            message.target.dispatchMessage(message);
        }
    }

}
