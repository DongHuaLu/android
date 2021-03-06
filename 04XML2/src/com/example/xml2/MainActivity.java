package com.example.xml2;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xml2.domain.Sms;
import com.example.xml2.service.XmlService;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void showSms(View view) {
		TextView tv = (TextView) findViewById(R.id.tv);
		try {
			List<Sms> smss = XmlService.getSmss(MainActivity.class.getClassLoader().getResourceAsStream("backsms.xml"));
			StringBuffer sb=new StringBuffer();
			for (Sms sms:smss){
				sb.append("\nid:"+sms.getId());
				sb.append("\ncontent:"+sms.getContent());
				sb.append("\naddress:"+sms.getAddress());
				sb.append("\ntype:"+sms.getType());
				sb.append("\ndate:"+sms.getDate());
			}
			System.out.println(sb.toString());
			tv.setText(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "解析失败", 0);
		}
	}
}
