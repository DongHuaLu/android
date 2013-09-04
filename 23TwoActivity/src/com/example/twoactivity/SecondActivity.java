package com.example.twoactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;

public class SecondActivity extends Activity {

	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);

		Intent intent = getIntent();
		Uri uri = intent.getData();
		String data = uri.toString();
		et = (EditText) findViewById(R.id.editText1);
		et.setText(data);
	}

}