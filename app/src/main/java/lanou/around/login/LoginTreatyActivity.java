package lanou.around.login;



import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import lanou.around.R;
import lanou.around.base.BaseActivity;

/**
 * Created by dllo on 16/10/31.
 */

public class LoginTreatyActivity extends BaseActivity implements View.OnClickListener {
    private TextView loginTreaty;
    private ImageView back;
    private WebView loginWeb;



    @Override
    protected int setContentView() {
        return R.layout.activity_theaty;
    }

    @Override
    protected void initViews() {
        loginTreaty = findView(R.id.video_title_tv);
        back = findView(R.id.video_title_back);
        loginWeb = findView(R.id.login_webview);

    }

    @Override
    protected void initListeners() {
        //设置WebView的一些缩放功能点
        loginWeb.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        loginWeb.setHorizontalScrollBarEnabled(false);
        loginWeb.getSettings().setSupportZoom(true);
        //设置WebView可触摸放大缩小
        loginWeb.getSettings().setBuiltInZoomControls(true);
        loginWeb.setInitialScale(200);
        loginWeb.setHorizontalScrollbarOverlay(true);
        back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        //标题
        loginTreaty.setText("转转用户服务协议");
        loginTreaty.setTextColor(Color.BLACK);
        back.setImageResource(R.mipmap.rn);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        loginWeb.loadUrl(url);
        WebSettings webSettings = loginWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        loginWeb.setWebViewClient(new WebViewClient());

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
