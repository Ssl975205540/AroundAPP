package lanou.around.login;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import lanou.around.R;
import lanou.around.base.BaseActivity;
import lanou.around.tools.http.URLValues;

/**
 * Created by dllo on 16/11/8.
 */

public class AboutAroundActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back ;
    private TextView about_title;
    private Button update , agreement , contact;
    @Override
    protected int setContentView() {
        return R.layout.about_around_activity;
    }

    @Override
    protected void initViews() {
        back = findView(R.id.video_title_back);
        about_title = findView(R.id.video_title_tv);
        update = findView(R.id.about_update);
        agreement = findView(R.id.about_xieyi);
        contact = findView(R.id.about_contact);
    }

    @Override
    protected void initListeners() {
        back.setOnClickListener(this);
        update.setOnClickListener(this);
        agreement.setOnClickListener(this);
        contact.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        //标题
        about_title.setText("关于转转");
        about_title.setTextColor(Color.BLACK);
        back.setImageResource(R.mipmap.rn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_title_back:
                finish();
                break;
            case R.id.about_update:
                Toast.makeText(this, "已是最新版本,敬请期待下一版本", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_xieyi:
                Intent intent = new Intent(this , LoginTreatyActivity.class);
                intent.putExtra("url", URLValues.URL_TREATY);
                startActivity(intent);
                break;
            case R.id.about_contact:
                Intent intent1 = new Intent(this , ContactActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
