package lanou.around.widget.flingswipe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * Created by dllo on 16/10/24.
 */

public class FlingCardListener implements View.OnTouchListener{
    private final float objectX;
    private final float objectY;
    private final int objectH;
    private final int objectW;
    private final int parentWidth;

    private final FlingListener mFlingListener;
    private final Object dataObject;
    private final float halfWidth;
    private float BASE_ROTATION_DEGREES;

    private float aPosX;
    private float aPosY;
    private float aDownTouchX;
    private float aDownTouchY;
    private static final int INVALID_POINTER_ID = -1;

    // 活跃的指针是我们目前移动一个对象
    private int mActivePointerId = INVALID_POINTER_ID;
    private View frame = null;

    private final int TOUCH_ABOVE = 0;
    private final int TOUCH_BELOW = 1;
    private int touchPosition;

    private boolean isAnimationRunning = false;
    private float MAX_COS = (float) Math.cos(Math.toRadians(45));
    // 支持左右滑
    private boolean isNeedSwipe = true;

    private float aTouchUpX;

    private int animDuration = 300;
    private float scale;

    public FlingCardListener(View frame, Object itemAtPosition, float rotation_degrees, FlingListener flingListener) {
        super();
        this.frame = frame;
        this.objectX = frame.getX();
        this.objectY = frame.getY();
        this.objectW = frame.getWidth();
        this.objectH = frame.getHeight();
        this.halfWidth = objectW/2f;
        this.dataObject = itemAtPosition;
        this.parentWidth = ((ViewGroup) frame.getParent()).getWidth();
        this.BASE_ROTATION_DEGREES = rotation_degrees;
        this.mFlingListener = flingListener;
    }
    //建立需要的卡片
    public void setIsNeedSwipe(boolean isNeedSwipe) {
        this.isNeedSwipe = isNeedSwipe;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        try {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:

                    //保存这个指针的ID
                    mActivePointerId = event.getPointerId(0);
                    final float x = event.getX(mActivePointerId);
                    final float y = event.getY(mActivePointerId);

                    // 记住开始的地方
                    aDownTouchX = x;
                    aDownTouchY = y;
                    // 防止初始跳的放大镜,aposX和aPosY须有放大镜帧的值

                    if (aPosX == 0) {
                        aPosX = frame.getX();
                    }
                    if (aPosY == 0) {
                        aPosY = frame.getY();
                    }

                    if (y < objectH/2) {
                        touchPosition = TOUCH_ABOVE;
                    } else {
                        touchPosition = TOUCH_BELOW;
                    }
                    break;

                case MotionEvent.ACTION_POINTER_DOWN:
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    // 提取的索引指针,离开了触觉传感器
                    final int pointerIndex = (event.getAction() &
                            MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                    final int pointerId = event.getPointerId(pointerIndex);
                    if (pointerId == mActivePointerId) {
                        // 这是我们活动的指针上升。选择一个新的活跃的指针,并相应地调整。
                        final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                        mActivePointerId = event.getPointerId(newPointerIndex);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:

                    // 找到积极的索引指针并获取其位置
                    final int pointerIndexMove = event.findPointerIndex(mActivePointerId);
                    final float xMove = event.getX(pointerIndexMove);
                    final float yMove = event.getY(pointerIndexMove);

                    // from http://android-developers.blogspot.com/2010/06/making-sense-of-multitouch.html
                    // 计算移动的距离
                    final float dx = xMove - aDownTouchX;
                    final float dy = yMove - aDownTouchY;

                    // 移动的框架
                    aPosX += dx;
                    aPosY += dy;

                    // 计算旋转度
                    float distObjectX = aPosX - objectX;
                    float rotation = BASE_ROTATION_DEGREES * 2f * distObjectX / parentWidth;
                    if (touchPosition == TOUCH_BELOW) {
                        rotation = -rotation;
                    }

                    // 在这方面会做一些与视图代码框架移动。
                    if (isNeedSwipe) {
                        frame.setX(aPosX);
                        frame.setY(aPosY);
                        frame.setRotation(rotation);
                        mFlingListener.onScroll(getScrollProgress(), getScrollXProgressPercent());
                    }
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    //mActivePointerId = INVALID_POINTER_ID;
                    int pointerCount = event.getPointerCount();
                    int activePointerId = Math.min(mActivePointerId, pointerCount - 1);
                    aTouchUpX = event.getX(activePointerId);
                    mActivePointerId = INVALID_POINTER_ID;
                    resetCardViewOnStack(event);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }


    private float getScrollProgress() {
        float dx = aPosX - objectX;
        float dy = aPosY - objectY;
        float dis = Math.abs(dx) + Math.abs(dy);
        return Math.min(dis, 400f) / 400f;
    }

    private float getScrollXProgressPercent() {
        if (movedBeyondLeftBorder()) {
            return -1f;
        } else if (movedBeyondRightBorder()) {
            return 1f;
        } else {
            float zeroToOneValue = (aPosX + halfWidth - leftBorder()) / (rightBorder() - leftBorder());
            return zeroToOneValue * 2f - 1f;
        }
    }

    private boolean resetCardViewOnStack(MotionEvent event) {
        if (isNeedSwipe) {
            final int duration = 200;
            if(movedBeyondLeftBorder()){
                // 左滑动
                onSelected(true, getExitPoint(-objectW), duration);
                mFlingListener.onScroll(1f, -1.0f);
            }else if(movedBeyondRightBorder()) {
                // 右边滑动
                onSelected(false, getExitPoint(parentWidth), duration);
                mFlingListener.onScroll(1f, 1.0f);
            }else{
                float absMoveXDistance = Math.abs(aPosX-objectX);
                float absMoveYDistance = Math.abs(aPosY-objectY);
                if(absMoveXDistance < 4 && absMoveYDistance < 4){
                    mFlingListener.onClick(event, frame, dataObject);
                }else{
                    frame.animate()
                            .setDuration(animDuration)
                            .setInterpolator(new OvershootInterpolator(1.5f))
                            .x(objectX)
                            .y(objectY)
                            .rotation(0)
                            .start();
                    scale = getScrollProgress();
                    this.frame.postDelayed(animRun, 0);
                }
                aPosX = 0;
                aPosY = 0;
                aDownTouchX = 0;
                aDownTouchY = 0;
            }
        } else {
            float distanceX = Math.abs(aTouchUpX - aDownTouchX);
            if(distanceX < 4)
                mFlingListener.onClick(event, frame, dataObject);
        }
        return false;
    }

    private Runnable animRun = new Runnable() {
        @Override
        public void run() {
            mFlingListener.onScroll(scale, 0);
            if (scale > 0) {
                scale = scale - 0.1f;
                if (scale < 0)
                    scale = 0;
                frame.postDelayed(this, animDuration / 20);
            }
        }
    };

    private boolean movedBeyondLeftBorder() {
        return aPosX+halfWidth < leftBorder();
    }

    private boolean movedBeyondRightBorder() {
        return aPosX+halfWidth > rightBorder();
    }


    public float leftBorder(){
        return parentWidth/4f;
    }

    public float rightBorder(){
        return 3*parentWidth/4f;
    }


    public void onSelected(final boolean isLeft, float exitY, long duration){
        isAnimationRunning = true;
        float exitX;
        if(isLeft) {
            exitX = -objectW-getRotationWidthOffset();
        }else {
            exitX = parentWidth+getRotationWidthOffset();
        }

        this.frame.animate()
                .setDuration(duration)
                .setInterpolator(new LinearInterpolator())
                .translationX(exitX)
                .translationY(exitY)
                //.rotation(isLeft ? -BASE_ROTATION_DEGREES:BASE_ROTATION_DEGREES)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (isLeft) {
                            mFlingListener.onCardExited();
                            mFlingListener.leftExit(dataObject);
                        } else {
                            mFlingListener.onCardExited();
                            mFlingListener.rightExit(dataObject);
                        }
                        isAnimationRunning = false;
                    }
                }).start();
    }

    /**
     * Starts a default left exit animation.
     */
    public void selectLeft(){
        if(!isAnimationRunning)
            selectLeft(animDuration);
    }
    /**
     * Starts a default left exit animation.
     */
    public void selectLeft(long duration){
        if(!isAnimationRunning)
            onSelected(true, objectY, duration);
    }

    /**
     * Starts a default right exit animation.
     */
    public void selectRight(){
        if(!isAnimationRunning)
            selectRight(animDuration);
    }
    /**
     * Starts a default right exit animation.
     */
    public void selectRight(long duration){
        if(!isAnimationRunning)
            onSelected(false, objectY, duration);
    }

    private float getExitPoint(int exitXPoint) {
        float[] x = new float[2];
        x[0] = objectX;
        x[1] = aPosX;

        float[] y = new float[2];
        y[0] = objectY;
        y[1] = aPosY;

        LinearRegression regression = new LinearRegression(x,y);

        //Your typical y = ax+b 线性回归
        return (float) regression.slope() * exitXPoint +  (float) regression.intercept();
    }

    private float getExitRotation(boolean isLeft){
        float rotation = BASE_ROTATION_DEGREES * 2f * (parentWidth - objectX)/parentWidth;
        if (touchPosition == TOUCH_BELOW) {
            rotation = -rotation;
        }
        if(isLeft){
            rotation = -rotation;
        }
        return rotation;
    }

    /**
     * When the object rotates it's width becomes bigger.
     * The maximum width is at 45 degrees.
     *
     * The below method calculates the width offset of the rotation.
     *
     */
    private float getRotationWidthOffset() {
        return objectW/MAX_COS - objectW;
    }


    public void setRotationDegrees(float degrees) {
        this.BASE_ROTATION_DEGREES = degrees;
    }


    protected interface FlingListener {
        void onCardExited();
        void leftExit(Object dataObject);
        void rightExit(Object dataObject);
        void onClick(MotionEvent event, View v, Object dataObject);
        void onScroll(float progress, float scrollXProgress);
    }
}
