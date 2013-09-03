package com.example.soundpool;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	private SoundPool soundPool;
	private int soundId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		soundId = soundPool.load(this, R.raw.sho1, 1);
	}

	public void click(View view) {
		soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
	}
}
