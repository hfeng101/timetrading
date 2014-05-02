package com.gamelife.timetrading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.gamelife.timetrading.fragment.market.MarketListViewAdapter;
import com.gamelife.timetrading.library.PullToRefreshBase.Mode;
import com.gamelife.timetrading.library.PullToRefreshListView;
import com.gamelife.timetrading.library.PullToRefreshBase;
import com.gamelife.timetrading.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.gamelife.timetrading.library.PullToRefreshBase.OnRefreshListener;
import com.gamelife.timetrading.library.PullToRefreshBase.State;
import com.gamelife.timetrading.library.extras.SoundPullEventListener;
//import com.gamelife.timetrading.samples.PullToRefreshListActivity;


/**
 * @author hanfeng
 *	功能描述：帮助Activity界面
 */
/*public class HelpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_help);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);
	}
}
*/

public class HelpActivity extends ListActivity {
	//public class HelpActivity extends ListActivity {
	static final int MENU_MANUAL_REFRESH = 0;
	static final int MENU_DISABLE_SCROLL = 1;
	static final int MENU_SET_MODE = 2;
	static final int MENU_DEMO = 3;

	private static String TAG = "HelpActivity";
	private LinkedList<String> mListItems;
	private PullToRefreshListView mPullRefreshListView;
	private ArrayAdapter<String> mAdapter;

	// Called when the activity is first created. 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_help);

		Log.e(TAG, "Before mPullRefreshListView");
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.list_market);
		Log.e(TAG, "After mPullRefreshListView");


		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

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
				Toast.makeText(HelpActivity.this, "End of List!", Toast.LENGTH_SHORT).show();
			}
		});

		ListView actualListView = mPullRefreshListView.getRefreshableView();

		// Need to use the Actual ListView when registering for Context Menu
		registerForContextMenu(actualListView);

		mListItems = new LinkedList<String>();
		mListItems.addAll(Arrays.asList(mStrings));

		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);

		// Add Sound Event Listener
		SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(this);
		soundListener.addSoundEvent(State.PULL_TO_REFRESH, R.raw.pull_event);
		soundListener.addSoundEvent(State.RESET, R.raw.reset_sound);
		soundListener.addSoundEvent(State.REFRESHING, R.raw.refreshing_sound);
		mPullRefreshListView.setOnPullEventListener(soundListener);

		// You can also just use setListAdapter(mAdapter) or
		// mPullRefreshListView.setAdapter(mAdapter)
		actualListView.setAdapter(mAdapter);

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
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_MANUAL_REFRESH, 0, "Manual Refresh");
		menu.add(0, MENU_DISABLE_SCROLL, 1,
				mPullRefreshListView.isScrollingWhileRefreshingEnabled() ? "Disable Scrolling while Refreshing"
						: "Enable Scrolling while Refreshing");
		menu.add(0, MENU_SET_MODE, 0, mPullRefreshListView.getMode() == Mode.BOTH ? "Change to MODE_PULL_DOWN"
				: "Change to MODE_PULL_BOTH");
		menu.add(0, MENU_DEMO, 0, "Demo");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;

		menu.setHeaderTitle("Item: " + getListView().getItemAtPosition(info.position));
		menu.add("Item 1");
		menu.add("Item 2");
		menu.add("Item 3");
		menu.add("Item 4");

		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem disableItem = menu.findItem(MENU_DISABLE_SCROLL);
		disableItem
				.setTitle(mPullRefreshListView.isScrollingWhileRefreshingEnabled() ? "Disable Scrolling while Refreshing"
						: "Enable Scrolling while Refreshing");

		MenuItem setModeItem = menu.findItem(MENU_SET_MODE);
		setModeItem.setTitle(mPullRefreshListView.getMode() == Mode.BOTH ? "Change to MODE_FROM_START"
				: "Change to MODE_PULL_BOTH");

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case MENU_MANUAL_REFRESH:
				new GetDataTask().execute();
				mPullRefreshListView.setRefreshing(false);
				break;
			case MENU_DISABLE_SCROLL:
				mPullRefreshListView.setScrollingWhileRefreshingEnabled(!mPullRefreshListView
						.isScrollingWhileRefreshingEnabled());
				break;
			case MENU_SET_MODE:
				mPullRefreshListView.setMode(mPullRefreshListView.getMode() == Mode.BOTH ? Mode.PULL_FROM_START
						: Mode.BOTH);
				break;
			case MENU_DEMO:
				mPullRefreshListView.demo();
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	private String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler" };
}
