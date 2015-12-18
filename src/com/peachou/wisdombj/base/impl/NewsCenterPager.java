package com.peachou.wisdombj.base.impl;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.peachou.wisdombj.MainActivity;
import com.peachou.wisdombj.base.BaseMenuDetailPager;
import com.peachou.wisdombj.base.BasePager;
import com.peachou.wisdombj.domain.NewsData;
import com.peachou.wisdombj.domain.NewsData.NewsMenuData;
import com.peachou.wisdombj.fragment.LeftMenuFragment;
import com.peachou.wisdombj.global.GlobalContants;
import com.peachou.wisdombj.menudetail.InteractDetailPager;
import com.peachou.wisdombj.menudetail.NewDetailPager;
import com.peachou.wisdombj.menudetail.PhotoDetailPager;
import com.peachou.wisdombj.menudetail.TopicDetailPager;

public class NewsCenterPager extends BasePager {
	private ArrayList<BaseMenuDetailPager> mPagers;
	private NewsData mNewsdata;
	private int pos;

	public NewsCenterPager(Activity activity) {
		super(activity);
	}
	@Override
	public void initData() {
		setSlidingMenuEnable(true);
		getDataFromServer();
		
	}
	/**
	 * 从服务器获取
	 */
	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, GlobalContants.CATEGORIES_URL, new RequestCallBack<String>() {
			//访问成功
			@Override
			public void onSuccess(ResponseInfo responseInfo) {
				String result = (String) responseInfo.result;
				System.out.println(result);
				parseDate(result);
			}
			//失败
			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
				error.printStackTrace();
			}
		});
	}
	private void parseDate(String result) {
		Gson gson = new Gson();
		mNewsdata = gson.fromJson(result, NewsData.class);
//		刷新侧边栏数据
		MainActivity mainUi = (MainActivity) mActivity;
		LeftMenuFragment leftMenuFragment = mainUi.getLeftMenuFragment();
		leftMenuFragment.setMenuData(mNewsdata);
		//准备四个菜单详情页
		mPagers = new ArrayList<BaseMenuDetailPager>();
		mPagers.add(new NewDetailPager(mActivity,mNewsdata.data.get(0).children));
		mPagers.add(new TopicDetailPager(mActivity));
		mPagers.add(new PhotoDetailPager(mActivity));
		mPagers.add(new InteractDetailPager(mActivity));
		setCurrentMenuDetailPager(pos);//设置菜详情
	}
	/**
	 * 设置当前菜单详情页
	 */
	public void setCurrentMenuDetailPager(int position){
		pos = position;
		BaseMenuDetailPager pager = mPagers.get(position);
		flContent.removeAllViews();//清楚之前布局
		flContent.addView(pager.mRootView);
		NewsMenuData newsMenuData = mNewsdata.data.get(position);
		tvTitle.setText(newsMenuData.title);
		pager.initData();//初始化当前页面的数据
	}
	
}
