package com.example.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AliPayService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("AliPayService: onBind");
		return new MyBinder();
	}

	@Override
	public void onCreate() {
		System.out.println("AliPayService: onCreate");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		System.out.println("AliPayService: onDestroy");
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("AliPayService: onUnbind");
		return super.onUnbind(intent);
	}

	private class MyBinder extends IAlipayService.Stub {
		@Override
		public void callPay() {
			pay();
		}

	}

	private void pay() {
		System.out.println("支付方法");
	}

}
