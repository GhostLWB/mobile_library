package com.example.mobilelibrary2333;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.util.EncodingUtils;

import android.R.menu;
import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityMain extends Activity {

	private EditText userName;
	private EditText password;
	private CheckBox checkBox;
	private ImageButton loginButton;
	private Context context;
	private String promptWong = "密码或账号错误，请重试";
	private String userNameString;
	private String passwordString;
	private final String AdministratorFilePath="guanliyuan.txt";
	// private String[] data = null;
	private SuperAccount account;
	private String line = "";

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);
		userName = (EditText) findViewById(R.id.userName);
		password = (EditText) findViewById(R.id.password);
		checkBox = (CheckBox) findViewById(R.id.checkbox);
		loginButton = (ImageButton) findViewById(R.id.loginButton);
		context = this;

		//====================================注册控件==============================
	
		// ================================checkbox===============================
		checkBox.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (checkBox.isChecked()) {
					password.setTransformationMethod(HideReturnsTransformationMethod
							.getInstance());
				} else {
					password.setTransformationMethod(PasswordTransformationMethod
							.getInstance());
				}
			}
		});

		// ==============================loginbutton================================

		loginButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				CommuninateWithFile fileOperation=new CommuninateWithFile(AdministratorFilePath, context);
				
				String iniAdministrator="周秦春_2015141463262_123456_true_\n黎文彬_2015141463083_123456_true_\n宋羽珩_2015141463145_123456_true_\n";
				fileOperation.writeDataToFile(iniAdministrator, context);
				try {
					line=fileOperation.readDataFromFile(context);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String[] data = line.split("\n");
			
				// ====================================================================
				userNameString = userName.getText().toString();
				passwordString = password.getText().toString();
				// =============================判断密码与账户是否正确并进行页面跳转===========
				try {
					if (userNameString != null && passwordString != null) {

						int dismatch = 0;
						for (int i = 0; i < data.length; i++, dismatch++) {
							// =====将读取的账户进行分析，直到找到匹配的id和密码====
							String[] textArray;
							textArray = data[i].split("_");
							if (textArray[3].equals("true")) {
								account = new SuperAccount(textArray[1],
										textArray[0], textArray[2]);
							} else {
								break;
							}
							// ==========尝试与输入进行匹配=================
							if (account.getID().equals(userNameString)
									&& account.getPassWords().equals(
											passwordString)) {
								
								
								Toast.makeText(context, "欢迎您,"+account.getName(),
										Toast.LENGTH_SHORT).show();
								Intent intent = new Intent(context, Activity_Menu.class);
								 Bundle bundle = new Bundle();
								 bundle.putSerializable("SuperAccount", account);
								 intent.putExtras(bundle);
								 startActivity(intent);
								break;
							}

						}
						if (dismatch == data.length) {
							Toast.makeText(context, promptWong,
									Toast.LENGTH_SHORT).show();
						}
					}
				}
				// }
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
