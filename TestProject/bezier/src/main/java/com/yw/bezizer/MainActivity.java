package com.yw.bezizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.yw.bezizer.view.BezierView;

public class MainActivity extends AppCompatActivity {

    private float mTouchMoveStartY;
    private static final float TOUCH_MOVE_MAX = 600;

    BezierView bezierView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bezierView = (BezierView) findViewById(R.id.bezier);

        findViewById(R.id.parent).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        mTouchMoveStartY = event.getY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        float y = event.getY();
                        if (y >= mTouchMoveStartY) {
                            float moveSize = y - mTouchMoveStartY;
                            float progress = moveSize >= TOUCH_MOVE_MAX ?
                                    1 : moveSize / TOUCH_MOVE_MAX;
                            bezierView.setProgress(progress);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        bezierView.setProgress(0);
                        return true;
                }
                return true;
            }
        });
    }
}
