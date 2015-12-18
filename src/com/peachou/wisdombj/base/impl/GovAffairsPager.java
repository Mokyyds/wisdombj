package com.peachou.wisdombj.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.peachou.wisdombj.base.BasePager;

public class GovAffairsPager extends BasePager {

	public GovAffairsPager(Activity activity) {
		super(activity);
	}
	@Override
	public void initData() {
		tvTitle.setText("政府");
		TextView text = new TextView(mActivity);
		text.setText("政府官员");
		text.setTextColor(Color.RED);
		text.setGravity(Gravity.CENTER);
		setSlidingMenuEnable(true);//显示SlidingMenu
		//向fragment动态添加布局
		flContent.addView(text);
	}
}
