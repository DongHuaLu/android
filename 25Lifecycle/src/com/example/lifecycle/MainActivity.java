package com.example.lifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("onCreate");
    }


    @Override
    protected void onDestroy() {
    	super.onDestroy();
        System.out.println("onDestroy");

    }
    
    @Override
    protected void onPause() {
    	super.onPause();
        System.out.println("onPause");

    }
    
    @Override
    protected void onResume() {
    	super.onResume();
        System.out.println("onResume");

    }
    
    @Override
    protected void onStart() {
    	super.onStart();
        System.out.println("onStart");

    }
    
    @Override
    protected void onStop() {
    	super.onStop();
        System.out.println("onStop");

    }
    
}
