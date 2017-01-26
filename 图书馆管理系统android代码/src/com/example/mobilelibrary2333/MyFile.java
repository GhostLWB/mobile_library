package com.example.mobilelibrary2333;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyFile extends Activity {

	/* 声明控件对象 */
	private Button bt_save, bt_saveto, bt_read;
	private TextView tv;
	private EditText et;
	private String et_str;
	
	private Context context;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/* 得到控件对象 */
		bt_save = (Button) findViewById(R.id.bt_save);
		bt_saveto = (Button) findViewById(R.id.bt_saveto);
		bt_read = (Button) findViewById(R.id.bt_read);
		tv = (TextView) findViewById(R.id.tv);
		et = (EditText) findViewById(R.id.et);
		context=this;

		/* 监听点击事件 */
		bt_save.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				et_str = et.getText().toString();// 取得EidtText的内容
				write(et_str);// 写入
			}
		});

		bt_read.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				read();// 读取
			}
		});

		bt_saveto.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				et_str = et.getText().toString();// 取得EidtText的内容
				writeToSd(et_str);// 写入到sd卡
			}
		});
	}
	
	public MyFile (){
		
	}

	/**
	 * 将数据写入系统默认位置
	 * 
	 * @param text
	 *            要保存的字符
	 */
	public void write(String text) {
		try {
			// 通过openFileOutput方法得到一个输出流，方法参数为创建的文件名（不能有斜杠），操作模式
			FileOutputStream fos = this.openFileOutput("myfirst.txt",
					Context.MODE_WORLD_READABLE);
			Log.i("Test", "creat fos successfully");
			fos.write(text.getBytes());// 写入
			fos.close(); // 关闭输出流
			// 弹出Toast消息
			Toast.makeText(MyFile.this, "写入到系统默认位置保存成功", Toast.LENGTH_LONG).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将数据写入到sd卡中
	 * 
	 * @param text
	 *            要保存的字符
	 */
	public void writeToSd(String text) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File sdCardDir = Environment.getExternalStorageDirectory();// 获取SDCard目录
			File sdFile = new File(sdCardDir, "myfirstsd.txt");

			try {
				FileOutputStream fos = new FileOutputStream(sdFile);
				fos.write(text.getBytes());
				fos.close();
				Toast.makeText(MyFile.this, "成功保存到sd卡", Toast.LENGTH_LONG)
						.show();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/** 读取文件 */
	public void read() {
		try {
			FileInputStream fis = this.openFileInput("myfirst.txt"); // 获得输入流
			// 用来获得内存缓冲区的数据，转换成字节数组
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				stream.write(buffer, 0, length);// 获取内存缓冲区中的数据
			}
			stream.close(); // 关闭
			fis.close();
			tv.setText(stream.toString()); // 设置文本控件显示内容
			Toast.makeText(MyFile.this, "读取成功", Toast.LENGTH_LONG).show();// 弹出Toast消息
		} catch (FileNotFoundException e) {
			Toast.makeText(MyFile.this, "文件不存在", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
