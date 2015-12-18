package com.peachou.wisdombj;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.peachou.wisdombj.fragment.ContentFragment;
import com.peachou.wisdombj.fragment.LeftMenuFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

public class MainActivity extends SlidingFragmentActivity {
	private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
	private static final String FRAGMENT_CONTENT = "fragment_content";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initFragment();
		setBehindContentView(R.layout.left_menu);// 侧边栏布局
		SlidingMenu slidingMenu = getSlidingMenu();// 获取侧边栏对象
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置全屏触摸
		// slidingMenu.setSecondaryMenu(R.layout.right_menu);//设置右侧边栏目
		// slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);//设置两边都有
		slidingMenu.setBehindOffset(500);// 设置预留屏幕宽度
	}

	/**
	 * 初始化fragment，将fragment数据填充到布局文件
	 */
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();// 开启事物
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),
				FRAGMENT_LEFT_MENU);
		transaction.replace(R.id.fl_content, new ContentFragment(),
				FRAGMENT_CONTENT);

		transaction.commit();// 提交事物
	}
	//获取侧边栏对象
	public LeftMenuFragment getLeftMenuFragment() {
		FragmentManager fm = getSupportFragmentManager();
		LeftMenuFragment fragment = (LeftMenuFragment) fm
				.findFragmentByTag(FRAGMENT_LEFT_MENU);
		return fragment;
	}
	//获取主界面对象
	public ContentFragment getContentFragment() {
		FragmentManager fm = getSupportFragmentManager();
		ContentFragment fragment = (ContentFragment) fm
				.findFragmentByTag(FRAGMENT_CONTENT);
		return fragment;
	}
}
