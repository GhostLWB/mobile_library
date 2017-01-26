package com.example.mobilelibrary2333;

import java.util.Vector;

import android.R.anim;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_List extends Activity {
	private SuperAccount superAccount;
	private Context context;
	private String text;
	private ImageButton mainImageButton;
	private ImageButton myImageButton;
	private ArrayAdapter<String> borrowerListAdapter;
	private String readFileResult;
	private String[] borrowerListViewSource = null;
	private CommuninateWithFile file;
	private ListView borrowerListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.list);
		context = this;
		mainImageButton = (ImageButton) findViewById(R.id.mainPageButtonList);
		myImageButton = (ImageButton) findViewById(R.id.myPageButtonList);
		borrowerListView = (ListView) findViewById(R.id.borrowerListViewList);

		Intent intent = this.getIntent();
		superAccount = (SuperAccount) intent
				.getSerializableExtra("SuperAccount");

		// =======================================================
		try {

			BorrowedAccountList borrowedAccountList = new BorrowedAccountList(
					context);
			readFileResult = borrowedAccountList.getString();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		borrowerListViewSource = readFileResult.split("\n");
		Log.i("Avtivity_List", "read file result:"+readFileResult);
		Vector<String> borrowerName = new Vector<String>();
		Vector<String> borrowerID = new Vector<String>();
		Log.i("Avtivity_List", "borrowerListViewSource's length:"+borrowerListViewSource.length);
		if (borrowerListViewSource.length > 1) {
			Log.i("Avtivity_List", "------------------------run 'if'method ");
			for (int i = 0; i < borrowerListViewSource.length; i++) {
				String[] borrowernameSource = borrowerListViewSource[i]
						.split("_");
				for(int j=0;j<borrowernameSource.length;j++){
				}
				borrowerName.add(borrowernameSource[0]);
				borrowerID.add(borrowernameSource[1]);
			}

		}

		borrowerListAdapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, borrowerName);
		
		borrowerListView.setAdapter(borrowerListAdapter);
	
		// ============listView的点击事件======================================
		borrowerListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Vector<String> borrowerIDi = new Vector<String>();
						
						if (borrowerListViewSource.length > 1) {
							for (int i = 0; i < borrowerListViewSource.length; i++) {
								borrowerIDi.add(borrowerListViewSource[i]);
							}

						}
						
						// TODO Auto-generated method stub
						String item = borrowerIDi.get(position);
						Log.i("Activity_List", "position:" + position);

						Log.i("Activity_List", "the item being click:" + item);

						String[] itemsplit = item.split("_");
						try {
							BorrowedAccountList borrowedAccountListi = new BorrowedAccountList(
									context);
							BorrowerAccount borrower = borrowedAccountListi
									.searchBorrowerAccountByID(itemsplit[1]);
							Log.i("Activity_List", "解析到的查找标准" + itemsplit[1]);
							Intent intent = new Intent(context,
									Activity_borrower.class);
							Bundle bundle = new Bundle();
							bundle.putSerializable("BorrowerAccount", borrower);
							intent.putExtras(bundle);
							Bundle bundle2= new Bundle();
							bundle2.putSerializable("SuperAccount", superAccount);
							intent.putExtras(bundle2);
							startActivity(intent);
							//finish();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});

		// =================底部两个按钮=====================================
		mainImageButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, Activity_Menu.class);
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
		// =======================================================================
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
