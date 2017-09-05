package com.dh.luckypanal;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final double MAX = 10000.0;

    private LuckyPanView mLuckyPanal;
    private ImageView mStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLuckyPanal = (LuckyPanView) findViewById(R.id.id_luckyPanal);
        mStartBtn = (ImageView) findViewById(R.id.id_start_btn);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random = new Random();
                Double d = random.nextDouble();

                if (!mLuckyPanal.isStart()) {
                    d = d * MAX;
                    Log.e("YW", "d = " + d);
                    if (d <= 2) {
                        mLuckyPanal.luckyStart(0);
                    } else if (d > 2 && d <= 10 ) {
                        mLuckyPanal.luckyStart(1);
                    } else if (d > 10 && d <= 500) {
                        mLuckyPanal.luckyStart(3);
                    } else if (d > 500 && d <= 1500) {
                        mLuckyPanal.luckyStart(4);
                    } else if (d > 1500 && d <= 6000) {
                        mLuckyPanal.luckyStart(2);
                    } else {
                        mLuckyPanal.luckyStart(5);
                    }
                    mStartBtn.setImageResource(R.mipmap.stop);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mLuckyPanal.luckyEnd();
                            mStartBtn.setImageResource(R.mipmap.start);
                        }
                    }, 3000);
                } /*else {
                    if (!mLuckyPanal.isShouldEnd()) {
                        mLuckyPanal.luckyEnd();
                        mStartBtn.setImageResource(R.mipmap.start);
                    }
                }*/
            }
        });
    }
}
