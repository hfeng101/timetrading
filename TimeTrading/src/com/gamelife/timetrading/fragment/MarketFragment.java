package com.gamelife.timetrading.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.gamelife.timetrading.R;
import com.gamelife.timetrading.R.layout;
import com.gamelife.timetrading.view.TitleMarketView;
import com.gamelife.timetrading.view.TitleMarketView.OnBackButtonClickListener;
import com.gamelife.timetrading.view.TitleMarketView.OnBuyButtonClickListener;
import com.gamelife.timetrading.view.TitleMarketView.OnSellButtonClickListener;
import com.gamelife.timetrading.view.TitleMarketView.OnMoreButtonClickListener;
import com.gamelife.timetrading.fragment.market.MarketListViewAdapter;


import com.gamelife.timetrading.library.PullToRefreshListView;
import com.gamelife.timetrading.library.PullToRefreshBase;
import com.gamelife.timetrading.library.PullToRefreshBase.Mode;
import com.gamelife.timetrading.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.gamelife.timetrading.library.PullToRefreshBase.OnRefreshListener;
import com.gamelife.timetrading.library.PullToRefreshBase.State;
import com.gamelife.timetrading.library.extras.SoundPullEventListener;
//import com.gamelife.timetrading.samples.PullToRefreshListActivity;
//import com.gamelife.timetrading.samples.PullToRefreshListActivity.GetDataTask;








import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link MarketFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link MarketFragment#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class MarketFragment extends Fragment {
	private final String TAG = "MarketFragment";
	private View mParent;
	private Activity mActivity;
	private TitleMarketView mTitle;
	
	private LinkedList<String> mListItems;
	private PullToRefreshListView mPullRefreshListView;
	private MarketListViewAdapter mAdapter;
	//private ArrayAdapter<Map<String, Object>> mAdapter;

	private String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler" };
	
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
	public static MarketFragment newInstance(String param1, String param2) {
		MarketFragment fragment = new MarketFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public MarketFragment() {
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
		
		
		mPullRefreshListView = (PullToRefreshListView) mParent.findViewById(R.id.list_market);

		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
			String label = DateUtils.formatDateTime(mActivity, System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

			//String label = DateUtils.formatDateTime(mParent, System.currentTimeMillis(),
			//		DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
				new GetDataTask().execute();
		
			}
		});

		// Add an end-of-list listener
		mPullRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
//				Toast.makeText(PullToRefreshListActivity.this, "End of List!", Toast.LENGTH_SHORT).show();
			}
		});

		ListView actualListView = mPullRefreshListView.getRefreshableView();

		Log.e(TAG, "before set ListView!!");
		// Need to use the Actual ListView when registering for Context Menu
		registerForContextMenu(actualListView);

		//mListItems = new LinkedList<String>();
		//mListItems.addAll(Arrays.asList(mStrings));

		Log.e(TAG, "init adapter!!");
		mAdapter = new MarketListViewAdapter(mActivity, getData());
		//mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);
		//mAdapter = new ArrayAdapter<Map<String, Object>>(this, android.R.layout.simple_list_item_1, getData());

		// Add Sound Event Listener
		SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(mActivity);
		soundListener.addSoundEvent(State.PULL_TO_REFRESH, R.raw.pull_event);
		soundListener.addSoundEvent(State.RESET, R.raw.reset_sound);
		soundListener.addSoundEvent(State.REFRESHING, R.raw.refreshing_sound);
		mPullRefreshListView.setOnPullEventListener(soundListener);

		// You can also just use setListAdapter(mAdapter) or
		//mPullRefreshListView.setAdapter(mAdapter);
		Log.e(TAG, "before set adapter!!");
		actualListView.setAdapter(mAdapter);
		
		mPullRefreshListView.setMode(Mode.BOTH);
	}
	

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("img", R.drawable.ic_launcher);
		map.put("userid", "G1");
		map.put("content", "google 1");

		list.add(map);

		map = new HashMap<String, Object>();
		map.put("img", R.drawable.ic_launcher);
		map.put("userid", "G2");
		map.put("content", "google 2");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("img", R.drawable.ic_launcher);
		map.put("userid", "G3");
		map.put("content", "google 3");
		list.add(map);
		
		return list;
	}
	
	
	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			return mStrings;
		}

		@Override
		protected void onPostExecute(String[] result) {
			mListItems.addFirst("Added after refresh...");
			mAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
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
