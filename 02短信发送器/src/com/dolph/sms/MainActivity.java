package com.dolph.sms;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private EditText et_number;
	private EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt_send=(Button) findViewById(R.id.bt_send);
        bt_send.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_send:
			et_number=(EditText) this.findViewById(R.id.et_number);
			et_content=(EditText) this.findViewById(R.id.et_content);
			String number=et_number.getText().toString().trim();
			String content=et_content.getText().toString().trim();
			if (TextUtils.isEmpty(number)){
				Toast.makeText(this, "号码不能为空", 0).show();
				return;
			}else if (TextUtils.isEmpty(content)){
				Toast.makeText(this, "内容不能为空", 0).show();
				return;
			}else{
				SmsManager smsManager=SmsManager.getDefault();
				ArrayList<String> contents=smsManager.divideMessage(content);
				for (String str:contents){
					smsManager.sendTextMessage(number, null, str, null, null);				
				}
			}
			
			break;

		default:
			break;
		}		
	}
	
}
