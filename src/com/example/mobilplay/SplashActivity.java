package com.example.mobilplay;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;

public class SplashActivity extends Activity {
	//动态修改
    private static final String TAG = SplashActivity.class.getSimpleName();

    private Handler handler =  new Handler();
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// 2秒后执行到这里
				//执行在主线程中
				startMainActivity();
				Log.e(TAG, "当前线程名称=="+Thread.currentThread().getName());
			}
		}, 2000);
    }

	private boolean isStartMain = false;//为防止主界面多次启动
	
  //跳转到主界面，并且把当前页面结束掉
	private void startMainActivity() {
		if(!isStartMain){
			isStartMain = true;
			Intent intent = new Intent(this,MainActivity.class);
			startActivity(intent);
			//关闭当前页面
			finish();
			
			
		}
	}
    
	//触摸事件
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
