package com.example.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View view) {
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher,
				"我是一个notification", System.currentTimeMillis());
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel://114"));
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				intent, 0);
		notification.setLatestEventInfo(this, "title", "text", pendingIntent);
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.vibrate = new long[] { 200, 300 };
		notification.icon=R.drawable.ic_launcher;
		// Notification notification = new Builder(this)
		// .setContentTitle("title")
		// .setContentText("subject")
		// .setSmallIcon(R.drawable.ic_launcher)
		// .setLargeIcon(
		// BitmapFactory.decodeResource(getResources(),
		// R.drawable.ic_launcher)).build();

		nm.notify(0, notification);
	}
}
