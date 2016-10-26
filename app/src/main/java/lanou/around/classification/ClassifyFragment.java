package lanou.around.classification;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.bean.ClassifyTabBean;
import lanou.around.classification.classifiview.CenterViewFragment;
import lanou.around.classification.classifiview.ClassifyViewAdapter;
import lanou.around.classification.classifiview.LeftViewFragment;
import lanou.around.classification.classifiview.RightViewFragment;
import lanou.around.presenter.ClassifyTabPresenter;
import lanou.around.tools.recycle.http.URLValues;
import lanou.around.widget.PullZoomView;

/**
 * Created by dllo on 16/10/22.
 */

public class ClassifyFragment extends BaseFragment implements InterView<ClassifyTabBean> {

    private ViewPager mViewPager;
    private RecyclerView mRecyclerView;
    private List<ClassifyBean> strings;
    private PullZoomView mPzv;
    private ImageView mPhoto;
    private TextView mTitle;
    private TextView mMessage;
    private ArrayList<ImageView> dots = new ArrayList<>();
    private LinearLayout mDotsLinear;


    @Override
    protected int setContentView() {
        return R.layout.class_fragment;
    }

    @Override
    protected void initViews() {
        mViewPager = findView(R.id.viewPager_classify);
        mRecyclerView = findView(R.id.recyclerView_classify);
        mPzv = findView(R.id.pzv);
        mPhoto = findView(R.id.iv_classify_photo);
        mTitle = findView(R.id.tv_classify_title);
        mMessage = findView(R.id.et_classify_message);
        mDotsLinear = findView(R.id.ll_viewpager);

    }

    @Override
    protected void initListeners() {
        ClassifyTabPresenter presenter = new ClassifyTabPresenter(this);
        presenter.startRequest(URLValues.CLASSIFY_EDITTEXT_TITLTE);
    }

    @Override
    protected void initData() {

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LeftViewFragment());
        fragments.add(new CenterViewFragment());
        fragments.add(new RightViewFragment());

        ClassifyViewAdapter adapter = new ClassifyViewAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        //创建小圆点个数
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(context);
            if (0 == i) {
                imageView.setImageResource(R.drawable.dot_normal);
            } else {
                imageView.setImageResource(R.drawable.dot_focus);
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
            params.setMargins(10, 0, 10, 0);
            imageView.setLayoutParams(params);
            dots.add(imageView);
            mDotsLinear.addView(dots.get(i));
        }
        viewPagerScallListener();


        strings = new ArrayList<>();
        strings.add(new ClassifyBean("手机", "有机品验机", 1));
        strings.add(new ClassifyBean("22222", "20202020", 2));
        strings.add(new ClassifyBean("33333", "30303030", 3));
        strings.add(new ClassifyBean("44444", "40404040", 4));
        strings.add(new ClassifyBean("55555", "50505050", 5));


        ClassifyAdapter myAdapter = new ClassifyAdapter(context);
        myAdapter.setStrings(strings);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(myAdapter);

        pullZoomViewData();
    }

    private void viewPagerScallListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int a;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (dots.size() > 0){
                    a = position % dots.size();
                    for (int i = 0; i < dots.size(); i++) {
                        if (i == a) {
                            dots.get(i).setImageResource(R.drawable.dot_normal);
                        } else {
                            dots.get(i).setImageResource(R.drawable.dot_focus);
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void pullZoomViewData() {
        Intent intent = getActivity().getIntent();
        float sensitive = intent.getFloatExtra("sensitive", 1.5f);
        int zoomTime = intent.getIntExtra("zoomTime", 500);
        boolean isParallax = intent.getBooleanExtra("isParallax", true);
        boolean isZoomEnable = intent.getBooleanExtra("isZoomEnable", true);

        mPzv.setIsParallax(isParallax);
        mPzv.setIsZoomEnable(isZoomEnable);
        mPzv.setSensitive(sensitive);
        mPzv.setZoomTime(zoomTime);
        mPzv.setOnScrollListener(new PullZoomView.OnScrollListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                System.out.println("onScroll   t:" + t + "  oldt:" + oldt);
            }

            @Override
            public void onHeaderScroll(int currentY, int maxY) {
                System.out.println("onHeaderScroll   currentY:" + currentY + "  maxY:" + maxY);
            }

            @Override
            public void onContentScroll(int l, int t, int oldl, int oldt) {
                System.out.println("onContentScroll   t:" + t + "  oldt:" + oldt);
            }
        });
        mPzv.setOnPullZoomListener(new PullZoomView.OnPullZoomListener() {
            @Override
            public void onPullZoom(int originHeight, int currentHeight) {
                System.out.println("onPullZoom  originHeight:" + originHeight + "  currentHeight:" + currentHeight);
            }

            @Override
            public void onZoomFinish() {
                System.out.println("onZoomFinish");
            }
        });
    }

    @Override
    public void startAnimation() {

    }

    @Override
    public void stopAnimation() {

    }

    @Override
    public void onResponse(ClassifyTabBean classifyTabBean) {
        Picasso.with(context).load(classifyTabBean.getRespData().getPhotoUrl()).into(mPhoto);
        mTitle.setText(classifyTabBean.getRespData().getShowName());
        mMessage.setText(classifyTabBean.getRespData().getInputName());

    }

    @Override
    public void onError() {

    }

    @Override
    public void onDestroyView() {
        dots.clear();
        super.onDestroyView();
    }
}
