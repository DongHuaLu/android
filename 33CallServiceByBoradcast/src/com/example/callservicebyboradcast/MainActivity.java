package com.example.callservicebyboradcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = new Intent(this, MyService.class);
		startService(intent);

	}

	public void call(View view) {
		Intent intent = new Intent();
		intent.setAction("com.example.callservicebyboradcast");
		sendBroadcast(intent);
	}

}