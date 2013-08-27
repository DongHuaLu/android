package com.example.smartimageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.image.SmartImageView;

public class MainActivity extends Activity {
	private SmartImageView iv;
	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (SmartImageView) findViewById(R.id.iv);
		et = (EditText) findViewById(R.id.et);
	}

	public void click(View view) {
		iv.setImageUrl(et.getText().toString().trim(), R.drawable.ic_launcher, R.drawable.ic_launcher);
	}
}
