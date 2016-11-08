package lanou.around.classification.seek;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseActivity;
import lanou.around.bean.ClassifyTabBean;
import lanou.around.presenter.ClassifyPresenter;
import lanou.around.presenter.SeekPresenter;
import lanou.around.tools.http.URLValues;
import lanou.around.widget.FlowLayout;

public class SeekActivity extends BaseActivity implements View.OnClickListener, InterView {


    private EditText mSeekText;
    private ImageView mBack;
    private TextView mSeek;
    private String mStr;
    private FlowLayout mFlowLayout;
    private ListView mSearch;

    @Override
    protected int setContentView() {
        return R.layout.activity_seek;
    }

    @Override
    protected void initViews() {
        mSeekText = findView(R.id.et_seek_text);
        mBack = findView(R.id.iv_seek_back);
        mSeek = findView(R.id.tv_seek_text);
        mFlowLayout = findView(R.id.flow_layout);
        mSearch = findView(R.id.lv_class_search);
    }

    @Override
    protected void initListeners() {
        mSeekText.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mSeek.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        ClassifyPresenter presenter = new ClassifyPresenter(this);
        presenter.startRequest(URLValues.CLASSIFY_EDITTEXT_TITLTE, ClassifyTabBean.class);



        //搜索
        mSeekText.addTextChangedListener(new TextWatcher() {
            SeekPresenter seekPresenter = new SeekPresenter(SeekActivity.this);
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mStr = null;
                try {
                    mStr = URLEncoder.encode(mSeekText.getText().toString(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                seekPresenter.startRequest(URLValues.SEARCH_SUGGEST,
                        URLValues.SEARCH_SUGGEST_BODY + mStr, SeekSuggestBean.class);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_seek_back:
                onBackPressed();
                break;
            case R.id.ll_search_set:
                break;
            case R.id.tv_seek_text:

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
        if (t instanceof ClassifyTabBean) {
            ClassifyTabBean tabBean = (ClassifyTabBean) t;
            mSeekText.setHint(tabBean.getRespData().getInputName());
            mSeekText.setHintTextColor(Color.BLACK);
            mSeekText.setTextColor(Color.BLACK);
        }
        if (t instanceof SeekSuggestBean) {
            SeekSuggestBean  suggestBean = (SeekSuggestBean) t;
            SeekSuggestAdapter adapter = new SeekSuggestAdapter(SeekActivity.this, suggestBean.getRespData());
            mSearch.setAdapter(adapter);
        }
    }

    @Override
    public void onError() {

    }
}
