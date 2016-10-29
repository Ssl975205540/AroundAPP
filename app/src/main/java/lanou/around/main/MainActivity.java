package lanou.around.main;


import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;

import lanou.around.R;
import lanou.around.base.BaseActivity;
import lanou.around.classification.ClassifyFragment;
import lanou.around.home.HomeFragment;
import lanou.around.login.LoginFragment;
import lanou.around.video.VideoFragment;


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
    private View view;
    private LinearLayout pop_linear;
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
        view = LayoutInflater.from(this).inflate(R.layout.popupwindow_view, null);

        pop_linear = findView(view, R.id.pop_linear);

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
        fragmentTransaction.add(R.id.fl_main, homeFragment);
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

                ivHome.setBackgroundResource(R.mipmap.ma);
                ivClass.setBackgroundResource(R.mipmap.vp);
                ivLogin.setBackgroundResource(R.mipmap.sw);
                ivVideo.setBackgroundResource(R.mipmap.sy);

                break;
            case R.id.iv_class:

                if (classFragment == null) {
                    classFragment = new ClassifyFragment(statusBarHeight);
                    StatusBarUtil.setColor(this, Color.BLACK);
                }

                switchContent(classFragment);
                //给此页的状态栏设置颜色 需添加依赖
                
                ivHome.setBackgroundResource(R.mipmap.m_);
                ivClass.setBackgroundResource(R.mipmap.vq);
                ivLogin.setBackgroundResource(R.mipmap.sw);
                ivVideo.setBackgroundResource(R.mipmap.sy);

                break;
            case R.id.iv_video:
                if (videoFragment == null) {
                    videoFragment = new VideoFragment();
                }
                switchContent(videoFragment);
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

                StatusBarUtil.setColor(this, Color.TRANSPARENT);

                break;


            case R.id.iv_release:

                popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, 400, true);
                // 设置动画效果
//                popupWindow.setAnimationStyle(R.style.AnimationFade);
                popupWindow.setAnimationStyle(R.style.j8);

                popupWindow.setBackgroundDrawable(new BitmapDrawable());

                popupWindow.showAtLocation(iv_release, Gravity.BOTTOM, 0, 0);


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

                break;
        }
    }



    public void switchContent(Fragment to) {

        if (fragmentGank != to) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();


            if (!to.isAdded()) {

                transaction.hide(fragmentGank).add(R.id.fl_main, to).commit();
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

        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        // 状态栏高度
        statusBarHeight = frame.top;
        homeFragment.setStatusBarHeight(statusBarHeight);

    }


}
