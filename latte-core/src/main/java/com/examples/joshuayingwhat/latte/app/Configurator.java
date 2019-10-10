package com.examples.joshuayingwhat.latte.app;

import android.app.Activity;
import android.content.Context;
import android.os.IInterface;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * 配置文件的存储和获取
 *
 * @author joshuayingwhat
 * @date 2018/12/18
 */

public class Configurator {

    private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();
    /**
     * 存储iconify的空间
     */
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        //初始化配置  首先设置初始化配置未完成
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
    }

    /**
     * 静态内部类的初始化
     */
    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    final WeakHashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }

    /**
     * 设置网络拦截器
     *
     * @param interceptor
     * @return
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(), INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(), INTERCEPTORS);
        return this;
    }

    /**
     * 初始化iconify
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptorIcons) {
        ICONS.add(descriptorIcons);
        return this;
    }

    /**
     * 设置微信app id
     *
     * @param appId
     * @return
     */
    public final Configurator withWeChatAppId(String appId) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID.name(), appId);
        return this;
    }

    /**
     * 设置微信WE_CHAT_APP_SECRET
     */
    public final Configurator withWeChatAppSecret(String weChatAppSecret) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET.name(), weChatAppSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY.name(), activity);
        return this;
    }

    /**
     * 检查配置项是否完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call Configurat");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigKeys> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
