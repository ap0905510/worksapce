package com.yw.observer.proxy;

/**
 * 作者：create by YW
 * 日期：2017.04.28 16:19
 * 描述：静态代理类 （代理 Drink）
 */
public class DrinkProxy implements Drink {

    private Drink drinkImpl;

    //通过构造函数传入Water对象
    public DrinkProxy(Drink drinkImpl) {
        this.drinkImpl = drinkImpl;
    }

    @Override
    public void drink() {
        //在执行被代理对象的方法前做一些事情
        System.out.println("before drink");
        //执行被代理对象的方法
        drinkImpl.drink();
        //在执行被代理对象的方法后做一些事
        System.out.println("after drink");
    }

}
