package com.example.listview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {

	public PersonSQLiteOpenHelper(Context context) {
		super(context, "person.db", null, 5);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table person (id integer primary key autoincrement,name varchar(20),tel varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("ALTER TABLE person ADD COLUMN money integer");
	}

}
