package com.peachou.wisdombj.menudetail;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peachou.wisdombj.R;
import com.peachou.wisdombj.base.BaseMenuDetailPager;
import com.peachou.wisdombj.base.TabDetailPager;
import com.peachou.wisdombj.domain.NewsData.NewsTabData;

/**
 * 菜单详情页：新闻
 * @author Peach
 *
 */
public class NewDetailPager extends BaseMenuDetailPager {
	
	private ViewPager mViewPager;
	private ArrayList<TabDetailPager> mPagerList;
	
	private ArrayList<NewsTabData> mNewsTabData;//页签网络数据
	
	public NewDetailPager(Activity activity, ArrayList<NewsTabData> children) {
		super(activity);
		
		mNewsTabData = children;
		
	}

	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.news_menu_detail, null);
		mViewPager = (ViewPager) view.findViewById(R.id.vp_menu_detail);
		
		return view;
	}
	@Override
	public void initData() {
		mPagerList = new ArrayList<TabDetailPager>();
		//初始化页签数据
		for (int i = 0; i < mNewsTabData.size(); i++) {
			TabDetailPager pager = new TabDetailPager(mActivity,mNewsTabData.get(i));
			mPagerList.add(pager);
		}
		mViewPager.setAdapter(new MenuDetailAdapter());
	}
	class MenuDetailAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return mPagerList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			TabDetailPager pager = mPagerList.get(position);
			container.addView(pager.mRootView);
			pager.initData();
			return pager.mRootView;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
}
