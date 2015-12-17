package com.peachou.wisdombj;

import java.util.ArrayList;

import com.peachou.wisdombj.utils.PrefUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GuideActivity extends Activity {
	private static final int[] mImageIds = new int[] { R.drawable.guide_1,
			R.drawable.guide_2, R.drawable.guide_3 };
	private ViewPager vpGuide;
	private ArrayList<ImageView> mImageViewList;
	private LinearLayout llPointGroup;// 引导圆点的父控件
	private int mPoint;//圆点之间的距离
	private View pointRed;//小圆点
	private Button btnStart;//开始体验

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		vpGuide = (ViewPager) findViewById(R.id.vp_guide);
		vpGuide.setAdapter(new GuideAdapter());
		vpGuide.setOnPageChangeListener(new GuidePageListener());
		llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);
		btnStart = (Button) findViewById(R.id.bt_start);
		
		btnStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//更新sp,表示已经展示了新手引导
				PrefUtils.SetBoolean(GuideActivity.this, "is_user_guide_showed", true);
				//跳转主页面
				startActivity(new Intent(GuideActivity.this,MainActivity.class));
				finish();
			}
		});
		
		pointRed = findViewById(R.id.view_red_point);
		initViews();
	}

	/**
	 * 初始化界面
	 */
	private void initViews() {
		mImageViewList = new ArrayList<ImageView>();
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView image = new ImageView(this);
			image.setBackgroundResource(mImageIds[i]);// 设置引导页背景
			mImageViewList.add(image);
		}
		// 初始化引导页小圆点
		for (int i = 0; i < mImageIds.length; i++) {
			View point = new View(this);
			point.setBackgroundResource(R.drawable.shape_point_gray);// 默认圆点
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					20, 20);
			if (i > 0) {
				params.leftMargin = 20;// 设置圆点间隔
			}

			point.setLayoutParams(params);// 设置引导页的大小

			llPointGroup.addView(point);// 将圆点添加给线性布局
		}
		
		// 拿到视图树
		llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					
					// 当layout执行结束回调此方法
					@Override
					public void onGlobalLayout() {
						mPoint = llPointGroup.getChildAt(1).getLeft()
								- llPointGroup.getChildAt(0).getLeft();
						System.out.println("圆点距离: " + mPoint);
						llPointGroup.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
					}
				});
	}

	/**
	 * viewPager适配器
	 */
	class GuideAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mImageIds.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mImageViewList.get(position));
			return mImageViewList.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}

	/**
	 * ViewPager滑动监听
	 * 
	 * @author Peach
	 * 
	 */
	class GuidePageListener implements OnPageChangeListener {

		// 某个页面被选中
		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		// 滑动事件
		/**
		 * positionOffset百分比，positionOffsetPixels滑动距离
		 */
		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			float len = mPoint * positionOffset + position*mPoint;//圆点移动距离
			RelativeLayout.LayoutParams layoutParams =  (RelativeLayout.LayoutParams) pointRed.getLayoutParams();
			layoutParams.leftMargin = (int) len;
			pointRed.setLayoutParams(layoutParams);
		}

		// 滑动状态发生变化
		@Override
		public void onPageSelected(int arg0) {
			if (arg0 == mImageIds.length-1) {
				btnStart.setVisibility(View.VISIBLE);
			}else {
				btnStart.setVisibility(View.INVISIBLE);
			}
		}

	}
}
