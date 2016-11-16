package lanou.around.home;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import lanou.around.R;
import lanou.around.app.AroundAPP;
import lanou.around.base.BaseActivity;
import lanou.around.login.LoginTreatyActivity;
import lanou.around.tools.http.URLValues;

import static lanou.around.app.AroundAPP.context;

/**
 * Created by dllo on 16/11/1.
 */

public class FriendActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private TextView login_title , treaty;
    private Button login_btn;

    @Override
    protected int setContentView() {
        return R.layout.login_fragment;
    }

    @Override
    protected void initViews() {
        back = findView(R.id.video_title_back);
        login_title = findView(R.id.video_title_tv);
        login_btn = findView(R.id.around_login);
        treaty = findView(R.id.around_treaty);
    }

    @Override
    protected void initListeners() {
        back.setOnClickListener(this);
        //协议
        treaty.setOnClickListener(this);
        //qq 登录
        login_btn.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        //标题
        login_title.setText("登录");
        login_title.setTextColor(Color.BLACK);
        back.setImageResource(R.mipmap.rn);
        ShareSDK.initSDK(this,"sharesdk的appkey");

    }


    @Override
    public void onClick(View v) {
        final PlatformActionListener paListener = new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                PlatformDb platDB = platform.getDb();//获取数平台数据DB
                Intent intent = new Intent("getIcon");
                intent.putExtra("name", platDB.getUserName());
                intent.putExtra("icon", platDB.getUserIcon());
                intent.putExtra("sex" , platDB.getUserGender());
                AroundAPP.getContext().sendBroadcast(intent);
                Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(context, "取消登录", Toast.LENGTH_SHORT).show();
            }
        };
        switch (v.getId()) {
            case R.id.around_treaty:
                Intent intent = new Intent(AroundAPP.getContext() , LoginTreatyActivity.class);
                intent.putExtra("url", URLValues.URL_TREATY);
                startActivity(intent);
                break;
            case R.id.video_title_back:
                finish();
                break;
            case R.id.around_login:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);

                qq.setPlatformActionListener(paListener);
//authorize与showUser单独调用一个即可
                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息
                finish();
                break;
        }
    }
}
