package com.example.sqlite.test;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.Dao.PersonDao;
import com.example.domain.Person;
import com.example.sqlite.PersonSQLiteOpenHelper;

public class testPersonDB extends AndroidTestCase {
	
    
    public void testCreateDB(){
    	PersonSQLiteOpenHelper helper=new PersonSQLiteOpenHelper(getContext());
    	SQLiteDatabase db=helper.getWritableDatabase();
    }
    
    public void testAdd(){
    	
    }

}
