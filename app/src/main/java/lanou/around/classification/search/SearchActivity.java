package lanou.around.classification.search;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import lanou.around.bean.ClassifyKindBean;
import lanou.around.bean.ClassifyTabBean;
import lanou.around.bean.ClassifyViewBean;
import lanou.around.classification.checkall.CheckAllActivity;
import lanou.around.classification.seek.SeekActivity;
import lanou.around.presenter.ClassifyPresenter;
import lanou.around.presenter.ClassifyViewPresenter;
import lanou.around.presenter.SearchPresenter;
import lanou.around.tools.http.URLValues;
import lanou.around.tools.recycle.IntentUtils;
import lanou.around.tools.recycle.JsonFileReader;
import lanou.around.widget.MyRecyclerView;

import static lanou.around.classification.checkall.CheckAllActivity.JSON_TYPE;
import static lanou.around.classification.checkall.CheckAllActivity.sLists;

/**
 * Created by dllo on 16/10/31.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener, InterView {

    public static String NAME = "name";
    public static String LEFT = "Left";
    public static String RIGHT = "Right";
    public static String CENTER = "Center";
    public static String CATE_ID_LEFT = "cateIdLeft";
    public static String CATE_NAME_LEFT = "cateNameLeft";
    public static String CATE_ID_RIGHT = "cateIdRight";
    public static String CATE_NAME_RIGHT = "cateNameRight";
    public static String CATE_ID_CENTER = "cateIdCenter";
    public static String CATE_NAME_CENTER = "cateNameCenter";
    public static String PROVINCE_DATA = "province_data.json";
    public static String JSON_PROVINCE = "province";
    public static String JSON_CITY = "city";
    public static String JSON_NAME = "name";
    public static String JSON_AREA = "area";


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
    private ListView leftListView;
    private ListView centerListView;
    private ListView rightListView;
    private List<String> provinceBeanList = new ArrayList<>();
    // 城市
    private List<String> cities;
    private ArrayList<List<String>> cityList = new ArrayList<>();
    // 区/县
    private List<String> district;
    private List<List<String>> districts;
    private List<List<List<String>>> districtList = new ArrayList<>();
    private PopupWindow popupWindow;
    private SearchAdapter mSearchAdapter;
    private Bundle mBundle;
    private Thread newThread; //声明一个子线程
    private ListView mKindListView;
    private ListView mClassifyListView;
    private ClassifyViewBean mViewBean;
    private LinearLayout mLinear;
    private int mPosition;


    @Override
    protected int setContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {

        popView = LayoutInflater.from(this).inflate(R.layout.layoutpop, null);
        leftListView = (ListView) popView.findViewById(R.id.pop_lv1);
        centerListView = (ListView) popView.findViewById(R.id.pop_lv2);
        rightListView = (ListView) popView.findViewById(R.id.pop_lv3);
        tv_search_area = findView(R.id.tv_search_area);
        mSearchText = findView(R.id.tv_search);
        mBack = findView(R.id.iv_search_back);
        mSearch = findView(R.id.ll_search);
        mOrder = findView(R.id.ll_order);
        search_recyclerview = findView(R.id.search_recyclerview);
        mPhone = findView(R.id.tv_search_phone);
        mOrderText = findView(R.id._tv_search_order);
        mLinear = findView(R.id.ll_search_phone);

    }

    @Override
    protected void initListeners() {
        tv_search_area.setOnClickListener(this);
        mSearchText.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mOrder.setOnClickListener(this);
        mLinear.setOnClickListener(this);
        ClassifyPresenter presenter = new ClassifyPresenter(this);
        presenter.startRequest(URLValues.CLASSIFY_EDITTEXT_TITLTE, ClassifyTabBean.class);

        search_recyclerview.setLoadingListener(new MyRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                search_recyclerview.refreshComplete();
            }


            @Override
            public void onLoadMore() {

//                search_recyclerview.loadMoreComplete();

            }
        });
    }

    @Override
    protected void initData() {

        ClassifyViewPresenter presenter = new ClassifyViewPresenter(this);
        presenter.startRequest(URLValues.CLASSIFY_CHILD_CATES_LOGIC, ClassifyViewBean.class);

        mBundle = this.getIntent().getExtras();
        String name = mBundle.getString(NAME);
        mPhone.setText(name);

        String cateIdLeft = mBundle.getString(CATE_ID_LEFT);
        mCateNameLeft = mBundle.getString(CATE_NAME_LEFT);

        String cateIdRight = mBundle.getString(CATE_ID_RIGHT);
        String cateNameRight = mBundle.getString(CATE_NAME_RIGHT);

        String cateIdCenter = mBundle.getString(CATE_ID_CENTER);
        String cateNameCenter = mBundle.getString(CATE_NAME_CENTER);

        if (mBundle.getString(CATE_ID_LEFT) != null) {
            String REQUEST_LEFT_BODY = URLValues.REQUEST_BODY_BEFOR + cateIdLeft + URLValues.REQUEST_BODY_AFTER;
            SearchPresenter leftPresenter = new SearchPresenter(this, REQUEST_LEFT_BODY);
            leftPresenter.startRequest(URLValues.POST_CHILD_LOGIC, ClassifyKindBean.class);
            mPhone.setText(mCateNameLeft);
        }
        if (mBundle.getString(CATE_ID_CENTER) != null) {
            String REQUEST_CENTER_BODY = URLValues.REQUEST_BODY_BEFOR + cateIdCenter + URLValues.REQUEST_BODY_AFTER;
            SearchPresenter centerPresenter = new SearchPresenter(this, REQUEST_CENTER_BODY);
            centerPresenter.startRequest(URLValues.POST_CHILD_LOGIC, ClassifyKindBean.class);
            mPhone.setText(cateNameCenter);
        }
        if (mBundle.getString(CATE_ID_RIGHT) != null) {
            String REQUEST_RIGHT_BODY = URLValues.REQUEST_BODY_BEFOR + cateIdRight + URLValues.REQUEST_BODY_AFTER;
            SearchPresenter rightPresenter = new SearchPresenter(this, REQUEST_RIGHT_BODY);
            rightPresenter.startRequest(URLValues.POST_CHILD_LOGIC, ClassifyKindBean.class);
            mPhone.setText(cateNameRight);
        }

        newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //  获取json数据
                String province_data_json = JsonFileReader.getJson(SearchActivity.this, PROVINCE_DATA);
                //  解析json数据
                parseJson(province_data_json);
            }
        });
        newThread.start(); //启动线程


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search_area:
                if (popupWindow != null) {
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
                IntentUtils.getIntents().Intent(this, SeekActivity.class, new Bundle());
                break;
            case R.id.ll_order:
                if (popupWindow != null) {
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                        popupWindow = null;
                    }
                }
                showPopupWindow(v);
                break;
            case R.id.ll_search_phone:
                if (popupWindow != null) {
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                        popupWindow = null;
                    }
                }
                showKindPopupWindow(v);
                break;
        }
    }

    private void showAroeWindow(View view) {
        popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        // 设置动画效果
        popupWindow.setAnimationStyle(R.style.e1);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(view);
        view.setFocusable(true);
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

        AreaAdapter oneAdapter = new AreaAdapter(SearchActivity.this, provinceBeanList);
        leftListView.setAdapter(oneAdapter);
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private AreaAdapter twoAdapter;

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                rightListView.setVisibility(View.INVISIBLE);
                centerListView.setVisibility(View.VISIBLE);
                twoAdapter = new AreaAdapter(SearchActivity.this, cityList.get(i));
                centerListView.setAdapter(twoAdapter);


                final int finalI = i;
                centerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int j, long l) {

                        rightListView.setVisibility(View.VISIBLE);
                        AreaAdapter threeAdapter = new AreaAdapter(SearchActivity.this, districtList.get(finalI).get(j));
                        rightListView.setAdapter(threeAdapter);
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

    private void showKindPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.kind_pop_window, null);
        mKindListView = (ListView) contentView.findViewById(R.id.listview_kind);
        mClassifyListView = (ListView) contentView.findViewById(R.id.listview_classify);
        newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String type = JsonFileReader.getJson(SearchActivity.this, JSON_TYPE);
                CheckAllActivity.parseJson(type);
            }
        });
        newThread.start(); //启动线程
        KindAdapter kindAdapter = new KindAdapter(this, mViewBean.getRespData());
        mKindListView.setAdapter(kindAdapter);
        mKindListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mPosition = position;
                ClassAdapter classAdapter = new ClassAdapter(SearchActivity.this, sLists.get(position));
                mClassifyListView.setAdapter(classAdapter);
            }
        });
        mClassifyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPhone.setText(sLists.get(mPosition).get(position));
                popupWindow.dismiss();
            }
        });

        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, 800, true);
        popupWindow.setTouchable(true);
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
        if (t instanceof ClassifyKindBean) {
            ClassifyKindBean bean = (ClassifyKindBean) t;
            mSearchAdapter = new SearchAdapter(this, bean.getRespData());
            if (mBundle.getInt(LEFT, 3) == 1) {
                if (mBundle.getString(CATE_NAME_LEFT).equals("服装鞋帽")) {
                    mSearchAdapter.setLayout(R.layout.search_item_grid);
                    search_recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
                } else {
                    mSearchAdapter.setLayout(R.layout.search_item_linear);
                    search_recyclerview.setLayoutManager(new LinearLayoutManager(this));
                }
            }
            if (mBundle.getInt(CENTER, 1) == 2) {
                if (mBundle.getString(CATE_NAME_CENTER).equals("玩具乐器")
                        || mBundle.getString(CATE_NAME_CENTER).equals("珠宝配饰")) {
                    mSearchAdapter.setLayout(R.layout.search_item_grid);
                    search_recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
                } else {
                    mSearchAdapter.setLayout(R.layout.search_item_linear);
                    search_recyclerview.setLayoutManager(new LinearLayoutManager(this));

                }
            }
            if (mBundle.getInt(RIGHT, 2) == 3) {
                if (mBundle.getString(CATE_NAME_RIGHT).equals("艺术古玩")
                        || mBundle.getString(CATE_NAME_RIGHT).equals("美容保健")) {
                    mSearchAdapter.setLayout(R.layout.search_item_grid);
                    search_recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
                } else {
                    mSearchAdapter.setLayout(R.layout.search_item_linear);
                    search_recyclerview.setLayoutManager(new LinearLayoutManager(this));

                }
            }
            search_recyclerview.setAdapter(mSearchAdapter);
        }
        if (t instanceof ClassifyTabBean) {
            mTabBean = (ClassifyTabBean) t;
            mSearchText.setText(mTabBean.getRespData().getInputName());
        }

        if (t instanceof ClassifyViewBean) {
            mViewBean = (ClassifyViewBean) t;
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
                String provinceName = provinceObject.getString(JSON_PROVINCE);
                provinceBeanList.add(provinceName);
                //  获取城市数组
                JSONArray cityArray = provinceObject.optJSONArray(JSON_CITY);
                cities = new ArrayList<>();//   声明存放城市的集合
                districts = new ArrayList<>();//声明存放区县集合的集合
                //  遍历城市数组
                for (int j = 0; j < cityArray.length(); j++) {
                    //  获取城市对象
                    JSONObject cityObject = cityArray.optJSONObject(j);
                    //  将城市放入集合
                    String cityName = cityObject.optString(JSON_NAME);
                    cities.add(cityName);
                    district = new ArrayList<>();// 声明存放区县的集合
                    //  获取区县的数组
                    JSONArray areaArray = cityObject.optJSONArray(JSON_AREA);
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