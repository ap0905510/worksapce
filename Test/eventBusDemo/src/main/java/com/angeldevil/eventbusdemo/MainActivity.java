
package com.angeldevil.eventbusdemo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

public class MainActivity extends FragmentActivity {

    public static final String TAG = "angeldevil";

    TextView tv, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("mm", "-----------------------");

        tv = (TextView) findViewById(R.id.tv);
        tv2 = (TextView) findViewById(R.id.tv2);

        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        ImageView iv_media_file = (ImageView) findViewById(R.id.iv_media_file);
        iv_media_file.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MediaFileDisplayActivity.class);
                startActivity(intent);
            }
        });

        EventBus.getDefault().register(this);
    }

    /**
     * List点击时会发送些事件，接收到事件后更新详情
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEventMainThread(String text) {
        tv2.setText(text);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String text) {
        tv.setText("llalalaa");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void jumpPic(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "选择图片"), 0x01);
    }

    public void jumpVideo(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("video/*");
        Intent wrapperIntent = Intent.createChooser(intent, null);
        startActivityForResult(wrapperIntent, 0x02);
    }

    public void jumpAudio(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("audio/*");
        startActivityForResult(intent, 0x03);
    }

    public void jumpWord(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, 0x04);
    }

    public void jumpPdf(View view) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File("file:///android_asset/helphelp.pdf"));
        intent.setDataAndType (uri, "application/pdf");
        this.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        try {
            Uri uriPhoto = data.getData();
            Log.e("xx", uriPhoto.getPath());
        } catch (Exception e) { }

        switch (requestCode) {
            case 0x01:
                Uri uriPhoto = data.getData();
                ImageView iv_img = (ImageView) findViewById(R.id.iv_img);
                if(uriPhoto.toString().contains("image")) {
                    iv_img.setImageURI(uriPhoto);
                }
                break;
            case 0x02:
                Uri uriVideo = data.getData();
                if(uriVideo.toString().contains("video")) {
                    VideoView vv_video = (VideoView) findViewById(R.id.vv_video);
                    vv_video.setVideoPath(uriVideo.toString());
                    vv_video.start();
                    vv_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();
                            mediaPlayer.setLooping(true);
                        }
                    });

                    vv_video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                            return true;
                        }
                    });
                }
                break;
            case 0x03:
                Uri uriAudio = data.getData();
                if (uriAudio.toString().contains("audio") || uriAudio.toString().contains("music")) {
                    TextView tv_audio = (TextView) findViewById(R.id.tv_audio);
                    tv_audio.setText(uriAudio.toString());
                }
                break;
            case 0x04:
                Uri uriText = data.getData();
                TextView tv_text = (TextView) findViewById(R.id.tv_text);
                tv_text.setText(uriText.toString());
                break;
        }

    }
}
