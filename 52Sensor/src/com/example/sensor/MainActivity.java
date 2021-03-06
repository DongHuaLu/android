package com.example.sensor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	private SensorManager sensorManager;
	private TextView tv;
	private MyListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		listener = new MyListener();
		sensorManager.registerListener(listener, sensor,
				SensorManager.SENSOR_DELAY_GAME);
	}

	private class MyListener implements SensorEventListener {

		// 0=North, 90=East, 180=South, 270=West
		// 传感器数据变化
		@Override
		public void onSensorChanged(SensorEvent event) {
			float[] values = event.values;
			float angle = values[0];
			if (angle < 90) {
				tv.setText("与正北的角度为:" + angle + "北");
			} else if (angle < 180) {
				tv.setText("与正北的角度为:" + angle + "东");
			} else if (angle < 270) {
				tv.setText("与正北的角度为:" + angle + "南");
			} else {
				tv.setText("与正北的角度为:" + angle + "西");
			}

		}

		// 传感器精度变化
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}

	}

	@Override
	protected void onDestroy() {
		sensorManager.unregisterListener(listener);
		listener = null;
		super.onDestroy();
	}
}
