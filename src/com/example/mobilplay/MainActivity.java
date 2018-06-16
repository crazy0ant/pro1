package com.example.mobilplay;

import java.util.ArrayList;

import com.example.mobilplay.base.BasePager;
import com.example.mobilplay.page.AudioPager;
import com.example.mobilplay.page.NetAudioPager;
import com.example.mobilplay.page.NetVideoPager;
import com.example.mobilplay.page.VideoPager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class MainActivity extends Activity{
	/*
	 * MainActivity
	 */
	private FrameLayout fl_main_content;
	
	private RadioGroup rb_bottom_tag;
	
	//ҳ�漯��
	private ArrayList<BasePager> basePagers;
	
	//ѡ�е�λ��
	private int position;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fl_main_content = (FrameLayout) findViewById(R.id.fl_main_content);
		rb_bottom_tag = (RadioGroup) findViewById(R.id.rb_bottom_tag);
		
		rb_bottom_tag.check(R.id.rb_video);//Ĭ��ѡ����ҳ
		
		
		basePagers = new ArrayList<BasePager>();
		basePagers.add(new VideoPager(this));//��ӱ�����Ƶҳ��-0
		basePagers.add(new AudioPager(this));//-1
		basePagers.add(new NetVideoPager(this));//-2
		basePagers.add(new NetAudioPager(this));//-3
		
		//����radiogroup�ļ���
		rb_bottom_tag.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
		
		
	}
	
	class MyOnCheckedChangeListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (key) {
			case value:
				
				break;

			default:
				break;
			}
		}
	}
}
