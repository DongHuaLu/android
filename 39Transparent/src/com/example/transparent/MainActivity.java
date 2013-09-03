package com.example.transparent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv_before;
	private ImageView iv_after;
	private Bitmap alter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv_before = (ImageView) findViewById(R.id.before);
		iv_after = (ImageView) findViewById(R.id.after);
		Bitmap after = BitmapFactory.decodeResource(getResources(),
				R.drawable.after);
		Bitmap before = BitmapFactory.decodeResource(getResources(),
				R.drawable.before);
		alter = Bitmap.createBitmap(before.getWidth(), before.getHeight(),
				before.getConfig());

		iv_before.setImageBitmap(before);
		iv_after.setImageBitmap(after);

		iv_before.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					
					break;
				case MotionEvent.ACTION_MOVE:

					break;

				default:
					break;
				}
				return true;
			}
		});
	}
}
