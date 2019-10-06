package com.examples.joshuayingwhat.latte.app;

import android.content.Context;

import java.util.ArrayList;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * @author joshuayingwhat
 * @date 2018/12/18
 */

public final class Latte {
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.name(), context);
        return Configurator.getInstance();
    }

    public static WeakHashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getContext() {
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT.name());
    }
}
