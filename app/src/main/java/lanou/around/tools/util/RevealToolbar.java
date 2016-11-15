package lanou.around.tools.util;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by adiK on 29-May-15.
 */
public class RevealToolbar {

    static protected Activity activity;
    static protected Drawable drawable;
    static protected int color = Color.WHITE;
    static protected int Size = 72;
    static ImageView fab;
    static protected int fabX, fabY;
    static float revealX, revealY;
    static float revealRadius;
    static SupportAnimator revealAnimator, hideAnimator;
//    static ArcAnimator revealArc, hideArc;

//    public static void Fab(Activity act, Drawable draw, int clr, int size) {
//        activity = act;
//        drawable = draw;
//        color = clr;
//        Size = size;
//
//    }
//
//    //设置位置大小
//    public static FloatingActionButton Create() {
//        fab = new FloatingActionButton.Builder(activity)
//                .withDrawable(drawable)
//                .withButtonColor(color)
//                .withMargins(0, 0, 50, 50)
//                .withGravity(Gravity.RIGHT | Gravity.TOP)
//                .create();
//        fab.setVisibility(View.VISIBLE);
//        return fab;
//
//    }

    public static void setView(ImageView view) {

        fab = view;

    }

    public static boolean Reveal(final View Myview) {

//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
//        params.setMargins(Myview.getLeft(),Myview.getTop(),Myview.getRight(), Myview.getBottom());
//        params.gravity = (Gravity.BOTTOM);
//        Myview.setLayoutParams(params);


//        fabX = (fab.getLeft() + fab.getRight()) / 2;
//        fabY = (fab.getTop() + fab.getBottom()) / 2;
//        revealX = (Myview.getLeft() + Myview.getRight()) / 2;
//        revealY = (Myview.getTop() + Myview.getBottom()) / 2;

        revealRadius = Math.max(Myview.getWidth(), Myview.getHeight());
        revealAnimator = ViewAnimationUtils.createCircularReveal(Myview, -Myview.getWidth() / 2, Myview.getHeight(), revealRadius, -100);
        revealAnimator.setDuration(200);
        revealAnimator.start();


//        revealAnimator.start();
//        revealAnimator.setInterpolator(new AccelerateInterpolator());
//        revealArc = ArcAnimator.createArcAnimator(fab, fabX, fabY, +5, Side.LEFT);
//        revealArc.setDuration(300);
//这个是按钮的动画
//        revealArc.setInterpolator(new AccelerateInterpolator());
//        revealArc.start();
//        revealArc.addListener(new com.nineoldandroids.animation.Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(com.nineoldandroids.animation.Animator animation) {
//
//
//            }
//
//            @Override
//            public void onAnimationEnd(com.nineoldandroids.animation.Animator animation) {
////                fab.hideFloatingActionButton();
//                fab.setVisibility(View.INVISIBLE);
//                fab.setEnabled(false);
//
//
//            }
//
//            @Override
//            public void onAnimationCancel(com.nineoldandroids.animation.Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(com.nineoldandroids.animation.Animator animation) {
//
//            }
//        });
//
        return true;

    }

    public static boolean HideReveal(final View Rootview) {
        Log.d("RevealToolbar", "revealRadius:" + revealRadius);
        Log.d("RevealToolbar", "fabY:" + fabY);
        revealRadius = Math.max(Rootview.getWidth(), Rootview.getHeight());
        revealAnimator = ViewAnimationUtils.createCircularReveal(Rootview, Rootview.getWidth(), Rootview.getHeight() / 2, revealRadius, -100);

//        SupportAnimator revealAnimator1 = ViewAnimationUtils.createCircularReveal(fab, Rootview.getWidth(), Rootview.getHeight() / 2, revealRadius, -100);
        revealAnimator.setDuration(200);
        revealAnimator.start();


        //动画
//        revealAnimator.setInterpolator(new AccelerateInterpolator());
//        revealArc = ArcAnimator.createArcAnimator(fab, fabX, fabY, -5, Side.RIGHT);
//        revealArc.setDuration(120);
//        revealArc.setInterpolator(new AccelerateInterpolator());

        revealAnimator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {

                Rootview.setVisibility(View.INVISIBLE);
//                fab.setVisibility(View.VISIBLE);
//                fab.setEnabled(true);
//                fab.showFloatingActionButton();
//                revealArc.start();
//
            }

            @Override
            public void onAnimationCancel() {

            }

            @Override
            public void onAnimationRepeat() {

            }
        });


        return true;

    }


}
