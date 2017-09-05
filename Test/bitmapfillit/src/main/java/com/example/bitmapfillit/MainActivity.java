package com.example.bitmapfillit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv_left_big = (ImageView) findViewById(R.id.iv_left_big);
        ImageView iv_right_big = (ImageView) findViewById(R.id.iv_right_big);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.hxm_app_rep_detail_bg);

        Bitmap left_bmp = BitmapFillet.canvasTriangle(bitmap, 30, 0);
        iv_left_big.setImageBitmap(left_bmp);

        Bitmap right_bmp = BitmapFillet.canvasTriangle(bitmap, 30, 1);
        iv_right_big.setImageBitmap(right_bmp);
    }
}