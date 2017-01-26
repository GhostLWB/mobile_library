package com.example.mobilelibrary2333;

import android.R.string;
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
import android.widget.TextView;

public class Activity_my extends Activity{
	private ImageButton listPage;
	private ImageButton mainPage;
	private Context context;
	private SuperAccount superAccount;
	private TextView idTextView;
	private TextView nameTextView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.myupdate);
		mainPage=(ImageButton) findViewById(R.id.mainPageButtonMy);
		listPage=(ImageButton) findViewById(R.id.listPageButtonMy);
		idTextView=(TextView) findViewById(R.id.idContentMy);
		nameTextView=(TextView) findViewById(R.id.userNameContent);
		context=this;
		Intent intent = this.getIntent(); 
		superAccount=(SuperAccount)intent.getSerializableExtra("SuperAccount");
		
		//=====================实现底端两个按钮====================
		mainPage.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context, Activity_Menu.class);
				Bundle bundle = new Bundle();
				 bundle.putSerializable("SuperAccount", superAccount);
				 intent.putExtras(bundle);
				 startActivity(intent);
				//finish();
			}
		});
		
		listPage.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		listPage.setOnClickListener(new OnClickListener() {
			
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
		//=================================================
		//==================设置文本显示内容====================
		String ID=superAccount.getID();
		String name=superAccount.getName();
		idTextView.setText(""+ID);
		nameTextView.setText(""+name);
		
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
	@Override
		protected void onResume(){
			super.onResume();
			Log.i("Test2", "onResume method run in class:Activity_my");
			Intent intent = this.getIntent(); 
			superAccount=(SuperAccount)intent.getSerializableExtra("SuperAccount");
			String ID=superAccount.getID();
			String name=superAccount.getName();
			idTextView.setText(""+ID);
			nameTextView.setText(""+name);
		}

}
