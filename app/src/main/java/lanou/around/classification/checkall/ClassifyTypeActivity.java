package lanou.around.classification.checkall;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import lanou.around.R;
import lanou.around.base.BaseActivity;

public class ClassifyTypeActivity extends BaseActivity {


    private ListView mPreparation;
    private ArrayList<String> mChoose;
    private ImageView mBack;
    private TextView mKind;

    @Override
    protected int setContentView() {
        return R.layout.activity_classify_type;
    }

    @Override
    protected void initViews() {
        mPreparation = findView(R.id.lv_preparation);
        mBack = findView(R.id.image_back);
        mKind = findView(R.id.tv_all_kind);
    }

    @Override
    protected void initListeners() {
        Intent intent = getIntent();
        mChoose = intent.getStringArrayListExtra("type");

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void initData() {

        ChooseAdapter adapter = new ChooseAdapter(this,mChoose);
        mPreparation.setAdapter(adapter);
        mKind.setText("全部" + getIntent().getStringExtra("name"));
    }
}
