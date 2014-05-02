package com.gamelife.timetrading.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamelife.timetrading.HelpActivity;
import com.gamelife.timetrading.R;
import com.gamelife.timetrading.view.TitleView;
import com.gamelife.timetrading.view.TitleView.OnLeftButtonClickListener;
import com.gamelife.timetrading.view.TitleView.OnRightButtonClickListener;

/**
 * @author hanfeng
 *	功能描述：设置fragment页面
 */
public class SettingsFragment extends Fragment {
	
	private View mParent;
	
	private Activity mActivity;

	private TitleView mTitle;

	/**
	 * Create a new instance of DetailsFragment, initialized to show the text at
	 * 'index'.
	 */
	public static SettingsFragment newInstance(int index) {
		SettingsFragment fragment = new SettingsFragment();

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
		View view = inflater
				.inflate(R.layout.fragment_settings, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mParent = getView();
		mActivity = getActivity();

		mTitle = (TitleView) mParent.findViewById(R.id.title_settings);
		mTitle.setTitle(R.string.title_settings);
		mTitle.setLeftButton(R.string.back, new OnLeftButtonClickListener() {

			@Override
			public void onClick(View button) {
			}
		});
		mTitle.hiddenLeftButton();
		mTitle.setRightButton(R.string.help, new OnRightButtonClickListener() {

			@Override
			public void onClick(View button) {
				goHelpActivity();
			}
		});

	}
	
	private void goHelpActivity() {
		Intent intent = new Intent(mActivity, HelpActivity.class);
		startActivity(intent);
	}

}
