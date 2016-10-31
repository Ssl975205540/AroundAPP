package lanou.around.classification;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import lanou.around.R;
import lanou.around.base.BaseActivity;

public class WebActivity extends BaseActivity {

    private WebView mWebView;

    @Override
    protected int setContentView() {
        return R.layout.activity_web;
    }

    @Override
    protected void initViews() {
        mWebView = findView(R.id.webview);

    }

    @Override
    protected void initListeners() {
        //设置WebView的一些缩放功能点
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.getSettings().setSupportZoom(true);
        //设置WebView可触摸放大缩小
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setInitialScale(200);
        mWebView.setHorizontalScrollbarOverlay(true);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        mWebView.loadUrl(url);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }
}
