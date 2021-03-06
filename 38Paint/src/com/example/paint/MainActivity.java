package com.example.paint;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ImageView iv;
	private Bitmap bm;
	private Bitmap baseBm;
	private Canvas canvas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
	}

	public void save(View view) {
		try {
			File file = new File("/sdcard/a.jpg");
			FileOutputStream out = new FileOutputStream(file);
			bm.compress(CompressFormat.JPEG, 100, out);
			out.close();
			Toast.makeText(this, "保存图片成功", 0).show();
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
			intent.setData(Uri.fromFile(Environment
					.getExternalStorageDirectory()));
			sendBroadcast(intent);
		} catch (Exception e) {
			Toast.makeText(this, "保存图片失败", 0).show();
			e.printStackTrace();
		}
	}

	public void select(View view) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_PICK);
		intent.setType("image/*");
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Uri uri = data.getData();
		String imagepath = Utils.convertUriToFilepath(this, uri);
		addCanvas(imagepath);
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void addCanvas(String imagepath) {
		baseBm = Utils.loadImage(imagepath, 492, 730);
		bm = Bitmap.createBitmap(492, 730, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bm);
		canvas.drawBitmap(baseBm, new Matrix(), new Paint());
		iv.setImageBitmap(bm);
		iv.setOnTouchListener(new OnTouchListener() {
			int startX;
			int startY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = (int) event.getX();
					startY = (int) event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					int newX = (int) event.getX();
					int newY = (int) event.getY();
					Paint paint = new Paint();
					paint.setStrokeWidth(10);
					paint.setColor(Color.RED);

					canvas.drawLine(startX, startY, newX, newY, paint);

					// 重新更新画笔的位置
					startX = (int) event.getX();
					startY = (int) event.getY();
					iv.setImageBitmap(bm);
					break;
				case MotionEvent.ACTION_UP:

					break;

				default:
					break;
				}

				return true;
			}
		});
	}
}
