package com.example.mobilplay.page;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mobilplay.R;
import com.example.mobilplay.base.BasePager;

/*
 * 本地视频页面
 */
public class VideoPager extends BasePager{
	private ListView listview;
	private TextView tv_nomedia;
	private ProgressBar pb_loading;

	private TextView textView;
	public VideoPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		View view = View.inflate(context, R.layout.video_pager, null);
		listview = (ListView) view.findViewById(R.id.listview);
		tv_nomedia = (TextView) view.findViewById(R.id.tv_nomedia);
		pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);
		return textView;
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		Log.i("Test", "本地视频页面的数据被初始化了");
		textView.setText("本地视频页面");
		
	}

}














