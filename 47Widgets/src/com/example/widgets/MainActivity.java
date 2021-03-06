package com.example.widgets;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.add("123");
		adapter.add("456");
		adapter.add("789");
		adapter.add("000");
		spinner.setAdapter(adapter);

		AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.actv);
		String[] names = new String[] { "老1", "老2", "老3", "老4", "小5", "小6" };
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, names);
		actv.setAdapter(adapter2);
	}

}
