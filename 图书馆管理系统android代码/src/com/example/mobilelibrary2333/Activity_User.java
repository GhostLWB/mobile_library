package com.example.mobilelibrary2333;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Activity_User extends Activity {
	private Context context;
	private EditText userNameUser;
	private EditText userIDUser;
	private EditText passwordUser;
	private EditText passwordConfirmUser;
	private ImageButton registerUser;
	private ImageButton mainPage;
	private ImageButton listPage;
	private ImageButton myPage;
	private SuperAccount superAccount;
	private String getnameUser;
	private String getIDUser;
	private String getpasswordUser;
	private String getpasswordConfirmUser;
	private Button isAvaliable;
	private String userData ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.adduser);
		userNameUser = (EditText) findViewById(R.id.userNameUser);
		userIDUser = (EditText) findViewById(R.id.IDuser);
		passwordUser = (EditText) findViewById(R.id.passwordUser);
		passwordConfirmUser = (EditText) findViewById(R.id.passwordConfirmUser);
		registerUser = (ImageButton) findViewById(R.id.registerUser);
		mainPage = (ImageButton) findViewById(R.id.mainPageUser23);
		listPage = (ImageButton) findViewById(R.id.listPageUser23);
		myPage = (ImageButton) findViewById(R.id.myPageUser);
		context = this;
		isAvaliable = (Button) findViewById(R.id.isAvailable);

		Intent intent = this.getIntent();
		superAccount = (SuperAccount) intent
				.getSerializableExtra("SuperAccount");
		
		
		getnameUser = userNameUser.getText().toString();
		
		
		// =================底部三个按钮=====================================
		mainPage.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, Activity_Menu.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("SuperAccount", superAccount);
				intent.putExtras(bundle);
				startActivity(intent);
				////finish();
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
				////finish();
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
		isAvaliable.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getIDUser = userIDUser.getText().toString();
				try {
					BorrowedAccountList borrowedAccountList = new BorrowedAccountList(
							context);
					
					if (borrowedAccountList
							.searchBorrowerAccountByID(getIDUser) != null) {
						Toast.makeText(context, "ID不可用", Toast.LENGTH_SHORT).show();
								userIDUser.setText("");
						return;
					}else{
						Toast.makeText(context, "ID可用，请继续", Toast.LENGTH_SHORT).show();
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// =======================================================================
		registerUser.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i("Activity_User", "register button is click");
				getpasswordUser = passwordUser.getText().toString();
				getpasswordConfirmUser = passwordConfirmUser.getText()
						.toString();
				getnameUser = userNameUser.getText().toString();
				getIDUser = userIDUser.getText().toString();
				
				
				Log.i("Activity_User", "password is:"+getpasswordUser);
				Log.i("Activity_User", "passwordConfirm is:"+getpasswordConfirmUser);
				Log.i("Activity_User", "name of user is:"+getnameUser);
				Log.i("Activity_User", "length of id is:"+getIDUser.length());
				
				if (getnameUser != null && getIDUser .length()!=0&&getpasswordUser.equals(getpasswordConfirmUser)) {
					try {
						BorrowedAccountList borrowedAccountList = new BorrowedAccountList(
								context);
					
						
						//=============================================
						userData = getnameUser + "_" + getIDUser + "_"
								+ getpasswordConfirmUser + "_" + "false";
						
						Log.i("Activity_User", "userData:"+userData);
						BorrowerAccount borrowerAccount;
						borrowedAccountList
								.addBorrowerAccount(borrowerAccount = MakeAccountFromString
										.getBorrowerAccount(userData));
						Log.i("Activity_User", "成功创建借阅者："+borrowedAccountList.getString());
						borrowedAccountList.writeBorrowerAccountToFile(context);// 往借阅者清单中写入借阅者

						MatchIDWithBorrowerItem matchIDWithBorrowerItem = MatchIDWithBorrowerItem
								.getSingleInstance(context);
						matchIDWithBorrowerItem.addIDWithPath(getIDUser);
						matchIDWithBorrowerItem.WriteDataToFile(context);// 创建一个对应的借书清单
						Toast.makeText(context, "成功注册借阅者："+getnameUser, Toast.LENGTH_SHORT).show();
						
						
						Intent intent = new Intent(context, Activity_Menu.class);
						Bundle bundle = new Bundle();
						bundle.putSerializable("SuperAccount", superAccount);
						intent.putExtras(bundle);
						startActivity(intent);
						finish();
						return;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}else if(!getpasswordUser.equals(getpasswordConfirmUser)){
					Toast.makeText(context, "密码不一致！", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}
	@SuppressWarnings("unused")
	private ThreadLocal<Boolean> isAvailableCheck(String ID){
		final ThreadLocal<Boolean> check=new ThreadLocal<Boolean>();
		isAvaliable.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				BorrowedAccountList borrowedAccountList;
				try {
					borrowedAccountList = new BorrowedAccountList(
							context);
					if (borrowedAccountList
							.searchBorrowerAccountByID(getIDUser) != null) {
						check.set(false);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		
		return check;
	}
}
