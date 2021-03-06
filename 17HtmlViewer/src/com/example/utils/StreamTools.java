package com.example.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTools {

	public static String readInputStream(InputStream in,String contentType) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		byte[] buffer = new byte[1024];

		try {
			while ((len = in.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			in.close();
			String str=baos.toString();
			byte[] result = baos.toByteArray();
			String[] type=contentType.split("=");
			if(type.length==2){
				String temp=new String(result,type[1]);
				return temp;
			}else{
				String temp=  new String(result);
				if (temp.contains("utf-8")){
					return temp;
				}else if(temp.contains("gb2312")){
					return new String(result,"gb2312");
				}else if(temp.contains("gbk")){
					return new String(result,"gbk");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
