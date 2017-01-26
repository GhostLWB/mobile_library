package com.example.mobilelibrary2333;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class Activity_Menu extends Activity {

	private ImageButton searchImageButton;
	private ImageButton addImageButton;
	private ImageButton returnImageButton;
	private ImageButton listImageButton;
	private ImageButton myImageButton;
	private ImageButton addUserButton;
	private Context context;
	private SuperAccount superAccount;
	private BorrowerAccount borrowerAccount = null;
	private   long mExitTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.menu);
		searchImageButton = (ImageButton) findViewById(R.id.searchButtonMenu);
		addImageButton = (ImageButton) findViewById(R.id.addButtonMenu);
		returnImageButton = (ImageButton) findViewById(R.id.returnButtonMenu);
		listImageButton = (ImageButton) findViewById(R.id.listViewButtonMenu);
		myImageButton = (ImageButton) findViewById(R.id.myPageButtonMenu);
		addUserButton = (ImageButton) findViewById(R.id.addUserButtonMenu);
		context = this;
		Intent intent = this.getIntent();
		superAccount = (SuperAccount) intent
				.getSerializableExtra("SuperAccount");

		searchImageButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, Activity_Search.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("SuperAccount", superAccount);
				intent.putExtras(bundle);
				Bundle bundle2 = new Bundle();
				bundle.putSerializable("BorrowerAccount", borrowerAccount);
				intent.putExtras(bundle2);
				startActivity(intent);
				Log.i("Test2", "start Activity_Search.class");
				//finish();
			}
		});

		addImageButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, Activity_add.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("SuperAccount", superAccount);
				intent.putExtras(bundle);
				startActivity(intent);
				//finish();
			}
		});

		returnImageButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, Activity_return.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("SuperAccount", superAccount);
				intent.putExtras(bundle);
				startActivity(intent);
				//finish();
			}
		});
		addUserButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, Activity_User.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("SuperAccount", superAccount);
				intent.putExtras(bundle);
				startActivity(intent);
				//finish();
			}
		});

		// ==================================================================
		listImageButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, Activity_List.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("SuperAccount", superAccount);
				intent.putExtras(bundle);
				startActivity(intent);
				//finish();
			}
		});

		myImageButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, Activity_my.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("SuperAccount", superAccount);
				intent.putExtras(bundle);
				startActivity(intent);
				//finish();
			}
		});
	}
	
	
	
	private void showTips() {
		 
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("退出图书馆管理系统")
                .setMessage("是否退出图书馆管理系统？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
 
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);  
                        intent.addCategory(Intent.CATEGORY_HOME);  
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
                        startActivity(intent); 
                        finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                    }
 
                }).setNegativeButton("取消",
 
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                }).create(); // 创建对话框
        alertDialog.show(); // 显示对话框
    }
	
	public boolean dispatchKeyEvent(KeyEvent event)    
    {    
        int keyCode=event.getKeyCode();    
        switch(keyCode)    
        {    
            case KeyEvent.KEYCODE_BACK: {    
                 if(event.isLongPress()&&event.getRepeatCount() == 1)    
                 {   
                	 showTips();
                	 Log.i("3", "33333333333333333333333333333333333333333333333333333");
                     this.stopService(this.getIntent());    
                     
                     return true;    
                 }else    
                 {   
                	 
                     return false;    
      
                 }    
            }      
        }    
        return super.dispatchKeyEvent(event);    
            
    }  
	  
	
}
