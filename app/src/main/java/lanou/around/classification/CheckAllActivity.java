package lanou.around.classification;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseActivity;
import lanou.around.base.BaseFragment;
import lanou.around.bean.ClassifyViewBean;

import lanou.around.classification.classifiview.ClassifyViewPresenter;
import lanou.around.tools.http.URLValues;

/**
 * Created by dllo on 16/10/31.
 */
public class CheckAllActivity extends BaseActivity implements View.OnClickListener, InterView {

    private ImageView mBack;
    private ListView mClassify;

    @Override
    protected int setContentView() {
        return R.layout.activity_check_all;
    }

    @Override
    protected void initViews() {
        mBack = findView(R.id.iv_check_all_back);
        mClassify = findView(R.id.lv_classify_check_all);
    }

    @Override
    protected void initListeners() {
        mBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        ClassifyViewPresenter presenter = new ClassifyViewPresenter(this);
        presenter.startRequest(URLValues.CLASSIFY_CHILD_CATES_LOGIC, ClassifyViewBean.class);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_check_all_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void startAnimation() {

    }

    @Override
    public void stopAnimation() {

    }

    @Override
    public <T> void onResponse(T t) {
        ClassifyViewBean classifyViewBean = (ClassifyViewBean) t;
        CheckAllAdapter adapter = new CheckAllAdapter(this, classifyViewBean.getRespData());
        mClassify.setAdapter(adapter);
    }

    @Override
    public void onError() {

    }
}
