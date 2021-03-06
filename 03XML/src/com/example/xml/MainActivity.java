package com.example.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import com.example.domain.Sms;

public class MainActivity extends Activity {
	private List<Sms> smss;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		smss = new ArrayList<Sms>();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			Sms sms = new Sms(random.nextInt(2) + 1, "content" + i, 155 + ""
					+ i, System.currentTimeMillis(), i);
			smss.add(sms);
		}
	}

	public void backSms(View view) {
		XmlSerializer serializer = Xml.newSerializer();
		try {
			FileOutputStream os = new FileOutputStream(new File(
					Environment.getExternalStorageDirectory(), "backsms.xml"));
			serializer.setOutput(os, "utf-8");
			serializer.startDocument("utf-8", true);
			serializer.startTag(null, "smss");
			
			
			for (Sms sms:smss){
				serializer.startTag(null, "sms");
				serializer.attribute(null, "id", sms.getId()+"");
				
				serializer.startTag(null, "address");
				serializer.text(sms.getAddress());
				serializer.endTag(null, "address");
				
				serializer.startTag(null, "date");
				serializer.text(sms.getDate()+"");
				serializer.endTag(null, "date");
				
				serializer.startTag(null, "type");
				serializer.text(sms.getType()+"");
				serializer.endTag(null, "type");
				
				serializer.endTag(null, "sms");
			}
			
			
			serializer.endTag(null, "smss");
			serializer.endDocument();
			os.close();
			Toast.makeText(this, "保存成功", 0).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "保存失败", 0).show();
		}

	}

}
