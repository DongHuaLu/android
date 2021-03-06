package com.example.uploadfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainActivity extends Activity {

	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.et);
	}

	public void click(View view) {
		String path = et.getText().toString().trim();
		File file = new File(path);
		try {
			if (file.exists() && file.length() > 0) {
				AsyncHttpClient client = new AsyncHttpClient();
				RequestParams params = new RequestParams();
				params.put("profile_picture", file);
				client.post("http://10.200.0.157:8080/Server/UploadServlet",
						params, new AsyncHttpResponseHandler() {

							@Override
							public void onSuccess(String content) {
								Toast.makeText(MainActivity.this, "上传成功", 0)
										.show();
								super.onSuccess(content);
							}

							@Override
							public void onFailure(Throwable error,
									String content) {
								Toast.makeText(MainActivity.this, "上传失败", 0)
										.show();
								super.onFailure(error, content);
							}

						});
			} else {
				Toast.makeText(this, "文件不存在", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
