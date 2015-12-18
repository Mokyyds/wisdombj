package com.peachou.wisdombj.menudetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.peachou.wisdombj.base.BaseMenuDetailPager;

/**
 * 菜单详情页：互动
 * @author Peach
 *
 */
public class InteractDetailPager extends BaseMenuDetailPager {
	public InteractDetailPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initViews() {

		TextView text = new TextView(mActivity);
		text.setText("菜单详情页-互动");
		text.setTextColor(Color.RED);
		text.setGravity(Gravity.CENTER);
		return text;
	}

}
