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
 * ������Ƶҳ��
 */
public class VideoPager extends BasePager{
	private ListView listview;
	private TextView tv_nomedia;
	private ProgressBar pb_loading;

	//װ���ݼ���
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
				//������
				//����������
				adapter = new videoPagerAdapter(context, mediaItems);
				listview.setAdapter(adapter);
				//�ı�����
				tv_nomedia.setVisibility(View.GONE);
			}else{
				//û������
				//�ı���ʾ
				tv_nomedia.setVisibility(View.VISIBLE);
			}
			//���ı���progressbar����
			pb_loading.setVisibility(View.GONE);
		};
		
	};
	
	@Override
	public View initView() {
		View view = View.inflate(context, R.layout.video_pager, null);
		listview = (ListView) view.findViewById(R.id.listview);
		tv_nomedia = (TextView) view.findViewById(R.id.tv_nomedia);
		pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);
		//����listview����¼�
		listview.setOnItemClickListener(new MyOnItemClickListener());
		return view;
	}
	
	class MyOnItemClickListener implements AdapterView.OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			MediaItem mediaItem = mediaItems.get(position);
			Toast.makeText(context, "mediaItem:"+mediaItem.toString(), 1000).show();
			
			//1.����ϵͳ���еĲ�����-��ʽ��ͼ
//			Intent intent = new Intent();
//			intent.setDataAndType(Uri.parse(mediaItem.getData()), "video/*");//��Ƶ��ַ����Ƶ��ʽ
//			context.startActivity(intent);
			//2.�����Լ�д�Ĳ�����-��ʾ��ͼ
			Intent intent = new Intent(context,SystemVideoPlayer.class);
			intent.setDataAndType(Uri.parse(mediaItem.getData()), "video/*");//��Ƶ��ַ����Ƶ��ʽ
			context.startActivity(intent);
			
		}
		
		
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		Log.i("Test", "������Ƶҳ������ݱ���ʼ����");
	
		//���ر�����Ƶ����
		getDataFromLocal();
		
	}

	/*
	 * �ӱ���sd���õ�����
	 * 1.����sd����׺��
	 * 2.�������ṩ�������ȡ
	 * 3.�����6.0ϵͳ����̬��ȡ��ȡsdcard��Ȩ��
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
						MediaStore.Video.Media.DISPLAY_NAME,//��Ƶ�ļ���sdcard������
						MediaStore.Video.Media.DURATION,//��Ƶʱ��
						MediaStore.Video.Media.SIZE,//��Ƶ�ļ���С
						MediaStore.Video.Media.DATA,//��Ƶ�ľ��Ե�ַ
						MediaStore.Video.Media.ARTIST,//�����ݳ���
						
						
						
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
						String name = cursor.getString(0);//��Ƶ����
						mediaItem.setName(name);
						long duration = cursor.getLong(1);//��Ƶ��ʱ��
						mediaItem.setDuration(duration);
						long size = cursor.getLong(2);//��Ƶ���ļ���С
						mediaItem.setSize(size);
						String data = cursor.getString(3);//��Ƶ���ŵ�ַ
						mediaItem.setData(data);
						String artist = cursor.getString(4);//������
						mediaItem.setArtist(artist);
					}
					cursor.close();
					
				}
				//����Ϣ
				handler.sendEmptyMessage(0);
			};
		}.start();
	}


 

}














