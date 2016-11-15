package lanou.around.classification.checkall;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseActivity;

public class ClassifyTypeActivity extends BaseActivity {

    private ListView mPreparation;
    private List<String> mChoose;
    private ImageView mBack;
    private TextView mKind;
    public static String NAME = "name";
    public static String TYPE = "type";
    public static String KIND_ALL = "全部";

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
        Intent intent = getIntent();
        mChoose = intent.getStringArrayListExtra(TYPE);

        ChooseAdapter adapter = new ChooseAdapter(this, mChoose);
        mPreparation.setAdapter(adapter);
        mKind.setText(KIND_ALL + getIntent().getStringExtra(NAME));
    }
}
