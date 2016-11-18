package lanou.around.login;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import lanou.around.R;
import lanou.around.base.BaseActivity;

/**
 * Created by dllo on 16/11/8.
 */

public class ContactActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private TextView contaxt_title;
    private ImageView videoCode;

    @Override
    protected int setContentView() {
        return R.layout.contact_activity;
    }

    @Override
    protected void initViews() {
        back = findView(R.id.video_title_back);
        contaxt_title = findView(R.id.video_title_tv);
        contaxt_title.setVisibility(View.GONE);
        videoCode = findView(R.id.video_code);

    }

    @Override
    protected void initListeners() {
        back.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        //标题
        contaxt_title.setText("联系我们");
        contaxt_title.setTextColor(Color.BLACK);
        back.setImageResource(R.mipmap.rn);
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
