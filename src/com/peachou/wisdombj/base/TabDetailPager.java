package com.peachou.wisdombj.base;

import com.peachou.wisdombj.domain.NewsData.NewsTabData;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * 页签详情页
 * @author Peach
 *
 */
public class TabDetailPager extends BaseMenuDetailPager {

	NewsTabData mTabData;
	private TextView text;
	public TabDetailPager(Activity activity, NewsTabData newsTabData) {
		super(activity);
		mTabData = newsTabData;
	}
	@Override
	public View initViews() {
		text = new TextView(mActivity);
		text.setText("页签详情页");
		text.setTextColor(Color.RED);
		text.setGravity(Gravity.CENTER);
		return text;
	}
	@Override
	public void initData() {
		text.setText(mTabData.title);
	}
}
