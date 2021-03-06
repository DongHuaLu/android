package com.example.activitymessage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.et1);
	}

	public void click(View view) {
		String value = et.getText().toString().trim();
		if (TextUtils.isEmpty(value)) {
			Toast.makeText(this, "不能为空", 0).show();
			return;
		} else {
			Intent intent = new Intent(this, ResultActivity.class);
			intent.putExtra("value", value);
			startActivity(intent);
		}

	}

}
