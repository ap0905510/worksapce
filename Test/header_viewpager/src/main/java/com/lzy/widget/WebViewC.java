package com.lzy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

/**
 * 作者：create by YW
 * 日期：2017.05.03 17:43
 * 描述：
 */

public class WebViewC extends WebView {

    private ScrollInterface mScrollInterface;

    public WebViewC(Context context) {
        this(context, null);
    }

    public WebViewC(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(event);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {

        super.onScrollChanged(l, t, oldl, oldt);

        if (mScrollInterface != null) {
            mScrollInterface.onSChanged(l, t, oldl, oldt);
        }

    }

    public void setOnCustomScrollChangeListener(ScrollInterface scrollInterface) {

        this.mScrollInterface = scrollInterface;

    }

    public interface ScrollInterface {
        void onSChanged(int l, int t, int oldl, int oldt);
    }

}
