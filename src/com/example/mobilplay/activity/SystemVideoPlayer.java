package com.example.mobilplay.activity;

import com.example.mobilplay.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.MediaController.MediaPlayerControl;

/*
 * 系统播放器
 */
public class SystemVideoPlayer extends Activity{
	private VideoView videoview;
	private Uri uri;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_system_video_player);
		videoview = (VideoView) findViewById(R.id.videoview);
		
		//准备好的监听
		videoview.setOnPreparedListener(new MyOnPreparedListener());
		//播放出错了的监听
		videoview.setOnErrorListener(new MyOnErrorListener());
		//播放完成的监听
		videoview.setOnCompletionListener(new MyOnCompletionListener());
		//得到播放地址
		uri = getIntent().getData();
		if(uri!=null){
			videoview.setVideoURI(uri);
		}
		//设置控制面板
		videoview.setMediaController(new MediaController(this));
	}
	

	class MyOnPreparedListener implements OnPreparedListener{

		//当底层解码准备好时候
		@Override
		public void onPrepared(MediaPlayer mp) {
			// TODO Auto-generated method stub
			videoview.start();
		}
	}
	class MyOnErrorListener implements OnErrorListener{

		@Override
		public boolean onError(MediaPlayer mp, int what, int extra) {
			// TODO Auto-generated method stub
			Toast.makeText(SystemVideoPlayer.this, "播放出错了", 1000);
			return false;
		}
	}
	class MyOnCompletionListener implements OnCompletionListener{

		@Override
		public void onCompletion(MediaPlayer mp) {
			// TODO Auto-generated method stub
			Toast.makeText(SystemVideoPlayer.this, "播放完成："+uri, 1000);
		}
		
		
	}
	
}


