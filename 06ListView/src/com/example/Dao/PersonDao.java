package com.example.Dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.domain.Person;
import com.example.listview.PersonSQLiteOpenHelper;

public class PersonDao {
	private PersonSQLiteOpenHelper helper;

	public PersonDao(Context context) {
		this.helper = new PersonSQLiteOpenHelper(context);
	}

	public void add(String name, String tel) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("INSERT INTO person (name,tel) VALUES (?,?)", new Object[] {
				name, tel });
		db.close();
	}

	public List<Person> findTelByName(String name){
		SQLiteDatabase db= helper.getReadableDatabase();
		Cursor cursor=db.rawQuery("SELECT id,name,tel FROM person WHERE name = ?", new String[]{name});
		List<Person> persons = new ArrayList<Person>();
		while (cursor.moveToNext()){
			Person p=new Person();
			p.setId(cursor.getInt(cursor.getColumnIndex("id")));
			p.setName(cursor.getString(cursor.getColumnIndex("name")));
			p.setTel(cursor.getString(cursor.getColumnIndex("tel")));
			persons.add(p);
		}
		cursor.close();
		db.close();	
		return persons;
	}
	
	public void updateTel(String name,String tel){
		SQLiteDatabase db=helper.getWritableDatabase();
		db.execSQL("UPDATE person SET tel = ? WHERE name = ?",new Object[]{tel,name});
		db.close();		
	}
	
	public void delete(String name){
		SQLiteDatabase db=helper.getWritableDatabase();
		db.execSQL("DELETE FROM person WHERE name = ?",new Object[]{name});
		db.close();
		
	}
}
