package com.peachou.wisdombj.base;

import android.app.Activity;
import android.view.View;

/**
 * 菜单详情页
 * @author Peach
 *
 */
public abstract class BaseMenuDetailPager {
	public Activity mActivity;
	
	public View mRootView;//根部局对象
	public BaseMenuDetailPager(Activity activity) {
		mActivity = activity;
		mRootView = initViews();
	}
	/**
	 * 初始化界面
	 */
	public abstract View initViews();
	/**
	 * 初始化数据
	 */
	public void initData(){
		
	}
}
