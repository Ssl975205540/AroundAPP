package lanou.around.main;


import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import lanou.around.R;
import lanou.around.base.BaseActivity;
import lanou.around.classification.classify.ClassifyFragment;
import lanou.around.home.HomeFragment;
import lanou.around.login.LoginFragment;
import lanou.around.tools.recycle.StatusBarUtils;
import lanou.around.video.VideoFragment;

import static lanou.around.R.id.fl_main;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivHome;
    private ImageView ivClass;
    private ImageView ivVideo;
    private ImageView ivLogin;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private HomeFragment homeFragment;
    private Fragment fragmentGank;
    private boolean back = false;
    private ClassifyFragment classFragment;
    private VideoFragment videoFragment;
    private LoginFragment loginFragment;
    private ImageView iv_release;
    private PopupWindow popupWindow;
    private FrameLayout flMain;
    private boolean has = true;
    private int statusBarHeight;


    @Override
    protected int setContentView() {

        return R.layout.activity_main;

    }

    @Override
    protected void initViews() {

        ivHome = findView(R.id.iv_home);
        ivClass = findView(R.id.iv_class);
        ivVideo = findView(R.id.iv_video);
        ivLogin = findView(R.id.iv_login);

        flMain= findView(R.id.fl_main);







        iv_release = findView(R.id.iv_release);
    }

    @Override
    protected void initListeners() {
        ivHome.setOnClickListener(this);
        ivClass.setOnClickListener(this);
        ivVideo.setOnClickListener(this);
        ivLogin.setOnClickListener(this);
        iv_release.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment();


        fragmentTransaction.add(fl_main, homeFragment);
        Log.d("statusBarHeight00", String.valueOf(statusBarHeight));



        fragmentTransaction.commit();
        fragmentGank = homeFragment;


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_home:

                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }

                switchContent(homeFragment);

                StatusBarUtils.setWindowStatusBarColor(this,R.color.black_a10_color);

                ivHome.setBackgroundResource(R.mipmap.ma);
                ivClass.setBackgroundResource(R.mipmap.vp);
                ivLogin.setBackgroundResource(R.mipmap.sw);
                ivVideo.setBackgroundResource(R.mipmap.sy);

                break;
            case R.id.iv_class:

                if (classFragment == null) {
                    classFragment = new ClassifyFragment();
//
                }
                classFragment.setStatusBarHeight(statusBarHeight);
                StatusBarUtils.setWindowStatusBarColor(this,R.color.home_adapter_item);

                switchContent(classFragment);
                
                ivHome.setBackgroundResource(R.mipmap.m_);
                ivClass.setBackgroundResource(R.mipmap.vq);
                ivLogin.setBackgroundResource(R.mipmap.sw);
                ivVideo.setBackgroundResource(R.mipmap.sy);

                break;
            case R.id.iv_video:
                if (videoFragment == null) {
                    videoFragment = new VideoFragment();
                }
                videoFragment.setStatusBarHeight(statusBarHeight);
                switchContent(videoFragment);
//                Intent intent = new Intent();
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                intent.setClass(MainActivity.this, VideoActivity.class);
//                startActivity(intent);
//                overridePendingTransition(0, 0);
                ivHome.setBackgroundResource(R.mipmap.m_);
                ivClass.setBackgroundResource(R.mipmap.vp);
                ivLogin.setBackgroundResource(R.mipmap.sw);
                ivVideo.setBackgroundResource(R.mipmap.sz);

                break;
            case R.id.iv_login:
                if (loginFragment == null) {
                    loginFragment = new LoginFragment();
                }
                switchContent(loginFragment);
                ivLogin.setBackgroundResource(R.mipmap.sx);
                ivHome.setBackgroundResource(R.mipmap.m_);
                ivClass.setBackgroundResource(R.mipmap.vp);
                ivVideo.setBackgroundResource(R.mipmap.sy);

                break;


            case R.id.iv_release:



//
                Intent intent = new Intent(MainActivity.this,ReleaseActivity.class);

                startActivity(intent);



                break;
        }
    }



    public void switchContent(Fragment to) {

        if (fragmentGank != to) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();


            if (!to.isAdded()) {

                transaction.hide(fragmentGank).add(fl_main, to).commit();
            } else {

                transaction.hide(fragmentGank).show(to).commit();
            }
            fragmentGank = to;
        }
    }

    @Override
    public void onBackPressed() {


        if (back == false) {
            onWindowFocusChanged(true);
            Toast.makeText(this, "再按一次退出转转", Toast.LENGTH_SHORT).show();
            back = true;
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(has){
            Rect frame = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            // 状态栏高度
            statusBarHeight = frame.top;
            Log.d("statusBarHeight0", String.valueOf(statusBarHeight));
            homeFragment.setStatusBarHeight(statusBarHeight);
            has = false;
        }


    }



}
