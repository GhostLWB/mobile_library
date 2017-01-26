package com.example.mobilelibrary2333;

import java.util.Timer;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import java.util.TimerTask;

public class Activity_Welcome extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcom);
		Timer timer=new Timer();
		TimerTask timerTask=new TimerTask() {
			
			@Override
			public void run() {
				Intent intent=new Intent(Activity_Welcome.this,ActivityMain.class);
				startActivity(intent);
				finish();
				
			}
	};
	timer.schedule(timerTask, 2000);
}
}