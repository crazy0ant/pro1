package com.example.mobilplay.page;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.mobilplay.base.BasePager;

public class AudioPager extends BasePager{

	private TextView textView;
	public AudioPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		Log.i("Test","本地音乐页面被初始化了");
		textView = new TextView(context);
		textView.setTextSize(25);
		textView.setGravity(Gravity.CENTER);
		textView.setTextColor(Color.RED);
		return textView;
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		Log.i("Test", "本地音乐页面的数据被初始化了");
		textView.setText("本地音乐页面");
		
	}

}














