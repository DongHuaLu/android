package com.example.killself;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View view) {
		int pid = android.os.Process.myPid();
		android.os.Process.killProcess(pid);

		ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		// 需要加权限 不能用于自杀
		// am.killBackgroundProcesses("packagename");
	}

}
