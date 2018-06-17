package com.example.mobilplay.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilplay.R;
import com.example.mobilplay.domain.MediaItem;

public class videoPagerAdapter extends BaseAdapter{

	private final Context context;
	private final ArrayList<MediaItem> mediaItems;
	
	
	public videoPagerAdapter(Context context,ArrayList<MediaItem> mediaItems){
		this.context = context;
		this.mediaItems = mediaItems;
		
	}
	
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
			convertView = View.inflate(context, R.layout.item_video_pager, null);
			viewHoder = new ViewHoder();
			viewHoder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
			viewHoder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			viewHoder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			viewHoder.tv_size = (TextView) convertView.findViewById(R.id.tv_size);
			convertView.setTag(viewHoder);
		}else{
			viewHoder = (ViewHoder) convertView.getTag();
		}
		
		//根据position得到列表中对应位置的数据
		MediaItem mediaItem = mediaItems.get(position);
		viewHoder.tv_name.setText(mediaItem.getName());
		viewHoder.tv_size.setText(Formatter.formatFileSize(context, mediaItem.getSize()));
		viewHoder.tv_time.setText(transForDate1(mediaItem.getSize()));
		
		return convertView;
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