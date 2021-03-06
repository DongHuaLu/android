package com.example.videoplayer;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private EditText et_path;
	private Button play, pause, stop, replay;
	private MediaPlayer mediaPlayer;
	private SurfaceView sv;
	private SeekBar sb;
	private int currentPosition = 0;
	private boolean isPlaying = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_path = (EditText) findViewById(R.id.et_path);
		sv = (SurfaceView) findViewById(R.id.sv);
		sb = (SeekBar) findViewById(R.id.seekBar1);
		sv.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				if (mediaPlayer != null && mediaPlayer.isPlaying()) {
					int porgress = seekBar.getProgress();
					mediaPlayer.seekTo(porgress);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

			}
		});

		sv.getHolder().addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				System.out.println("holder销毁了");
				if (mediaPlayer != null && mediaPlayer.isPlaying()) {
					currentPosition = mediaPlayer.getCurrentPosition();
					stop();
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				System.out.println("holder创建了");
				if (currentPosition > 0) {
					play(currentPosition);
				}
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				System.out.println("holder大小变化了");

			}
		});

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
			play(0);
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
		play(0);

	}

	private void stop() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
			play.setEnabled(true);
			mediaPlayer.release();
			mediaPlayer = null;
			isPlaying = false;
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

	private void play(final int currentPosition2) {
		String path = et_path.getText().toString().trim();
		// File file = new File(path);

		// if (file.exists() && file.length() > 0) {
		if (true) {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setDisplay(sv.getHolder());
			try {
				mediaPlayer.setDataSource(path);
				// 同步准备
				// mediaPlayer.prepare();
				// mediaPlayer.start();

				// 异步准备,增加监听器,在准备完成后调用start()
				mediaPlayer.prepareAsync();
				mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

					@Override
					public void onPrepared(MediaPlayer mp) {
						mediaPlayer.start();
						int max = mediaPlayer.getDuration();
						sb.setMax(max);
						mediaPlayer.seekTo(currentPosition2);

						new Thread() {
							public void run() {
								isPlaying = true;
								while (isPlaying) {
									int position = mediaPlayer
											.getCurrentPosition();
									sb.setProgress(position);
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}

							};
						}.start();
					}
				});
				play.setEnabled(false);
				mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						play.setEnabled(true);
						mediaPlayer.release();
						mediaPlayer = null;
						currentPosition = 0;
						isPlaying = false;
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
