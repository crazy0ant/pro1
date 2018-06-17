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
 * ϵͳ������
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
		
		//׼���õļ���
		videoview.setOnPreparedListener(new MyOnPreparedListener());
		//���ų����˵ļ���
		videoview.setOnErrorListener(new MyOnErrorListener());
		//������ɵļ���
		videoview.setOnCompletionListener(new MyOnCompletionListener());
		//�õ����ŵ�ַ
		uri = getIntent().getData();
		if(uri!=null){
			videoview.setVideoURI(uri);
		}
		//���ÿ������
		videoview.setMediaController(new MediaController(this));
	}
	

	class MyOnPreparedListener implements OnPreparedListener{

		//���ײ����׼����ʱ��
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
			Toast.makeText(SystemVideoPlayer.this, "���ų�����", 1000);
			return false;
		}
	}
	class MyOnCompletionListener implements OnCompletionListener{

		@Override
		public void onCompletion(MediaPlayer mp) {
			// TODO Auto-generated method stub
			Toast.makeText(SystemVideoPlayer.this, "������ɣ�"+uri, 1000);
		}
		
		
	}
	
}


