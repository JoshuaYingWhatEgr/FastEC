package com.examples.joshuayingwhat.latte.net;

import com.examples.joshuayingwhat.latte.net.callback.IError;
import com.examples.joshuayingwhat.latte.net.callback.IFailure;
import com.examples.joshuayingwhat.latte.net.callback.IRequest;
import com.examples.joshuayingwhat.latte.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {

    private String mUrl;
    private static Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest;
    private IError mIError;
    private IFailure mIFailure;
    private ISuccess mISuccess;
    private RequestBody mRequestBody;

    public RestClientBuilder() {
    }

    public final RestClientBuilder url(String mUrl) {
        this.mUrl = mUrl;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> mParams) {
        PARAMS.putAll(mParams);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest mIRequest) {
        this.mIRequest = mIRequest;
        return this;
    }

    //传入原始数据
    public final RestClientBuilder raw(String raw) {
        this.mRequestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    //对map做处理
//    private Map<String, Object> checkParams() {
//        if (mParams == null) {
//            return new WeakHashMap<>();
//        }
//        return mParams;
//    }

    public final RestClient builder() {
        return new RestClient(mUrl, PARAMS, mIRequest, mIError, mIFailure, mISuccess, mRequestBody);
    }
}

