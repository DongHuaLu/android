package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.db.DatabaseHelper;

public class ProcessDao {
	private DatabaseHelper helper = null;

	public ProcessDao(Context context) {
		super();
		this.helper = DatabaseHelper.getInstance(context);
	}

	public synchronized long add(String threadId) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("threadid", threadId);
		values.put("total", 0);
		long id = db.insert("process", null, values);
		db.close();
		return id;
	}

	public synchronized int getTotalByThreadId(String threadId) {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db
				.query("process", new String[] { "total" }, "threadid=?",
						new String[] { threadId }, null, null, null, null);
		cursor.moveToNext();
		int total = cursor.getInt(cursor.getColumnIndex("total"));
		db.close();
		return total;
	}

	public synchronized int updateByThreadId(String threadId, int total) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("total", total);
		int num = db.update("process", values, "threadId=?",
				new String[] { threadId });
		db.close();
		return num;
	}

	public synchronized int deleteByThreadId(String threadId) {
		SQLiteDatabase db = helper.getWritableDatabase();
		int num = db.delete("process", "threadid=?", new String[] { threadId });
		db.close();
		return num;
	}

	public synchronized int deleteAll() {
		SQLiteDatabase db = helper.getWritableDatabase();
		int num = db.delete("process", null, null);
		db.close();
		return num;
	}
}
