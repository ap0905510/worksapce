package com.youmai.okhttp3.response;

import android.os.Handler;
import android.os.Looper;

import com.youmai.okhttp3.exception.OkHttpException;
import com.youmai.okhttp3.listener.DisposeCookieListener;
import com.youmai.okhttp3.listener.DisposeDataHandle;
import com.youmai.okhttp3.listener.DisposeDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/21.
 * Json请求回调
 */
public class CommonJsonCallback implements Callback {

    /**
     * the logic layer exception, may alter in different app
     */
    protected final String RESULT_CODE = "ecode"; // 本公司:"s"
    protected final int RESULT_CODE_VALUE = 0; // 本公司：1 -> 成功
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it
    // can has the value of
    // set-cookie2

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OTHER_ERROR = -3;

    private DisposeDataListener mListener;
    private Class<?> mClazz;
    private Handler mDelieverHandler;

    public CommonJsonCallback(DisposeDataHandle handle) {
        mListener = handle.mListener;
        mClazz = handle.mClazz;
        mDelieverHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mDelieverHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(e.toString());
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        //在子线程中操作的
        final String result = response.body().string();
        final ArrayList<String> headerCookies = handleCookie(response.headers());
        mDelieverHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onSuccess(result);

                /**
                 * handle the cookie
                 */
                if (mListener instanceof DisposeCookieListener) {
                    ((DisposeCookieListener) mListener).onCookie(headerCookies);
                }
            }
        });
    }

    /**
     * 处理cookie
     *
     * @param headers
     * @return
     */
    private ArrayList<String> handleCookie(Headers headers) {
        ArrayList<String> tempList = new ArrayList<>();
        for (int i = 0; i < headers.size(); i++) {
            if (headers.value(i).equalsIgnoreCase(COOKIE_STORE)) {
                tempList.add(headers.value(i));
            }
        }
        return tempList;
    }

    //暂时不使用 -- 放到调用者来实现
    public void handleResponse(String result) {
        if (null == result || "".equals(result)) { //网络异常
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        try {
            JSONObject resultObj = new JSONObject(result);
            if (resultObj.has(RESULT_CODE)) { // --> s
                if (resultObj.optInt(RESULT_CODE) == RESULT_CODE_VALUE) { // s = 1
                    // 有传入对象
                    if (null == mClazz) {
                        mListener.onSuccess(result);
                    } else {
                        // Gson 解析
                        //Object obj = (Object)Gson.formJson(result, mClazz);
                        //mListener.onSuccess(obj);//走到此处呢，我们的响应真正处理成功了
                    }
                } else { // --> s = -1 根据业务处理
                    mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                }
            }
        } catch (JSONException e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }
    }
}
