package lanou.around.classification.seek;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseActivity;
import lanou.around.bean.ClassifyTabBean;
import lanou.around.classification.search.SearchActivity;
import lanou.around.presenter.ClassifyPresenter;
import lanou.around.presenter.SeekPresenter;
import lanou.around.tools.db.SearchHelper;
import lanou.around.tools.http.URLValues;
import lanou.around.tools.util.IntentUtils;
import lanou.around.widget.FlowLayout;
import static android.support.v7.widget.ListPopupWindow.WRAP_CONTENT;
import static lanou.around.classification.search.SearchActivity.K_NAME;

public class SeekActivity extends BaseActivity implements View.OnClickListener, InterView {


    private EditText mSeekText;
    private ImageView mBack;
    private TextView mSeek;
    private String mStr;
    private FlowLayout mFlowLayout;
    private ListView mSearch;
    private SQLiteDatabase mDatabase;
    private LinearLayout mLinear;
    private RelativeLayout mRelative;
    private ImageView mDelete;
    private List<String> mStrings;

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
        mSearch = findView(R.id.lv_seek_search);
        mLinear = findView(R.id.linear_seek);
        mRelative = findView(R.id.relative_seek);
        mDelete = findView(R.id.seek_delete);
    }

    @Override
    protected void initListeners() {
        mSeekText.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mSeek.setOnClickListener(this);
        mDelete.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        //创建数据库
        SearchHelper helper = new SearchHelper(this, "kind.db", null, 1);
        mDatabase = helper.getWritableDatabase();

        ClassifyPresenter presenter = new ClassifyPresenter(this);
        presenter.startRequest(URLValues.CLASSIFY_EDITTEXT_TITLTE, ClassifyTabBean.class);
        queryData();
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

                mLinear.setVisibility(View.INVISIBLE);
                mSearch.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void dataBase() {
        if (mSeekText.getText().length() != 0) {
            ContentValues values = new ContentValues();
            values.put("name", mSeekText.getText().toString());
            mDatabase.insert("search", null, values);
        }
    }

    //查找数据库后放在FlowLayout
    public void queryData() {
        mStrings = new ArrayList<>();
        Cursor cursor = mDatabase.query("search", null, null, null, null, null, null);
//        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(HomeQueryActivity.this,R.layout.item_record,cursor,new String[]{"name"},new int[]{R.id.tv_search_query});
//        lv.setAdapter(adapter);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                mStrings.add(name);
            }
            if (!mStrings.isEmpty()) {
                for (int i = 0; i < mStrings.size(); i++) {
                    for (int j = mStrings.size() - 1; j > i; j--) {
                        if (mStrings.get(i).equals(mStrings.get(j))) {
                            mStrings.remove(j);
                        }
                    }
                }
            }
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                    WRAP_CONTENT, WRAP_CONTENT);
            lp.bottomMargin = 5;
            lp.leftMargin = 15;
            lp.rightMargin = 15;
            lp.topMargin = 5;

            for (int j = 0; j < mStrings.size(); j++) {
                TextView view = new TextView(SeekActivity.this);
                view.setText(mStrings.get(j));
                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.flowlayout));
                mLinear.setVisibility(View.VISIBLE);
                mSearch.setVisibility(View.INVISIBLE);
                mFlowLayout.addView(view, lp);
            }
            mFlowLayout.setOnItemClickListener(new FlowLayout.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    mSeekText.setText(mStrings.get(position));
                    Bundle bundle = new Bundle();
                    IntentUtils.getIntents().Intent(SeekActivity.this, SearchActivity.class, bundle);
                }
            });
        }

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
                dataBase();

                if (mSeekText.getText().length() > 0) {
                    Bundle bundle = new Bundle();
                    IntentUtils.getIntents().Intent(this, SearchActivity.class, bundle);
                    finish();
               } else {
                    ProgressDialog.Builder builder = new ProgressDialog.Builder(SeekActivity.this);
                    builder.setIcon(R.mipmap.a0x);
                    builder.setMessage("搜索框内容不能为空");
                    builder.show();
                }

                break;
            case R.id.seek_delete:
                mDatabase.delete("search", null, null);
                mStrings.clear();
                mLinear.setVisibility(View.INVISIBLE);

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
            final SeekSuggestBean suggestBean = (SeekSuggestBean) t;
            SeekSuggestAdapter adapter =  new SeekSuggestAdapter(SeekActivity.this, suggestBean.getRespData());
            mSearch.setAdapter(adapter);
            mSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    mSeekText.setText(suggestBean.getRespData().get(position).getK());
                    dataBase();
                    bundle.getString(K_NAME, suggestBean.getRespData().get(position).getK());
                    IntentUtils.getIntents().Intent(SeekActivity.this, SearchActivity.class, bundle);
                }
            });

        }

    }

    @Override
    public void onError() {

    }
}
