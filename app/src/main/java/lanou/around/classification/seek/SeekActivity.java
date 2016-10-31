package lanou.around.classification.seek;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseActivity;
import lanou.around.bean.ClassifyTabBean;
import lanou.around.presenter.ClassifyPresenter;
import lanou.around.tools.http.URLValues;
import lanou.around.tools.recycle.EncodeUtil;

public class SeekActivity extends BaseActivity implements View.OnClickListener, InterView {


    private EditText mSeekText;
    private ImageView mBack;
    private TextView mSeek;

    @Override
    protected int setContentView() {
        return R.layout.activity_seek;
    }

    @Override
    protected void initViews() {
        mSeekText = findView(R.id.et_seek_text);
        mBack = findView(R.id.iv_seek_back);
        mSeek = findView(R.id.tv_seek_text);
    }

    @Override
    protected void initListeners() {
        mSeekText.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mSeek.setOnClickListener(this);
        ClassifyPresenter presenter = new ClassifyPresenter(this);
        presenter.startRequest(URLValues.CLASSIFY_EDITTEXT_TITLTE, ClassifyTabBean.class);
    }

    @Override
    protected void initData() {
        EncodeUtil.decode("%E8%A3%A4%E5%AD%90");
        Log.d("SeekActivity", EncodeUtil.decode("%E8%A3%A4%E5%AD%90"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_seek_back:
                onBackPressed();
                break;
            case R.id.ll_search_set:
                break;
            case R.id.tv_seek_text:
                if (mSeekText.getText().toString() == null){

                }
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
        mSeekText.setText(tabBean.getRespData().getInputName());
    }

    @Override
    public void onError() {

    }
}
