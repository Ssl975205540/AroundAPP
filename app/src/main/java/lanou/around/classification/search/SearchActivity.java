package lanou.around.classification.search;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseActivity;
import lanou.around.bean.Bean;
import lanou.around.bean.ClassifyTabBean;
import lanou.around.classification.seek.SeekActivity;
import lanou.around.presenter.ClassifyPresenter;
import lanou.around.presenter.SearchPresenter;
import lanou.around.tools.http.URLValues;
import lanou.around.widget.MyRecyclerView;

/**
 * Created by dllo on 16/10/31.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener, InterView {

    private TextView mSearchText;
    private ImageView mBack;
    private LinearLayout mSearch;
    private MyRecyclerView search_recyclerview;


    @Override
    protected int setContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        mSearchText = findView(R.id.tv_search);
        mBack = findView(R.id.iv_search_back);
        mSearch = findView(R.id.ll_search);
        search_recyclerview = findView(R.id.search_recyclerview);
    }

    @Override
    protected void initListeners() {
        mSearchText.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mBack.setOnClickListener(this);
        ClassifyPresenter presenter = new ClassifyPresenter(this);
        presenter.startRequest(URLValues.CLASSIFY_EDITTEXT_TITLTE, ClassifyTabBean.class);
        search_recyclerview.setLoadingListener(new MyRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void setdisplay(int i) {

            }

            @Override
            public void onLoadMore() {

            }
        });

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String cateId = intent.getStringExtra("cateId");
        String REQUEST_BODY = URLValues.REQUEST_BODY_BEFOR + cateId + URLValues.REQUEST_BODY_AFTER;
        SearchPresenter searchPresenter = new SearchPresenter(this, REQUEST_BODY);
        searchPresenter.startRequest(URLValues.POST_CHILD_LOGIC, Bean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
        if (t instanceof Bean) {
            Bean bean = (Bean) t;
            List<Bean.RespDataBean> bean1 = bean.getRespData();
            SearchAdapter searchAdapter = new SearchAdapter(this, bean1);
            search_recyclerview.setAdapter(searchAdapter);
            search_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        } else {
            ClassifyTabBean tabBean = (ClassifyTabBean) t;
            mSearchText.setText(tabBean.getRespData().getInputName());
        }
    }

    @Override
    public void onError() {

    }
}
