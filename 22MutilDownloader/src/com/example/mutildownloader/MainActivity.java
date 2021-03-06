package com.example.mutildownloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.dao.ProcessDao;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final int UPDATA_TEXTVIEW = 0;
	private static final int DOWN_SUCCESS = 1;

	private EditText et;
	private ProgressBar pb;
	private TextView tv;
	private ProcessDao dao = new ProcessDao(this);

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_SUCCESS:
				Toast.makeText(MainActivity.this, "下载完成", 0).show();
				break;
			case UPDATA_TEXTVIEW:
				tv.setText("下载进度" + pb.getProgress() * 100 / pb.getMax());
				break;
			}
		}

	};

	public static int threadCount = 5;
	public static int runningThread = 0;

	public int currentPorgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.et);
		pb = (ProgressBar) findViewById(R.id.pb);
		tv = (TextView) findViewById(R.id.tv);
	}

	public void click(View view) {
		final String path = et.getText().toString().trim();

		if (TextUtils.isEmpty(path)) {
			Toast.makeText(this, "路径不能为空", 0).show();
		}

		new Thread() {
			public void run() {
				try {
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.setReadTimeout(5000);
					conn.setRequestMethod("GET");
					int code = conn.getResponseCode();
					if (code == 200) {
						// 服务器返回的数据的长度,其实就是文件的长度
						int length = conn.getContentLength();
						pb.setMax(length);
						pb.setProgress(0);
						System.out.println("文件长度:" + length);
						// 在客户端本地创建一个length大小的临时文件
						String[] temp = path.split("/");
						String filename = temp[temp.length-1];
						File file = new File("/sdcard/" + filename);
						if (file.exists()) {
							file.delete();
						}
						RandomAccessFile raf = new RandomAccessFile(file, "rwd");

						raf.setLength(length);
						raf.close();
						// 假设三个线程
						// 平均每个线程下载文件的大小
						int blockSize = length / threadCount;

						for (int threadId = 1; threadId <= threadCount; threadId++) {
							int startIndex = (threadId - 1) * blockSize;
							int endIndex = (threadId) * blockSize - 1;
							if (threadId == threadCount) {
								endIndex = length;
							}
							System.out.println("线程" + threadId + "下载:"
									+ startIndex + "------>" + endIndex);
							new DownloadThread(threadId, startIndex, endIndex,
									path,filename).start();
						}
					} else {
						System.out.println("服务器错误");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			};
		}.start();

	}

	public class DownloadThread extends Thread {

		private int threadId;
		private int startIndex;
		private int endIndex;
		private String path;
		private String filename;

		public DownloadThread(int threadId, int startIndex, int endIndex,
				String path,String filename) {
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.path = path;
			this.filename=filename;
			dao.add(threadId+filename);

		}

		@Override
		public void run() {
			runningThread++;
			try {
				// File f = new File("/sdcard/" + threadId + ".txt");
				// if (f.exists() && f.length() > 0) {
				// FileInputStream fis = new FileInputStream(f);
				// byte[] info = new byte[1024];
				// int leng = fis.read(info);
				int downloadedLength = dao.getTotalByThreadId(threadId+filename);
				if (downloadedLength != 0) {
					int alreadyDownloadedInt = downloadedLength - startIndex;
					currentPorgress += alreadyDownloadedInt;
					startIndex = downloadedLength;
				}
				// fis.close();
				// }

				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				// 请求头,得到部分的文件
				conn.setRequestProperty("Range", "bytes=" + startIndex + "-"
						+ endIndex);
				System.out.println("线程" + threadId + "真实下载长度" + startIndex
						+ "---->" + endIndex);
				conn.setReadTimeout(5000);
				conn.setRequestMethod("GET");
				int code = conn.getResponseCode();
				System.out.println("code=" + code);

				InputStream in = conn.getInputStream();
				RandomAccessFile raf = new RandomAccessFile("/sdcard/"
						+ filename, "rwd");
				// 文件从哪个位置开始写
				raf.seek(startIndex);

				int len = 0;
				byte[] buff = new byte[65535];
				// 记录当前线程下载的长度
				int total = 0;
				// File file = new File("/sdcard/" + threadId + ".txt");
				while ((len = in.read(buff)) != -1) {
					// RandomAccessFile info = new RandomAccessFile(file,
					// "rwd");
					raf.write(buff, 0, len);
					total += len;
					// info.write(String.valueOf(total +
					// startIndex).getBytes());
					// info.close();

					dao.updateByThreadId(threadId+filename, total + startIndex);
					synchronized (MainActivity.this) {
						currentPorgress += len;
						pb.setProgress(currentPorgress);
						Message msg = Message.obtain();
						msg.what = UPDATA_TEXTVIEW;
						handler.sendMessage(msg);
					}
				}
				in.close();
				raf.close();
				System.out.println("线程" + threadId + "下载完成");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				threadTeardown();
			}
		}

		private synchronized void threadTeardown() {
			runningThread--;
			dao.deleteByThreadId(threadId+filename);
			if (runningThread == 0) {
				// for (int i = 1; i <= threadCount; i++) {
				// File deleteFile = new File("/sdcard/" + i + ".txt");
				// deleteFile.delete();
				// }
				System.out.println("文件下载完毕");
				Message msg = new Message();
				msg.what = DOWN_SUCCESS;
				handler.sendMessage(msg);
			}
		}

	}
}
