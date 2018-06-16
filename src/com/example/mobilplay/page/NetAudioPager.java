package com.example.mobilplay.page;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Gallery;
import android.widget.TextView;

import com.example.mobilplay.base.BasePager;

public class NetAudioPager extends BasePager{

	private TextView textView;
	public NetAudioPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		Log.i("Test","网络音乐页面被初始化了");
		textView = new TextView(context);
		textView.setTextSize(25);
		textView.setGravity(Gravity.CENTER);
		textView.setTextColor(Color.RED);
		return textView;
	}
	
	@Override
	public void initDate() {
		// TODO Auto-generated method stub
		super.initDate();
		Log.i("Test", "网络音乐页面的数据被初始化了");
		textView.setText("网络音乐页面");
		
	}

}














