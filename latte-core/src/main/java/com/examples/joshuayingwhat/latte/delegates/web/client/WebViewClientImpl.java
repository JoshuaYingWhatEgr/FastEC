package com.examples.joshuayingwhat.latte.delegates.web.client;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.examples.joshuayingwhat.latte.app.Latte;
import com.examples.joshuayingwhat.latte.delegates.IPageLoadListener;
import com.examples.joshuayingwhat.latte.delegates.web.WebDelegate;
import com.examples.joshuayingwhat.latte.delegates.web.route.Router;
import com.examples.joshuayingwhat.latte.ui.LatteLoader;

import java.io.InvalidClassException;

/**
 * @author joshuayingwhat
 */
public class WebViewClientImpl extends WebViewClient{

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

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener == null) {
            throw new RuntimeException("IPageLoadListener 接口未初始化!");
        }
        mIPageLoadListener.onLoadEnd();
    }
}
