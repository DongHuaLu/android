package com.example.activitymessage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		tv=(TextView) findViewById(R.id.tv1);
		Intent intent = getIntent();
		String value = intent.getStringExtra("value");
		tv.setText(value);
	}

}
