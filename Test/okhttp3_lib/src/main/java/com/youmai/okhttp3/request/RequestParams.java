package com.youmai.okhttp3.request;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/10/21.
 */
public class RequestParams {

    public ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, Object> fileParams = new ConcurrentHashMap<>();

    /**
     * constructs a new empty {@code RequestParams} instance
     */
    public RequestParams() {
        this((Map<String, String>) null);
    }

    /**
     * constructs for map key and value
     *
     * @param source
     */
    public RequestParams(Map<String, String> source) {
        if (null != source) {
            for (Map.Entry<String, String> entry : source.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public RequestParams(final String key, final String value) {
        this(new HashMap<String, String>() {
            {
                put(key, value);
            }
        });
    }

    /**
     * Adds a key/value string pair to the request.
     *
     * @param key the key name for the new param.
     * @param value the value string for the new param.
     *
     */
    private void put(String key, String value) {
        if (null != key && null != value) {
            urlParams.put(key, value);
        }
    }

    public void put(String key, Object object) throws FileNotFoundException {
        if (null != key) {
            fileParams.put(key, object);
        }
    }
}
