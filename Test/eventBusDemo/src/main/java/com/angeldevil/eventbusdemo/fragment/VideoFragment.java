package com.angeldevil.eventbusdemo.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.angeldevil.eventbusdemo.R;

/**
 * 作者：create by YW
 * 日期：2016.11.21 15:18
 * 描述：
 */

public class VideoFragment extends Fragment {

    public static VideoFragment newInstance() {
        Bundle bundle = new Bundle();
        VideoFragment pageFragment = new VideoFragment();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
