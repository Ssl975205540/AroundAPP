package lanou.around.classification.checkall;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseActivity;
import lanou.around.bean.ClassifyViewBean;
import lanou.around.presenter.ClassifyViewPresenter;
import lanou.around.tools.http.URLValues;
import lanou.around.tools.recycle.JsonFileReader;

/**
 * Created by dllo on 16/10/31.
 */
public class CheckAllActivity extends BaseActivity implements View.OnClickListener, InterView {

    private ImageView mBack;
    private ListView mClassify;
    private List<String> mTypeList;

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

        String type = JsonFileReader.getJson(this, "type.json");
        parseJson(type);
    }

    ArrayList<ArrayList<String>> a = new ArrayList<>();

    private void parseJson(String str) {

        try {
            //  获取json中的数组
            JSONArray jsonArray = new JSONArray(str);
            //  遍历数据组
            for (int i = 0; i < jsonArray.length(); i++) {
                //  获取种类的对象
                ArrayList<String> mArrayLists = new ArrayList<>();

                JSONObject j = jsonArray.getJSONObject(i);


                JSONArray areaArray = j.optJSONArray("type");

                for (int k = 0; k < areaArray.length(); k++) {
                    String s1 = areaArray.getString(k);
                    mArrayLists.add(s1);
                }

                //  获取种类名称放入集合
                a.add(mArrayLists);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


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
    public void onResponse(Object t) {
        final ClassifyViewBean classifyViewBean = (ClassifyViewBean) t;
        CheckAllAdapter adapter = new CheckAllAdapter(this, classifyViewBean.getRespData());
        mClassify.setAdapter(adapter);
        mClassify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CheckAllActivity.this, ClassifyTypeActivity.class);
                intent.putExtra("name", classifyViewBean.getRespData().get(position).getCateName());
                intent.putStringArrayListExtra("type", a.get(position));
                startActivity(intent);
            }
        });
    }


    @Override
    public void onError() {

    }
}
