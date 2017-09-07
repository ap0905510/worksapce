package com.yw.animatorpath;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    public final static float SCALE_FACTOR = 13f;
    public final static int ANIMATION_DURATION = 900;
    public final static int MINIMUN_X_DISTANCE = 200;

    private boolean mRevealFlag;
    private float mFabSize = 56f;

    private View mFab;
    FrameLayout mFabContainer;
    LinearLayout mControlsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        bindViews();
    }

    private void bindViews() {
        mFab = findViewById(R.id.fab);
        mFabContainer = (FrameLayout) findViewById(R.id.fab_container);
        mControlsContainer = (LinearLayout) findViewById(R.id.media_controls_container);
    }

    public void onFabPressed(View v) {
        final float startX = mFab.getX();
        AnimatorPath mPath = new AnimatorPath();
        mPath.moveTo(0, 0);
        mPath.cubicTo(-200, 200, -400, 100, -600, 50); //相对坐标

        mPath.lineTo(-800, 200);
        mPath.lineTo(0, 200);

        //属性动画认知：本质是控制一个对象身上的任何属性值 反射setTranslation(xxx)  setAlpha(0, 5);
        ObjectAnimator animator = ObjectAnimator.ofObject(this, "haha",
                new PathEvaluator(), mPath.mPoints.toArray()); //数组
        animator.setDuration(ANIMATION_DURATION);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (Math.abs(startX - mFab.getX()) > MINIMUN_X_DISTANCE) {

                }
            }
        });
    }

    public void setHaha(PathPoint p) {
        //控制属性动画变化
        mFab.setTranslationX(p.getX());
        mFab.setTranslationY(p.getY());
    }
}
