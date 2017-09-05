package com.yw.connectionquality;

import android.util.Log;

import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionQuality;

/**
 * 作者：create by YW
 * 日期：2017.07.05 11:32
 * 描述：
 */
public class ConnectionChangedListener implements
        ConnectionClassManager.ConnectionClassStateChangeListener {
    @Override
    public void onBandwidthStateChange(ConnectionQuality bandwidthState) {
        Log.e("onBandwidthStateChange", bandwidthState.toString());
    }
}
