package com.yw.animatorpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yw on 2017/9/8.
 */

public class CustomView extends View {

    Paint mPaint;
    Path mPath;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        mPaint = new Paint();

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(7);

        mPath.moveTo(100, 100);
        mPath.cubicTo(200, 200, 300, 0, 400, 100); //三阶贝塞尔曲线
        mPath.lineTo(500, 500); //直线

        canvas.drawPath(mPath, mPaint);
    }
}
