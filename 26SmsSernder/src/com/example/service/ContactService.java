package com.example.service;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.domain.ContactInfo;

public class ContactService {

	public static List<ContactInfo> getContactsInfo(Context context) {
		ContentResolver resolver = context.getContentResolver();
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri dataUri = Uri.parse("content://com.android.contacts/data");

		Cursor cursor = resolver.query(uri, new String[] { "contact_id" },
				null, null, null);
		List<ContactInfo> infos = new ArrayList<ContactInfo>();
		while (cursor.moveToNext()) {
			String id = cursor.getString(0);
			if (id != null) {
				ContactInfo info = new ContactInfo();
				Cursor cursorData = resolver.query(dataUri, new String[] {
						"data1", "mimetype" }, "raw_contact_id=?",
						new String[] { id }, null);
				while (cursorData.moveToNext()) {
					String data = cursorData.getString(0);
					String minetype = cursorData.getString(1);
					if ("vnd.android.cursor.item/phone_v2".equals(minetype)) {
						info.setNumber(data);
					} else if ("vnd.android.cursor.item/name".equals(minetype)) {
						info.setName(data);
					}
				}
				infos.add(info);
				cursorData.close();
			}
		}
		cursor.close();
		return infos;
	}
}
