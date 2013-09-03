package com.example.smssernder;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.domain.ContactInfo;
import com.example.service.ContactService;

public class SelectContactActivity extends Activity {
	private ListView lvContacts;
	List<ContactInfo> infos;
	String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectcontact);
		lvContacts = (ListView) findViewById(R.id.lvContacts);
		infos = ContactService.getContactsInfo(this);
		lvContacts.setAdapter(new ContactAdapter());

		lvContacts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView tvName = (TextView) view.findViewById(R.id.tvName);
				TextView tvPhone = (TextView) view.findViewById(R.id.tvPhone);
				String name = tvName.getText().toString().trim();
				String phone = tvPhone.getText().toString().trim();
				Intent data=new Intent();
				data.putExtra("phone", phone);
				setResult(0, data);
				finish();
			}

		});
	}



	private class ContactAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return infos.size();
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
			ContactInfo info = infos.get(position);
			View v = View.inflate(getApplicationContext(),
					R.layout.contact_info, null);
			TextView tvName = (TextView) v.findViewById(R.id.tvName);
			TextView tvPhone = (TextView) v.findViewById(R.id.tvPhone);
			tvName.setText(info.getName());
			tvPhone.setText(info.getNumber());
			return v;
		}
	}
}
