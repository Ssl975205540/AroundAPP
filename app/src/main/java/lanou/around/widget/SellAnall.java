package lanou.around.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import lanou.around.R;
import lanou.around.aroundinterface.InterToolBar;

/**
 * Created by dllo on 16/11/11.
 */

public class SellAnall extends View {
    private int mDuration;

    private int mAlpha = 0;

    private int DEFAULT_DURATION = 150;
    private Paint mPaintBg;
    private int MAX_WIDTH;
    private int MAX_HEIGHT;
    private int mState = 0;
    private int mMinusBtnPosition = 0;
    private int mWidth = 0;
    private boolean mIsForward = true;
    private InterToolBar interToolBar;

    public SellAnall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typeArray = getContext().obtainStyledAttributes(attrs,
                R.styleable.ShoppingView);

        //动画时间  后面的是默认没有设置的
        mDuration = typeArray.getInt(R.styleable.ShoppingView_sv_duration, DEFAULT_DURATION);
        //获得输入的字 后面的是默认没有设置字 应该走的

        //这个是设置的字的大小  如果没有设置字体大小 会走后面的方法获取一个大小
        int textSize = (int) typeArray.getDimension(R.styleable.ShoppingView_sv_text_size, sp2px(16));

        //背景色  获得设置的背景色
        int bgColor = typeArray.getColor(R.styleable.ShoppingView_sv_bg_color, ContextCompat.getColor(getContext(), R.color.sv_blue));

        //把获得的数据得到 要想生效 调这个方法
        typeArray.recycle();

        //这个画笔就是画背景的画笔
        mPaintBg = new Paint();
        mPaintBg.setColor(bgColor);
        mPaintBg.setStyle(Paint.Style.FILL);
        //这个属性是设置抗锯齿的  设置了就能好看点
        mPaintBg.setAntiAlias(true);

        MAX_WIDTH = 700;

        MAX_HEIGHT = 60;

//        if (MAX_WIDTH / (float) MAX_HEIGHT < 3.5) {
//            MAX_WIDTH = (int) (MAX_HEIGHT * 3.5);
//        }


    }

    //这个是获取一个字体大小用的,没什么卵用
    private float sp2px(int spValue) {

        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public SellAnall(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SellAnall(Context context) {
        this(context, null);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MAX_WIDTH, MAX_HEIGHT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("ShoppingView_onDraw", "mState:" + mState);
        Log.d("ShoppingView", "ccc");
        if (mState == 0) {
            //这个方法就是制作背景的
            drawBgMove(canvas);
            //这个方法是绘制文字的

        } else if (mState == 1) {
            //制作了一次背景
            drawBgMove(canvas);
        } else if (mState == 2) {
            mState = 3;
            if (mIsForward) {
                //这个是添加加减的
                drawAddBtn(canvas);
                Log.d("ShoppingView", "lll1111");

                startRotateAnim();
            } else {
                drawBgMove(canvas);
//                drawShoppingText(canvas);
                mState = 0;
                mIsForward = true;
//                mNum = 0;
            }
        } else if (mState == 3) {
            Log.d("ShoppingView", "lll");
//            mPaintMinus.setAlpha(mAlpha);
//            mPaintNum.setAlpha(mAlpha);
//            drawMinusBtn(canvas, mAngle);
//            drawNumText(canvas);
            drawAddBtn(canvas);
        } else if (mState == 4) {
//            drawMinusBtn(canvas, mAngle);
//            drawNumText(canvas);
            drawAddBtn(canvas);
            if (!mIsForward) {
                startMoveAnim();
            }
        }


    }

    private void drawAddBtn(Canvas canvas) {

//        canvas.drawCircle(MAX_WIDTH - MAX_HEIGHT, MAX_HEIGHT / 2, MAX_HEIGHT / 2, mPaintBg);
        canvas.drawCircle(MAX_WIDTH - MAX_HEIGHT, MAX_HEIGHT / 2, 30, mPaintBg);


    }

    public void startMoveAnim() {
        mState = 1;
        Log.d("ShoppingView", "mState:" + mState);
        ValueAnimator valueAnimator;
        if (mIsForward) {
            valueAnimator = ValueAnimator.ofInt(0, MAX_WIDTH - MAX_HEIGHT);
            Log.d("ShoppingView", String.valueOf(mIsForward));
        } else {
            valueAnimator = ValueAnimator.ofInt(MAX_WIDTH - MAX_HEIGHT, 0);
            Log.d("ShoppingView", String.valueOf(mIsForward));
        }
        valueAnimator.setDuration(mDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {


                mWidth = (Integer) valueAnimator.getAnimatedValue();
                if (mIsForward) {
                    //长变短
                    if (mWidth == MAX_WIDTH - MAX_HEIGHT) {
                        mState = 2;

                    }
                } else {
                    if (mWidth == 0) {
                        //短变长
                        mState = 2;
                        Log.d("ShoppingView2", "mState:" + mState);

                    }
                }

                invalidate();
            }
        });
        valueAnimator.start();


    }

    public void setBolll(InterToolBar interToolBar) {

        this.interToolBar = interToolBar;
        mState = 3;
        mIsForward = false;
    }

    public void startRotateAnim() {


        ValueAnimator animatorBtnMove;
        if (mIsForward) {
            animatorBtnMove = ValueAnimator.ofInt(MAX_WIDTH - MAX_HEIGHT / 2, MAX_HEIGHT / 2);
        } else {
            animatorBtnMove = ValueAnimator.ofInt(MAX_HEIGHT / 2, MAX_WIDTH - MAX_HEIGHT / 2);
        }
        animatorBtnMove.setDuration(mDuration);
        animatorBtnMove.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mMinusBtnPosition = (Integer) valueAnimator.getAnimatedValue();

                if (mIsForward) {
                    if (mMinusBtnPosition == MAX_HEIGHT / 2) {
                        mState = 4;
                    }
                } else {
                    if (mMinusBtnPosition == MAX_WIDTH - MAX_HEIGHT / 2) {
                        mState = 4;
                        interToolBar.setTools(mState);
                    }
                }

                invalidate();
            }
        });
        animatorBtnMove.start();


//        animatorList.add(animatorBtnMove);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.setDuration(mDuration);
//        animatorSet.playTogether(animatorList);
//        animatorSet.start();

    }


    private void drawBgMove(Canvas canvas) {

//        canvas.drawArc(new RectF(mWidth, 0, mWidth + MAX_HEIGHT, MAX_HEIGHT), 90, 180, false, mPaintBg);
        canvas.drawRect(new RectF(mWidth + MAX_HEIGHT / 2, 0, MAX_WIDTH - MAX_HEIGHT / 2, MAX_HEIGHT), mPaintBg);
//        canvas.drawArc(new RectF(MAX_WIDTH - MAX_HEIGHT, 0, MAX_WIDTH, MAX_HEIGHT), 180, 270, false, mPaintBg);
    }
}
