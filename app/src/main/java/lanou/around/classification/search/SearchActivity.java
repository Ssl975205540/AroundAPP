package lanou.around.classification.search;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
import lanou.around.tools.recycle.JsonFileReader;
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
    private LinearLayout tv_search_area;
    private View popView;
    private ListView listView1;
    private ListView listView2;
    private ListView listView3;
    private ArrayList<String> provinceBeanList = new ArrayList<>();
    //  城市
    private ArrayList<String> cities;
    private ArrayList<List<String>> cityList = new ArrayList<>();
    //  区/县
    private ArrayList<String> district;
    private ArrayList<List<String>> districts;
    private ArrayList<List<List<String>>> districtList = new ArrayList<>();
    private PopupWindow popupWindow;

    @Override
    protected int setContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {

        popView = LayoutInflater.from(this).inflate(R.layout.layoutpop, null);

        listView1 = (ListView) popView.findViewById(R.id.pop_lv1);

        listView2 = (ListView) popView.findViewById(R.id.pop_lv2);
        listView3 = (ListView) popView.findViewById(R.id.pop_lv3);
        tv_search_area = findView(R.id.tv_search_area);
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
        tv_search_area.setOnClickListener(this);
        mSearchText.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mOrder.setOnClickListener(this);
        ClassifyPresenter presenter = new ClassifyPresenter(this);
        presenter.startRequest(URLValues.CLASSIFY_EDITTEXT_TITLTE, ClassifyTabBean.class);

        search_recyclerview.setLoadingListener(new MyRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                search_recyclerview.refreshComplete();
            }

            @Override
            public void setdisplay(int i) {

            }

            @Override
            public void onLoadMore() {

                search_recyclerview.loadMoreComplete();
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

        //  获取json数据
        String province_data_json = JsonFileReader.getJson(this, "province_data.json");
        //  解析json数据
        parseJson(province_data_json);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.tv_search_area:
                if(popupWindow != null){
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                        popupWindow = null;
                    }
                }

                showAroeWindow(v);

                break;

            case R.id.iv_search_back:
                onBackPressed();
                break;
            case R.id.ll_search:
                Intent intent = new Intent(SearchActivity.this, SeekActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_order:

                if(popupWindow != null){
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                        popupWindow = null;
                    }
                }
                showPopupWindow(v);
                break;
        }
    }

    private void showAroeWindow(View view) {
        popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        // 设置动画效果
        popupWindow.setAnimationStyle(R.style.e0);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.showAsDropDown(view);


        view.setFocusable(true);//comment by danielinbiti,设置view能够接听事件，标注1
        view.setFocusableInTouchMode(true);


        view.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                    }
                }
                return false;
            }
        });


        AreaAdapter oneAdapter = new AreaAdapter(SearchActivity.this);
        oneAdapter.setProvinceBeanList(provinceBeanList);
        listView1.setAdapter(oneAdapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private AreaAdapter twoAdapter;

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                listView3.setVisibility(View.INVISIBLE);
                listView2.setVisibility(View.VISIBLE);
                twoAdapter = new AreaAdapter(SearchActivity.this);
                twoAdapter.setProvinceBeanList(cityList.get(i));
                listView2.setAdapter(twoAdapter);


                final int finalI = i;
                listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int j, long l) {

                        listView3.setVisibility(View.VISIBLE);
                        AreaAdapter threeAdapter = new AreaAdapter(SearchActivity.this);
                        threeAdapter.setProvinceBeanList(districtList.get(finalI).get(j));
                        listView3.setAdapter(threeAdapter);


                    }
                });

            }
        });


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

        popupWindow = new PopupWindow(contentView,
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


    public void parseJson(String str) {
        try {
            //  获取json中的数组
            JSONArray jsonArray = new JSONArray(str);
            //  遍历数据组
            for (int i = 0; i < jsonArray.length(); i++) {
                //  获取省份的对象
                JSONObject provinceObject = jsonArray.optJSONObject(i);
                //  获取省份名称放入集合
                String provinceName = provinceObject.getString("province");
                provinceBeanList.add(provinceName);
                //  获取城市数组
                JSONArray cityArray = provinceObject.optJSONArray("city");
                cities = new ArrayList<>();//   声明存放城市的集合
                districts = new ArrayList<>();//声明存放区县集合的集合
                //  遍历城市数组
                for (int j = 0; j < cityArray.length(); j++) {
                    //  获取城市对象
                    JSONObject cityObject = cityArray.optJSONObject(j);
                    //  将城市放入集合
                    String cityName = cityObject.optString("name");
                    cities.add(cityName);
                    district = new ArrayList<>();// 声明存放区县的集合
                    //  获取区县的数组
                    JSONArray areaArray = cityObject.optJSONArray("area");
                    //  遍历区县数组，获取到区县名称并放入集合
                    for (int k = 0; k < areaArray.length(); k++) {
                        String areaName = areaArray.getString(k);
                        district.add(areaName);
                    }
                    //  将区县的集合放入集合

                    districts.add(district);

                }
                //  将存放区县集合的集合放入集合
                districtList.add(districts);


                //  将存放城市的集合放入集合
                cityList.add(cities);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}