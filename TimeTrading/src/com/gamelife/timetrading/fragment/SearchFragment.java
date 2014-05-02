package com.gamelife.timetrading.fragment;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamelife.timetrading.MainActivity;
import com.gamelife.timetrading.R;
import com.gamelife.timetrading.view.TitleView;
import com.gamelife.timetrading.view.TitleView.OnLeftButtonClickListener;

/**
 * @author hanfeng
 *	功能描述：搜索fragment页面
 */
public class SearchFragment extends Fragment {

	private View mParent;
	private Activity mActivity;

	private TitleView mTitle;
	
	/**
	 * Create a new instance of DetailsFragment, initialized to show the text at
	 * 'index'.
	 */
	public static SearchFragment newInstance(int index) {
		SearchFragment fragment = new SearchFragment();

		// Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt("index", index);
		fragment.setArguments(args);

		return fragment;
	}

	public int getShownIndex() {
		return getArguments().getInt("index", 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_search, container,
				false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mParent = getView();
		mActivity = getActivity();

		mTitle = (TitleView) mParent.findViewById(R.id.title);
		mTitle.setTitle(R.string.title_search);
		mTitle.setLeftButton(R.string.back, new OnLeftButtonClickListener() {

			@Override
			public void onClick(View button) {
				backHomeFragment();
			}
		});
//		mTitle.setRightButton(R.string.help, new OnRightButtonClickListener() {
//
//			@Override
//			public void onClick(View button) {
////				goHelp();
//			}
//		});
	}
	
	/**
	 * 返回到首页
	 */
	private void backHomeFragment() {
		getFragmentManager().beginTransaction()
				.hide(MainActivity.mFragments[1])
				.show(MainActivity.mFragments[0]).commit();
		FragmentIndicator.setIndicator(0);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (!hidden) {
		}
	}


}
