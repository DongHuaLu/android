package com.example.callservicebyboradcast;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
	private MyReceiver myReceiver;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		// 采用代码的防止注册一个广播接收者
		myReceiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.example.callservicebyboradcast");
		registerReceiver(myReceiver, filter);
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		unregisterReceiver(myReceiver);
		myReceiver = null;
		super.onDestroy();
	}

	private void methodInService() {
		Toast.makeText(getApplicationContext(), "服务方法", 0).show();
	}

	private class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			methodInService();
		}

	}

}
