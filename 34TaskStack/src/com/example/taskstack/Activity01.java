package com.example.taskstack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity01 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity01);
	}

	public void click01(View view) {
		Intent intent = new Intent(this, Activity01.class);
		startActivity(intent);
	}
	
	public void click02(View view) {
		Intent intent = new Intent(this, Activity02.class);
		startActivity(intent);
	}

}