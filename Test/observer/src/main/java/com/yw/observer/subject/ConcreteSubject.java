package com.yw.observer.subject;

import com.yw.observer.interfaces.Observer;
import com.yw.observer.interfaces.Subject;

import java.util.Vector;

/**
 * 作者：create by YW
 * 日期：2017.04.28 10:30
 * 描述：具体的发布主题 Manager
 */
public class ConcreteSubject implements Subject {

    private boolean changed = false;
    private Vector<Observer> mVector;

    public ConcreteSubject() {
        mVector = new Vector();
    }

    /**
     * 将一个观察者添加到观察者列
     * @param o
     */
    @Override
    public void attach(Observer o) {
        if(!mVector.contains(o)) {
            mVector.add(o);
        }
    }

    /**
     * 将一个观察者对象从观察者列表上删除
     * @param o
     */
    @Override
    public void detach(Observer o) {
        if(mVector.contains(o)) {
            mVector.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        notifyObservers(null);
    }

    public void notifyObservers(Object arg) {
        //临时存放当前的观察者的状态
        Object[] arrLocal;
        synchronized (this) {
            if (!changed)
                return;
            arrLocal = mVector.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length - 1; i >= 0; i--) {
            ((Observer)arrLocal[i]).update(this, arg);
        }
    }

    /**
     * 将观察者列表清空
     */
    public synchronized void deleteObservers() {
        mVector.removeAllElements();
    }

    /**
     * 有已改变（主题） 设为 true
     */
    protected synchronized void setChanged() {
        changed = true;
    }

    /**
     * 将“已变化”重置为 false
     */
    protected void clearChanged() {
        changed = false;
    }

    /**
     * 本对象是否改变
     * @return
     */
    public synchronized boolean hasChanged() {
        return changed;
    }

    /**
     * 返还被观察对象（即此对象）的观察者总数
     */
    public synchronized int countObservers() {
        return mVector.size();
    }

}
