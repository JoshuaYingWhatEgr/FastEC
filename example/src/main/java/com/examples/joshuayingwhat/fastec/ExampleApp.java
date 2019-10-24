package com.examples.joshuayingwhat.fastec;

import android.app.Application;

import com.examples.joshuayingwhat.latte.app.Latte;
import com.examples.joshuayingwhat.latte.ec.database.DataBaseManager;
import com.examples.joshuayingwhat.latte.ec.icon.FontEcModule;
import com.examples.joshuayingwhat.latte.net.interceptors.DebugInterceptors;
import com.examples.joshuayingwhat.latte.net.rx.AddCookieInterceptor;
import com.examples.joshuayingwhat.latte.net.rx.AddHttpLogInterceptor;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.jpush.android.api.JPushInterface;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).withApiHost("http://mock.fulingjie.com/mock/api/")
                .withWeChatAppId("")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withWeChatAppSecret("")
                .withJavaScriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                .withInterceptor(AddHttpLogInterceptor.getInstance().setLevel(okhttp3.logging.HttpLoggingInterceptor.Level.BODY))
//                .withInterceptor(new AddCookieInterceptor())
                .withWebHost("http://www.baidu.com/")
                .withInterceptor(new DebugInterceptors("index", R.raw.test)).configure();
        initStetho();
        /**
         * 初始化数据库
         */
        DataBaseManager.getInstance().init(this);

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
