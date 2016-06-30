package com.yx.personal.ganhuo.Activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.yx.personal.ganhuo.R;
import com.yx.personal.ganhuo.Utils.AppManager;

public class WebActivity extends BaseActivity {
    private WebView webview;
    private ProgressBar progressBar;

    @Override
    protected int getContentView() {
        return R.layout.activity_web;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initToolbar();
        AppManager.getAppManager().addActivity(this);
    }

    private void initToolbar() {
        setTitle("详情页");
        setIsShowBack(true);
    }

    private void initView() {
        webview = (WebView) findViewById(R.id.webView_webActivity);
        progressBar = (ProgressBar) findViewById(R.id.pb_webViewActivity);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webview.setWebChromeClient(new ChromeClient());

        webview.loadUrl(getIntent().getStringExtra("URL"));

    }


    private class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);
            } else {
                if (View.INVISIBLE == progressBar.getVisibility()) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                progressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
}

