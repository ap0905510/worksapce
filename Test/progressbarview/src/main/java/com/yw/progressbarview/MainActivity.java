package com.yw.progressbarview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yw.progressbarview.view.CheckView;
import com.yw.progressbarview.view.DrawPicture;
import com.yw.progressbarview.view.RoundProgressBarWithProgress;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RoundProgressBarWithProgress circleProgress = (RoundProgressBarWithProgress) findViewById(R.id.circle_progress_bar);
        DrawPicture drawPicture = (DrawPicture) findViewById(R.id.pic);


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                count ++ ;
                circleProgress.setProgress(count);
            }
        }, 1000, 1000);

        CheckView checkView = (CheckView) findViewById(R.id.check);

        checkView.check();
        checkView.unCheck();
    }
}
