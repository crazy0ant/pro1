package com.example.mobilplay;


import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.mobilplay.base.BasePager;
import com.example.mobilplay.page.AudioPager;
import com.example.mobilplay.page.NetAudioPager;
import com.example.mobilplay.page.NetVideoPager;
import com.example.mobilplay.page.VideoPager;




import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class MainActivity extends FragmentActivity{
	/*
	 * MainActivity
	 */
	private FrameLayout fl_main_content;
	
	private RadioGroup rb_bottom_tag;
	
	//页面集合
	private ArrayList<BasePager> basePagers;
	
	//选中的位置
	private int position = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fl_main_content = (FrameLayout) findViewById(R.id.fl_main_content);
		rb_bottom_tag = (RadioGroup) findViewById(R.id.rb_bottom_tag);
		
		rb_bottom_tag.check(R.id.rb_video);//默认选中首页
		
		
		basePagers = new ArrayList<BasePager>();
		basePagers.add(new VideoPager(this));//添加本地视频页面-0
		basePagers.add(new AudioPager(this));//-1
		basePagers.add(new NetVideoPager(this));//-2
		basePagers.add(new NetAudioPager(this));//-3
		
		//设置radiogroup的监听
		rb_bottom_tag.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
		
		
	}
	
	class MyOnCheckedChangeListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			default:
				position = 0;
				break;
			case R.id.rb_audio:
				position = 1;
				break;
			case R.id.rb_net_video:
				position = 2;
				break;
			case R.id.rb_net_audio:
				position = 3;
				break;
			}
			
			setFragment();
			
		}
	}

	/*
	 * 把页面添加到frangmeng中
	 */
	public void setFragment() {
		//1.得到FragmentManger
		FragmentManager manager = getSupportFragmentManager();
		//2.开启事务
		FragmentTransaction ft = manager.beginTransaction();
		ft.remove(null);
		//3.替换

		ft.replace(R.id.fl_main_content, new Fragment(){
			@Override
			public View onCreateView(LayoutInflater inflater,
					ViewGroup container, Bundle savedInstanceState) {
				// TODO Auto-generated method stub
				BasePager basePager1 = getBasePager();
				if(basePager1!=null){
					//各个页面的视图
					return basePager1.rootView;
				}
				return null;
			}
		});//The specified child already has a parent. You must call removeView() on the child's parent first.

		//4.提交事务
		ft.commit();
	}

	/*
	 * 根据位置得到对应的页面
	 */
	protected BasePager getBasePager() {
		// TODO Auto-generated method stub
		BasePager basePager2 = basePagers.get(position);
		if(basePager2!=null&&!basePager2.isInitData){
			basePager2.initDate();//联网请求或者绑定数据
			basePager2.isInitData = true;
		}
		return basePager2;
	}
}
