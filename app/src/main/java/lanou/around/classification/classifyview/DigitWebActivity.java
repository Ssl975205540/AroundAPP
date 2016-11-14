package lanou.around.classification.classifyview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import cn.sharesdk.framework.ShareSDK;
import lanou.around.R;
import lanou.around.base.BaseActivity;
import sharesdk.onekeyshare.OnekeyShare;

public class DigitWebActivity extends BaseActivity {

    private WebView mWebView;
    private ImageView mBack;
    private ImageView mShare;
    private TextView mTitle;
    public static String URL = "url";
    public static String GO_URL = "goUrl";
    public static String POST_NAME = "postName";
    public static String BANNER_URL = "bannerUrl";



    @Override
    protected int setContentView() {
        return R.layout.activity_web;
    }

    @Override
    protected void initViews() {
        mWebView = findView(R.id.webview);
        mBack = findView(R.id.iv_back);
        mShare = findView(R.id.iv_share);
        mTitle = findView(R.id.tv_digit_title);
    }

    @Override
    protected void initListeners() {

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });

    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        String url = intent.getStringExtra(URL);
        Bundle bundle = this.getIntent().getExtras();
        String goUrl = bundle.getString(GO_URL);
        String postName = bundle.getString(POST_NAME);


        //接受手机传过来的值
        String bannerUrl = bundle.getString(BANNER_URL);
        if (bundle.getString(BANNER_URL) != null) {
            mWebView.loadUrl(bannerUrl);
            mTitle.setText("转转优品");
        }

        if (bundle.getString(GO_URL) != null) {
            mWebView.loadUrl(goUrl);
            mTitle.setText(postName);
        }

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
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        // 启动分享
        oks.show(this);
    }


}
