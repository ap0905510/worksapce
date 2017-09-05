package com.yw.bezizer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：create by YW
 * 日期：2017.06.27 13:42
 * 描述：
 */

public class Bezier3 extends View {

    public Bezier3(Context context) {
        super(context);
    }

    public Bezier3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);

        Path path = new Path();
        //path.rQuadTo(100, 100, 300, 300);
        path.rCubicTo(200, 200, 500, 500, 300, 100);
        canvas.drawPath(path, paint);
    }
}
