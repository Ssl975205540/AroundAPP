package lanou.around.video;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.tedcoder.wkvideoplayer.util.DensityUtil;
import com.android.tedcoder.wkvideoplayer.view.MediaController;
import com.android.tedcoder.wkvideoplayer.view.SuperVideoPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lanou.around.R;
import lanou.around.base.BaseFragment;
import lanou.around.flingswipe.SwipeFlingAdapterView;

/**
 * Created by dllo on 16/10/22.
 */

public class VideoFragment extends BaseFragment implements SwipeFlingAdapterView.onFlingListener,
        SwipeFlingAdapterView.OnItemClickListener {

    private TextView video_title;
    private ImageButton video_code;
    private SuperVideoPlayer mSuperVideoPlayer;

    String[] names = {"梅嘲讽", "我差点就信了", "淮秀帮", "咸鱼", "徐老师来巡山", "鬼畜精选"};
    String[] citys = {"9999", "8888", "7777", "6666", "1111", "2222"};

    String[] edus = {"asdia ", " adha", "irs ", "uhuhehye", "ahueddZZ"};

    String[] years = {"1年", "2年", "3年", "4年", "5年"};

    Random ran = new Random();


    private int cardWidth;
    private int cardHeight;

    private SwipeFlingAdapterView swipeView;
    private InnerAdapter adapter;


    @Override
    protected int setContentView() {
        return R.layout.video_fragment;


    }

    @Override
    protected void initViews() {
        //标题栏
        video_title = findView(R.id.video_title_tv);
        video_code = findView(R.id.video_code);
        //卡片模式
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        cardWidth = (int) (dm.widthPixels - (2 * 18 * density));
        cardHeight = (int) (dm.heightPixels - (338 * density));
        swipeView = (SwipeFlingAdapterView) findView(R.id.video_swipe_view);
        swipeView.setFlingListener(this);
        swipeView.setOnItemClickListener(this);

        //适配器
        adapter = new InnerAdapter(getContext());

        adapter.setCardHight(cardHeight);
        adapter.setCardWidth(cardWidth);
        swipeView.setAdapter(adapter);
        adapter.setDdd(new InnerAdapter.ddd() {
            @Override
            public void dad(SuperVideoPlayer superVideoPlayer, String url) {

                VideoFragment.this.mSuperVideoPlayer =  superVideoPlayer;
                mSuperVideoPlayer.setVideoPlayCallback(mVideoPlayCallback);
                mSuperVideoPlayer.setAutoHideController(false);
                Uri uri = Uri.parse(url);
                mSuperVideoPlayer.loadAndPlay(uri,0);
            }
        });

    }

    @Override
    protected void initListeners() {


    }

    @Override
    protected void initData() {
        //设置标题栏
        video_title.setText("视频");
        video_code.setImageResource(R.mipmap.ic_launcher);


    }

    @Override
    public void onItemClicked(MotionEvent event, View v, Object dataObject) {

    }

    @Override
    public void removeFirstObjectInAdapter() {
        adapter.remove(0);

    }

    @Override
    public void onLeftCardExit(Object dataObject) {

    }

    @Override
    public void onRightCardExit(Object dataObject) {

    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        loadData();
        Log.d("VideoFragment", "5555555");
        if (itemsInAdapter == 3) {

            loadData();
        }

    }

    private void loadData() {
        new AsyncTask<Void, Void, List<Talent>>() {
            @Override
            protected List<Talent> doInBackground(Void... params) {
                ArrayList<Talent> list = new ArrayList<>(10);
                Talent talent;
                for (int i = 0; i < 10; i++) {
                    talent = new Talent();

                    talent.nickname = names[ran.nextInt(names.length - 1)];
                    talent.cityName = citys[ran.nextInt(citys.length - 1)];
                    talent.educationName = edus[ran.nextInt(edus.length - 1)];
                    talent.workYearName = years[ran.nextInt(years.length - 1)];
                    list.add(talent);
                }
                return list;
            }


            @Override
            protected void onPostExecute(List<Talent> list) {
                super.onPostExecute(list);

                adapter.addAll(list);


            }
        }.execute();
    }
    @Override
    public void onScroll(float progress, float scrollXProgress) {

    }

    public static class Talent {

        public String nickname;
        public String cityName;
        public String educationName;
        public String workYearName;
    }

    /**
     * 播放器的回调函数
     */
    private SuperVideoPlayer.VideoPlayCallbackImpl mVideoPlayCallback = new SuperVideoPlayer.VideoPlayCallbackImpl() {
        /**
         * 播放器关闭按钮回调
         */
        @Override
        public void onCloseVideo() {
            mSuperVideoPlayer.close();//关闭VideoView
//            mPlayBtnView.setVisibility(View.VISIBLE);
            mSuperVideoPlayer.setVisibility(View.GONE);
            resetPageToPortrait();
        }

        /**
         * 播放器横竖屏切换回调
         */
        @Override
        public void onSwitchPageType() {
            if (getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
            } else {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                mSuperVideoPlayer.setPageType(MediaController.PageType.EXPAND);
            }
        }

        /**
         * 播放完成回调
         */
        @Override
        public void onPlayFinish() {

        }
    };



    /***
     * 旋转屏幕之后回调
     *
     * @param newConfig newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (null == mSuperVideoPlayer) return;
        /***
         * 根据屏幕方向重新设置播放器的大小
         */
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getActivity().getWindow().getDecorView().invalidate();
            float height = DensityUtil.getWidthInPx(getContext());
            float width = DensityUtil.getHeightInPx(getContext());
            mSuperVideoPlayer.getLayoutParams().height = (int) width;
            mSuperVideoPlayer.getLayoutParams().width = (int) height;
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            final WindowManager.LayoutParams attrs = getActivity().getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getActivity().getWindow().setAttributes(attrs);
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            float width = DensityUtil.getWidthInPx(getContext());
            float height = DensityUtil.dip2px(getContext(), 200.f);
            mSuperVideoPlayer.getLayoutParams().height = (int) height;
            mSuperVideoPlayer.getLayoutParams().width = (int) width;
        }
    }

    /***
     * 恢复屏幕至竖屏
     */
    private void resetPageToPortrait() {
        if (getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
        }
    }





}
