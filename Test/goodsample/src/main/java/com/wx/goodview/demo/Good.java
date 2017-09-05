package com.wx.goodview.demo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wx.goodview.IGoodView;

/**
 * 作者：create by YW
 * 日期：2017.05.10 11:24
 * 描述：
 */

public class Good extends PopupWindow implements IGoodView {

    private float mFromYDelta = FROM_Y_DELTA;
    private float mToYDelta = TO_Y_DELTA;

    private Context mContext;
    private TextView mGoodView;
    private AnimationSet animationSet;
    private boolean isChanged = false;

    public Good(Context context) {
        mContext = context;
        initView();
    }

    private void initView() {
        RelativeLayout rl = new RelativeLayout(mContext);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rl.setLayoutParams(params);
        setContentView(rl);

        mGoodView = new TextView(mContext);
        mGoodView.setLayoutParams(params);
        mGoodView.setTextColor(TEXT_COLOR);
        mGoodView.setTextSize(TEXT_SIZE);
        rl.addView(mGoodView);

        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mGoodView.measure(width, height);

        setWidth(mGoodView.getMeasuredWidth());
        setHeight(DISTANCE + mGoodView.getMeasuredHeight());
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setFocusable(false);
        setTouchable(false);
        setOutsideTouchable(false);

        animationSet = multiAnim();

    }

    public void setText(String text) {

        if (TextUtils.isEmpty(text)) {
            throw new NullPointerException();
        }

        mToYDelta = 300;

        mGoodView.setText(text);
        mGoodView.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        int w = (int) mGoodView.getPaint().measureText(text);
        setWidth(w);
        setHeight(DISTANCE + getMeasureHeight(mGoodView, w));

    }

    private int getMeasureHeight(View v, int width) {
        int w = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.AT_MOST);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
        return mGoodView.getMeasuredHeight();

    }

    public void show(View v) {
        if (!isShowing()) {
            int offsetY = -v.getHeight() - getHeight();
            showAsDropDown(v, v.getWidth() / 2 - getWidth() / 2, offsetY);
//            showAsDropDown(v, v.getWidth() / 2, -v.getHeight());
            if (animationSet == null || isChanged) {
                animationSet = multiAnim();
                isChanged = false;
            }
            mGoodView.startAnimation(animationSet);
        }
    }

    private AnimationSet multiAnim() {
        AnimationSet set = new AnimationSet(true);
        TranslateAnimation transAnim = new TranslateAnimation(0, 0, mFromYDelta, -mToYDelta);
        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.0f);
        set.addAnimation(transAnim);
        set.addAnimation(alphaAnim);
        set.setDuration(1000);
        set.setRepeatCount(1);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isShowing()) {
                    dismiss();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return set;
    }

}
