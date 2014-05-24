package com.gamelife.timetrading.view;

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

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gamelife.timetrading.R;

/**
 * @author hanfeng
 *	功能描述：自定义顶部工具栏
 */
public class LoginView extends FrameLayout implements View.OnClickListener {
	private final String TAG = "LoginView";
	private TextView mUserNametext;
	private EditText mUserName;
	private TextView mPasswdtext;
	private EditText mPasswd;
	private Button mLoginBtn;
	private Button mSignBtn;
	private TextView mTitle;

	private OnLoginButtonClickListener mOnLoginButtonClickListener;
	private OnSignButtonClickListener mOnSignButtonClickListener;

	public interface OnLoginButtonClickListener {
		public void onClick(View button);
	}

	public interface OnSignButtonClickListener {
		public void onClick(View button);
	}

	public void setLoginButton(String text, OnLoginButtonClickListener listener) {
		mLoginBtn.setText(text);
		mLoginBtn.setVisibility(View.VISIBLE);
		mOnLoginButtonClickListener = listener;
	}


	public void setLoginButton(int stringID, OnLoginButtonClickListener listener) {
		mLoginBtn.setText(stringID);
		mLoginBtn.setVisibility(View.VISIBLE);
		mOnLoginButtonClickListener = listener;
	}
	
	public void removeLoginButton() {
		mLoginBtn.setText("");
		mLoginBtn.setVisibility(View.INVISIBLE);
		mOnLoginButtonClickListener = null;
	}
	
	public void hiddenLoginButton() {
		mLoginBtn.setVisibility(View.INVISIBLE);
	}
	
	public void showLoginButton() {
		mLoginBtn.setVisibility(View.VISIBLE);
	}
	
	public void setSignButton(String text, OnSignButtonClickListener listener) {
		mSignBtn.setText(text);
		mSignBtn.setVisibility(View.VISIBLE);
		mOnSignButtonClickListener = listener;
	}

	
	public void setSignButton(int stringID, OnSignButtonClickListener listener) {
		mSignBtn.setText(stringID);
		mSignBtn.setVisibility(View.VISIBLE);
		mOnSignButtonClickListener = listener;
	}
	
	
	public void removeSignButton() {
		mSignBtn.setText("");
		mSignBtn.setVisibility(View.INVISIBLE);
		mOnSignButtonClickListener = null;
	}
	
	public void hiddenSignButton() {
		mSignBtn.setVisibility(View.INVISIBLE);
	}
	
	public void showSignButton() {
		mSignBtn.setVisibility(View.VISIBLE);
	}

	public LoginView(Context context) {
		this(context, null);
	}

	public LoginView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LoginView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.login_view, this, true);

		mUserNametext = (TextView) findViewById(R.id.username_text);
		mUserNametext.setText(R.string.username);
		mUserName = (EditText) findViewById(R.id.username);
		mPasswdtext = (TextView) findViewById(R.id.passwd_text);
		mPasswdtext.setText(R.string.passwd);
		mPasswd = (EditText) findViewById(R.id.passwd);		
		mLoginBtn = (Button) findViewById(R.id.login);
		//mLoginBtn.setOnClickListener(loginListener);
		setLoginButton(R.string.login, loginListener);
		mSignBtn = (Button) findViewById(R.id.sign_in);
		setSignButton(R.string.sign, signListener);
		//mSignBtn.setVisibility(View.VISIBLE);
		//mSignBtn.setOnClickListener(this);
		

		//mTitle = (TextView) findViewById(R.id.title_text);
		//mTitle.setVisibility(View.VISIBLE);
	}
	
	//登陆监听器    
  //OnClickListener loginListener = new OnClickListener(){   
	OnLoginButtonClickListener loginListener = new OnLoginButtonClickListener(){   
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
       params.add(new BasicNameValuePair("username", mUserName.getText().toString()));    
       params.add(new BasicNameValuePair("password", mPasswd.getText().toString()));                                  
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
   
      }};   
      
    OnSignButtonClickListener signListener = new OnSignButtonClickListener(){
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
         params.add(new BasicNameValuePair("username", mUserName.getText().toString()));    
         params.add(new BasicNameValuePair("password", mPasswd.getText().toString()));                                  
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
        }};  
        
/*
	public void setTitle(String text) {
		mTitle.setVisibility(View.VISIBLE);
		mTitle.setText(text);
	}
	
	public void setTitle(int stringID) {
		mTitle.setVisibility(View.VISIBLE);
		mTitle.setText(stringID);
	}
*/

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_btn:
			if(mOnLoginButtonClickListener != null)
				mOnLoginButtonClickListener.onClick(v);
			break;
		case R.id.right_btn:
			if(mOnSignButtonClickListener != null)
				mOnSignButtonClickListener.onClick(v);
			break;
		}
	}

}
