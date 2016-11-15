package lanou.around.home;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import lanou.around.R;
import lanou.around.base.BaseActivity;

/**
 * Created by dllo on 16/11/1.
 */

public class HSVClickActivity extends BaseActivity implements View.OnClickListener {
    private WebView webView;
    private TextView clickTitle;
    private ImageButton back;
    public static String URL = "url";
    public static String TITLE = "title";

    @Override
    protected int setContentView() {
        return R.layout.hsv_click_activity;
    }

    @Override
    protected void initViews() {
        webView = findView(R.id.hsv_click_webview);
        clickTitle = findView(R.id.video_title_tv);


    }

    @Override
    protected void initListeners() {
//设置WebView的一些缩放功能点
        webView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setSupportZoom(true);
        //设置WebView可触摸放大缩小
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setInitialScale(200);
        webView.setHorizontalScrollbarOverlay(true);

    }

    @Override
    protected void initData() {


        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        clickTitle.setText(title);
        clickTitle.setTextColor(Color.BLACK);

        webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_title_back:
                finish();
                break;
        }
    }
}
