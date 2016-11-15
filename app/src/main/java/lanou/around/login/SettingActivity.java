package lanou.around.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import lanou.around.R;
import lanou.around.base.BaseActivity;
import lanou.around.tools.util.IntentUtils;
import lanou.around.widget.DataCleanManager;

/**
 * Created by dllo on 16/11/7.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private Button clearData, data , closeLogin , aroundAbout , aroundShielding , personData;
    private ImageButton back;
    private TextView setting_title;
    @Override
    protected int setContentView() {
        return R.layout.setting_activity;
    }

    @Override
    protected void initViews() {
        clearData = findView(R.id.my_setting_clear);
        data = findView(R.id.my_setting_get);
        back = findView(R.id.video_title_back);
        setting_title = findView(R.id.video_title_tv);
        closeLogin = findView(R.id.log_out);
        aroundAbout = findView(R.id.around_about);
        aroundShielding = findView(R.id.around_shielding);
        personData = findView(R.id.around_personal_data);

    }

    @Override
    protected void initListeners() {
        back.setOnClickListener(this);
        closeLogin.setOnClickListener(this);
        aroundAbout.setOnClickListener(this);
        aroundShielding.setOnClickListener(this);
        personData.setOnClickListener(this);

    }

    @Override
    protected void initData() {
//标题
        setting_title.setText("设置");
        setting_title.setTextColor(Color.BLACK);
        back.setImageResource(R.mipmap.rn);
        ShareSDK.initSDK(this,"sharesdk的appkey");
        //清理缓存
        try {
            data.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
            clearData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SettingActivity.this, "清理完成", Toast.LENGTH_SHORT).show();
                    DataCleanManager.clearAllCache(SettingActivity.this);
                    try {
                        data.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.video_title_back:
                finish();
                break;
            case R.id.log_out:

                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                if (qq.isAuthValid()) {
                    qq.removeAccount(true);
                    Intent intent = new Intent("getIcon");
                    intent.putExtra("icon", "");
                    sendBroadcast(intent);
                    Toast.makeText(this, "下次再见,亲", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "还未登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.around_about:
                IntentUtils.getIntents().Intent(this , AboutAroundActivity.class , new Bundle());
                break;
            case R.id.around_shielding:
                Toast.makeText(this, "你还没有屏蔽任何人哦", Toast.LENGTH_SHORT).show();
                break;
            case R.id.around_personal_data:
                IntentUtils.getIntents().Intent(this , PresonDataActivity.class , new Bundle());
                break;
        }
    }
}
