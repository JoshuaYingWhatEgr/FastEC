package com.examples.joshuayingwhat.fastec;

import android.app.Application;

import com.examples.joshuayingwhat.latte.app.Latte;
import com.examples.joshuayingwhat.latte.net.interceptors.DebugInterceptors;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptors("index", R.raw.test)).configure();
    }
}
