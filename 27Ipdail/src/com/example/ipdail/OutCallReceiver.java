package com.example.ipdail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class OutCallReceiver extends BroadcastReceiver {
	private SharedPreferences sp;

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("onReceive 发现外拨电话");
		String number = getResultData();
		sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		String ipnumber = sp.getString("ipnumber", "");
		setResultData(ipnumber + number);

	}

}
