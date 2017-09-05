package com.yw.progressbarview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：create by YW
 * 日期：2017.02.05 18:00
 * 描述：
 */

public class DrawPicture extends View {


    public DrawPicture(Context context) {
        this(context, null);

        recording();    // 调用录制
    }

    public DrawPicture(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 1.创建Picture
    private Picture mPicture = new Picture();

    // 2.录制内容方法
    private void recording() {
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);
        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // 在Canvas中具体操作
        // 位移
        canvas.translate(250, 250);
        // 绘制一个圆
        canvas.drawCircle(0, 0, 100, paint);

        mPicture.endRecording();

        mPicture.draw(canvas);
    }
}
