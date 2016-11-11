package lanou.around.widget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;

import lanou.around.R;

public class LayoutAdder {
	
	/*
	 * 
	 * 该类是一个Layout控制器，通过传入的Layout的container（一个先行的布局），往改布局中加入一个footer和一个Header,该container处于一个scrollview中
	 */

	private LinearLayout outerContainerScrollView;
	private LinearLayout innerContainerLayout;
	private LayoutInflater inflater;
	private View headerView;
	//----headerview下面的子View
	private TextView updateTimeTextView;//显示上次更新时间
	private TextView stateTextView;//用于显示下拉，上拉，正在刷新等状态
	private ProgressBar progressBar;
	private ImageView arrowView;
	private   int viewHeaderHight;
	int downY = 0;
	int  moveY;
	boolean isUpEdge=false;
	  int scrollviewScrolledPosistion,heightInner,heightScroller;
	
	private enum state{
		STATE_NONE,//常规状态
		STATE_PULL_TO_REFRESH,//下拉刷新状态
		STATE_RELEASE_TO_REFRESH,//释放刷新状态
		STATE_REFRESHING//正在是刷先状态
	}
	
	private state currentState= state.STATE_NONE;
	private PullToRefreshListener listener;
	private RotateAnimation animationRotate01, animationRotate02;

	public LayoutAdder(LinearLayout innerContainer, LinearLayout outerContainer, Context context){
		this.outerContainerScrollView=outerContainer;
		this.innerContainerLayout=innerContainer;
		inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		initAnimation();//初始化动画
		initHeaderView();
		setScrollViewOnTouchListener();
		
	}


	private void setScrollViewOnTouchListener() {
		
	
     outerContainerScrollView.setOnTouchListener(new View.OnTouchListener() {
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY=(int) event.getRawY();
			Log.d("mytest", "actionDown"+downY);
		
			break;
		case MotionEvent.ACTION_MOVE:
		      if(currentState== state.STATE_REFRESHING){
		    	  
		    	  return false;//这个时间传递给其子View
		      }
		      
		      moveY=(int)event.getRawY();
		      Log.d("mytest", "actionmove"+moveY);
		      /*
		       * 
		       * 下面需要确定在actionMove状态下，是不是在scrollview的顶部或者最底部，只有在顶部或者底部时才能触发headerview界面的改变
		       * 要确定scrollview是否在顶部，需要看scrollVIEW移动的高度为0，那么isUpEdge=true
		       */
		      if(heightScroller==0){
		    	  heightScroller=outerContainerScrollView.getHeight();
		    	  heightInner=innerContainerLayout.getHeight();
		    	  
		      }
		      
		//     scrollviewScrolledPosistion=outerContainerScrollView.getScrollY();
		     
//		      if(!isUpEdge&&scrollviewScrolledPosistion<=0){
//		    	  isUpEdge=true; 
//		      }
		
		      
		      int distance=moveY-downY;
		      
		    	if (!(distance < -6 || distance > 6)) {

					// 返回true表示执行自身的ontouch方法，若返回false则执行子view的onTouch方法进行处理

					return false;// 交给其他的方法去处理

				}
		    	
		    	isUpEdge=true;
		      if(isUpEdge&&distance>0&&(currentState!= state.STATE_REFRESHING)){
		    	  prepareToRefreshing(distance);
		    	  return true;
		      }
			
		    break;
			
		case MotionEvent.ACTION_UP:
			isUpEdge=false;
			if(currentState== state.STATE_PULL_TO_REFRESH||currentState== state.STATE_RELEASE_TO_REFRESH){
				if(getPaddingTop(headerView)>0){//真刷新状态
					Log.d("mytest", "--currentstate "+currentState+"--padding:"+getPaddingTop(headerView));
					headerRefreshing();
				}else{
					//----隐藏headview,做一个线程来执行隐藏
					//hideHeaderViewByThread();
					Log.d("mytest", "--currentstate "+currentState+"--padding:"+getPaddingTop(headerView));
					currentState= state.STATE_NONE;
					//hideHeaderByThread();
				  setPaddingTop(headerView, -viewHeaderHight);
				}
				
				
			}
			
			break;
		}
		return true;
	}





});
		
	}


	
	private void headerRefreshing() {
	currentState= state.STATE_REFRESHING;
	Log.d("mytest", "---refresing--paddingbefore "+getPaddingTop(headerView));
	setPaddingTop(headerView, 0);
	arrowView.setVisibility(View.GONE);
	arrowView.setImageDrawable(null);
	progressBar.setVisibility(View.VISIBLE);
	stateTextView.setVisibility(View.VISIBLE);
	stateTextView.setText("正在刷新，请稍后...");
	Log.d("mytest", "---refresing--padding "+getPaddingTop(headerView));
	if(listener!=null){
		listener.onPullToRefreshing();
		}
	}
	
	

	private void initHeaderView() {
		
	headerView=	inflater.inflate(R.layout.header, null);
	updateTimeTextView=(TextView) headerView.findViewById(R.id.header_lastUpdatedTextView);
	progressBar=(ProgressBar) headerView.findViewById(R.id.header_progressBar);
	stateTextView=(TextView) headerView.findViewById(R.id.header_tipsTextView);
	arrowView=(ImageView) headerView.findViewById(R.id.header_arrowImageView);
	updateTimeTextView.setText("------");
	measureView(headerView);//初始化时先测量一下View
	
    viewHeaderHight=headerView.getMeasuredHeight();
    setPaddingTop(headerView,-1*viewHeaderHight);//先隐藏
    Log.d("mytest", "viewheader_height:"+viewHeaderHight+"---padding"+getPaddingTop(headerView));
    innerContainerLayout.addView(headerView,0);
	}
	
	
	//初始化动画
	private void initAnimation() {

		animationRotate01 = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

		animationRotate01.setInterpolator(new LinearInterpolator());

		animationRotate01.setDuration(250);

		animationRotate01.setFillAfter(true);

		animationRotate02 = new RotateAnimation(-180, 0,

		RotateAnimation.RELATIVE_TO_SELF, 0.5f,

		RotateAnimation.RELATIVE_TO_SELF, 0.5f);

		animationRotate02.setInterpolator(new LinearInterpolator());

		animationRotate02.setDuration(250);

		animationRotate02.setFillAfter(true);

	}
	
	private void prepareToRefreshing(int distance) {
		setPaddingTop(headerView, -viewHeaderHight+distance/3);
		int headerPaddintTop=getPaddingTop(headerView);
		if(headerPaddintTop>=0&&currentState!= state.STATE_RELEASE_TO_REFRESH){
			currentState= state.STATE_RELEASE_TO_REFRESH;
		}
		
		if(headerPaddintTop<0&&currentState!= state.STATE_PULL_TO_REFRESH){
			currentState= state.STATE_PULL_TO_REFRESH;
		}
		
		
		if(currentState== state.STATE_PULL_TO_REFRESH){
			stateTextView.setVisibility(View.VISIBLE);
			updateTimeTextView.setVisibility(View.VISIBLE);
		    arrowView.clearAnimation();
		    arrowView.startAnimation(animationRotate01);//顺时针旋转180度的动画
			progressBar.setVisibility(View.GONE);
			stateTextView.setText("下拉刷新");
			
		}else if(currentState== state.STATE_RELEASE_TO_REFRESH){
			stateTextView.setVisibility(View.VISIBLE);
			updateTimeTextView.setVisibility(View.VISIBLE);
			arrowView.clearAnimation();
			 arrowView.startAnimation(animationRotate02);//
			progressBar.setVisibility(View.GONE);
			stateTextView.setText("释放刷新");	
		}
		
		
	}

	private void setPaddingTop(View view, int topPadding) {
		Log.d("mytest", "setpadding:"+topPadding);
		view.setPadding(0, topPadding, 0, 0);	
	}
	
  private int getPaddingTop(View view){
	  return  view.getPaddingTop();
  }

	private void measureView(View viewSholdBeMeasure) {
		//用于测量View的宽和高，主要使用view.measur(int widthSpec,int hightSpe),此处要获得这两个参数，再调用measure()方法
		
		ViewGroup.LayoutParams viewLayoutParams=viewSholdBeMeasure.getLayoutParams();
		if(viewLayoutParams==null){
			viewLayoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		
		
		int widthSpecificationConstraint= ViewGroup.getChildMeasureSpec(0, 0, viewLayoutParams.width);
		int heightSpecificationConstraint;
		if(viewLayoutParams.height>0){
			heightSpecificationConstraint= MeasureSpec.makeMeasureSpec(0, MeasureSpec.EXACTLY );
		}else{
			heightSpecificationConstraint= MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED );
		}
		
		viewSholdBeMeasure.measure(widthSpecificationConstraint, heightSpecificationConstraint);
		
		
	}
	
	public void setRefreshingCompleted(){
		
		currentState= state.STATE_NONE;
		setPaddingTop(headerView, -viewHeaderHight);
		arrowView.setImageResource(R.mipmap.arrow_down);// 由于动画的原因，这里反�?设置的图片是朝上
		arrowView.setVisibility(View.VISIBLE);
		updateTimeTextView.setVisibility(View.VISIBLE);
		stateTextView.setVisibility(View.GONE);
		progressBar.setVisibility(View.GONE);
		updateTimeTextView.setText("更新于："+new Date().toLocaleString());
	}
	
	public void  setOnPullToRefreshListener(PullToRefreshListener listener){
		if(listener!=null){
			this.listener=listener;
		}
		
	}
	public interface PullToRefreshListener{
		
		public void onPullToRefreshing();
		
	}


}
