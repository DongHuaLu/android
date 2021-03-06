package com.example.paint;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.provider.MediaStore;

public class Utils {

	public static Bitmap loadImage(String filepath, int width, int height) {
		Options opts = new Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filepath, opts);
		int imgHeight = opts.outHeight;
		int imgWidth = opts.outWidth;
		int scaleX = imgWidth / width;
		int scaleY = imgHeight / height;
		int scale = 1;
		if (scaleX > scaleY && scaleX > 1) {
			scale = scaleX;
		}
		if (scaleY > scaleX && scaleY > 1) {
			scale = scaleY;
		}
		opts.inJustDecodeBounds = false;
		opts.inSampleSize = scale;
		Bitmap bitmap = BitmapFactory.decodeFile(filepath, opts);
		return bitmap;
	}

	public static String convertUriToFilepath(Activity activity, Uri uri) {
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor actualimagecursor = activity.managedQuery(uri, proj, null, null,
				null);
		int actual_image_column_index = actualimagecursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		actualimagecursor.moveToFirst();
		String imagepath = actualimagecursor
				.getString(actual_image_column_index);
		return imagepath;
	}

}
