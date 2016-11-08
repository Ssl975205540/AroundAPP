package lanou.around.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import lanou.around.R;
import lanou.around.base.BaseFragment;
import lanou.around.home.FriendActivity;
import lanou.around.tools.recycle.IntentUtils;
import lanou.around.widget.CircleTransform;

/**
 * Created by dllo on 16/10/22.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private ImageView setting , userIcon;
    private Button sesame , friend , pay , redPaket , cricle , helpCenter;
    private LoginBroadCastRecevier recevier;



    @Override
    protected void initData() {

        recevier = new LoginBroadCastRecevier();
        IntentFilter filter = new IntentFilter();
        filter.addAction("getIcon");
        getContext().registerReceiver(recevier, filter);
        ShareSDK.initSDK(context,"sharesdk的appkey");


    }

    @Override
    protected int setContentView() {
        return R.layout.my_fragment;
    }

    @Override
    protected void initViews() {
        setting = findView(R.id.around_setting);
        userIcon = findView(R.id.user_icon);
        sesame = findView(R.id.around_zhima);
        friend = findView(R.id.around_my_friend);
        pay = findView(R.id.around_my_pay);
        redPaket = findView(R.id.around_red_packet);
        cricle = findView(R.id.around_my_circle);
        helpCenter = findView(R.id.around_help_center);



    }

    @Override
    protected void initListeners() {
        setting.setOnClickListener(this);
        userIcon.setOnClickListener(this);
        sesame.setOnClickListener(this);
        friend.setOnClickListener(this);
        pay.setOnClickListener(this);
        redPaket.setOnClickListener(this);
        cricle.setOnClickListener(this);
        helpCenter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        switch (v.getId()) {
            case R.id.around_setting:
                IntentUtils.getIntents().Intent(context , SettingActivity.class , new Bundle());

                break;
            case R.id.user_icon:
                IntentUtils.getIntents().Intent(context , FriendActivity.class , new Bundle());
                break;
            case R.id.around_zhima:

                if (qq.isAuthValid()) {
                    IntentUtils.getIntents().Intent(context , SemaseActivity.class , new Bundle());
                } else {
                    IntentUtils.getIntents().Intent(context , FriendActivity.class , new Bundle());
                }

                break;
            case R.id.around_my_friend:
                if (qq.isAuthValid()) {
                    Toast.makeText(context, "好友都在这,快来分享宝贝", Toast.LENGTH_SHORT).show();
                } else {
                    IntentUtils.getIntents().Intent(context, FriendActivity.class, new Bundle());
                }
                break;
            case R.id.around_my_pay:
                if (qq.isAuthValid()) {
                    IntentUtils.getIntents().Intent(context , LoginPayActivity.class , new Bundle());
                } else {
                    IntentUtils.getIntents().Intent(context, FriendActivity.class, new Bundle());
                }
                break;
            case R.id.around_red_packet:
                if (qq.isAuthValid()) {
                    Toast.makeText(context, "亲,还没有红包,快去抢一个", Toast.LENGTH_SHORT).show();
                } else {
                    IntentUtils.getIntents().Intent(context, FriendActivity.class, new Bundle());
                }
                break;
            case R.id.around_my_circle:
                if (qq.isAuthValid()) {
                    Toast.makeText(context, "你还没有加入任何圈子", Toast.LENGTH_SHORT).show();
                } else {
                    IntentUtils.getIntents().Intent(context, FriendActivity.class, new Bundle());
                }
                break;
            case R.id.around_help_center:
                if (qq.isAuthValid()) {
                    Toast.makeText(context, "去设置里联系我们吧", Toast.LENGTH_SHORT).show();
                } else {
                    IntentUtils.getIntents().Intent(context, FriendActivity.class, new Bundle());
                }
                break;

        }

    }

    private class LoginBroadCastRecevier extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

            String str = intent.getStringExtra("icon");
            if (str.equals("")) {
                userIcon.setImageResource(R.mipmap.kp);
            }else  {
                Picasso.with(context).load(str).transform(new CircleTransform()).into(userIcon);

            }
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(recevier);

    }

}
