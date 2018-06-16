package com.example.mobilplay.base;

import android.content.Context;
import android.view.View;

public abstract class BasePager {

	/*
	 * ������
	 */
	public final Context context;
	private View rootView;
	
	public BasePager(Context context){
		this.context = context;
		rootView = initView();
	}

	/*
	 * ǿ���к���ʵ�֣�ʵ���ض���Ч��
	 */
	public abstract View initView();
	
	/*
	 * ����ҳ����Ҫ��ʼ�����ݣ�������ȡ���ݻ������ʱ��д�÷���
	 */
	public void initDate(){
		
	}
	
}















