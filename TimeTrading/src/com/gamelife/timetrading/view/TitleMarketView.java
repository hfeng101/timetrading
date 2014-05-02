package com.gamelife.timetrading.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gamelife.timetrading.R;

/**
 * @author hanfeng
 *	功能描述：自定义顶部工具栏
 */
public class TitleMarketView extends FrameLayout implements View.OnClickListener {

	private Button backBtn;
	private Button buyBtn;
	private Button sellBtn;
	private Button moreBtn;
	private TextView mTitle;

	private OnBackButtonClickListener mOnBackButtonClickListener;
	private OnBuyButtonClickListener mOnBuyButtonClickListener;
	private OnSellButtonClickListener mOnSellButtonClickListener;
	private OnMoreButtonClickListener mOnMoreButtonClickListener;

	public interface OnBackButtonClickListener {
		public void onClick(View button);
	}

	public interface OnBuyButtonClickListener {
		public void onClick(View button);
	}
	
	public interface OnSellButtonClickListener {
		public void onClick(View button);
	}
	
	public interface OnMoreButtonClickListener {
		public void onClick(View button);
	}


	public void setBackButton(String text, OnBackButtonClickListener listener) {
		backBtn.setText(text);
		backBtn.setVisibility(View.VISIBLE);
		mOnBackButtonClickListener = listener;
	}
	
	public void setBackButton(int stringID, OnBackButtonClickListener listener) {
		backBtn.setText(stringID);
		backBtn.setVisibility(View.VISIBLE);
		mOnBackButtonClickListener = listener;
	}
	
	public void removeBackButton() {
		backBtn.setText("");
		backBtn.setVisibility(View.INVISIBLE);
		mOnBackButtonClickListener = null;
	}
	
	public void hiddenBackButton() {
		backBtn.setVisibility(View.INVISIBLE);
	}
	
	public void showBackButton() {
		backBtn.setVisibility(View.VISIBLE);
	}
	
	public void setBuyButton(String text, OnBuyButtonClickListener listener) {
		buyBtn.setText(text);
		buyBtn.setVisibility(View.VISIBLE);
		mOnBuyButtonClickListener = listener;
	}
	
	public void setBuyButton(int stringID, OnBuyButtonClickListener listener) {
		buyBtn.setText(stringID);
		buyBtn.setVisibility(View.VISIBLE);
		mOnBuyButtonClickListener = listener;
	}
	
	public void setSellButton(String text, OnSellButtonClickListener listener) {
		sellBtn.setText(text);
		sellBtn.setVisibility(View.VISIBLE);
		mOnSellButtonClickListener = listener;
	}
	
	public void setSellButton(int stringID, OnSellButtonClickListener listener) {
		sellBtn.setText(stringID);
		sellBtn.setVisibility(View.VISIBLE);
		mOnSellButtonClickListener = listener;
	}
	
	public void setMoreButton(String text, OnMoreButtonClickListener listener) {
		moreBtn.setText(text);
		moreBtn.setVisibility(View.VISIBLE);
		mOnMoreButtonClickListener = listener;
	}
	
	public void setMoreButton(int stringID, OnMoreButtonClickListener listener) {
		moreBtn.setText(stringID);
		moreBtn.setVisibility(View.VISIBLE);
		mOnMoreButtonClickListener = listener;
	}
	
	
	public void removeBuyButton() {
		buyBtn.setText("");
		buyBtn.setVisibility(View.INVISIBLE);
		mOnBuyButtonClickListener = null;
	}
	
	public void removeSellButton() {
		sellBtn.setText("");
		sellBtn.setVisibility(View.INVISIBLE);
		mOnSellButtonClickListener = null;
	}
	
	public void hiddenBuyButton() {
		buyBtn.setVisibility(View.INVISIBLE);
	}
	
	public void showBuyButton() {
		buyBtn.setVisibility(View.VISIBLE);
	}
	
	public void hiddenSellButton() {
		sellBtn.setVisibility(View.INVISIBLE);
	}
	
	public void showSellButton() {
		sellBtn.setVisibility(View.VISIBLE);
	}
	
	public void hiddenMoreButton() {
		moreBtn.setVisibility(View.INVISIBLE);
	}
	
	public void showMoreButton() {
		moreBtn.setVisibility(View.VISIBLE);
	}

	public TitleMarketView(Context context) {
		this(context, null);
	}

	public TitleMarketView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TitleMarketView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.title_market_view, this, true);

		backBtn = (Button) findViewById(R.id.back);
		backBtn.setVisibility(View.INVISIBLE);
		backBtn.setOnClickListener(this);
		buyBtn = (Button) findViewById(R.id.buy);
		buyBtn.setVisibility(View.INVISIBLE);
		buyBtn.setOnClickListener(this);
		sellBtn = (Button) findViewById(R.id.sell);
		sellBtn.setVisibility(View.INVISIBLE);
		sellBtn.setOnClickListener(this);
		moreBtn = (Button) findViewById(R.id.more);
		moreBtn.setVisibility(View.INVISIBLE);
		moreBtn.setOnClickListener(this);
		
		//mTitle = (TextView) findViewById(R.id.title_text);
		//mTitle.setVisibility(View.INVISIBLE);
	}
	
	public void setTitle(String text) {
		mTitle.setVisibility(View.VISIBLE);
		mTitle.setText(text);
	}
	
	public void setTitle(int stringID) {
		mTitle.setVisibility(View.VISIBLE);
		mTitle.setText(stringID);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			if(mOnBackButtonClickListener != null)
				mOnBackButtonClickListener.onClick(v);
			break;
		case R.id.buy:
			if(mOnBuyButtonClickListener != null)
				mOnBuyButtonClickListener.onClick(v);
			break;
		case R.id.sell:
			if(mOnSellButtonClickListener != null)
				mOnSellButtonClickListener.onClick(v);
			break;
		case R.id.more:
			if(mOnMoreButtonClickListener != null)
				mOnMoreButtonClickListener.onClick(v);
			break;
		}
	}

}
