package com.yw.observer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yw.observer.observer.ConcreteObserver;
import com.yw.observer.observer.Watcher;
import com.yw.observer.proxy.CommonInvocationHandler;
import com.yw.observer.proxy.Drink;
import com.yw.observer.proxy.Water;
import com.yw.observer.proxy.proxy2.IOneKeyCareListener;
import com.yw.observer.proxy.proxy2.OneKeyCareManager;
import com.yw.observer.subject.Watched;
import com.yw.observer.util.TextObserver;
import com.yw.observer.util.TextSubject;

import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity implements IOneKeyCareListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //发布
        Watched watched = new Watched();

        //观察者
        ConcreteObserver observer = new ConcreteObserver(watched);
        watched.setData("YW welcome！");

        watched.detach(observer);

        Watcher watcher = new Watcher(watched);
        watched.setData("Hello world!");

        watched.detach(watcher);

        /******************调用系统的没作用 ？？？*********************/
        TextSubject subject = new TextSubject();
        TextObserver textObserver = new TextObserver();

        subject.addObserver(textObserver);
        subject.changeData("Hello Observer!");
        subject.deleteObserver(textObserver);

        //被代理对象
        Water water = new Water();
        //动态获取代理对象
        Drink drink = (Drink) Proxy.newProxyInstance(water.getClass().getClassLoader(),
                water.getClass().getInterfaces(),
                new CommonInvocationHandler(water));
        //通过代理对象调用方法
        drink.drink();

        OneKeyCareManager.getInstance().addListener(this);

        OneKeyCareManager.getInstance().listener.oneKeyCareResult(true);
        OneKeyCareManager.getInstance().listener.oneKeyCareContinue();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OneKeyCareManager.getInstance().removeListener(this);
    }

    @Override
    public void oneKeyCareResult(boolean result) {
        Log.e("YW", "result: " + result);
    }

    @Override
    public void oneKeyCareContinue() {
        Log.e("YW", "oneKeyCareContinue: ");
    }
}
