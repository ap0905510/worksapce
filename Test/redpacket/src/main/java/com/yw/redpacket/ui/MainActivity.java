package com.yw.redpacket.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunzhanghu.redpacketui.utils.RPRedPacketUtil;
import com.yw.redpacket.adapter.RecyclerAdapter;
import com.yw.redpacket.utils.CircleTransform;
import com.yw.redpacket.utils.GridItemDecoration;
import com.yw.redpacket.utils.PreferenceUtil;
import com.yw.redpacket.utils.RecyclerItemClickListener;
import com.yw.redpacket.utils.RedPacketUtil;

import com.yw.redpacket.R;

import static com.yw.redpacket.DemoApplication.sCurrentNickname;
import static com.yw.redpacket.DemoApplication.sCurrentAvatarUrl;

public class MainActivity extends FragmentActivity implements View.OnClickListener, RecyclerItemClickListener.OnItemClickListener {

    private TextView mTvCurrentUsername;

    private ImageView mIvCurrentUserAvatar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TextUtils.isEmpty(PreferenceUtil.getInstance().getSenderName()) || TextUtils.isEmpty(PreferenceUtil.getInstance().getReceiverName())) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            RedPacketUtil.initUserInfo();
        }
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_clear_user_cache).setOnClickListener(this);
        mTvCurrentUsername = (TextView) findViewById(R.id.tv_current_user_nickname);
        mIvCurrentUserAvatar = (ImageView) findViewById(R.id.iv_current_user_avatar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new GridItemDecoration(this));
        recyclerView.setAdapter(new RecyclerAdapter());
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTvCurrentUsername.setText(sCurrentNickname);
        Glide.with(this).load(sCurrentAvatarUrl).placeholder(R.drawable.default_avatar).error(R.drawable.default_avatar).transform(new CircleTransform(this)).into(mIvCurrentUserAvatar);
    }

    @Override
    public void onClick(View view) {
        clearCache();
    }

    private void clearCache() {
        PreferenceUtil.getInstance().setSenderName(null);
        PreferenceUtil.getInstance().setReceiverName(null);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


    @Override
    public void onItemClick(View view, int position) {
        int fromFlag;
        Intent intent = null;
        switch (position) {
            case 0:
                fromFlag = 0;
                intent = new Intent(this, DetailActivity.class);
                intent.putExtra("from", fromFlag);
                break;
            case 1:
                fromFlag = 1;
                intent = new Intent(this, DetailActivity.class);
                intent.putExtra("from", fromFlag);
                break;
            case 2:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.system)
                        .setMessage(getString(R.string.msg_system_red_packet))
                        .setPositiveButton(R.string.btn_str_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
                break;
            case 3:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.ad)
                        .setMessage(getString(R.string.msg_ad_red_packet))
                        .setPositiveButton(R.string.btn_str_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
                break;
            case 4:
                RPRedPacketUtil.getInstance().startChangeActivity(this);
                break;
            case 5:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.contact_us)
                        .setMessage(getString(R.string.msg_contact_us))
                        .setPositiveButton(R.string.btn_str_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }

    }

}
