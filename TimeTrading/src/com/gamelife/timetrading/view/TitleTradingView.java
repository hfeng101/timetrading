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
public class TitleTradingView extends FrameLayout implements View.OnClickListener {

	private Button backBtn;
	private Button moreBtn;
	private TextView mTitle;

	private OnBackButtonClickListener mOnBackButtonClickListener;
	private OnMoreButtonClickListener mOnMoreButtonClickListener;

	public interface OnBackButtonClickListener {
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
	
	public void removeMoreButton() {
		moreBtn.setText("");
		moreBtn.setVisibility(View.INVISIBLE);
		mOnMoreButtonClickListener = null;
	}
	
	public void hiddenMoreButton() {
		moreBtn.setVisibility(View.INVISIBLE);
	}
	
	public void showMoreButton() {
		moreBtn.setVisibility(View.VISIBLE);
	}

	public TitleTradingView(Context context) {
		this(context, null);
	}

	public TitleTradingView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TitleTradingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.title_trading_view, this, true);

		backBtn = (Button) findViewById(R.id.back);
		backBtn.setVisibility(View.INVISIBLE);
		backBtn.setOnClickListener(this);
		moreBtn = (Button) findViewById(R.id.more);
		moreBtn.setVisibility(View.INVISIBLE);
		moreBtn.setOnClickListener(this);
		
		mTitle = (TextView) findViewById(R.id.login_text);
		mTitle.setVisibility(View.INVISIBLE);
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
		case R.id.more:
			if(mOnMoreButtonClickListener != null)
				mOnMoreButtonClickListener.onClick(v);
			break;
		}
	}

}
