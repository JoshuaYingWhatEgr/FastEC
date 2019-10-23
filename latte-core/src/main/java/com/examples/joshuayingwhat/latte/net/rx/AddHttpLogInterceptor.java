package com.examples.joshuayingwhat.latte.net.rx;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

public class AddHttpLogInterceptor {

    public static HttpLoggingInterceptor getInstance() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("fastec_net_work", message);
            }
        });
    }
}
