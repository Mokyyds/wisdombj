package com.peachou.wisdombj.fragment;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.peachou.wisdombj.R;
import com.peachou.wisdombj.base.BasePager;
import com.peachou.wisdombj.base.impl.GovAffairsPager;
import com.peachou.wisdombj.base.impl.HomePager;
import com.peachou.wisdombj.base.impl.NewsCenterPager;
import com.peachou.wisdombj.base.impl.SettingPager;
import com.peachou.wisdombj.base.impl.SmartServicePager;

public class ContentFragment extends BaseFragment {

	@ViewInject(R.id.rg_grup)
	private RadioGroup rgGroup;
	@ViewInject(R.id.vp_content)
	private ViewPager mViewPager;
	private ArrayList<BasePager> mPagerList;
	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		ViewUtils.inject(this, view); //注入view和事件
		return view;
	}
	@Override
	public void initData() {
		/**
		 * 设置监听RadioGroup事件
		 */
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.tab_home:
//					mViewPager.setCurrentItem(0);//动画切换
					mViewPager.setCurrentItem(0, false);//去掉页面切换动画
					break;
				case R.id.tab_news:
					mViewPager.setCurrentItem(1,false);
					break;
				case R.id.tab_smart:
					mViewPager.setCurrentItem(2,false);
					break;
				case R.id.tab_gov:
					mViewPager.setCurrentItem(3,false);
					break;
				case R.id.tab_setting:
					mViewPager.setCurrentItem(4,false);
					break;
				default:
					break;
				}
			}
		});

		rgGroup.check(R.id.tab_home);//默认勾选首页
		//初始化5个子页面
		mPagerList = new ArrayList<BasePager>();
//		for (int i = 0; i < 5; i++) {
//			BasePager pager = new BasePager(mActivity);
//			mPagerList.add(pager);
//		}
		mPagerList.add(new HomePager(mActivity));
		mPagerList.add(new NewsCenterPager(mActivity));
		mPagerList.add(new SmartServicePager(mActivity));
		mPagerList.add(new GovAffairsPager(mActivity));
		mPagerList.add(new SettingPager(mActivity));
		mViewPager.setAdapter(new ContentAdapter());
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				mPagerList.get(arg0).initData();//初始化该页面数据
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		mPagerList.get(0).initData();//手动初始化首页
		
	}
	
	class ContentAdapter extends PagerAdapter{
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
			BasePager pager = mPagerList.get(position);
			container.addView(pager.mRootView);
//			pager.initData();//初始化数据，不要放在此处初始化数据，否则会预加载下个界面
			return pager.mRootView;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
	public NewsCenterPager getNewsCenterPager(){
		return (NewsCenterPager) mPagerList.get(1);
	}
}
