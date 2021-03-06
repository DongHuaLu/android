package com.example.tweenanim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);

	}

	/**
	 * 透明度变化
	 * 
	 * @param view
	 */

	public void click1(View view) {
		// 透明度类(开始透明度,最终透明度)
		AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
		// 动画的持续时间
		animation.setDuration(2000);
		animation.setRepeatCount(Animation.INFINITE);
		animation.setRepeatMode(Animation.REVERSE);
		iv.startAnimation(animation);
	}

	public void click2(View view) {
		ScaleAnimation animation = new ScaleAnimation(0.2f, 2.0f, 0.2f, 2.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animation.setDuration(2000);
		animation.setRepeatCount(Animation.INFINITE);
		animation.setRepeatMode(Animation.REVERSE);
		iv.startAnimation(animation);
	}

	public void click3(View view) {
		RotateAnimation animation = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animation.setDuration(2000);
		animation.setRepeatCount(Animation.INFINITE);
		animation.setRepeatMode(Animation.REVERSE);
		iv.startAnimation(animation);
	}

	public void click4(View view) {
		TranslateAnimation animation = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.2f,
				Animation.RELATIVE_TO_PARENT, 1.0f,
				Animation.RELATIVE_TO_PARENT, 0.2f,
				Animation.RELATIVE_TO_PARENT, 1.0f);
		animation.setDuration(2000);
		animation.setRepeatCount(Animation.INFINITE);
		animation.setRepeatMode(Animation.REVERSE);
		iv.startAnimation(animation);
	}

	public void click5(View view) {
		AnimationSet set = new AnimationSet(false);

		AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
		// 动画的持续时间
		animation1.setDuration(2000);
		animation1.setRepeatCount(Animation.INFINITE);
		animation1.setRepeatMode(Animation.REVERSE);

		ScaleAnimation animation2 = new ScaleAnimation(0.2f, 2.0f, 0.2f, 2.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animation2.setDuration(2000);
		animation2.setRepeatCount(Animation.INFINITE);
		animation2.setRepeatMode(Animation.REVERSE);

		RotateAnimation animation3 = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animation3.setDuration(2000);
		animation3.setRepeatCount(Animation.INFINITE);
		animation3.setRepeatMode(Animation.REVERSE);

		TranslateAnimation animation4 = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.5f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.5f);
		animation4.setDuration(2000);
		animation4.setRepeatCount(Animation.INFINITE);
		animation4.setRepeatMode(Animation.REVERSE);

		set.addAnimation(animation1);
		set.addAnimation(animation2);
		set.addAnimation(animation3);
		set.addAnimation(animation4);

		iv.startAnimation(set);
	}
}
