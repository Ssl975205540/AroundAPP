package lanou.around.login;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import lanou.around.R;
import lanou.around.base.BaseActivity;

/**
 * Created by dllo on 16/11/8.
 */

public class LoginPayActivity extends BaseActivity{
    private ImageView back;
    private TextView pay_title;
    @Override
    protected int setContentView() {
        return R.layout.login_pay;
    }

    @Override
    protected void initViews() {
        back = findView(R.id.video_title_back);
        pay_title = findView(R.id.video_title_tv);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
//标题
        pay_title.setText("我的账单");
        pay_title.setTextColor(Color.BLACK);
        back.setImageResource(R.mipmap.rn);
    }
}
