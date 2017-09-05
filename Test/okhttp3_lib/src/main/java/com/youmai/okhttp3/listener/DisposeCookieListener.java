package com.youmai.okhttp3.listener;

import java.util.ArrayList;

/**
 * 作者：create by YW
 * 日期：2016.10.25 10:19
 * 描述：当需要专门处理Cookie时创建此回调接口
 */
public interface DisposeCookieListener extends DisposeDataListener {
    public void onCookie(ArrayList<String> cookieStrLists);
}
