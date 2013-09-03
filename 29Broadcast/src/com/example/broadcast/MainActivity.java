package com.example.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void click(View view){
    	Intent intent=new Intent();
    	intent.setAction("com.example.xxxooo");
    	
    	//无序发送的,所有广播接受者都会接受到这条广播
    	sendBroadcast(intent);
    	
    	//有序发送的广播,会按照优先级接受广播
    	sendOrderedBroadcast(intent, null);
    }
    
}
