package com.yw.bezizer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 作者：create by YW
 * 日期：2017.06.27 10:24
 * 描述：
 */
public class BezierView extends View {

    private Paint mPaint;
    private Path mPath = new Path();
    private int mRadius = 30;

    private float mStartX;
    private float mStartY;

    private float mDragHeight = 150;
    private float mProgress;

    public BezierView(Context context) {
        super(context);
        init();
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        //初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int iWidth = 2 * mRadius + getPaddingLeft() + getPaddingRight();
        int iHeight = (int) (mDragHeight * mProgress) + getPaddingTop() + getPaddingBottom();

        int measureWidth = 0, measureHeight = 0;

        if (widthMode == MeasureSpec.EXACTLY) { // 精确的
            measureWidth = width;
        } else if (widthMode == MeasureSpec.AT_MOST) { //最多的
            measureWidth = Math.min(iWidth, width);
        } else if (widthMode == MeasureSpec.UNSPECIFIED) { //未知
            measureWidth = iWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) { // 精确的
            measureHeight = height;
        } else if (heightMode == MeasureSpec.AT_MOST) { //最多的
            measureHeight = Math.min(iHeight, height);
        } else if (heightMode == MeasureSpec.UNSPECIFIED) { //未知
            measureHeight = iHeight;
        }

        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartX = getWidth() >> 1;
        mStartY = getHeight() >> 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = mPaint;
        canvas.drawCircle(mStartX, mStartY, mRadius, paint);

        Path path = mPath;
        path.lineTo(100, 100);
        path.rQuadTo(100, 100, 500, 500);
        canvas.drawPath(path, paint);
    }

    public void setProgress(float progress) {
        Log.e("YW", "progress: " + progress);
        this.mProgress = progress;

        //刷新并测量
        requestLayout();
    }
}
