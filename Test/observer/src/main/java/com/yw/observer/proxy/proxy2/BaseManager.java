package com.yw.observer.proxy.proxy2;

public abstract class BaseManager<T> {

    /**
     * add
     * @param m_listener
     */
    public abstract void addListener(T m_listener);

    /**
     * remove
     * @param m_listener
     */
    public abstract void removeListener(T m_listener);

}
