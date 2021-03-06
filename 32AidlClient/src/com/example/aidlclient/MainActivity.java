package com.example.aidlclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.example.aidl.IAlipayService;

public class MainActivity extends Activity {
	private Intent intent;
	private IAlipayService iAlipayService;
	private Conn conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		intent = new Intent();
		intent.setAction("com.dolph.alipay");
	}

	public void bind(View view) {
		conn = new Conn();
		boolean b = bindService(intent, conn, BIND_AUTO_CREATE);
		System.out.println(b);
	}

	public void unbind(View view) {
		unbindService(conn);
	}

	private class Conn implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			iAlipayService = IAlipayService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}

	}

	public void call(View view) {
		try {
			iAlipayService.callPay();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
