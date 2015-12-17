package com.peachou.wisdombj.fragment;

import com.peachou.wisdombj.R;

import android.view.View;

public class ContentFragment extends BaseFragment {

	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		return view;
	}

}
