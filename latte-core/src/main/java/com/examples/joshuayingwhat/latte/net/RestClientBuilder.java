package com.examples.joshuayingwhat.latte.net;

import android.content.Context;

import com.examples.joshuayingwhat.latte.net.callback.IError;
import com.examples.joshuayingwhat.latte.net.callback.IFailure;
import com.examples.joshuayingwhat.latte.net.callback.IRequest;
import com.examples.joshuayingwhat.latte.net.callback.ISuccess;
import com.examples.joshuayingwhat.latte.ui.LoaderStyle;

import java.io.File;
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
    private Context mContext;
    private LoaderStyle mLoaderStyle;
    private File mFile;
    private String mDownloadDir;
    private String extension;
    private String mName;

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

    public final RestClientBuilder file(File mFile) {
        this.mFile = mFile;
        return this;
    }

    public final RestClientBuilder file(String mFile) {
        this.mFile = new File(mFile);
        return this;
    }

    /***
     * 文件下载
     */
    public final RestClientBuilder dir(String mDownloadDir) {
        this.mDownloadDir = mDownloadDir;
        return this;
    }

    /**
     * 文件后缀名
     *
     * @param extension
     * @return
     */
    public final RestClientBuilder extension(String extension) {
        this.extension = extension;
        return this;
    }

    /**
     * 文件名称
     *
     * @param mName
     * @return
     */
    public final RestClientBuilder name(String mName) {
        this.mName = mName;
        return this;
    }

//    public final RestClientBuilder downloadFile(String mDownloadDir, String extension, String mName) {
//        this.mDownloadDir = mDownloadDir;
//        this.extension = extension;
//        this.mName = mName;
//        return this;
//    }

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

    /**
     * 自定义loader
     *
     * @param context
     * @param loaderStyle
     * @return
     */
    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    /**
     * 默认样式loader
     *
     * @param context
     * @return
     */
    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotateMultipleIndicator;
        return this;
    }

    //对map做处理
//    private Map<String, Object> checkParams() {
//        if (mParams == null) {
//            return new WeakHashMap<>();
//        }
//        return mParams;
//    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIRequest, mIError,
                mIFailure, mISuccess, mRequestBody,
                mContext, mLoaderStyle, mFile,
                mDownloadDir, extension, mName);
    }
}

