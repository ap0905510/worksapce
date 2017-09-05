package com.wingsofts.custombehavior.handler;

/**
 * 作者：create by YW
 * 日期：2017.08.17 20:51
 * 描述：
 */

public class Message {

    String what;
    Handler target;

    public Message(String what) {
        this.what = what;
    }

    @Override
    public String toString() {
        return "Message{" +
                "what='" + what + '\'' +
                '}';
    }
}
