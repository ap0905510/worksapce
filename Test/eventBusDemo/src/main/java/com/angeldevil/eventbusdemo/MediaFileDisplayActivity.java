package com.angeldevil.eventbusdemo;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.angeldevil.eventbusdemo.fragment.AudioFragment;
import com.angeldevil.eventbusdemo.fragment.FileFragment;
import com.angeldevil.eventbusdemo.fragment.PictureFragment;
import com.angeldevil.eventbusdemo.fragment.VideoFragment;

/**
 * 作者：create by YW
 * 日期：2016.11.21 13:44
 * 描述：
 */

public class MediaFileDisplayActivity extends FragmentActivity {

    private static String PICTURE_FRAGMENT_TAG = "picture_fragment_tag";
    private static String VIDEO_FRAGMENT_TAG = "video_fragment_tag";
    private static String AUDIO_FRAGMENT_TAG = "audio_fragment_tag";
    private static String FILE_FRAGMENT_TAG = "file_fragment_tag";

    RadioButton rb_pic;
    RadioButton rb_audio;
    RadioButton rb_video;
    RadioButton rb_file;
    RadioGroup rg_parent;
    TextView tv_line;
    FrameLayout fl_media_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_dispay);

        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {

        rg_parent = (RadioGroup) findViewById(R.id.rg_parent);

        rb_pic = (RadioButton) findViewById(R.id.rb_pic);
        rb_audio = (RadioButton) findViewById(R.id.rb_audio);
        rb_video = (RadioButton) findViewById(R.id.rb_video);
        rb_file = (RadioButton) findViewById(R.id.rb_file);

        tv_line = (TextView) findViewById(R.id.tv_line);
        fl_media_container = (FrameLayout) findViewById(R.id.fl_media_container);

        initFragmentTrans();

        initTabLineWidth();

        setClickListener();
    }

    private void initFragmentTrans() {
        FragmentTransaction mTransaction = getFragmentManager().beginTransaction();
        mTransaction.replace(R.id.fl_media_container, PictureFragment.newInstance(), PICTURE_FRAGMENT_TAG).commit();
    }

    private void setClickListener() {
        rg_parent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_pic:
                        Toast.makeText(MediaFileDisplayActivity.this, "图片资源", Toast.LENGTH_SHORT).show();
                        moveToPos(0);
                        FragmentTransaction mTransaction1 = getFragmentManager().beginTransaction();
                        mTransaction1.replace(R.id.fl_media_container, PictureFragment.newInstance(), PICTURE_FRAGMENT_TAG).commit();
                        break;
                    case R.id.rb_audio:
                        Toast.makeText(MediaFileDisplayActivity.this, "语音资源", Toast.LENGTH_SHORT).show();
                        moveToPos(1);
                        FragmentTransaction mTransaction2 = getFragmentManager().beginTransaction();
                        mTransaction2.replace(R.id.fl_media_container, AudioFragment.newInstance(), AUDIO_FRAGMENT_TAG).commit();
                        break;
                    case R.id.rb_video:
                        Toast.makeText(MediaFileDisplayActivity.this, "视频资源", Toast.LENGTH_SHORT).show();
                        moveToPos(2);
                        FragmentTransaction mTransaction3 = getFragmentManager().beginTransaction();
                        mTransaction3.replace(R.id.fl_media_container, VideoFragment.newInstance(), VIDEO_FRAGMENT_TAG).commit();
                        break;
                    case R.id.rb_file:
                        Toast.makeText(MediaFileDisplayActivity.this, "文件资源", Toast.LENGTH_SHORT).show();
                        moveToPos(3);
                        FragmentTransaction mTransaction4 = getFragmentManager().beginTransaction();
                        mTransaction4.replace(R.id.fl_media_container, FileFragment.newInstance(), FILE_FRAGMENT_TAG).commit();
                        break;
                }
            }
        });
    }

    private int mLineWidth;//线宽
    private int leftWidth;//左偏

    private void initTabLineWidth() {
        Display display = this.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mLineWidth = outMetrics.widthPixels / 4;
        android.view.ViewGroup.LayoutParams lp = tv_line.getLayoutParams();
        lp.width = mLineWidth;
    }

    private void moveToPos(int position) {

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tv_line.getLayoutParams();
        /*
        if (position == 0) {//0->1
            leftWidth = (int) (mLineWidth * position);
            lp.leftMargin = leftWidth;
        }
        if (position == 1) {//1->0
            lp.leftMargin = (int) (mLineWidth * position);
        }
        if (position == 2) {// 1->2
            lp.leftMargin = (int) (mLineWidth * position);
        }
        if (position == 3) {// 2->3
            lp.leftMargin = (int) (mLineWidth * position);
        }*/

        leftWidth = (int) (mLineWidth * position);
        lp.leftMargin = leftWidth;
        tv_line.setLayoutParams(lp);
    }

}

