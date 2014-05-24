package com.gamelife.timetrading.fragment.market;

import java.util.List;
import java.util.Map;

import com.gamelife.timetrading.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MarketListViewAdapter extends BaseAdapter{
		private final String TAG = "MarketListViewAdapter";
		private LayoutInflater mInflater;
		private List<Map<String, Object>> mData;
		private Context context;
	
		public MarketListViewAdapter(Context context, List<Map<String, Object>> data)
		{
			this.context = context;
			mInflater = LayoutInflater.from(context);   //创建视图容器并设置上下文
			this.mData = data;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return  mData.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		public void UpdateData(List<Map<String, Object>> data)
		{
			Log.e(TAG, "[UpdateData]: Updata Data Info!!!" + mData.size());
			this.mData = data;
		}
		
		public final class ViewHolder{
			public ImageButton headImage;
			public TextView userID;
			public TextView content;
			public Button comment;
			public Button trade;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup viewGroup) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder=new ViewHolder();
				
				convertView = mInflater.inflate(R.layout.market_content, null);
				Log.e(TAG, "[getView]: before findViewById!!!");
				holder.headImage = (ImageButton)convertView.findViewById(R.id.head_image);
				holder.userID = (TextView)convertView.findViewById(R.id.user_id);
				holder.content = (TextView)convertView.findViewById(R.id.content);
				holder.comment = (Button)convertView.findViewById(R.id.comment);
				holder.trade = (Button)convertView.findViewById(R.id.trade);
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHolder)convertView.getTag();
			}
			
			Log.e(TAG, "before set adapter!!!");
			holder.headImage.setBackgroundResource((Integer)mData.get(position).get("img"));
			Log.e(TAG, "[UpdateData]: userID is " + mData.get(position).get("userid"));
			holder.userID.setText((String)mData.get(position).get("userid"));
			Log.e(TAG, "[UpdateData]: content is " + mData.get(position).get("content"));
			holder.content.setText((String)mData.get(position).get("content"));
			
			holder.headImage.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
									
				}
			});
			
			holder.comment.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
									
				}
			});
			
			holder.trade.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
									
				}
			});
			
			return convertView;
		}
}
