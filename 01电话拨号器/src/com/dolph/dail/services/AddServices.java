package com.dolph.dail.services;

import android.util.Log;

public class AddServices {
	private String tag="AddServices";

	public int add(int x,int y){
		Log.v(tag, "v");
		Log.d(tag, "d");
		Log.i(tag, "i");
		Log.w(tag, "w");
		Log.e(tag, "e");
		
		System.out.println("out");
		System.err.println("err");
		return x+y;
	}
}
