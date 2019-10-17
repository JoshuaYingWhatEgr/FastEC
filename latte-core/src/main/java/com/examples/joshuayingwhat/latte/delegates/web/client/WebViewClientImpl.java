package com.examples.joshuayingwhat.latte.delegates.web.client;

import android.graphics.Bitmap;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.examples.joshuayingwhat.latte.app.ConfigKeys;
import com.examples.joshuayingwhat.latte.app.Latte;
import com.examples.joshuayingwhat.latte.delegates.IPageLoadListener;
import com.examples.joshuayingwhat.latte.delegates.web.WebDelegate;
import com.examples.joshuayingwhat.latte.delegates.web.route.Router;
import com.examples.joshuayingwhat.latte.utils.storage.LattePreference;

/**
 * @author joshuayingwhat
 */
public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;

    private IPageLoadListener mIPageLoadListener = null;

    public void setPageLoadListener(IPageLoadListener mIPageLoadListener) {
        this.mIPageLoadListener = mIPageLoadListener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        return Router.getInstance().handlerWebViewUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener == null) {
            throw new RuntimeException("IPageLoadListener 接口未初始化!");
        }
        mIPageLoadListener.onLoadStart();
    }

    /**
     * 同步cookie
     */
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        //这里的cookie和api中的cookie不一样
        final String webHost = (String) Latte.getConfigurations().get(ConfigKeys.WEB_HOST.name());
        if (webHost != null) {
            if (manager.hasCookies()) {
                final String cookieStr = manager.getCookie(webHost);
                if (cookieStr != null && !cookieStr.isEmpty()) {
                    LattePreference.addCustomAppProfile("cookie", cookieStr);
                }
            }
        } else {
            throw new NullPointerException("WEB HOST 不能为空!");
        }

    }


    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener == null) {
            throw new RuntimeException("IPageLoadListener 接口未初始化!");
        }
        //同步cookie
        syncCookie();
        mIPageLoadListener.onLoadEnd();
    }
}
