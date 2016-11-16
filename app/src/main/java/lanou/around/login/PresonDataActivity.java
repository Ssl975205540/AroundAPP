package lanou.around.login;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import lanou.around.R;
import lanou.around.base.BaseActivity;
import lanou.around.widget.CircleTransform;

import static lanou.around.app.AroundAPP.context;

/**
 * Created by dllo on 16/11/8.
 */

public class PresonDataActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private TextView person_title , name , sex;
    private ImageView personIcon;
    private Platform qq;


    @Override
    protected int setContentView() {
        return R.layout.person_activity;
    }

    @Override
    protected void initViews() {
        back = findView(R.id.video_title_back);
        person_title = findView(R.id.video_title_tv);
        personIcon = findView(R.id.person_icon);
        name = findView(R.id.person_name);
        sex = findView(R.id.person_sex);
    }

    @Override
    protected void initListeners() {
    back.setOnClickListener(this);
    }

    @Override
    protected void initData() {
//标题
        person_title.setText("个人资料");
        person_title.setTextColor(Color.BLACK);
        back.setImageResource(R.mipmap.rn);
        ShareSDK.initSDK(context,"sharesdk的appkey");
        qq = ShareSDK.getPlatform(QQ.NAME);
        PlatformDb platDB = qq.getDb();
       if (qq.isAuthValid()) {
            name.setText(platDB.getUserName());
        } else {
            name.setText("你没名吗?");
        }
        if (qq.isAuthValid()) {
            if (platDB.getUserGender().equals("m")) {
                sex.setText("男");
            } else if (platDB.getUserGender().equals("f")){
                sex.setText("女");
            } else {
                sex.setText("你是人妖吗?");
            }

        } else {
            sex.setText("啥");
        }
        if (qq.isAuthValid()) {
           String str = platDB.getUserIcon();

            Picasso.with(context).load(str).transform(new CircleTransform()).into(personIcon);
        } else {
            personIcon.setImageResource(R.mipmap.kp);
        }
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
