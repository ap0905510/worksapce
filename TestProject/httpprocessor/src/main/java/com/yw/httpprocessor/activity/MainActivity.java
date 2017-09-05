package com.yw.httpprocessor.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yw.httpprocessor.R;
import com.yw.httpprocessor.bean.ExpressBean;
import com.yw.httpprocessor.http.HttpCallback;
import com.yw.httpprocessor.http.HttpHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private Button button;
    //快递接口
    private String url2 = "http://www.kuaidi100.com/query?type=quanfengkuaidi&postid=300008026630";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            Map<String, Object> params = new HashMap<>();
            params.put("Y", "W");
            //访问网络
            HttpHelper.obtainInstance().post(url2, params, new HttpCallback<ExpressBean>() {
                @Override
                public void onSuccess(ExpressBean expressBean) {
                    Log.i("onSuccess: ", expressBean.getData().toString());
                    StringBuffer sb = new StringBuffer();
                    if (expressBean != null) {
                        ArrayList<ExpressBean.DataBean> datas = (ArrayList<ExpressBean.DataBean>) expressBean.getData();
                        for (ExpressBean.DataBean data : datas) {
                            sb.append("时间：")
                                    .append(data.getTime() + "\r\n")
                                    .append("地点和跟踪进度：")
                                    .append(data.getContext() + "\r\n" + "\r\n");
                            textView.setText(sb.toString());
                        }
                    }
                }

                @Override
                public void onFailed(String string) {
                    Toast.makeText(MainActivity.this, "请求失败了。。" + string, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
