package com.example.useimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;
	private Bitmap bm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		bm = BitmapFactory.decodeFile("/sdcard/aa.jpg");
		iv.setImageBitmap(bm);
	}

	public void add(View view) {
		Bitmap bitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(),
				Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmap);

		canvas.drawBitmap(bm, new Matrix(), new Paint());
		Paint paint = new Paint();
		paint.setTextSize(20);
		paint.setColor(Color.RED);
		canvas.drawText("我是添加的文字", 40, 40, paint);
		iv.setImageBitmap(bitmap);

	}

}
