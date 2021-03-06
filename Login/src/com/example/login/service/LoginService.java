package com.example.login.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

public class LoginService {
	
	public static boolean saveUserInfo(Context context,String username,String password,int mode){
		FileOutputStream fos=null;
		try {
			//File file=new File("/data/data/com.example.login/info.txt");
			
			File file=new File(context.getFilesDir(),"info.txt");
			//fos=new FileOutputStream(file);
			
			switch (mode) {
			case 1:
				fos=context.openFileOutput("private.txt", Context.MODE_PRIVATE);
				break;
			case 2:
				fos=context.openFileOutput("readable.txt", Context.MODE_WORLD_READABLE);
				break;
			case 3:
				fos=context.openFileOutput("witreable.txt", Context.MODE_WORLD_WRITEABLE);
				break;
			case 4:
				fos=context.openFileOutput("public.txt", Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE);
				break;
			}
			
			fos.write((username+","+password).getBytes());			
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}

	public static Map<String,String> getSavedUserInfo(Context context){
		BufferedReader br=null;
		FileInputStream fis=null;
		File file=null;
		try {
			file=new File(context.getFilesDir(),"info.txt");
			fis=new FileInputStream(file);
			br=new BufferedReader(new InputStreamReader(fis));
			try {
				String str=br.readLine();
				String[] infos= str.split(",");
				Map<String,String> infomap=new HashMap<String, String>();
				infomap.put("username", infos[0]);
				infomap.put("password", infos[1]);
				br.close();
				fis.close();
				return infomap;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}	
	}
}
