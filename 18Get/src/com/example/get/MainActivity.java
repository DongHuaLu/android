package com.example.get;

import java.net.MalformedURLException;
import java.net.URL;

import com.example.service.LoginService;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et1;
	private EditText et2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et1 = (EditText) findViewById(R.id.et1);
		et2 = (EditText) findViewById(R.id.et2);
	}

	public void clickGet(View view) {
		final String username = et1.getText().toString().trim();
		final String password = et2.getText().toString().trim();

		new Thread() {
			public void run() {
				try {
					final String result = LoginService.loginByGet(username,
							password);

					if (result == null) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(MainActivity.this, result, 0)
										.show();
							}
						});
					} else {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(MainActivity.this, result, 0)
										.show();
							}
						});
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}.start();

	}

	public void clickPost(View view) {
		final String username = et1.getText().toString().trim();
		final String password = et2.getText().toString().trim();

		new Thread() {
			public void run() {
				try {
					final String result = LoginService.loginByPost(username,
							password);

					if (result == null) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(MainActivity.this, result, 0)
										.show();
							}
						});
					} else {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(MainActivity.this, result, 0)
										.show();
							}
						});
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}.start();
	}

	public void clientClickGet(View view) {

		final String username = et1.getText().toString().trim();
		final String password = et2.getText().toString().trim();

		new Thread() {
			public void run() {
				try {
					final String result = LoginService.loiginByClientGet(
							username, password);
					if (result == null) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(MainActivity.this, result, 0)
										.show();
							}
						});
					} else {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(MainActivity.this, result, 0)
										.show();
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}.start();

	}
	
	public void clientClickPost(View view) {
		
		final String username = et1.getText().toString().trim();
		final String password = et2.getText().toString().trim();
		
		new Thread() {
			public void run() {
				try {
					final String result = LoginService.loiginByClientPost(
							username, password);
					if (result == null) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(MainActivity.this, result, 0)
								.show();
							}
						});
					} else {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(MainActivity.this, result, 0)
								.show();
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}.start();
		
	}
}
