package com.example.readsms;

import java.util.Date;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View view) {
		ContentResolver resolver = getContentResolver();
		Uri uri = Uri.parse("content://sms/");

		Cursor cursor = resolver.query(uri, new String[] { "address", "date",
				"type", "body" }, null, null, null);
		while (cursor.moveToNext()) {
			String address = cursor.getString(0);
			String date = cursor.getString(1);
			String type = cursor.getString(2);
			String body = cursor.getString(3);
			System.out.println("address:" + address + "date:" + date + "type:"
					+ type + "body:" + body);
			System.out.println("--------------------------------");
		}
		cursor.close();
	}
	
	
	
}
