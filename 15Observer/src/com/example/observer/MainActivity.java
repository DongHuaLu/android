package com.example.observer;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ContentResolver resolver = getContentResolver();
		Uri uri = Uri.parse("content://sms/");
		// resolver.notifyChange(uri, observer) <---- 发送一个变化的消息
		resolver.registerContentObserver(uri, true, new MyObserver(
				new Handler()));
	}

	private class MyObserver extends ContentObserver {

		public MyObserver(Handler handler) {
			super(handler);
		}

		// 当观察者观察到消息邮箱数据库变化的内容通知
		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			Toast.makeText(MainActivity.this, "sms 变化了", 0).show();
		}

	}
}
