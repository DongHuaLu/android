package com.example.sqlite.test;

import java.util.Random;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.Dao.PersonDao2;
import com.example.listview.PersonSQLiteOpenHelper;

public class testPersonDao2 extends AndroidTestCase {

	public void testAdd() {
		PersonDao2 dao = new PersonDao2(getContext());
		long number = 65784141l;
		Random random = new Random();
		for (int i = 0; i < 50; i++) {
			dao.add("wangwu" + i, Long.toString(number + i),
					random.nextInt(10000));
		}
	}

	public void testTransaction() throws Exception {
		PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(getContext());
		SQLiteDatabase db = helper.getWritableDatabase();
		db.beginTransaction();
		try {
			db.execSQL("UPDATE person SET money=money-1000 WHERE name=?",
					new Object[] { "zhangsan" });
			int i = 1 / 0;
			db.execSQL("UPDATE person SET money=money+1000 WHERE name=?",
					new Object[] { "wangwu" });
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
	}

	public void testTransaction2() throws Exception {
		PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(getContext());
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("UPDATE person SET money=money-1000 WHERE name=?",
				new Object[] { "zhangsan" });
		int i = 1 / 0;
		db.execSQL("UPDATE person SET money=money+1000 WHERE name=?",
				new Object[] { "wangwu" });
	}

}
