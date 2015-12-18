package com.peachou.wisdombj.fragment;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.peachou.wisdombj.MainActivity;
import com.peachou.wisdombj.R;
import com.peachou.wisdombj.base.impl.NewsCenterPager;
import com.peachou.wisdombj.domain.NewsData;
import com.peachou.wisdombj.domain.NewsData.NewsMenuData;
/**
 * 侧边栏
 * @author Peach
 *
 */
public class LeftMenuFragment extends BaseFragment {
	@ViewInject(R.id.lv_list)
	private ListView lvList;
	private ArrayList<NewsMenuData> mMenuList;
	private int mCurrentPos;//当前被点击的菜单
	private MenuAdapter mAdapter;
	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
		ViewUtils.inject(this,view);
		return view;
	}
	@Override
	public void initData() {
		lvList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mCurrentPos = position;
				mAdapter.notifyDataSetChanged();
				setCurrentMenuDetailPager(position);
				toggleSlidingMenu();
			}
		});
	}
	//切换SlidingMenu的状态
	protected void toggleSlidingMenu(){
		MainActivity mainUi = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUi.getSlidingMenu();
		slidingMenu.toggle();//切换状态
	}
	
	//设置菜单详情页
	public void setCurrentMenuDetailPager(int position){
		MainActivity mainUi = (MainActivity) mActivity;
		ContentFragment con =  mainUi.getContentFragment();
		NewsCenterPager newsCenterPager = con.getNewsCenterPager();
		newsCenterPager.setCurrentMenuDetailPager(position);
	}
	//设置网络数据
	public void setMenuData(NewsData data){
		//System.out.println("解析结果："+data);
		mMenuList = data.data;
		mAdapter = new MenuAdapter();
		lvList.setAdapter(mAdapter);
	}
	class MenuAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return mMenuList.size();
		}

		@Override
		public Object getItem(int position) {
			return mMenuList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(mActivity, R.layout.item_list_menu, null);
			TextView tvTiele = (TextView) view.findViewById(R.id.tv_title);
			NewsMenuData newsMenuData = mMenuList.get(position);
			tvTiele.setText(newsMenuData.title);
			if (mCurrentPos == position) {//判断当前绘制的View是否被选中
				//显示红色
				tvTiele.setEnabled(true);
			}else {
				//显示白色
				tvTiele.setEnabled(false);
			}
			return view;
		}
		
	}
}
