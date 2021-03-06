package com.example.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.util.StreamTools;

public class LoginService {

	public static String loginByGet(String username, String password) {
		try {
			String path = "http://10.200.0.157:8080/Server/LoginServlet?username="
					+ URLEncoder.encode(username, "utf-8")
					+ "&password="
					+ URLEncoder.encode(password, "utf-8");
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5000);
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			if (code == 200) {
				return StreamTools.readInputStream(conn.getInputStream());
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String loginByPost(String username, String password) {
		String path = "http://10.200.0.157:8080/Server/LoginServlet";
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5000);

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			String data = "username=" + URLEncoder.encode(username, "utf-8")
					+ "&password=" + URLEncoder.encode(password, "utf-8");
			conn.setRequestProperty("Content-Length", data.length() + "");

			conn.setDoOutput(true);
			conn.getOutputStream().write(data.getBytes());

			int code = conn.getResponseCode();
			if (code == 200) {
				return StreamTools.readInputStream(conn.getInputStream());
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String loiginByClientGet(String username, String password) {
		try {
			HttpClient client = new DefaultHttpClient();

			String path = "http://10.200.0.157:8080/Server/LoginServlet?username="
					+ URLEncoder.encode(username)
					+ "&password="
					+ URLEncoder.encode(password);

			HttpGet httpget = new HttpGet(path);
			HttpResponse response = client.execute(httpget);
			int code = response.getStatusLine().getStatusCode();

			if (code == 200) {
				return StreamTools.readInputStream(response.getEntity()
						.getContent());
			}
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String loiginByClientPost(String username, String password) {
		try {
			HttpClient client = new DefaultHttpClient();

			String path = "http://10.200.0.157:8080/Server/LoginServlet";

			HttpPost httppost = new HttpPost(path);
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();

			parameters.add(new BasicNameValuePair("username", username));
			parameters.add(new BasicNameValuePair("password", password));

			httppost.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
			HttpResponse response = client.execute(httppost);
			int code = response.getStatusLine().getStatusCode();

			if (code == 200) {
				return StreamTools.readInputStream(response.getEntity()
						.getContent());
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
