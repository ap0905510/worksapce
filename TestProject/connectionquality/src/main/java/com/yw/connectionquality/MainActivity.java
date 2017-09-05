package com.yw.connectionquality;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.facebook.network.connectionclass.DeviceBandwidthSampler;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button connect;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connect = (Button) findViewById(R.id.connect);
        tv = (TextView) findViewById(R.id.tv);

        setClickListener();

        DeviceBandwidthSampler.getInstance().startSampling();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ConnectionQuality connectionQuality = ConnectionClassManager.getInstance().getCurrentBandwidthQuality();
                        double downloadKBitsPerSecond = ConnectionClassManager.getInstance().getDownloadKBitsPerSecond();
                        tv.setText("connectionQuality:" + connectionQuality + "\n" + "downloadKBitsPerSecond:" + downloadKBitsPerSecond + " kb/s");
                    }
                });
            }
        }, 1000, 1000);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ConnectionClassManager.getInstance().register(listener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ConnectionClassManager.getInstance().remove(listener);
    }

    private void setClickListener() {
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url("http://test2.jweb.huxin.biz/jhuxin-openapi/op/represent?v=5").build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        DeviceBandwidthSampler.getInstance().stopSampling();
                        Log.e("TAG", "onFailure:" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        DeviceBandwidthSampler.getInstance().stopSampling();
//                        final ConnectionQuality connectionQuality = ConnectionClassManager.getInstance().getCurrentBandwidthQuality();
//                        final double downloadKBitsPerSecond = ConnectionClassManager.getInstance().getDownloadKBitsPerSecond();
//                        Log.e("TAG", "connectionQuality:" + connectionQuality + " downloadKBitsPerSecond:" + downloadKBitsPerSecond + " kb/s");

                        listener.onBandwidthStateChange(null);
                    }
                });
            }
        });
    }

    ConnectionChangedListener listener = new ConnectionChangedListener();

    private class ConnectionChangedListener implements ConnectionClassManager.ConnectionClassStateChangeListener {
        @Override
        public void onBandwidthStateChange(final ConnectionQuality bandwidthState) {
            final ConnectionQuality connectionQuality = ConnectionClassManager.getInstance().getCurrentBandwidthQuality();
            final double downloadKBitsPerSecond = ConnectionClassManager.getInstance().getDownloadKBitsPerSecond();
            Log.e("TAG", "onBandwidthStateChange:" + connectionQuality + " downloadKBitsPerSecond:" + downloadKBitsPerSecond + " kb/s");

            tv.post(new Runnable() {
                @Override
                public void run() {
                    tv.setText("onBandwidthStateChange:" + connectionQuality + "\n" + "downloadKBitsPerSecond:" + downloadKBitsPerSecond + " kb/s");
                }
            });
        }
    }
}