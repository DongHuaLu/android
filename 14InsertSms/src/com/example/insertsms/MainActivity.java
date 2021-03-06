package com.example.insertsms;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        new Thread(){
        	public void run(){
        		try {
					sleep(2);
					ContentResolver resolver = getContentResolver();
					Uri uri=Uri.parse("content://sms/");
					
					ContentValues values=new ContentValues();
					values.put("address", "95533");
					values.put("type", "1");
					values.put("date", System.currentTimeMillis());
					values.put("body", "您尾号为545的银行卡,余额为人民币100,000,000元");
					
					resolver.insert(uri, values);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
        }.start();
        
    }

}
