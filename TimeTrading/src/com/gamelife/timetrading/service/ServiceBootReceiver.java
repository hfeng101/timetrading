package com.gamelife.timetrading.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ServiceBootReceiver extends BroadcastReceiver {
	public ServiceBootReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// an Intent broadcast.
		Intent bootIntent = new Intent(context, BaseService.class);
		context.startService(bootIntent);
		
		//throw new UnsupportedOperationException("Not yet implemented");
	}
}
