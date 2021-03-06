package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("onBind");
		return new MyBinder();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("onUnbind");
		return super.onUnbind(intent);
	}

	private class MyBinder extends Binder implements IMyBinder {
		public void callMethod(String str) {
			toCallMethod(str);
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("onCreate");
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		System.out.println("onStart");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy");
	}

	public void toCallMethod(String str) {
		System.out.println("Method called" + str);
		Toast.makeText(getApplicationContext(), "服务的方法被调用了,参数:" + str, 0)
				.show();
	}

}
