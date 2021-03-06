package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	public static final String TAG = "DatabaseHelper";
	private static final String DB_NAME = "process.db";
	private static final int DB_VERSION = 1;

	private Context mContext;
	private static DatabaseHelper mInstance;

	private DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	public synchronized static DatabaseHelper getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new DatabaseHelper(context);
		}
		return mInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE process (id integer primary key autoincrement,threadid varchar,total integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	
	

}
