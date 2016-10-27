package lanou.around.video;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Environment;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lanou.around.R;
import lanou.around.base.BaseFragment;
import lanou.around.bean.VideoDetailsBean;
import lanou.around.flingswipe.SwipeFlingAdapterView;
import lanou.around.tools.recycle.http.URLValues;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dllo on 16/10/22.
 */

public class VideoFragment extends BaseFragment implements SwipeFlingAdapterView.onFlingListener,
        SwipeFlingAdapterView.OnItemClickListener {

    private TextView video_title;
    private ImageButton video_code;
    private SuperVideoPlayer mSuperVideoPlayer;
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
        //适配器
        adapter = new InnerAdapter(getContext());


    }

    @Override
    protected void initLoad() {
        loadData();
    }

    @Override
    protected void initListeners() {
        swipeView.setFlingListener(this);
        swipeView.setOnItemClickListener(this);
//        VideoPresenter presenter = new VideoPresenter(this);
//        presenter.startRequest(URL_VIDEO);


    }

    @Override
    protected void initData() {

        //设置标题栏
        video_title.setText("视频");
        video_code.setImageResource(R.mipmap.ic_launcher);

        adapter.setCardHight(cardHeight);
        adapter.setCardWidth(cardWidth);
        adapter.setVideoSuper(new InnerAdapter.videoSuper() {
            @Override
            public void superVideo(SuperVideoPlayer superVideoPlayer, String url) {

                VideoFragment.this.mSuperVideoPlayer = superVideoPlayer;
                mSuperVideoPlayer.setVideoPlayCallback(mVideoPlayCallback);
                mSuperVideoPlayer.setAutoHideController(false);
                Uri uri = Uri.parse(url);
                mSuperVideoPlayer.loadAndPlay(uri, 0);
            }
        });
        loadData();


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
        if (itemsInAdapter == 3) {
            loadData();
        }

    }

    private void loadData() {

        File fileDir = Environment.getDownloadCacheDirectory();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .cache(new Cache(fileDir, 10 * 1024 * 1024))
                .build();
        FormBody body = new FormBody.Builder()
                .build();
        final Request request = new Request.Builder()
                .post(body)
                .url(URLValues.URL_VIDEO)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String str = response.body().string();
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<VideoDetailsBean>>() {
                    }.getType();
                    String s =  type.toString();
                    final List<VideoDetailsBean> been = gson.fromJson(str, type);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("VideoFragment", "been.size():" + been.size());
                            adapter.setVideoDetailsBeans(been);
                            swipeView.setAdapter(adapter);
                        }
                    });


                }
            }
        });

    }

    @Override
    public void onScroll(float progress, float scrollXProgress) {

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
