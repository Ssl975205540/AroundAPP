package lanou.around.classification.search;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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
    private TextView mPhone;
    private ClassifyTabBean mTabBean;
    private String mCateNameLeft;
    private LinearLayout mOrder;
    private TextView mOrderText;


    @Override
    protected int setContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        mSearchText = findView(R.id.tv_search);
        mBack = findView(R.id.iv_search_back);
        mSearch = findView(R.id.ll_search);
        mOrder = findView(R.id.ll_order);
        search_recyclerview = findView(R.id.search_recyclerview);
        mPhone = findView(R.id.tv_search_phone);
        mOrderText = findView(R.id._tv_search_order);

    }

    @Override
    protected void initListeners() {
        mSearchText.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mOrder.setOnClickListener(this);
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
        String cateIdLeft = intent.getStringExtra("cateIdLeft");
        mCateNameLeft = intent.getStringExtra("cateNameLeft");
        mPhone.setText(mCateNameLeft);
//        String cateIdRight = intent.getStringExtra("cateIdRight");
//        String cateNameRight = intent.getStringExtra("cateNameRight");
//        mPhone.setText(cateNameRight);
//        String cateIdCenter = intent.getStringExtra("cateIdCenter");
//        String cateNameCenter = intent.getStringExtra("cateNameCenter");
//        mPhone.setText(cateNameCenter);
        String REQUEST_LEFT_BODY = URLValues.REQUEST_BODY_BEFOR + cateIdLeft + URLValues.REQUEST_BODY_AFTER;
//        String REQUEST_RIGHT_BODY = URLValues.REQUEST_BODY_BEFOR + cateIdRight + URLValues.REQUEST_BODY_AFTER;
//        String REQUEST_CENTER_BODY = URLValues.REQUEST_BODY_BEFOR + cateIdCenter + URLValues.REQUEST_BODY_AFTER;
        SearchPresenter leftPresenter = new SearchPresenter(this, REQUEST_LEFT_BODY);
        leftPresenter.startRequest(URLValues.POST_CHILD_LOGIC, Bean.class);

//        SearchPresenter centerPresenter = new SearchPresenter(this, REQUEST_CENTER_BODY);
//        centerPresenter.startRequest(URLValues.POST_CHILD_LOGIC, Bean.class);
//
//        SearchPresenter rightPresenter = new SearchPresenter(this, REQUEST_RIGHT_BODY);
//        rightPresenter.startRequest(URLValues.POST_CHILD_LOGIC, Bean.class);
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
            case R.id.ll_order:
                showPopupWindow(v);
                break;
        }
    }

    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_window, null);
        final TextView order = (TextView) contentView.findViewById(R.id.tv_order);
        final TextView publish = (TextView) contentView.findViewById(R.id.tv_publish);
        final TextView lowest = (TextView) contentView.findViewById(R.id.tv_lowest);
        final TextView highest = (TextView) contentView.findViewById(R.id.tv_highest);
        final TextView recently = (TextView) contentView.findViewById(R.id.tv_recently);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        // 设置按钮的点击事件
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                mOrderText.setText(order.getText());
            }
        });
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                mOrderText.setText(publish.getText());
            }
        });
        lowest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                mOrderText.setText(lowest.getText());
            }
        });
        highest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                mOrderText.setText(highest.getText());
            }
        });
        recently.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                mOrderText.setText(recently.getText());
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.popup_item));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);

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
            SearchAdapter searchAdapter = new SearchAdapter(this, bean.getRespData());
            search_recyclerview.setAdapter(searchAdapter);
            search_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        }
        if (t instanceof ClassifyTabBean) {
            mTabBean = (ClassifyTabBean) t;
            mSearchText.setText(mTabBean.getRespData().getInputName());
        }

    }

    @Override
    public void onError() {

    }
}
