package com.example.asnychttp;

import java.net.URLEncoder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void clickGet(View view) {
		AsyncHttpClient client = new AsyncHttpClient();

		String path = "http://10.200.0.157:8080/Server/LoginServlet?username="
				+ URLEncoder.encode("ddd") + "&password="
				+ URLEncoder.encode("xxx");

		client.get(path, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				Toast.makeText(MainActivity.this, response, 0).show();
			}

			@Override
			public void onFailure(Throwable error, String content) {
				Toast.makeText(MainActivity.this, content, 0).show();
			}
		});
	}

	public void clickPost(View view) {
		AsyncHttpClient client = new AsyncHttpClient();

		String path = "http://10.200.0.157:8080/Server/LoginServlet";
		RequestParams params = new RequestParams();
		params.put("username", "zhang2");
		params.put("password", "ppppp");

		client.post(path, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				Toast.makeText(MainActivity.this, response, 0).show();
			}

			@Override
			public void onFailure(Throwable error, String content) {
				Toast.makeText(MainActivity.this, content, 0).show();
			}
		});
	}

}
