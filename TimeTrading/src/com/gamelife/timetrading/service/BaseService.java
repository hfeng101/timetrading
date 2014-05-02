package com.gamelife.timetrading.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BaseService extends Service {
	public BaseService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public void onCreate()
	{
		
	}
	
	public void onStart(Intent intent, int startId)
	{
		
	}
	
}
