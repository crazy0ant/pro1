package com.example.mobilplay.base;

import android.content.Context;
import android.view.View;

public abstract class BasePager {

	/*
	 * 上下文
	 */
	public final Context context;
	public View rootView;
	public boolean isInitData = false;
	
	public BasePager(Context context){
		this.context = context;
		rootView = initView();
	}

	/*
	 * 强制有孩子实现，实现特定的效果
	 */
	public abstract View initView();
	
	/*
	 * 当子页面需要初始化数据，联网获取数据或绑定数据时重写该方法
	 */
	public void initData(){
		
	}
	
}















