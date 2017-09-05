package com.angeldevil.eventbusdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends Activity {
	
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		EventBus.getDefault().register(this);

        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EventBus.getDefault().post("hello world!");
				finish();
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
		finish();
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEventMainThread(String text) {
		Log.e("mm", "onEventMainThread = " + text);
		tv.setText(text);
	}

}
