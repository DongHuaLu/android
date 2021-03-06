package com.example.xml2.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.example.xml2.domain.Sms;

public class XmlService {

	public static List<Sms> getSmss(InputStream in) {

		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(in, "utf-8");
			int type = parser.getEventType();
			List<Sms> smss = null;
			Sms sms = null;
			while (type != XmlPullParser.END_DOCUMENT) {
				switch (type) {
				case XmlPullParser.START_TAG:
					if ("smss".equals(parser.getName())) {
						smss = new ArrayList<Sms>();
					} else if ("sms".equals(parser.getName())) {
						sms = new Sms();
						sms.setId(Integer.parseInt(parser.getAttributeValue(
								null, "id")));
					} else if ("address".equals(parser.getName())) {
						sms.setAddress(parser.nextText().trim());
					} else if ("date".equals(parser.getName())) {
						sms.setDate(Long.parseLong((parser.nextText().trim())));
					} else if ("type".equals(parser.getName())) {
						sms.setType(Integer.parseInt(parser.nextText().trim()));
					}
					break;
			
				case XmlPullParser.END_TAG:
					if ("sms".equals(parser.getName())) {
						smss.add(sms);
						sms = null;
					}
				default:
					break;
				}
				type = parser.next();
			}
			return smss;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
