package lanou.around.classification.search;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseActivity;
import lanou.around.bean.ClassifyTabBean;
import lanou.around.classification.seek.SeekActivity;
import lanou.around.presenter.ClassifyPresenter;
import lanou.around.tools.http.URLValues;

/**
 * Created by dllo on 16/10/31.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener, InterView {

    private TextView mSearchText;
    private ImageView mBack;
    private LinearLayout mSearch;

    @Override
    protected int setContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        mSearchText = findView(R.id.tv_search);
        mBack = findView(R.id.iv_search_back);
        mSearch = findView(R.id.ll_search);
    }

    @Override
    protected void initListeners() {
        mSearchText.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mBack.setOnClickListener(this);
        ClassifyPresenter presenter = new ClassifyPresenter(this);
        presenter.startRequest(URLValues.CLASSIFY_EDITTEXT_TITLTE, ClassifyTabBean.class);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search_back:
                onBackPressed();
                break;
            case R.id.ll_search:
                Intent intent = new Intent(SearchActivity.this, SeekActivity.class);
                startActivity(intent);
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
    public void onResponse(Object t) {
        ClassifyTabBean tabBean = (ClassifyTabBean) t;
        mSearchText.setText(tabBean.getRespData().getInputName());
    }

    @Override
    public void onError() {

    }
}
