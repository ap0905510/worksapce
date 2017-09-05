package com.yw.observer.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 作者：create by YW
 * 日期：2017.04.28 16:23
 * 描述：代理 Handler
 */
public class CommonInvocationHandler implements InvocationHandler {

    private Object mProxy;

    public CommonInvocationHandler(Object proxy) {
        this.mProxy = proxy;
    }

    /**
     * @param proxy  调用该方法的代理对象
     * @param method 代理对象所调用的方法
     * @param args   调用的方法的参数
     * @return Object 调用的方法的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e("YW", "before");
        Object result = method.invoke(mProxy, args);
        Log.e("YW", "after");
        return result;
    }

}
