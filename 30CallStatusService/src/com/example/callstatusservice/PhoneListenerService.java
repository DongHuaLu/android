package com.example.callstatusservice;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneListenerService extends Service {
	private MediaRecorder recorder;

	// 长期运行在后台的服务 如果不手动关闭 ,不会停止

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("服务创建了");
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		tm.listen(new MyPhoneStatusListener(),
				PhoneStateListener.LISTEN_CALL_STATE);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("服务销毁了");
	}

	private class MyPhoneStatusListener extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);
			try {
				switch (state) {
				case TelephonyManager.CALL_STATE_IDLE:// 空闲状态
					System.out.println("空闲状态");
					if (recorder != null) {
						recorder.stop();
						recorder.reset(); // You can reuse the object by going
											// back
											// to setAudioSource() step
						recorder.release(); // Now the object cannot be reused
						recorder = null;
					}
					break;
				case TelephonyManager.CALL_STATE_RINGING:// 响铃状态
					System.out.println("响铃状态,准备录制,来电号码" + incomingNumber);

					recorder = new MediaRecorder();
					// 设置录制的音频源
					recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					recorder.setOutputFile("/sdcard/"
							+ System.currentTimeMillis() + "from"
							+ incomingNumber + ".3gp");
					recorder.prepare();
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:// 通话状态
					System.out.println("通话状态");
					if (recorder != null) {
						recorder.start(); // Recording is now started
					}

					break;
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
