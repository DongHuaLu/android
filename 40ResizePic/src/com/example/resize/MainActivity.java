package com.example.resize;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv1;
	private ImageView iv2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv1 = (ImageView) findViewById(R.id.iv1);
		iv2 = (ImageView) findViewById(R.id.iv2);

		Bitmap bm = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		iv1.setImageBitmap(bm);

		Bitmap alterAble = Bitmap.createBitmap(bm.getWidth() * 2,
				bm.getHeight() * 2, bm.getConfig());

		Canvas canvas = new Canvas(alterAble);
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		Matrix matrix = new Matrix();
		// matrix.setValues(new float[] { 2, 0, 0, 0, 2, 0, 0, 0, 1 });
		// 缩放
		// matrix.setScale(sx, sy);

		// 以图像的的x,y为坐标 ,旋转degrees度
		// matrix.setRotate(degrees, px, py)
		// matrix.setRotate(180, alterAble.getWidth() / 2,
		// alterAble.getHeight() / 2);
		// matrix.setRotate(15);
		// 消除锯齿
		// paint.setAntiAlias(true);

		// 图片的平移 set只有drawBitmap后才会平移,post会直接平移
		matrix.setTranslate(10, 10);
//		matrix.postTranslate(10, 10);
		canvas.drawBitmap(bm, matrix, paint);
		iv2.setImageBitmap(alterAble);
	}
}
