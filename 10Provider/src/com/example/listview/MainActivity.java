package com.example.listview;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.Dao.PersonDao2;
import com.example.domain.Person;
import com.example.provider.R;

public class MainActivity extends Activity {
	List<Person> persons;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PersonDao2 dao = new PersonDao2(this);
		persons = dao.findAll();
		ListView lv = (ListView) findViewById(R.id.lv);

		lv.setAdapter(new MyAdapter());
	}

	private class MyAdapter extends BaseAdapter {

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

			Person person = persons.get(position);

			View view = View.inflate(MainActivity.this, R.layout.listviewitem,
					null);
			TextView tvId = (TextView) view.findViewById(R.id.tvId);
			tvId.setText("id:"+person.getId());
			TextView tvName = (TextView) view.findViewById(R.id.tvName);
			tvName.setText("name:"+person.getName());
			TextView tvTel = (TextView) view.findViewById(R.id.tvTel);
			tvTel.setText("id:"+person.getTel());
			return view;

		}

	}

}
