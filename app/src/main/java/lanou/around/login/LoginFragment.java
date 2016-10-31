package lanou.around.login;

import android.widget.ImageButton;
import android.widget.TextView;

import lanou.around.R;
import lanou.around.base.BaseFragment;

/**
 * Created by dllo on 16/10/22.
 */

public class LoginFragment extends BaseFragment{
    private ImageButton back;
    private TextView login_title;
    @Override
    protected void initData() {
        login_title.setText("登录");
        back.setImageResource(R.mipmap.rn);

    }

    @Override
    protected int setContentView() {
        return R.layout.login_fragment;
    }

    @Override
    protected void initViews() {
        back = findView(R.id.video_title_back);
        login_title = findView(R.id.video_title_tv);

    }

    @Override
    protected void initListeners() {

    }
}
