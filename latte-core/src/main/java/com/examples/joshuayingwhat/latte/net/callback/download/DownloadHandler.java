package com.examples.joshuayingwhat.latte.net.callback.download;

import android.os.AsyncTask;

import com.examples.joshuayingwhat.latte.net.RestCreator;
import com.examples.joshuayingwhat.latte.net.callback.IError;
import com.examples.joshuayingwhat.latte.net.callback.IFailure;
import com.examples.joshuayingwhat.latte.net.callback.IRequest;
import com.examples.joshuayingwhat.latte.net.callback.ISuccess;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadHandler {

    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final ISuccess SUCCESS;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHandler(String url, IRequest request, IError error,
                           IFailure failure, ISuccess success,
                           String downloadDir, String extension, String name) {
        this.URL = url;
        this.REQUEST = request;
        this.ERROR = error;
        this.FAILURE = failure;
        this.SUCCESS = success;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public void handlerDownlaod() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final SaveFileTask saveFileTask = new SaveFileTask(REQUEST, SUCCESS);
                            saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR,
                                    EXTENSION, response.body(), NAME);

                            /**
                             * 这里的判断主要是防止文件下载不全
                             */
                            if (saveFileTask.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (ERROR != null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
