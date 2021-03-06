package com.example.simpleadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView lv = (ListView) findViewById(R.id.lv);

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "name1");
		map1.put("iconid", R.drawable.btn_minus_disable);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "name2");
		map2.put("iconid", R.drawable.btn_radio_on);

		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("name", "name3");
		map3.put("iconid", R.drawable.compass_base);

		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("name", "name4");
		map4.put("iconid", R.drawable.expander_ic_maximized);

		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("name", "name5");
		map5.put("iconid", R.drawable.ic_jog_dial_vibrate_on);

		data.add(map1);
		data.add(map2);
		data.add(map3);
		data.add(map4);
		data.add(map5);

		String[] from = new String[] { "name", "iconid" };
		int[] to = new int[] { R.id.tv, R.id.iv };
		lv.setAdapter(new SimpleAdapter(this, data, R.layout.list_item, from,
				to));
	}
}
