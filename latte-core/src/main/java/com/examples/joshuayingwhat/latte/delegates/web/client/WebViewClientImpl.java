package com.examples.joshuayingwhat.latte.delegates.web.client;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.examples.joshuayingwhat.latte.delegates.web.WebDelegate;
import com.examples.joshuayingwhat.latte.delegates.web.route.Router;

/**
 * @author joshuayingwhat
 */
public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        return Router.getInstance().handlerWebViewUrl(DELEGATE, url);
    }


}
