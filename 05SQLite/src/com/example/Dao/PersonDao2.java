package com.example.Dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.domain.Person;
import com.example.sqlite.PersonSQLiteOpenHelper;

public class PersonDao2 {

	private PersonSQLiteOpenHelper helper = null;

	public PersonDao2(Context context) {
		helper = new PersonSQLiteOpenHelper(context);
	}

	public long add(String name, String tel,int money) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", name);
		values.put("tel", tel);
		values.put("money", money);
		long id = db.insert("person", null, values);
		db.close();
		return id;
	}

	public List<Person> findPersonByName(String name) {
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.query("person", null, "name=?",
				new String[] { name }, null, null, null, null);
		List<Person> persons=new ArrayList<Person>();
		while(cursor.moveToNext()){
			Person p=new Person();
			p.setId(cursor.getInt(cursor.getColumnIndex("id")));
			p.setName(cursor.getString(cursor.getColumnIndex("name")));
			p.setTel(cursor.getString(cursor.getColumnIndex("tel")));
			p.setMoney(cursor.getInt(cursor.getColumnIndex("money")));
			persons.add(p);
		}
		db.close();
		return persons;

	}

	public int update(String name, String tel,int money) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("tel", tel);
		values.put("money", money);
		int number = db.update("person", values, "name=?",
				new String[] { name });
		db.close();
		return number;
	}
	
	public int delete(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		int number=db.delete("person", "name=?", new String[]{name});
		db.close();
		return number;
	}
}
