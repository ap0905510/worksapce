package com.wingsofts.custombehavior;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wingsofts.custombehavior.handler.Handler;
import com.wingsofts.custombehavior.handler.Looper;
import com.wingsofts.custombehavior.handler.Message;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        findViewById(R.id.iv_avatar).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //startActivity(new Intent(MainActivity.this, EasyBehaviorActivity.class));
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.e("YW", "click");
//
//                        Looper.prepare();
//                        Handler handler = new Handler(Looper.myLooper());
//                        handler.sendMessage(new Message("1"));
//                        Looper.loop();
//                    }
//                }).start();
//            }
//        });

    }


    public void titleMode(View v) {
//        findViewById(R.id.iv_avatar).setVisibility(View.INVISIBLE);
//        findViewById(R.id.tv_title).setVisibility(View.VISIBLE);
    }

//    public void avatarMode(View v) {
//
//        findViewById(R.id.iv_avatar).setVisibility(View.VISIBLE);
//        findViewById(R.id.tv_title).setVisibility(View.INVISIBLE);
//    }
}
