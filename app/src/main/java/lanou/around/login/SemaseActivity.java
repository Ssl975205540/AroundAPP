package lanou.around.login;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import lanou.around.R;
import lanou.around.base.BaseActivity;

/**
 * Created by dllo on 16/11/7.
 */

public class SemaseActivity extends BaseActivity{
    private ImageView back;
    private TextView semase_title;
    @Override
    protected int setContentView() {
        return R.layout.semase_acticity;
    }

    @Override
    protected void initViews() {
        back = findView(R.id.video_title_back);
        semase_title = findView(R.id.video_title_tv);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
//标题
        semase_title.setText("芝麻认证");
        semase_title.setTextColor(Color.BLACK);
        back.setImageResource(R.mipmap.rn);
    }
}
