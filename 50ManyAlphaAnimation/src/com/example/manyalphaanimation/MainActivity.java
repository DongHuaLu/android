package com.example.manyalphaanimation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	private ImageView iv;
	private ViewGroup ll;
	private List<ImageView> livs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ll = (ViewGroup) findViewById(R.id.ll);
		livs = new ArrayList<ImageView>();
		for (int i = 0; i < 16; i++) {
			ImageView temp = (ImageView) findViewById(R.id.ImageView01 + i);
			livs.add(temp);
		}

	}

	/**
	 * 透明度变化
	 * 
	 * @param view
	 */

	public void click1(View view) {
		for (ImageView ivs : livs) {
			alpha(ivs);
		}
	}

	public void click2(View view) {
		for (ImageView ivs : livs) {
			scale(ivs);
		}
	}

	public void click3(View view) {
		for (ImageView ivs : livs) {
			rotate(ivs);
		}
	}

	public void click4(View view) {
		for (ImageView ivs : livs) {
			trans(ivs);
		}
	}

	public void click5(View view) {
		for (ImageView ivs : livs) {
			comp(ivs);
		}
	}

	public void alpha(ImageView i) {
		AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
		Random random = new Random();
		// 动画的持续时间
		animation.setDuration(random.nextInt(500) + 100);
		animation.setRepeatCount(Animation.INFINITE);
		animation.setRepeatMode(Animation.REVERSE);
		i.startAnimation(animation);
	}

	public void scale(ImageView i) {
		Random random = new Random();
		ScaleAnimation animation = new ScaleAnimation(random.nextFloat(),
				random.nextFloat() + 1, random.nextFloat(),
				random.nextFloat() + 1, Animation.RELATIVE_TO_SELF,
				random.nextFloat(), Animation.RELATIVE_TO_SELF,
				random.nextFloat());
		animation.setDuration(random.nextInt(500) + 100);
		animation.setRepeatCount(Animation.INFINITE);
		animation.setRepeatMode(Animation.REVERSE);
		i.startAnimation(animation);
	}

	public void rotate(ImageView i) {
		Random random = new Random();
		RotateAnimation animation = new RotateAnimation(random.nextInt(100),
				random.nextInt(360), Animation.RELATIVE_TO_SELF,
				random.nextFloat(), Animation.RELATIVE_TO_SELF,
				random.nextFloat());
		animation.setDuration(random.nextInt(500) + 100);
		animation.setRepeatCount(Animation.INFINITE);
		animation.setRepeatMode(Animation.REVERSE);
		i.startAnimation(animation);
	}

	public void trans(ImageView i) {
		Random random = new Random();
		TranslateAnimation animation = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, random.nextFloat(),
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, random.nextFloat());
		animation.setDuration(random.nextInt(500) + 100);
		animation.setRepeatCount(Animation.INFINITE);
		animation.setRepeatMode(Animation.REVERSE);
		i.startAnimation(animation);
	}

	public void comp(ImageView i) {
		AnimationSet set = new AnimationSet(false);
		Random random = new Random();
		AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
		// 动画的持续时间
		animation1.setDuration(random.nextInt(500) + 100);
		animation1.setRepeatCount(Animation.INFINITE);
		animation1.setRepeatMode(Animation.REVERSE);

		ScaleAnimation animation2 = new ScaleAnimation(random.nextFloat(),
				random.nextFloat() + 1, random.nextFloat(),
				random.nextFloat() + 1, Animation.RELATIVE_TO_SELF,
				random.nextFloat(), Animation.RELATIVE_TO_SELF,
				random.nextFloat());
		animation2.setDuration(random.nextInt(500) + 100);
		animation2.setRepeatCount(Animation.INFINITE);
		animation2.setRepeatMode(Animation.REVERSE);

		RotateAnimation animation3 = new RotateAnimation(random.nextInt(100),
				random.nextInt(360), Animation.RELATIVE_TO_SELF,
				random.nextFloat(), Animation.RELATIVE_TO_SELF,
				random.nextFloat());
		animation3.setDuration(random.nextInt(500) + 100);
		animation3.setRepeatCount(Animation.INFINITE);
		animation3.setRepeatMode(Animation.REVERSE);

		TranslateAnimation animation4 = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, random.nextFloat(),
				Animation.RELATIVE_TO_PARENT, random.nextFloat(),
				Animation.RELATIVE_TO_PARENT, random.nextFloat(),
				Animation.RELATIVE_TO_PARENT, random.nextFloat());
		animation4.setDuration(random.nextInt(500) + 100);
		animation4.setRepeatCount(Animation.INFINITE);
		animation4.setRepeatMode(Animation.REVERSE);

		set.addAnimation(animation1);
		set.addAnimation(animation2);
		set.addAnimation(animation3);
		set.addAnimation(animation4);

		i.startAnimation(set);
	}

}
