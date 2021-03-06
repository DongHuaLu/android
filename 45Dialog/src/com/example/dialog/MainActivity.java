package com.example.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void alertDialog(View view) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("title");
		builder.setMessage("MESSAGE");
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "按了确定", 0).show();
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.show();
	}

	public void singleChoice(View view) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("singleChoice");
		final String[] items = new String[] { "条目1", "条目2", "条目3", "条目4", };
		builder.setSingleChoiceItems(items, 0, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, items[which] + "被选中了", 0)
						.show();
				dialog.dismiss();
			}
		});

		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.show();
	}

	boolean[] checkedItems;

	public void multiChoice(View view) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("multiChoice");
		final String[] items = new String[] { "条目1", "条目2", "条目3", "条目4" };
		checkedItems = new boolean[] { true, true, false, false };
		builder.setMultiChoiceItems(items, checkedItems,
				new OnMultiChoiceClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which,
							boolean isChecked) {
						Toast.makeText(MainActivity.this,
								items[which] + isChecked, 0).show();
					}
				});
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, checkedItems[3] + "", 0)
						.show();
			}
		});

		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.show();
	}

	public void progress(View view) {
		ProgressDialog pd = new ProgressDialog(this);
		pd.setTitle("progress");
		pd.setMessage("请等待...");
		pd.show();
	}

	ProgressDialog pd;

	public void progressStyle(View view) {
		pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMax(100);
		new Thread() {
			public void run() {
				for (int i = 0; i <= 100; i++) {
					pd.setProgress(i);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				pd.dismiss();
			};
		}.start();
		pd.setTitle("progress");
		pd.setMessage("请等待...");
		pd.show();
	}
}
