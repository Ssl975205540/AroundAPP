package lanou.around.login;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import lanou.around.R;
import lanou.around.app.AroundAPP;
import lanou.around.base.BaseFragment;
import lanou.around.tools.http.URLValues;

/**
 * Created by dllo on 16/10/22.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private ImageButton back;
    private TextView login_title , treaty;
    private Button login_btn;

    @Override
    protected void initData() {
        //标题
        login_title.setText("登录");
        login_title.setTextColor(Color.BLACK);
        back.setImageResource(R.mipmap.rn);
        //协议
        treaty.setOnClickListener(this);


    }

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.around_treaty:
                Intent intent = new Intent(AroundAPP.getContext() , LoginTreatyActivity.class);
                intent.putExtra("url", URLValues.URL_TREATY);
                getActivity().startActivity(intent);
                break;
        }
    }
}
