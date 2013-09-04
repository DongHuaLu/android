package com.example.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item1:
			Toast.makeText(this, "item1", 0).show();
			break;
		case R.id.item2:
			Toast.makeText(this, "item2", 0).show();
			break;
		case R.id.item3:
			Toast.makeText(this, "item3", 0).show();
			break;
		case R.id.item4:
			Toast.makeText(this, "item4", 0).show();
			break;

		}
		return super.onOptionsItemSelected(item);
	}

}