package com.example.smslistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("短信收到了...");
		Object[] pdus = (Object[]) intent.getExtras().get("pdus");
		for (Object pdu : pdus) {
			SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
			String body = smsMessage.getMessageBody();
			String sender = smsMessage.getOriginatingAddress();
			System.out.println("body:" + body + "sender" + sender);
		}

		// 结束低优先级的广播事件 配置receiver 中的intent-filter的属性android:priority=""来设置优先级
		abortBroadcast();

	}

}
