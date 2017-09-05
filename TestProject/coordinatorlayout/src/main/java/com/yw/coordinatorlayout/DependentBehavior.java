package com.yw.coordinatorlayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * 作者：create by YW
 * 日期：2017.08.14 14:53
 * 描述：
 */
public class DependentBehavior extends CoordinatorLayout.Behavior<View> {

    public DependentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        Log.e("YW", "layoutDependsOn: " + (dependency.getTop() - child.getTop()));
        return child instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.e("YW", "onDependentViewChanged: " + (dependency.getTop() - child.getTop()));
        int offset = dependency.getTop() - child.getTop();
        ViewCompat.offsetTopAndBottom(child, offset);
        return true;
    }

}