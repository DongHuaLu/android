package com.example.userprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View view) {
		// 得到手机的中间人
		ContentResolver resolver = getContentResolver();
		Uri uri = Uri.parse("content://com.example.provider.personprovider/query/16");
		Cursor cursor = resolver.query(uri, new String[] { "name", "money" },
				null, null, null);

		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int money=cursor.getInt(cursor.getColumnIndex("money"));
			System.out.println("name:"+name+"money:"+money);
		}
		cursor.close();
	}
}
