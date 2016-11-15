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
import lanou.around.tools.util.JsonFileReader;

import static lanou.around.classification.checkall.ClassifyTypeActivity.NAME;

/**
 * Created by dllo on 16/10/31.
 */
public class CheckAllActivity extends BaseActivity implements View.OnClickListener, InterView {

    private ImageView mBack;
    private ListView mClassify;
    public static String JSON_TYPE = "type.json";
    public static String TYPE = "type";
    private Thread newThread;
    public static List<ArrayList<String>> sLists;


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

        newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 要写在子线程中
                String type = JsonFileReader.getJson(CheckAllActivity.this, JSON_TYPE);
                parseJson(type);
            }
        });
        newThread.start(); //启动线程

    }


    public static void parseJson(String str) {
        sLists = new ArrayList<>();
        try {
            //  获取json中的数组
            JSONArray jsonArray = new JSONArray(str);
            //  遍历数据组
            for (int i = 0; i < jsonArray.length(); i++) {
                //  获取种类的对象
                ArrayList<String> mArrayLists = new ArrayList<>();
                JSONObject json = jsonArray.getJSONObject(i);
                JSONArray areaArray = json.optJSONArray(TYPE);
                for (int k = 0; k < areaArray.length(); k++) {
                    String s1 = areaArray.getString(k);
                    mArrayLists.add(s1);
                }
                //  获取种类名称放入集合
                sLists.add(mArrayLists);
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
    public void onResponse(Object t) {
        final ClassifyViewBean classifyViewBean = (ClassifyViewBean) t;
        CheckAllAdapter adapter = new CheckAllAdapter(this, classifyViewBean.getRespData());
        mClassify.setAdapter(adapter);
        mClassify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CheckAllActivity.this, ClassifyTypeActivity.class);
                intent.putExtra(NAME, classifyViewBean.getRespData().get(position).getCateName());
                intent.putStringArrayListExtra(TYPE, sLists.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onError() {

    }
}
