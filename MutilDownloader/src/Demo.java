import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class Demo {

	/**
	 * @param args
	 */
	public static int threadCount = 5;
	public static int runningThread = 0;

	public static void main(String[] args) {
		// 1.连接服务器,获取文件长度,在本地创建一个大小一样的空文件
		String path = "http://10.200.0.157:8080/1.exe";
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5000);
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			if (code == 200) {
				// 服务器返回的数据的长度,其实就是文件的长度
				int length = conn.getContentLength();
				System.out.println("文件长度:" + length);
				// 在客户端本地创建一个length大小的临时文件
				RandomAccessFile raf = new RandomAccessFile("1.exe", "rwd");
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
					System.out.println("线程" + threadId + "下载:" + startIndex
							+ "------>" + endIndex);
					new DownloadThread(threadId, startIndex, endIndex, path)
							.start();
				}
			} else {
				System.out.println("服务器错误");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static class DownloadThread extends Thread {

		private int threadId;
		private int startIndex;
		private int endIndex;
		private String path;

		public DownloadThread(int threadId, int startIndex, int endIndex,
				String path) {
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.path = path;
		}

		@Override
		public void run() {
			runningThread++;
			try {
				File f = new File(threadId + ".txt");
				if (f.exists() && f.length() > 0) {
					FileInputStream fis = new FileInputStream(f);
					byte[] info = new byte[1024];
					int leng = fis.read(info);
					int downloadedLength = Integer.parseInt(new String(info, 0,
							leng));
					startIndex = downloadedLength;
					fis.close();
				}

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
				RandomAccessFile raf = new RandomAccessFile("1.exe", "rwd");
				// 文件从哪个位置开始写
				raf.seek(startIndex);

				int len = 0;
				byte[] buff = new byte[1024];
				// 记录当前线程下载的长度
				int total = 0;
				File file = new File(threadId + ".txt");
				while ((len = in.read(buff)) != -1) {
					RandomAccessFile info = new RandomAccessFile(file, "rwd");
					raf.write(buff, 0, len);
					total += len;
					info.write(String.valueOf(total + startIndex).getBytes());
					info.close();
				}
				in.close();
				raf.close();
				System.out.println("线程" + threadId + "下载完成");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				runningThread--;
				if (runningThread == 0) {
					for (int i = 1; i <= threadCount; i++) {
						File deleteFile = new File(i + ".txt");
						deleteFile.delete();
					}
					System.out.println("文件下载完毕");
				}
			}
		}

	}
}
