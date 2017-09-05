package com.wingsofts.custombehavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 作者：create by YW
 * 日期：2017.08.15 09:38
 * 描述：
 */

public class ToolbarBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    private int mStartY;

    public ToolbarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return child instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Toolbar child, View dependency) {

        if (mStartY <= 0) {
            mStartY = (int) dependency.getY();
        }

        Log.e("YW", "dependency.getY(): " + dependency.getY());
        if (Math.abs(dependency.getY()) < 100 && dependency.getY() != 0) {
            Log.e("YW", "dependency: " + dependency.getY());
            return true;
        }
        float percent = dependency.getY() / mStartY;

        child.setAlpha(percent);

        return true;
    }
}
