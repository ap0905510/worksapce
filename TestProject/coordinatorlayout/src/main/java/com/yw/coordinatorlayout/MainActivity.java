package com.yw.coordinatorlayout;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);

//        TextView dependence = (TextView) findViewById(R.id.dependence);
//        dependence.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ViewCompat.offsetTopAndBottom(view, 20);
//            }
//        });
    }
}
