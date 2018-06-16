package com.example.mobilplay;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;

public class SplashActivity extends Activity {
	//��̬�޸�
    private static final String TAG = SplashActivity.class.getSimpleName();

    private Handler handler =  new Handler();
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// 2���ִ�е�����
				//ִ�������߳���
				startMainActivity();
				Log.e(TAG, "��ǰ�߳�����=="+Thread.currentThread().getName());
			}
		}, 2000);
    }

	private boolean isStartMain = false;//Ϊ��ֹ������������
	
  //��ת�������棬���Ұѵ�ǰҳ�������
	private void startMainActivity() {
		if(!isStartMain){
			isStartMain = true;
			Intent intent = new Intent(this,MainActivity.class);
			startActivity(intent);
			//�رյ�ǰҳ��
			finish();
			
			
		}
	}
    
	//�����¼�
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.e(TAG, "onTouchEvent==Action"+event.getAction());
		startMainActivity();
		return super.onTouchEvent(event);
	}
//	@Override
//	protected void onDestroy() {
//		handler.removeCallbacksAndMessages(null);
//		super.onDestroy();
//	}
	
    
}
