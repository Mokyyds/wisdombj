package com.peachou.wisdombj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.peachou.wisdombj.MainActivity;
import com.peachou.wisdombj.base.BasePager;

public class HomePager extends BasePager {

	public HomePager(Activity activity) {
		super(activity);
	}
	@Override
	public void initData() {
		tvTitle.setText("智慧北京");
		TextView text = new TextView(mActivity);
		text.setText("首页");
		text.setTextColor(Color.RED);
		text.setGravity(Gravity.CENTER);
		btnMenu.setVisibility(View.INVISIBLE);
		setSlidingMenuEnable(false);//隐藏SlidingMenu
		//向fragment动态添加布局
		flContent.addView(text);
	}

}
