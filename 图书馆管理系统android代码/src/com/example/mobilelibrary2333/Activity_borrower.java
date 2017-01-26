package com.example.mobilelibrary2333;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_borrower extends Activity{
	private BorrowerAccount borrower;
	private TextView text;
	private Book book;
	private Context context;
	private String[]borrowerItems;
	private ListView itemList;
	private ArrayAdapter<String> borroweritemListAdapter;
	private ImageButton borrowButton;
	private SuperAccount superAccount ;
	private ImageButton mainPage;
	private ImageButton myPage;
	private ImageButton listPage;
	private String inputPassword;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.borrower);
		text=(TextView) findViewById(R.id.userNameBorList);
		itemList=(ListView) findViewById(R.id.booksBorrowedList);
		borrowButton=(ImageButton) findViewById(R.id.borrowBorr111);
		context=this;
		mainPage = (ImageButton) findViewById(R.id.mainPageBorrower);
		listPage = (ImageButton) findViewById(R.id.listPageBorrower);
		myPage = (ImageButton) findViewById(R.id.myPageBorrower);
		
	
		
		Intent intent = this.getIntent();
		borrower = (BorrowerAccount) intent
				.getSerializableExtra("BorrowerAccount");
		
		String borrowerData=borrower.toString();
		String[] data=borrowerData.split("_");
		text.setText("读者名称:"+data[0]+"读者编号："+data[1]);
		
		
		Intent intent2 = this.getIntent();
		superAccount = (SuperAccount) intent2
				.getSerializableExtra("SuperAccount");
		
		
	
		
		try {
			String ID=borrower.getID();
			ItemFactory itemFactory = new ItemFactory(context);//工厂模式
			BorrowedItem borrowedItem = itemFactory.makeBorrowedItem(ID,context);//工厂模式
			borrowerItems=borrowedItem.getString().split("\n");
			borroweritemListAdapter=new ArrayAdapter<String>(context,
					android.R.layout.simple_list_item_1, borrowerItems);
			itemList.setAdapter(borroweritemListAdapter);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		borrowButton.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("unused")
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				;
				
				//===============
				
			 final EditText inputServer = new EditText(context);
		        AlertDialog.Builder builder = new AlertDialog.Builder(context);
		        builder.setTitle("请输入密码").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
		                .setNegativeButton("取消", null);
		        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

		            public void onClick(DialogInterface dialog, int which) {
		            	final String password=  inputServer.getText().toString();
		            	inputPassword=password;
		            	Intent intent3 = new Intent(context, Activity_Search.class);
						Bundle bundle = new Bundle();
						bundle.putSerializable("SuperAccount", superAccount);
						intent3.putExtras(bundle);
						Bundle bundle2 = new Bundle();
						bundle2.putSerializable("BorrowerAccount", borrower);
						intent3.putExtras(bundle2);
						 if(inputPassword!=null&&inputPassword.equals(borrower.getPassWords())){
					        	startActivity(intent3);
					        }
		             }
		        });
		        builder.show();
		        
		        Log.i("3", "borrowerAccount is:"+borrower.toString());
				//String getPassword=checkPassword();
		        
		       
//		        else if(!inputPassword.equals(borrower.getPassWords())){
//		        	Toast.makeText(context, "用户密码没输对哦", Toast.LENGTH_SHORT);
//		        }
//		        
				//finish();
			}
		});
		
		// =========================底端三个按钮=======================
				mainPage.setOnClickListener(new OnClickListener() {

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

				myPage.setOnClickListener(new OnClickListener() {

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
	}
	public String checkPassword(){
		  final EditText inputServer = new EditText(context);
	        AlertDialog.Builder builder = new AlertDialog.Builder(context);
	        builder.setTitle("Server").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
	                .setNegativeButton("Cancel", null);
	        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

	            public void onClick(DialogInterface dialog, int which) {
	            	final String password=  inputServer.getText().toString();
	            	inputPassword=password;
	             }
	        });
	        builder.show();
	        return inputPassword;
	}

}
