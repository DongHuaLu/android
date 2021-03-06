package com.example.smssernder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText etPhoneNumber;
	private EditText etPhoneNumber2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
		etPhoneNumber2 = (EditText) findViewById(R.id.etPhoneNumber2);
	}

	public void select(View view) {
		Intent intent = new Intent(this, SelectContactActivity.class);
		startActivityForResult(intent, 1);
	}

	public void select2(View view) {
		Intent intent = new Intent(this, SelectContactActivity.class);
		startActivityForResult(intent, 2);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (data != null) {
			if (requestCode == 1) {
				String phone = data.getStringExtra("phone");
				etPhoneNumber.setText(phone);
			} else if (requestCode == 2) {
				String phone = data.getStringExtra("phone");
				etPhoneNumber2.setText(phone);
			}
		}
	}

}
