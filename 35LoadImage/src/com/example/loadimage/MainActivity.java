package com.example.loadimage;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;
	private int winHeight;
	private int winWidth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		winHeight = wm.getDefaultDisplay().getHeight();
		winWidth = wm.getDefaultDisplay().getWidth();
	}

	public void load(View view) {
		Options opts = new Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile("/sdcard/big.jpg", opts);
		int imgWidth = opts.outWidth;
		int imgHeight = opts.outHeight;

		int scaleX = imgWidth / winWidth;
		int scaleY = imgHeight / winHeight;
		int scale = 1;
		if (scaleX > scaleY && scaleX > 1) {
			scale = scaleX;
		} else if (scaleY > scaleX && scaleY > 1) {
			scale = scaleY;
		}
		opts.inJustDecodeBounds = false;
		opts.inSampleSize = scale;
		Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/big.jpg", opts);
		iv.setImageBitmap(bitmap);
	}

	public void read(View view) {
		try {
			ExifInterface exif = new ExifInterface("/sdcard/big.jpg");
			String time = exif.getAttribute(ExifInterface.TAG_DATETIME);
			System.out.println("time:" + time);
//			exif.setAttribute(ExifInterface.TAG_MODEL, "123");
//			exif.saveAttributes();
			String model = exif.getAttribute(ExifInterface.TAG_MODEL);
			System.out.println("model:" + model);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
