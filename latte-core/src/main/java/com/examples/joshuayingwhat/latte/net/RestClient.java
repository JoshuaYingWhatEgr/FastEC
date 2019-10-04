package com.examples.joshuayingwhat.latte.net;

import com.examples.joshuayingwhat.latte.net.callback.IError;
import com.examples.joshuayingwhat.latte.net.callback.IFailure;
import com.examples.joshuayingwhat.latte.net.callback.IRequest;
import com.examples.joshuayingwhat.latte.net.callback.ISuccess;
import com.examples.joshuayingwhat.latte.net.callback.RequestCallBacks;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RestClient {

    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final ISuccess SUCCESS;
    private final RequestBody REQUEST_BODY;

    public RestClient(String url, Map<String, Object> params, IRequest request,
                      IError error, IFailure failure,
                      ISuccess success, RequestBody requestBody) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.ERROR = error;
        this.FAILURE = failure;
        this.SUCCESS = success;
        this.REQUEST_BODY = requestBody;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    private void request(HttpMethod method) {
        final RestService restService = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = restService.get(URL, PARAMS);
                break;
            case POST:
                call = restService.post(URL, PARAMS);
                break;
            case PUT:
                call = restService.put(URL, PARAMS);
                break;
            case DELETE:
                call = restService.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallBack());
        }
    }

    public Callback<String> getRequestCallBack() {
        return new RequestCallBacks(REQUEST, ERROR, FAILURE, SUCCESS);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
