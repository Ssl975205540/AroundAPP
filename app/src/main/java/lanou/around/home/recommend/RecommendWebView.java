package lanou.around.home.recommend;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import lanou.around.R;
import lanou.around.base.BaseActivity;

/**
 * Created by dllo on 16/11/9.
 */

public class RecommendWebView extends BaseActivity {

    public static String INFO_URL = "infoUrl";
    public static final String INFO_URL_JUMP = "url";
    private WebView mWebView;

    @Override
    protected int setContentView() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initViews() {
        mWebView = findView(R.id.recommend_webview);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        Bundle bundle = this.getIntent().getExtras();
        String infoUrl = bundle.getString(INFO_URL);
        String url = bundle.getString(INFO_URL_JUMP);
        if (bundle.getString(INFO_URL) != null){
            mWebView.loadUrl(infoUrl);
        }
        if (bundle.getString(INFO_URL_JUMP) != null){
            mWebView.loadUrl(url);
        }

        WebSettings webSettings = mWebView.getSettings();
        // 设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        mWebView.setWebViewClient(new WebViewClient());

    }
}
