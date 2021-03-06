package com.example.transparent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv_before;
	private ImageView iv_after;
	private Bitmap alter;
	private Canvas canvas;
	private Paint paint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv_before = (ImageView) findViewById(R.id.before);
		iv_after = (ImageView) findViewById(R.id.after);
		Options opts = new Options();
		opts.inSampleSize = 1;

		Bitmap after = BitmapFactory.decodeResource(getResources(),
				R.drawable.after, opts);
		Bitmap before = BitmapFactory.decodeResource(getResources(),
				R.drawable.before, opts);
		alter = Bitmap.createBitmap(before.getWidth(), before.getHeight(),
				before.getConfig());

		canvas = new Canvas(alter);
		paint = new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(Color.BLACK);
		canvas.drawColor(Color.BLACK);

		iv_before.setImageBitmap(alter);
		iv_after.setImageBitmap(after);
		iv_before.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					break;
				case MotionEvent.ACTION_MOVE:
					int x = (int) event.getX();
					int y = (int) event.getY();
					System.out.println(x + "," + y);
					for (int i = -10; i < 10; i++) {
						for (int j = -10; j < 10; j++) {
							if (x + i > iv_before.getWidth()
									|| y + j > iv_before.getHeight()) {
								break;
							} else if (x + i < 0 || y + j < 0) {
								break;
							} else {
								alter.setPixel(x + i, y + j, Color.TRANSPARENT);
							}
						}
					}
					iv_before.setImageBitmap(alter);
					break;
				default:
					break;
				}
				return true;
			}
		});
	}
}
