package com.example.mobilplay.page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilplay.R;
import com.example.mobilplay.activity.SystemVideoPlayer;
import com.example.mobilplay.adapter.videoPagerAdapter;
import com.example.mobilplay.base.BasePager;
import com.example.mobilplay.domain.MediaItem;

/*
 * 本地视频页面
 */
public class VideoPager extends BasePager{
	private ListView listview;
	private TextView tv_nomedia;
	private ProgressBar pb_loading;

	//装数据集合
	private ArrayList<MediaItem> mediaItems;
	private videoPagerAdapter adapter;
	
	private TextView textView;
	public VideoPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if(mediaItems!=null&& mediaItems.size()>0){
				//有数据
				//设置适配器
				adapter = new videoPagerAdapter(context, mediaItems);
				listview.setAdapter(adapter);
				//文本隐藏
				tv_nomedia.setVisibility(View.GONE);
			}else{
				//没有数据
				//文本显示
				tv_nomedia.setVisibility(View.VISIBLE);
			}
			//把文本和progressbar隐藏
			pb_loading.setVisibility(View.GONE);
		};
		
	};
	
	@Override
	public View initView() {
		View view = View.inflate(context, R.layout.video_pager, null);
		listview = (ListView) view.findViewById(R.id.listview);
		tv_nomedia = (TextView) view.findViewById(R.id.tv_nomedia);
		pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);
		//设置listview点击事件
		listview.setOnItemClickListener(new MyOnItemClickListener());
		return view;
	}
	
	class MyOnItemClickListener implements AdapterView.OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			MediaItem mediaItem = mediaItems.get(position);
			Toast.makeText(context, "mediaItem:"+mediaItem.toString(), 1000).show();
			
			//1.调用系统所有的播放器-隐式意图
//			Intent intent = new Intent();
//			intent.setDataAndType(Uri.parse(mediaItem.getData()), "video/*");//视频地址，视频格式
//			context.startActivity(intent);
			//2.调用自己写的播放器-显示意图
			Intent intent = new Intent(context,SystemVideoPlayer.class);
			intent.setDataAndType(Uri.parse(mediaItem.getData()), "video/*");//视频地址，视频格式
			context.startActivity(intent);
			
		}
		
		
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		Log.i("Test", "本地视频页面的数据被初始化了");
	
		//加载本地视频数据
		getDataFromLocal();
		
	}

	/*
	 * 从本地sd卡得到数据
	 * 1.遍历sd，后缀名
	 * 2.从内容提供者里面获取
	 * 3.如果是6.0系统，动态获取读取sdcard的权限
	 */
	private void getDataFromLocal() {
		// TODO Auto-generated method stub
		mediaItems = new ArrayList<MediaItem>();
		new Thread(){
			public void run() {
				super.run();
				
				ContentResolver resolver = context.getContentResolver();
				Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				String objs[] = {
						MediaStore.Video.Media.DISPLAY_NAME,//视频文件在sdcard的名称
						MediaStore.Video.Media.DURATION,//视频时长
						MediaStore.Video.Media.SIZE,//视频文件大小
						MediaStore.Video.Media.DATA,//视频的绝对地址
						MediaStore.Video.Media.ARTIST,//歌曲演唱者
						
						
						
				};
				Cursor cursor = resolver.query(
						uri,
						objs, 
						null, 
						null, 
						null);
				if(cursor !=null){
					while (cursor.moveToNext()) {
						MediaItem mediaItem = new MediaItem();
						mediaItems.add(mediaItem);
						String name = cursor.getString(0);//视频名称
						mediaItem.setName(name);
						long duration = cursor.getLong(1);//视频的时长
						mediaItem.setDuration(duration);
						long size = cursor.getLong(2);//视频的文件大小
						mediaItem.setSize(size);
						String data = cursor.getString(3);//视频播放地址
						mediaItem.setData(data);
						String artist = cursor.getString(4);//艺术家
						mediaItem.setArtist(artist);
					}
					cursor.close();
					
				}
				//发消息
				handler.sendEmptyMessage(0);
			};
		}.start();
	}


 

}














