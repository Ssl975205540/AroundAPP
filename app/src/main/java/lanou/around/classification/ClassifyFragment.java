package lanou.around.classification;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lanou.around.R;
import lanou.around.aroundinterface.InterView;
import lanou.around.base.BaseFragment;
import lanou.around.tools.http.URLValues;
import lanou.around.bean.ClassifyBean;
import lanou.around.bean.ClassifyTabBean;
import lanou.around.classification.classifiview.CenterViewFragment;
import lanou.around.classification.classifiview.ClassifyViewAdapter;
import lanou.around.classification.classifiview.LeftViewFragment;
import lanou.around.classification.classifiview.RightViewFragment;
import lanou.around.presenter.ClassifyTabPresenter;
import lanou.around.tools.recycle.DisplayUtil;
import lanou.around.widget.PullZoomView;
import lanou.around.widget.TransparentToolBar;

/**
 * Created by dllo on 16/10/22.
 */


public class ClassifyFragment extends BaseFragment

        implements InterView, TransparentToolBar.OnScrollStateListener {



    private ViewPager mViewPager;
    private RecyclerView mRecyclerView;
    private List<ClassifyBean> mClassifyBeanList;
    private PullZoomView mPzv;
    private ImageView mPhoto;
    private TextView mTitle;
    private TextView mMessage;
    private ArrayList<ImageView> dots = new ArrayList<>();
    private LinearLayout mDotsLinear;
    private TransparentToolBar mToolBar;
    private TextView mSearch;
    private ImageView mSearchPhoto;
    private LinearLayout mSearchText;
    private ImageView mCheck;


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
        mMessage = findView(R.id.tv_classify_message);
        mDotsLinear = findView(R.id.ll_viewpager);
        mToolBar = findView(R.id.toobar_classify);
        mSearch = findView(R.id.tv_classify_search);
        mSearchPhoto = findView(R.id.iv_classify_search);
        mSearchText = findView(R.id.ll_classify_search);
        mCheck = findView(R.id.iv_classify_check);

    }

    @Override
    protected void initListeners() {
        ClassifyTabPresenter presenter = new ClassifyTabPresenter(this);


        mToolBar.setOnScrollStateListener(this);
        mToolBar.setOffset(200);
        mToolBar.setBgColor(getResources().getColor(R.color.toolbar_home_color));
        mPzv.setTitleBar(mToolBar);

        presenter.startRequest(URLValues.CLASSIFY_EDITTEXT_TITLTE, ClassifyTabBean.class);

        presenter.startRequest(URLValues.CLASSIFY_WANT_BUY_MESSAGE, ClassifyBean.class);

        mSearchPhoto.setImageAlpha(0);
        mSearch.setAlpha(0);
        mSearchText.setAlpha(0);
        mCheck.setImageAlpha(0);


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
                if (dots.size() > 0) {
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
            float centerHeight = DisplayUtil.dip2px(context, 150);
            float endHeight = DisplayUtil.dip2px(context, 200);
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {

                if (t >= 0 && t < centerHeight) {
                    mToolBar.setBackgroundColor(Color.alpha(0));
                    mSearchPhoto.setImageAlpha(0);
                    mSearchText.setAlpha(0f);
                    mSearch.setAlpha(0);
                    mCheck.setAlpha(0);
                }
                if (t >= centerHeight && t <= endHeight) {
                    int alpha = (int) ((t - centerHeight) / (endHeight - centerHeight) * 255);
                    float alp = (t - centerHeight) / (endHeight - centerHeight) * 255;
                    Log.d("ClassifyFragment", "alpha:" + alpha);
                    Log.d("ClassifyFragment", "alp:" + alp);
                    mToolBar.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
                    mSearchPhoto.setImageAlpha(alpha);
                    mSearchText.setAlpha(alp);
                    mSearch.setAlpha(alp);
                    mCheck.setImageAlpha(alpha);

                }
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

        mPzv.setTitleBar(mToolBar);
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
    public void onResponse(Object classifyTabBean) {


        if (classifyTabBean instanceof ClassifyTabBean) {

            ClassifyTabBean classifyTabBean1 = (ClassifyTabBean) classifyTabBean;
            Picasso.with(context).load(classifyTabBean1.getRespData().getPhotoUrl()).into(mPhoto);
            mTitle.setText(classifyTabBean1.getRespData().getShowName());
            mMessage.setText(classifyTabBean1.getRespData().getInputName());
        }


        if (classifyTabBean instanceof ClassifyBean) {

            ClassifyBean classifyTabBean1 = (ClassifyBean) classifyTabBean;

            for (int i = 0; i < classifyTabBean1.getRespData().size(); i++) {
                classifyTabBean1.getRespData().get(i).setType(i);

            }
            ClassifyAdapter myAdapter = new ClassifyAdapter(context);
            myAdapter.setClassifyBean(classifyTabBean1);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            mRecyclerView.setAdapter(myAdapter);
        }


    }

    @Override
    public void onError() {

    }

    @Override
    public void onDestroyView() {
        dots.clear();
        super.onDestroyView();
    }


    @Override
    public void updateFraction(float fraction) {
        //ToolBar滚动回调的百分比0~1
    }

}
