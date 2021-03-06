package com.example.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTools {

	public static String readInputStream(InputStream in) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		byte[] buffer = new byte[1024];

		try {
			while ((len = in.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			in.close();
			String str = baos.toString();
			byte[] result = baos.toByteArray();
			return new String(result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
