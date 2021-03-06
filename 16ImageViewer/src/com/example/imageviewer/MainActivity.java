package com.example.imageviewer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	protected static final int CHANGE_UI = 0;
	private EditText et;
	private ImageView iv;
	
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what==CHANGE_UI){
				iv.setImageBitmap((Bitmap) msg.obj);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.et);
		iv = (ImageView) findViewById(R.id.iv);
	}

	public void click(View view) {

		final String path = et.getText().toString().trim();
		if (TextUtils.isEmpty(path)) {
			Toast.makeText(this, "图片路径不能为空", 0).show();
		} else {
			new Thread() {
				@Override
				public void run() {
					try {
						URL url = new URL(path);
						HttpURLConnection conn = (HttpURLConnection) url
								.openConnection();
						// 首次连接的超时时间
						conn.setConnectTimeout(5000);
						// 中断后的超时时间
						// conn.setReadTimeout(timeoutMillis);
						conn.setRequestProperty("User-Agent",
								"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0");
						int code = conn.getResponseCode();
						if (code == 200) {
							InputStream in = conn.getInputStream();
							Bitmap bitmap = BitmapFactory.decodeStream(in);
							//iv.setImageBitmap(bitmap);
							Message msg=new Message();
							msg.what=CHANGE_UI;
							msg.obj=bitmap;
							handler.sendMessage(msg);
						} else {
							Toast.makeText(MainActivity.this, "显示图片失败", 0).show();
						}
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(MainActivity.this, "获取该路径失败", 0).show();
					}
				}
			}.start();
		}
	}
}
