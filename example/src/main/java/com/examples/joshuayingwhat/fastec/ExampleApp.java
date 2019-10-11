package com.examples.joshuayingwhat.fastec;

import android.app.Application;

import com.examples.joshuayingwhat.latte.app.Latte;
import com.examples.joshuayingwhat.latte.ec.database.DataBaseManager;
import com.examples.joshuayingwhat.latte.ec.icon.FontEcModule;
import com.examples.joshuayingwhat.latte.net.interceptors.DebugInterceptors;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).withApiHost("http://127.0.0.1/")
                .withWeChatAppId("")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withWeChatAppSecret("")
                .withInterceptor(new DebugInterceptors("index", R.raw.test)).configure();
        initStetho();
        /**
         * 初始化数据库
         */
        DataBaseManager.getInstance().init(this);
    }

    public void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
