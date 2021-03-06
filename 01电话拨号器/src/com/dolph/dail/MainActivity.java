package com.dolph.dail;

import com.dolph.dail.R;
import com.dolph.dail.R.id;
import com.dolph.dail.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText  et_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button bt_dail = (Button) this.findViewById(R.id.bt_dail);
		et_number = (EditText) MainActivity.this
				.findViewById(R.id.et_number);
		bt_dail.setOnClickListener(new myListener());
	}

	private class myListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			String number = et_number.getText().toString();
			if(TextUtils.isEmpty(number)){
				Toast.makeText(MainActivity.this, "号码不能为空", Toast.LENGTH_LONG).show();
				return;
			}
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:"+number));
			
			startActivity(intent);
		}
	}

}
