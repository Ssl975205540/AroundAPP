package lanou.around.classification.classifyview;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import lanou.around.R;
import lanou.around.base.BaseActivity;

public class DigitWebActivity extends BaseActivity {

    private WebView mWebView;
    private ImageView mBack;

    public final static int FILECHOOSER_RESULTCODE = 1;
    public final static int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2;
    public ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> mUploadMessageForAndroid5;

    @Override
    protected int setContentView() {
        return R.layout.activity_web;
    }

    @Override
    protected void initViews() {
        mWebView = findView(R.id.webview);
        mBack = findView(R.id.iv_back);

    }

    @Override
    protected void initListeners() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        mWebView.loadUrl(url);
        WebSettings webSettings = mWebView.getSettings();
        // 设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        mWebView.setWebViewClient(new WebViewClient());
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

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
