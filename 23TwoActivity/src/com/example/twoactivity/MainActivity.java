package com.example.twoactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View view) {
		// Intent intent=new Intent();
		// intent.setClassName(this, "com.example.twoactivity.SecondActivity");
		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent);
	}

	public void click2(View view) {
		// com.android.camera for activity com.android.camera/.CameraEntry
		Intent intent = new Intent();
		intent.setClassName("com.android.camera", "com.android.camera.Camera");
		startActivity(intent);
	}

	public void click3(View view) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("http://www.baidu.com"));
		startActivity(intent);
	}

	public void click4(View view) {
		Intent intent = new Intent();
		intent.setAction("com.d.d");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setData(Uri.parse("scheme:ddddd"));
		startActivity(intent);
	}
	
	public void click5(View view){
		Intent intent =new Intent();
		intent.setAction("android.intent.action.SENDTO");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setData(Uri.parse("sms:110"));
		startActivity(intent);
		
	}

	
}