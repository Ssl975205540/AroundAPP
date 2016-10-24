package lanou.around.classification;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.base.BaseFragment;
import lanou.around.widget.PullZoomView;

import static lanou.around.R.id.viewPager;

/**
 * Created by dllo on 16/10/22.
 */

public class ClassifyFragment extends BaseFragment{

    private ViewPager mViewPager;
    private RecyclerView mRecyclerView;
    private List<ClassifyBean> strings;
    private PullZoomView mPzv;

    @Override
    protected int setContentView() {
        return R.layout.class_fragment;
    }

    @Override
    protected void initViews() {
        mViewPager = findView(viewPager);
        mRecyclerView = findView(R.id.recyclerView);
        mPzv = findView(R.id.pzv);

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fragments.add(new OnFragment());
        }
        ClassifyViewAdapter adapter = new ClassifyViewAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);


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

}
