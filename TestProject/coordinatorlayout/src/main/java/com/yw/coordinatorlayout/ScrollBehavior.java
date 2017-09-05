package com.yw.coordinatorlayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 作者：create by YW
 * 日期：2017.08.14 15:32
 * 描述：
 */

public class ScrollBehavior extends CoordinatorLayout.Behavior {

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        Log.e("YW", "onStartNestedScroll: " + nestedScrollAxes);
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child,
                                  View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        Log.e("YW", "onNestedPreScroll: " + dy);
        int leftScrolled = target.getScrollY();
        child.setScrollY(leftScrolled);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child,
                                    View target, float velocityX, float velocityY) {
        ((NestedScrollView) child).fling((int)velocityY);
        return true;
    }
}
