package lanou.around.readphoto.region;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseActivity;
import lanou.around.readphoto.region.listview_Indexes.CharacterParser;
import lanou.around.readphoto.region.listview_Indexes.PinyinComparator;
import lanou.around.readphoto.region.listview_Indexes.RegionBean;
import lanou.around.readphoto.region.listview_Indexes.RegionListAdapter;
import lanou.around.readphoto.region.listview_Indexes.SideBar;
import lanou.around.tools.util.JsonFileReader;

import static lanou.around.classification.search.SearchActivity.JSON_AREA;
import static lanou.around.classification.search.SearchActivity.JSON_CITY;
import static lanou.around.classification.search.SearchActivity.JSON_NAME;
import static lanou.around.classification.search.SearchActivity.PROVINCE_DATA;

public class RegionActivity extends BaseActivity {


    private ListView listRegion;
    private SideBar sideBar;
    private PinyinComparator pinyinComparator;
    private CharacterParser characterParser;
    private LinearLayout titleLayout;
    private TextView title;
    private TextView textvv;
    private List<RegionBean> cities;
    private RegionListAdapter friendAdapter;
    private TextView tvItem;
    private View view;


    @Override
    protected int setContentView() {

        return R.layout.activity_region;
    }

    @Override
    protected void initViews() {
        listRegion = findView(R.id.list_region);
        sideBar = findView(R.id.sidrbar);
        titleLayout = findView(R.id.title_layout);
        title = findView(R.id.title_layout_catalog);
        textvv = findView(R.id.textvv);

        view = LayoutInflater.from(this).inflate(R.layout.region_item, null);

        listRegion.addHeaderView(view);

        tvItem = findView(view, R.id.tv_region_item);
    }

    @Override
    protected void initListeners() {
        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置

                textvv.setText(s);
                textvv.setTextSize(50);

                AlphaAnimation alp = new AlphaAnimation(1.0f, 0.0f);
                alp.setDuration(1000);
                textvv.setAnimation(alp);
                textvv.setVisibility(View.INVISIBLE);
                int position = friendAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    listRegion.setSelection(position + 1);
                }

            }


        });


        listRegion.setOnScrollListener(new AbsListView.OnScrollListener() {

            private int lastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0) {
                    titleLayout.setVisibility(View.INVISIBLE);

                    return;
                }
                firstVisibleItem = firstVisibleItem - 1;


                int section = getSectionForPosition(firstVisibleItem);

                int nextSection = getSectionForPosition(firstVisibleItem + 1);

                int nextSecPosition = getPositionForSection(+nextSection);

                if (firstVisibleItem != lastFirstVisibleItem) {
                    //获得layout的高度
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                            .getLayoutParams();
                    //设置layout距离上边缘的距离
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);


                    title.setText(cities.get(
                            getPositionForSection(section)).getFristLetters());
                }


                if (visibleItemCount != 0) {

                    titleLayout.setVisibility(View.VISIBLE);

                }


                if (nextSecPosition == firstVisibleItem + 1) {


                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = titleLayout.getHeight();
                        int bottom = childView.getBottom();

                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                .getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;

            }
        });


    }


    private List<RegionBean> filledData(List<RegionBean> date) {
        List<RegionBean> mSortList = new ArrayList<RegionBean>();
        RegionBean friend = null;
        for (int i = 0; i < date.size(); i++) {
            friend = date.get(i);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(friend.getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                Log.d("MainActivity", sortString.toUpperCase());
                friend.setFristLetters(sortString.toUpperCase());
            } else {
                friend.setFristLetters("#");
            }

            mSortList.add(friend);
        }
        return mSortList;

    }


    @Override
    protected void initData() {


        pinyinComparator = new PinyinComparator();
        characterParser = CharacterParser.getInstance();
        String province_data_json = JsonFileReader.getJson(RegionActivity.this, PROVINCE_DATA);
        parseJson(province_data_json);
        cities = filledData(cities);
        Collections.sort(cities, pinyinComparator);
        friendAdapter = new RegionListAdapter(this, cities);

        listRegion.setAdapter(friendAdapter);

    }


    public int getSectionForPosition(int position) {
        return cities.get(position).getFristLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < cities.size(); i++) {
            String sortStr = cities.get(i).getFristLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }


    public void parseJson(String str) {

        try {
            //  获取json中的数组
            cities = new ArrayList<>();

            JSONArray jsonArray = new JSONArray(str);
            //  遍历数据组
            for (int i = 0; i < jsonArray.length(); i++) {
                //  获取省份的对象
                JSONObject provinceObject = jsonArray.optJSONObject(i);
                //  获取省份名称放入集合

                JSONArray cityArray = provinceObject.optJSONArray(JSON_CITY);
                //   声明存放城市的集合

                //  遍历城市数组
                for (int j = 0; j < cityArray.length(); j++) {
                    //  获取城市对象
                    JSONObject cityObject = cityArray.optJSONObject(j);
                    //  将城市放入集合
                    String cityName = cityObject.optString(JSON_NAME);


                    //  获取区县的数组
                    JSONArray areaArray = cityObject.optJSONArray(JSON_AREA);
                    //  遍历区县数组，获取到区县名称并放入集合
                    for (int k = 0; k < areaArray.length(); k++) {
                        String areaName = areaArray.getString(k);
                        RegionBean friend = new RegionBean();
                        friend.setName(areaName);
                        cities.add(friend);

                    }
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}

