package com.youmai.okhttp3.response;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.youmai.okhttp3.exception.OkHttpException;
import com.youmai.okhttp3.listener.DisposeDataHandle;
import com.youmai.okhttp3.listener.DisposeDownloadListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 作者：create by YW
 * 日期：2016.10.25 10:27
 * 描述：专处理文件下载回调
 */
public class CommonFileCallback implements Callback {

    /**
     * the java layer exception, do not same to the logic error
     */
    protected static final int NETWORK_ERROR = -1;
    protected static final int IO_ERROR = -2;
    protected static final String EMPTY_MSG = "";

    /**
     * 将非UI线程的数据发到UI线程
     */
    private static final int PROGRESS_MSG = 0x01;
    private Handler mHandler;
    private DisposeDownloadListener mListener;
    private String mFilePath;
    private int mProgress;

    public CommonFileCallback(DisposeDataHandle handle) {
        this.mListener = (DisposeDownloadListener) handle.getListener();
        this.mFilePath = handle.getSource();

        this.mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case PROGRESS_MSG://刷新下载进度条
                        mListener.onProgress((int) msg.obj);
                        break;
                }
            }
        };
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, e));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final File file = handleResponse(response);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (null != file) {
                    mListener.onSuccessDownload(file);
                } else {
                    mListener.onFailure(new OkHttpException(IO_ERROR, EMPTY_MSG));
                }
            }
        });
    }

    /**
     * 在子线程中，不用调用回调接口
     *
     * @param response
     * @return
     */
    private File handleResponse(Response response) {
        if (null == response) {
            return null;
        }
        File file = null;
        InputStream is = null;
        FileOutputStream fos = null;
        byte[] buffer = new byte[2 * 1024];
        int len = -1;
        int currentLength = 0;
        double sumLength = 0;
        try {
            file = new File(mFilePath);
            fos = new FileOutputStream(file);
            is = response.body().byteStream();//okHttp
            sumLength = (double) response.body().contentLength();

            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                currentLength += len;
                mProgress = (int) (currentLength / sumLength * 100);
                mHandler.obtainMessage(PROGRESS_MSG, mProgress).sendToTarget();
            }
            fos.flush();

        } catch (FileNotFoundException e) {
            if(null != file) {
                file.delete();
                file = null;
            }
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
                if (null != fos) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

}
