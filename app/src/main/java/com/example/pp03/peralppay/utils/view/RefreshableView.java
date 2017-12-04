//package com.example.pp03.peralppay.utils.view;
//
//import android.content.Context;
//import android.graphics.drawable.AnimationDrawable;
//import android.text.format.DateFormat;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.LinearInterpolator;
//import android.view.animation.RotateAnimation;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.ScrollView;
//import android.widget.Scroller;
//import android.widget.TextView;
//
//import com.peralppay.cashier.R;
//
//public class RefreshableView extends ViewGroup {
//
//
//	private final static int STATE_PREPARE_FOR_REFRESH = 2;
//	private final static int STATE_REFRESHING = 3;
//	private final static int STATE_REFRESHING_FINISHED = 6;
//	private final static int STATE_PULL_TO_REFRESH = 4;
//	private final static int STATE_RELEASE_TO_REFRESH = 5;
//
//
//	private int state = STATE_PULL_TO_REFRESH;
//
//	private Scroller scroller;
//	private View refreshView;
//	private ImageView refreshIndicatorView;
//	private ImageView bar;
//	private TextView downTextView;
//	private TextView timeTextView;
//
//	private RefreshListener refreshListener;
//
//	private String refreshTime = null;
//	private int lastY;
//
//	private Context mContext;
//
//	private RotateAnimation mFlipAnimation;
//
//	private RotateAnimation mReverseFlipAnimation;
//
//	public RefreshableView(Context context) {
//		super(context);
//		mContext = context;
//		init();
//	}
//
//	public RefreshableView(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		mContext = context;
//		init();
//
//	}
//
//	private void init() {
//		if (isInEditMode()) { return; }
//		// 滑动对象，
//		scroller = new Scroller(mContext, new LinearInterpolator());
//
//		// 刷新视图顶端的的view
//		refreshView = LayoutInflater.from(mContext).inflate(R.layout.listview_header_view, null);
//		// 指示器view
//	 	refreshIndicatorView = (ImageView) refreshView.findViewById(R.id.head_arrow_iv);
//		// 刷新bar
//		bar = (ImageView) refreshView.findViewById(R.id.head_loading_iv);
//		AnimationDrawable anim = (AnimationDrawable) getResources().getDrawable(R.drawable.pullloading_anim_list);
//		bar.setImageDrawable(anim);
//		anim.start();
//
//		// 下拉显示text
//		downTextView = (TextView) refreshView.findViewById(R.id.head_tips_tv);
//		// 下来显示时间
//		timeTextView = (TextView) refreshView.findViewById(R.id.head_lasttime_tv);
//		timeTextView.setText(getCurrentTime());
//
//		addView(refreshView);
//		// 滑动效果
//		mFlipAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//		mFlipAnimation.setDuration(200);
//		mFlipAnimation.setFillAfter(true);
//
//		// 箭头颠倒效果
//		mReverseFlipAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//		mReverseFlipAnimation.setDuration(200);
//		mReverseFlipAnimation.setFillAfter(true);
//	}
//
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//
//
//		if(state == STATE_PREPARE_FOR_REFRESH){
//			return true;
//		}
//
//		int y = (int) event.getRawY();
//
//		switch (event.getAction()) {
//			case MotionEvent.ACTION_DOWN:
//				// 记录下y坐标
//				lastY = y;
//				break;
//
//			case MotionEvent.ACTION_MOVE:
//				// Log.i(TAG, "ACTION_MOVE");
//				// y移动坐标
//
//				if(getScrollY()>0){
//					return true;
//				}
//
//				int m = y - lastY;
//
//				doMovement(m);
//
//				this.lastY = y;
//				break;
//
//			case MotionEvent.ACTION_CANCEL:
//			case MotionEvent.ACTION_UP:
//
//				fling();
//
//				break;
//		}
//		return true;
//	}
//
//	/**
//	 * up事件处理
//	 */
//	private void fling() {
//		if (Math.abs(getScrollY()) >= refreshView.getHeight()) {// 拉到了触发可刷新事件
//			state = STATE_PREPARE_FOR_REFRESH;
//			scroller.startScroll(getScrollX(), getScrollY(), 0, -getScrollY()-refreshView.getHeight(), 200);
//			invalidate();
//		} else {
//			// 回到初始状态
//			scroller.startScroll(0, getScrollY(), 0, -getScrollY(),200);
//			invalidate();
//		}
//	}
//
//
//
//	/**
//	 * 刷新操作
//	 */
//	private void refresh() {
//
//		refreshIndicatorView.clearAnimation();
//		refreshIndicatorView.setVisibility(View.GONE);
//		bar.setVisibility(View.VISIBLE);
//		downTextView.setText(R.string.doing_head_refresh);
//
//		if(refreshListener != null){
//			refreshListener.onRefresh();
//		}
//		state = STATE_REFRESHING;
//	}
//
//	@Override
//	public void computeScroll() {
//		if (isInEditMode()) { return; }
//		if (!scroller.isFinished()) {
//			if (scroller.computeScrollOffset()) {
//				int oldX = getScrollX();
//				int oldY = getScrollY();
//				int x = scroller.getCurrX();
//				int y = scroller.getCurrY();
//
//				if (oldX != x || oldY != y) {
//					scrollTo(x, y);
//				}
//
//				invalidate();
//				return;
//			}
//		}
//		completeScroll();
//	}
//
//
//	void completeScroll(){
//		// 在滚动动画结束后开始刷新操作
//
//		if(state == STATE_PREPARE_FOR_REFRESH){
//			refresh();
//		}
//
//		else if (state == STATE_REFRESHING_FINISHED) {
//			refreshIndicatorView.setVisibility(View.VISIBLE);
//			bar.setVisibility(View.GONE);
//			refreshTime = getCurrentTime();
//			if (refreshTime != null) {
//				setRefreshTime(refreshTime);
//			}
//			downTextView.setText(R.string.pull_to_refresh);
//			state = STATE_PULL_TO_REFRESH;
//		}
//	}
//
//	/**
//	 * 下拉move事件处理
//	 *
//	 * @param moveY
//	 */
//	private void doMovement(int moveY) {
//
//		final int deltaY = -moveY/2;
//		final int divider = refreshView.getHeight();
//		final int scrollY = -getScrollY();
//
//
//		if(state == STATE_PULL_TO_REFRESH){
//			if(scrollY > divider){
//				state = STATE_RELEASE_TO_REFRESH;
//				flipUp();
//			}
//		}else if(state == STATE_RELEASE_TO_REFRESH){
//			if(scrollY < divider){
//				state = STATE_PULL_TO_REFRESH;
//				flipDown();
//			}
//		}
//
//		scrollBy(getScrollX(),deltaY);
//
//	}
//
//	private void flipDown() {
//		downTextView.setText(R.string.pull_to_refresh);
//		refreshIndicatorView.clearAnimation();
//		refreshIndicatorView.startAnimation(mReverseFlipAnimation);
//	}
//
//	private void flipUp() {
//
//		downTextView.setText(R.string.release_to_refresh);
//		// refreshIndicatorView.setImageResource(R.drawable.refresh_arrow_up);
////		if (lastReverseFlag != 1) {
//			refreshIndicatorView.clearAnimation();
//			refreshIndicatorView.startAnimation(mFlipAnimation);
////			lastReverseFlag = 1;
////		}
//
//	}
//
//	private String getCurrentTime() {
//		if (isInEditMode()) { return ""; }
//		return ""+ DateFormat.format("yyyy-MM-dd hh:mm", System.currentTimeMillis());
//	}
//
//	public void setRefreshListener(RefreshListener listener) {
//		this.refreshListener = listener;
//	}
//
//
//	public void setRefreshTime(String time) {
//		timeTextView.setText(time);
//	}
//
//	/**
//	 * 结束刷新事件
//	 */
//	public void finishRefresh() {
//		// Log.i(TAG, "执行了=====finishRefresh");
////		refreshIndicatorView.setVisibility(View.VISIBLE);
//		timeTextView.setVisibility(View.VISIBLE);
//		setRefreshTime(getCurrentTime() );
//		state = STATE_REFRESHING_FINISHED;
//		scroller.startScroll(0, getScrollY(), 0, -getScrollY(),200);
//		invalidate();
//
//	}
//
//	/*
//	 * 该方法一般和ontouchEvent 一起用 (non-Javadoc)
//	 *
//	 * @see android.view.ViewGroup#onInterceptTouchEvent(android.view.MotionEvent)
//	 */
//	@Override
//	public boolean onInterceptTouchEvent(MotionEvent e) {
//		int action = e.getAction();
//		int y = (int) e.getRawY();
//		switch (action) {
//			case MotionEvent.ACTION_DOWN:
//				lastY = y;
//				break;
//
//
//			case MotionEvent.ACTION_MOVE:
//				// y移动坐标
//				int m = y - lastY;
//
//				// 记录下此刻y坐标
//				this.lastY = y;
//				if (m > 6 && canScroll()) {
//					return true;
//				}
//				break;
//			case MotionEvent.ACTION_UP:
//				break;
//
//			case MotionEvent.ACTION_CANCEL:
//
//				break;
//		}
//		return false;
//	}
//
//	/**
//	 * 判断是否可以滑动
//	 *
//	 * @return boolean 可以返回true 不可以返回false
//	 */
//	private boolean canScroll() {
//		View childView;
//		if (getChildCount() > 1) {
//			childView = this.getChildAt(1);
//			if (childView instanceof ListView) {
//				int top = 0;
//				if(((ListView) childView).getChildAt(0)!=null){
//					top = ((ListView) childView).getChildAt(0).getTop();
//				}
//				int pad = ((ListView) childView).getListPaddingTop();
//				if ((Math.abs(top - pad)) < 3 && ((ListView) childView).getFirstVisiblePosition() == 0) {
//					return true;
//				} else {
//					return false;
//				}
//			} else if (childView instanceof ScrollView) {
//				if (((ScrollView) childView).getScrollY() == 0) {
//					return true;
//				} else {
//					return false;
//				}
//			}
//
//		}
//		return false;
//	}
//
//
//
//	/**
//	 * 刷新监听接口
//	 *
//	 * @author Nono
//	 *
//	 */
//	public interface RefreshListener {
//		public void onRefresh();
//
//	}
//
//
//
//	@Override
//	protected void onLayout(boolean changed, int l, int t, int r, int b) {
////		EDebug.e("RefreshableView", "onLayout("+changed+","+l+","+t+","+r+","+b+")");
//		if (isInEditMode()) { return; }
//
//		int top = 0;
//		refreshView.layout(l, -refreshView.getMeasuredHeight(), r, 0);
//		final int count = getChildCount();
//		for(int i=0; i<count; i++){
//			View child = getChildAt(i);
//			if(child != refreshView){
//				child.layout(l, top, r, top+child.getMeasuredHeight());
//				top += child.getMeasuredHeight();
//			}
//		}
//
//	}
//
//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		int measuredHeight = 0;
//		int measuredWidth = 0;
//
//		if (isInEditMode()) { return; }
//		refreshView.measure(widthMeasureSpec, getChildMeasureSpec(heightMeasureSpec, 0, refreshView.getLayoutParams().height));
//		measuredWidth = refreshView.getMeasuredWidth();
//		final int count = getChildCount();
//		for(int i=0; i<count; i++){
//			View child = getChildAt(i);
//			if(child != refreshView){
//				child.measure(widthMeasureSpec, heightMeasureSpec);
//				measuredHeight += child.getMeasuredHeight();
//
////				EDebug.e("RefreshableView", "measuredHeight = "+measuredHeight);
//
//			}
//		}
//
//		setMeasuredDimension(measuredWidth, measuredHeight);
////		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//	}
//
//
//}
