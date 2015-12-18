package com.peachou.wisdombj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.peachou.wisdombj.base.BasePager;

public class SettingPager extends BasePager {

	public SettingPager(Activity activity) {
		super(activity);
	}
	@Override
	public void initData() {
		tvTitle.setText("设置");
		TextView text = new TextView(mActivity);
		text.setText("设置中心");
		text.setTextColor(Color.RED);
		text.setGravity(Gravity.CENTER);
		btnMenu.setVisibility(View.INVISIBLE);
		setSlidingMenuEnable(false);//隐藏SlidingMenu
		//向fragment动态添加布局
		flContent.addView(text);
	}
}
