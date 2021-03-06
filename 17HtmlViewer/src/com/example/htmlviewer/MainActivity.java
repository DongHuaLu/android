package com.example.htmlviewer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.utils.StreamTools;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	protected static final int ERROR = 0;
	protected static final int SHOW_TEXT = 1;
	private TextView tv;
	private EditText et;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ERROR:
				Toast.makeText(MainActivity.this, "失败", 0).show();
				break;
			case SHOW_TEXT:
				tv.setText((CharSequence) msg.obj);
				break;
			}
		};

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		et = (EditText) findViewById(R.id.et);
	}

	public void click(View view) {
		final String path = et.getText().toString().trim();

		if (TextUtils.isEmpty(path)) {

		} else {
			new Thread() {
				public void run() {
					try {
						URL url = new URL(path);
						HttpURLConnection conn = (HttpURLConnection) url
								.openConnection();
						conn.setRequestMethod("GET");
						conn.setReadTimeout(5000);
						conn.setRequestProperty("User-Agent",
								"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0");

						int code = conn.getResponseCode();
						String contentType=conn.getHeaderField("Content-Type");
						if (code == 200) {
							InputStream in = conn.getInputStream();
							String result = StreamTools.readInputStream(in,contentType);
							if(result == null){
								Message msg = new Message();
								msg.what = ERROR;
								handler.sendMessage(msg);
							}else{
								Message msg=new Message();
								msg.what=SHOW_TEXT;
								msg.obj=result;
								handler.sendMessage(msg);
							}
						} else {
							Message msg = new Message();
							msg.what = ERROR;
							handler.sendMessage(msg);
						}
					} catch (Exception e) {
						e.printStackTrace();
						Message msg = new Message();
						msg.what = ERROR;
						handler.sendMessage(msg);
					}

				}

			}.start();
		}

	}

}
