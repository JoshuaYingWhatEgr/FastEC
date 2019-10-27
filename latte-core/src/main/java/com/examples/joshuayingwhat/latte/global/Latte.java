package com.examples.joshuayingwhat.latte.global;

import android.content.Context;

import java.util.WeakHashMap;

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

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }
}
