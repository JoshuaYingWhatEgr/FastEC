package com.examples.joshuayingwhat.latte.net.callback;

import android.util.Log;

import com.examples.joshuayingwhat.latte.ui.LatteLoader;
import com.examples.joshuayingwhat.latte.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author joshuayingwhat
 */
public class RequestCallBacks implements Callback<String> {
    private final IRequest REQUEST;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final ISuccess SUCCESS;
    private final LoaderStyle LOADER_STYLE;

    public RequestCallBacks(IRequest request, IError error,
                            IFailure ifailure, ISuccess iSuccess,
                            LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.ERROR = error;
        this.FAILURE = ifailure;
        this.SUCCESS = iSuccess;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void stopLoading() {
        if (LOADER_STYLE != null) {
            LatteLoader.stopLoading();
        }
    }
}
