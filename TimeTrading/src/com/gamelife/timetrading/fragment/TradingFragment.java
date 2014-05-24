package com.gamelife.timetrading.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.gamelife.timetrading.R;
import com.gamelife.timetrading.R.string;
import com.gamelife.timetrading.view.LoginView;
import com.gamelife.timetrading.view.LoginView.OnLoginButtonClickListener;
import com.gamelife.timetrading.view.LoginView.OnSignButtonClickListener;
import com.gamelife.timetrading.view.TitleMarketView;
import com.gamelife.timetrading.view.TitleMarketView.OnBackButtonClickListener;
import com.gamelife.timetrading.view.TitleMarketView.OnBuyButtonClickListener;
import com.gamelife.timetrading.view.TitleMarketView.OnMoreButtonClickListener;
import com.gamelife.timetrading.view.TitleMarketView.OnSellButtonClickListener;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link TradingFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link TradingFragment#newInstance} factory
 * method to create an instance of this fragment.
 * 
 */
public class TradingFragment extends Fragment {
	private final String TAG = "TradingFragment";
	private View mParent;
	private Activity mActivity;
	private LoginView mTitle;
	
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
	 * @return A new instance of fragment TradingFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static TradingFragment newInstance(String param1, String param2) {
		TradingFragment fragment = new TradingFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public TradingFragment() {
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

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mActivity = getActivity();
		mParent = getView();

		mTitle = (LoginView) mParent.findViewById(R.id.title_market);
		//mTitle.set
/*		//登陆
		mTitle.setLoginButton(R.string.login, new OnLoginButtonClickListener(){

			@Override   
	      public void onClick(View v) {   
	       System.out.println("Login Button clicked!");   
	       //服务器登录认证地址
	       String httpUrl = "http://10.0.2.2:8089/Gossip/LoginServlet";    
	       System.out.println(httpUrl);   
	       HttpPost request = new HttpPost(httpUrl);     
	       HttpClient httpClient = new DefaultHttpClient();     
	       //传递参数    
	       List<NameValuePair> params = new ArrayList<NameValuePair>();     
	      // params.add(new BasicNameValuePair("username", mUserName.getText().toString()));    
	      // params.add(new BasicNameValuePair("password", mPasswd.getText().toString()));                                  
	       HttpResponse response;   
	          try {   
	              HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");     
	              request.setEntity(entity);     
	              response = httpClient.execute(request);   
	             
	           //如果返回状态为200，获得返回的结果    
	           if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){     
	               String str = EntityUtils.toString(response.getEntity());     
	               System.out.println("response:"+str);   
	               if(str.trim().equals("success")){   
	                   //用户登录成功    ,界面跳转
	                   System.out.println("登录成功！");   
	          //         Intent intent = new Intent(GossipActivity.this,GossiplistActivity.class);   
	          //         startActivity(intent);   
	               }   
	               else{   
	                   //用户登录失败    
	                   System.out.println("登录失败！");   
	               }   
	           }else{     
	               System.out.println("连接失败！");   
	           }     
	          } catch (ClientProtocolException e) {   
	              // TODO Auto-generated catch block    
	              e.printStackTrace();   
	          } catch (IOException e) {   
	              // TODO Auto-generated catch block    
	                  e.printStackTrace();   
	              }     
	    }});  
		
		//注册
		mTitle.setSignButton(R.string.sign, new OnSignButtonClickListener() {

			@Override
			public void onClick(View button) {
				
			}
		});
*/
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//TextView textView = new TextView(getActivity());
		//textView.setText(R.string.hello_blank_fragment);
		View view = inflater
				.inflate(R.layout.fragment_trading, container, false);
		return view;
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
