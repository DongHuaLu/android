package com.example.login.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginService {

	public static void saveUserInfo(Context context, String username,
			String password) {
		SharedPreferences sp = context.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		editor.commit();
	}

	public static Map<String, String> getSavedUserInfo(Context context) {
		SharedPreferences sp = context.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		Map<String,String> map=new HashMap<String,String>();
		map.put("username", sp.getString("username", ""));
		map.put("password", sp.getString("password", ""));
		return map;
	}
}
