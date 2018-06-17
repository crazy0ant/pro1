package com.example.mobilplay.page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Context;
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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mobilplay.R;
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
				adapter = new videoPagerAdapter();
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
		return view;
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
	class videoPagerAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mediaItems.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			// TODO Auto-generated method stub
			ViewHoder viewHoder;
			if(convertView==null){
				View.inflate(context, R.layout.item_video_pager, null);
				viewHoder = new ViewHoder();
				viewHoder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
				viewHoder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
				viewHoder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
				viewHoder.tv_size = (TextView) convertView.findViewById(R.id.tv_size);
				convertView.setTag(viewHoder);
			}else{
				viewHoder = (ViewHoder) convertView.getTag();
			}
			
			//����position�õ��б��ж�Ӧλ�õ�����
			MediaItem mediaItem = mediaItems.get(position);
			viewHoder.tv_name.setText(mediaItem.getName());
			viewHoder.tv_size.setText(Formatter.formatFileSize(context, mediaItem.getSize()));
			viewHoder.tv_time.setText(transForDate1(mediaItem.getSize()));
			
			return convertView;
		}
		
	}
	static class ViewHoder{
		ImageView iv_icon;
		TextView tv_name,tv_time,tv_size;
		
		
	}
    public static String transForDate1(Long ms){  
        String str = "";  
        if(ms!=null){  
            long msl=(long)ms*1000;  
            SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");  
              
            if(ms!=null){  
                try {  
                    str=sdf.format(msl);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }         
        return str;  
    } 

}














