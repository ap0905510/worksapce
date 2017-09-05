package com.yw.httpprocessor.processor;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yw.httpprocessor.interfaces.ICallBack;
import com.yw.httpprocessor.interfaces.IHttpProcessor;

import java.util.Map;


public class VolleyProcessor implements IHttpProcessor {

    public static final String TAG ="VolleyProcessor";

    private static RequestQueue mQueue = null;

    public VolleyProcessor(Context context){
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(String url, Map<String, Object> params, final ICallBack callback) {
        Log.e("YW", "Volley");
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callback.onFailed(volleyError.toString());
            }
        });
        mQueue.add(stringRequest);
    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                callback.onFailed(volleyError.toString());
            }
        });
        mQueue.add(stringRequest);
    }
}
