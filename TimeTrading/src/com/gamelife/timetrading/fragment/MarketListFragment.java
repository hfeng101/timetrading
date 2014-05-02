package com.gamelife.timetrading.fragment;

import com.gamelife.timetrading.R;
import com.gamelife.timetrading.R.layout;
import com.gamelife.timetrading.fragment.market.MarketListView;
import com.gamelife.timetrading.view.TitleMarketView;
import com.gamelife.timetrading.view.TitleMarketView.OnBackButtonClickListener;
import com.gamelife.timetrading.view.TitleMarketView.OnBuyButtonClickListener;
import com.gamelife.timetrading.view.TitleMarketView.OnSellButtonClickListener;
import com.gamelife.timetrading.view.TitleMarketView.OnMoreButtonClickListener;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link MarketListFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link MarketListFragment#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class MarketListFragment extends ListFragment {
	private View mParent;
	private Activity mActivity;
	private TitleMarketView mTitle;
	private MarketListView mList;
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment MarketFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static MarketListFragment newInstance(String param1, String param2) {
		MarketListFragment fragment = new MarketListFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public MarketListFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_market, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mActivity = getActivity();
		mParent = getView();

		mTitle = (TitleMarketView) mParent.findViewById(R.id.title_market);
		//按钮结束退出
		mTitle.setBackButton(R.string.exit, new OnBackButtonClickListener(){

			@Override
			public void onClick(View button) {
				mActivity.finish();
			}
			
		});
		//跳转对时间的buy界面
		mTitle.setBuyButton(R.string.buy, new OnBuyButtonClickListener() {

			@Override
			public void onClick(View button) {
				
			}
		});
		//跳转对时间的sell界面
		mTitle.setSellButton(R.string.sell, new OnSellButtonClickListener() {

			@Override
			public void onClick(View button) {
				
			}
		});
		//更多的其他选项
		mTitle.setMoreButton(R.string.more, new OnMoreButtonClickListener() {

			@Override
			public void onClick(View button) {
				
			}
		});
		
		//mText = (TextView) mParent.findViewById(R.id.fragment_home_text);
		
		//mList = (MarketListView) mParent.findViewById(R.id.list_market);

	}
	

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		/*
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
		*/
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(Uri uri);
	}

}
