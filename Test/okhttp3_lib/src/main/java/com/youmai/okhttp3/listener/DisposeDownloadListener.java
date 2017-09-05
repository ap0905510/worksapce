package com.youmai.okhttp3.listener;

import java.io.File;

/**
 * 作者：create by YW
 * 日期：2016.10.25 10:17
 * 描述：监听下载进度
 */
public interface DisposeDownloadListener extends DisposeDataListener{
    public void onProgress(int progress);
    public void onSuccessDownload(File file);
}
