package com.yw.heightlight.interfaces;

import android.view.View;

import com.yw.heightlight.HighLight;
import com.yw.heightlight.view.HightLightView;

/**
 * <pre>
 * 控制高亮控件的接口
 * Created by isanwenyu on 2016/11/01.
 * Copyright (c) 2016 isanwenyu@163.com. All rights reserved.
 * </pre>
 */
public interface HighLightInterface<T> {

    /**
     * 移除
     */
    HighLight remove();

    /**
     * 显示
     */
    HighLight show();

    /**
     * 显示下一个布局
     *
     * @return
     */
    HighLight next();

    /**
     * @return 锚点布局
     */
    View getAnchor();

    /**
     * @return 高亮布局控件
     */
    T getHightLightView();

    public static interface OnClickCallback<T> {
        void onClick();
    }

    /**
     * 显示回调监听
     */
    public static interface OnShowCallback<T> {
        /**
         * @param hightLightView 高亮布局控件
         */
        void onShow(T hightLightView);
    }

    /**
     * 移除回调监听
     */
    public static interface OnRemoveCallback<T> {
        /**
         * 移除高亮布局
         */
        void onRemove();
    }

    /**
     * 下一个回调监听 只有Next模式下生效
     */
    public static interface OnNextCallback {
        /**
         * 监听下一步动作
         *
         * @param hightLightView 高亮布局控件
         * @param targetView     高亮目标控件
         * @param tipView        高亮提示控件
         */
        void onNext(HightLightView hightLightView, View targetView, View tipView);
    }

    /**
     * mAnchor全局布局监听器
     */
    public static interface OnLayoutCallback<T> {
        /**
         * 布局结束
         */
        void onLayouted();
    }
}