package com.example.listview;

import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.Dao.PersonDao2;
import com.example.domain.Person;

public class MainActivity extends Activity {
	List<Person> persons;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PersonDao2 dao = new PersonDao2(this);
		persons = dao.findAll();
		ListView lv=(ListView) findViewById(R.id.lv);
		
		lv.setAdapter(new MyAdapter());
	}
	
	
	
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return persons.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv=new TextView(getApplicationContext());
			tv.setTextSize(20);
			tv.setTextColor(Color.BLACK);
			Person person=persons.get(position);
			tv.setText(person.toString());
			return tv;
		}
		
	}

}
