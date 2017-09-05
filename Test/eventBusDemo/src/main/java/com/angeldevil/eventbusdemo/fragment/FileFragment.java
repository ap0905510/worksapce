package com.angeldevil.eventbusdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angeldevil.eventbusdemo.R;

/**
 * 作者：create by YW
 * 日期：2016.11.21 15:18
 * 描述：
 */

public class FileFragment extends Fragment {

    public static FileFragment newInstance() {
        Bundle bundle = new Bundle();
        FileFragment pageFragment = new FileFragment();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
