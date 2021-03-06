package com.example.login;

import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.login.service.LoginService;

public class MainActivity extends Activity {
	private static final String tag = "MainActivity";
	private EditText etUsername;
	private EditText etPassword;
	private CheckBox cb;
	private RadioGroup rg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etUsername = (EditText) findViewById(R.id.etUsername);
		etPassword = (EditText) findViewById(R.id.etPassword);
		cb = (CheckBox) findViewById(R.id.cbRemember);
		rg=(RadioGroup) findViewById(R.id.radioGroupMode);
		Map<String,String> infoMap=LoginService.getSavedUserInfo(this);
		if(infoMap!=null){
			etUsername.setText(infoMap.get("username"));
			etPassword.setText(infoMap.get("password"));
		}
	}

	public void login(View view) {
		String username = etUsername.getText().toString().trim();
		String password = etPassword.getText().toString().trim();
		if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
			Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
			return;
		} else {
			if (cb.isChecked()) {
				Log.i(tag, "保存密码");
				int radioId = rg.getCheckedRadioButtonId();
				int mode=0;
				switch (radioId) {
				case R.id.radioPrivate:
					mode=1;
					break;
				case R.id.radioReadable:
					mode=2;
					break;
				case R.id.radioWriteable:
					mode=3;
					break;
				case R.id.radioPublic:
					mode=4;
					break;
				}			
				
				boolean result = LoginService.saveUserInfo(this,username, password,mode);
				if(result){
					Toast.makeText(this, "保存用户信息成功", 0).show();
				}else{
					Toast.makeText(this, "保存用户信息失败", 0).show();
				}
			}
			if ("ddddd".equals(username) && "ppppp".equals(password)) {
				Toast.makeText(this, "登录成功", 0).show();
			} else {
				Toast.makeText(this, "登录失败,用户名或密码错误", 0).show();
			}

		}

	}

}
