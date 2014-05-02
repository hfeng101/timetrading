package com.gamelife.timetrading;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction; 
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.gamelife.timetrading.fragment.FragmentIndicator;
import com.gamelife.timetrading.fragment.FragmentIndicator.OnIndicateListener;
import com.gamelife.timetrading.fragment.MarketFragment;
import com.gamelife.timetrading.fragment.SettingsFragment;
import com.gamelife.timetrading.fragment.TradingFragment;
import com.gamelife.timetrading.R;

/**
 * @author hanfeng
 *	功能描述：主Activity类，继承自FragmentActivity
 */
//public class MainActivity extends FragmentActivity {
public class MainActivity extends Activity {
	private final String TAG = "MainActivity";

	public static Fragment[] mFragments;
	//public static MarketFragment mMarketFragment;
	//public static TradingFragment mTradingFragment;
	//public static SettingsFragment mSettinsgFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		setFragmentIndicator(0);
	}

	/**
	 * 初始化fragment
	 */
	private void setFragmentIndicator(int whichIsDefault) {
		mFragments = new Fragment[3];
		
		mFragments[0] = getFragmentManager().findFragmentById(R.id.fragment_market);
		mFragments[1] = getFragmentManager().findFragmentById(R.id.fragment_trading);
		mFragments[2] = getFragmentManager().findFragmentById(R.id.fragment_settings);
		getFragmentManager().beginTransaction().hide(mFragments[0])
				.hide(mFragments[1]).hide(mFragments[2]).show(mFragments[whichIsDefault]).commit();

		/*
		mMarketFragment = (MarketFragment) getFragmentManager().findFragmentById(R.id.fragment_market);
		mTradingFragment = (TradingFragment) getFragmentManager().findFragmentById(R.id.fragment_trading);
		mSettinsgFragment = (SettingsFragment) getFragmentManager().findFragmentById(R.id.fragment_settings);
		*/
		//FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		//transaction.replace(R.id.fragment_market, mMarketFragment);
		//transaction.replace(R.id.fragment_trading, mTradingFragment);
		//transaction.replace(R.id.fragment_settings, mSettinsgFragment);
	
		//transaction.add(R.id.main_layout, mMarketFragment);
		//transaction.add(R.id.main_layout, mTradingFragment);
		//transaction.add(R.id.main_layout, mSettinsgFragment);
		
		//提交前调用addToBackStack，好让用户拥有返回或者撤销的机会
		//transaction.addToBackStack(null);
		 
		//Commit the transaction
		//transaction.commit();
		
		//getFragmentManager().beginTransaction().hide(mMarketFragment).hide(mTradingFragment).
		//		hide(mSettinsgFragment).show(mMarketFragment).commit();
		
		FragmentIndicator mIndicator = (FragmentIndicator) findViewById(R.id.indicator);
		FragmentIndicator.setIndicator(whichIsDefault);
		mIndicator.setOnIndicateListener(new OnIndicateListener() {
			@Override
			public void onIndicate(View v, int which) {
				FragmentTransaction mTransaction = getFragmentManager().beginTransaction();
				mTransaction.hide(mFragments[0]).hide(mFragments[1])
						.hide(mFragments[2]).show(mFragments[which]).commit();
				/*
				if(which == 0)
				{
					Log.e(TAG,"switch to fragment_market!!!");
					mTransaction.hide(mMarketFragment).hide(mTradingFragment).hide(mSettinsgFragment).show(mMarketFragment);
					//transaction.replace(R.id.fragment_market, mMarketFragment);
				}
				else if(which == 1)
				{
					Log.e(TAG,"switch to fragment_trading!!!");
					mTransaction.hide(mMarketFragment).hide(mTradingFragment).hide(mSettinsgFragment).show(mTradingFragment);
					//transaction.replace(R.id.fragment_trading, mTradingFragment);
				}
				else if(which == 2)
				{
					Log.e(TAG,"switch to fragment_settings!!!");
					mTransaction.hide(mMarketFragment).hide(mTradingFragment).hide(mSettinsgFragment).show(mSettinsgFragment);
					//transaction.replace(R.id.fragment_settings, mSettinsgFragment);
				}
				else
					Log.e(TAG,"switch failed ,invaild number!!!");
				*/
				
				//mTransaction.addToBackStack(null);
				//mTransaction.commit();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
}
