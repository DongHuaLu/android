package com.example.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private class MyServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("代理连接返回了");
			myBinder = (IMyBinder) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

	}

	IMyBinder myBinder;

	MyServiceConnection myServiceConn;

	public void bind(View view) {
		Intent intent = new Intent(this, MyService.class);
		myServiceConn = new MyServiceConnection();
		bindService(intent, myServiceConn, BIND_AUTO_CREATE);
	}

	public void call(View view) {
		myBinder.callMethod("我");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void start(View view) {
		Intent intent = new Intent(this, MyService.class);
		startService(intent);

	}

	public void stop(View view) {
		Intent intent = new Intent(this, MyService.class);
		stopService(intent);
	}

	public void unbind(View view) {
		unbindService(myServiceConn);
	}

	@Override
	protected void onDestroy() {
		try {
			unbindService(myServiceConn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.onDestroy();
	}

}
