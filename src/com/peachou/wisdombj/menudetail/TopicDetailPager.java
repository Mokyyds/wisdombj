package com.peachou.wisdombj.menudetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.peachou.wisdombj.base.BaseMenuDetailPager;

/**
 * 菜单详情页：专题
 * @author Peach
 *
 */
public class TopicDetailPager extends BaseMenuDetailPager {
	public TopicDetailPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initViews() {

		TextView text = new TextView(mActivity);
		text.setText("菜单详情页-专题");
		text.setTextColor(Color.RED);
		text.setGravity(Gravity.CENTER);
		return text;
	}

}
