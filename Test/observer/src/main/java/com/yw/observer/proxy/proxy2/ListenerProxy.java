package com.yw.observer.proxy.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 接口回调的代理模式
 *
 * @param <T>
 * @author Administrator
 */
public class ListenerProxy<T> {

    public List<T> m_listeners = Collections.synchronizedList(new ArrayList<T>());

    private Class<T> m_objListenerClass;

    public ListenerProxy(Class<T> listener) {
        this.m_objListenerClass = listener;
    }

    private final InvocationHandler invocation = new InvocationHandler() {

        @Override
        public Object invoke(Object obj, Method method, Object[] args)
                throws Throwable {

            Object objTRet = null;
            ArrayList<T> arrayList = new ArrayList<>(m_listeners);

            for (T listener : arrayList) {
                objTRet = method.invoke(listener, args);
            }
            /*for (int i = 0; i < arrayList.size(); i++) {
				int iIndex = arrayList.indexOf(m_listeners.get(0));
				if(iIndex == -1){
					objTRet = method.invoke(m_objListenerClass, args);
				}
			}*/
            return objTRet;
        }
    };

    @SuppressWarnings("unchecked")
    public T createProxyListener() {
        return (T) Proxy.newProxyInstance(m_objListenerClass.getClassLoader(),
                new Class<?>[]{this.m_objListenerClass}, invocation);
    }

    public int getSize() {
        if (m_listeners == null) {
            return 0;
        }
        return m_listeners.size();
    }


    public void add(T m_listener) {
        if (m_listener == null) {
            return;
        }
        m_listeners.add(m_listener);
    }


    public void remove(T m_listener) {
        if (m_listener == null) {
            return;
        }
        m_listeners.remove(m_listener);
    }

    public void removeAllListener() {
        m_listeners.clear();
    }

}
