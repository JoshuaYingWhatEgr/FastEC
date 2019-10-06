package com.examples.joshuayingwhat.latte.net.interceptors;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author joshuayingwhat
 */
public abstract class BaseInterceptors implements Interceptor {
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        final HttpUrl httpUrl = chain.request().url();
        int size = httpUrl.querySize();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();

        for (int i = 0; i < size; i++) {
            params.put(httpUrl.queryParameterName(i), httpUrl.queryParameterValue(i));
        }
        return params;
    }

    protected String getUrlParamenters(Chain chain, String key) {
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }

    protected LinkedHashMap<String, String> getBodyparameters(Chain chain) {
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        int size = formBody.size();
        for (int i = 0; i < size; i++) {
            params.put(formBody.name(i), formBody.value(i));
        }
        return params;
    }

    protected String getBodyparaments(Chain chain, String key) {
        return getBodyparameters(chain).get(key);
    }
}
