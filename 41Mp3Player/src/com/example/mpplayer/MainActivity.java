package com.example.mpplayer;

import java.io.File;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private EditText et_path;
	private Button play, pause, stop, replay;
	private MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_path = (EditText) findViewById(R.id.et_path);
		pause = (Button) findViewById(R.id.pause);
		play = (Button) findViewById(R.id.play);
		stop = (Button) findViewById(R.id.stop);
		replay = (Button) findViewById(R.id.replay);

		play.setOnClickListener(this);
		stop.setOnClickListener(this);
		replay.setOnClickListener(this);
		pause.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play:
			play();
			break;
		case R.id.stop:
			stop();
			break;
		case R.id.replay:
			replay();
			break;
		case R.id.pause:
			pause();
			break;

		}
	}

	private void replay() {
		if (mediaPlayer != null) {
			mediaPlayer.seekTo(0);
			return;
		}

	}

	private void stop() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
			play.setEnabled(true);
			return;
		}
	}

	private void pause() {
		if ("继续".equals(pause.getText().toString().trim())) {
			mediaPlayer.start();
			pause.setText("暂停");
			return;
		}
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
			pause.setText("继续");
			return;
		}
	}

	private void play() {
		String path = et_path.getText().toString().trim();
		File file = new File(path);

		if (file.exists() && file.length() > 0) {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			try {
				mediaPlayer.setDataSource(path);
				mediaPlayer.prepare();
				mediaPlayer.start();
				play.setEnabled(false);
				mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						play.setEnabled(true);
					}
				});
			} catch (Exception e) {
				Toast.makeText(this, "错误", 0).show();
				e.printStackTrace();
			}
		} else {
			Toast.makeText(this, "路径错误", 0).show();
		}
	}
}
