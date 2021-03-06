package com.example.listview;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonDBProvider extends ContentProvider {

	// 定义一个uri的匹配器,用于匹配uri,如果不满足条件则返回-1
	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int INSERT = 1;
	private static final int DELETE = 2;
	private static final int UPDATE = 3;
	private static final int QUERY = 4;
	private static final int QUERYONE = 5;
	private PersonSQLiteOpenHelper helper;

	static {
		matcher.addURI("com.example.provider.personprovider", "insert", INSERT);
		matcher.addURI("com.example.provider.personprovider", "delete", DELETE);
		matcher.addURI("com.example.provider.personprovider", "update", UPDATE);
		matcher.addURI("com.example.provider.personprovider", "query", QUERY);
		matcher.addURI("com.example.provider.personprovider", "query/#",
				QUERYONE);
	}

	// content://com.example.provider.personprovider/insert
	// content://com.example.provider.personprovider/delete
	// content://com.example.provider.personprovider/update
	// content://com.example.provider.personprovider/query

	@Override
	public boolean onCreate() {
		helper = new PersonSQLiteOpenHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		if (matcher.match(uri)==QUERY){
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor= db.query("person", projection, selection, selectionArgs, null, null, sortOrder, null);
			return cursor;
		}else if(matcher.match(uri)==QUERYONE){
			long id=ContentUris.parseId(uri);
			SQLiteDatabase db=helper.getReadableDatabase();
			Cursor cursor= db.query("person", projection, "id=?", new String[]{id+""}, null, null, sortOrder, null);
			return cursor;
		}else{
			throw new IllegalArgumentException("路径错误");
		}
	}

	@Override
	public String getType(Uri uri) {
		if (matcher.match(uri)==QUERY){
			return "vnd.android.cursor.dir/person";
		}else if (matcher.match(uri)==QUERYONE){
			return "vnd.android.cursor.item/person";
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if (matcher.match(uri) == INSERT) {
			SQLiteDatabase db = helper.getWritableDatabase();
			db.insert("person", null, values);
		} else {
			throw new IllegalArgumentException("路径错误");
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		if (matcher.match(uri) == DELETE) {
			SQLiteDatabase db = helper.getWritableDatabase();
			db.delete("person", selection, selectionArgs);
		} else {
			throw new IllegalArgumentException("路径错误");
		}
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		if (matcher.match(uri) == UPDATE) {
			SQLiteDatabase db = helper.getWritableDatabase();
			db.update("person", values, selection, selectionArgs);
		} else {
			throw new IllegalArgumentException("路径错误");
		}
		return 0;
	}

}
