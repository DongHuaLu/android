package com.example.ipdail;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.et);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		et.setText(sp.getString("ipnumber", ""));
	}

	public void click(View view) {
		String ipnumber = et.getText().toString().trim();
		Editor editor = sp.edit();
		editor.putString("ipnumber", ipnumber);
		editor.commit();
		Toast.makeText(this, "保存成功", 0).show();

	}
}
