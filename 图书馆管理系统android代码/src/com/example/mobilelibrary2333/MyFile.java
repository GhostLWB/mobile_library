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

	/* �����ؼ����� */
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

		/* �õ��ؼ����� */
		bt_save = (Button) findViewById(R.id.bt_save);
		bt_saveto = (Button) findViewById(R.id.bt_saveto);
		bt_read = (Button) findViewById(R.id.bt_read);
		tv = (TextView) findViewById(R.id.tv);
		et = (EditText) findViewById(R.id.et);
		context=this;

		/* ��������¼� */
		bt_save.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				et_str = et.getText().toString();// ȡ��EidtText������
				write(et_str);// д��
			}
		});

		bt_read.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				read();// ��ȡ
			}
		});

		bt_saveto.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				et_str = et.getText().toString();// ȡ��EidtText������
				writeToSd(et_str);// д�뵽sd��
			}
		});
	}
	
	public MyFile (){
		
	}

	/**
	 * ������д��ϵͳĬ��λ��
	 * 
	 * @param text
	 *            Ҫ������ַ�
	 */
	public void write(String text) {
		try {
			// ͨ��openFileOutput�����õ�һ�����������������Ϊ�������ļ�����������б�ܣ�������ģʽ
			FileOutputStream fos = this.openFileOutput("myfirst.txt",
					Context.MODE_WORLD_READABLE);
			Log.i("Test", "creat fos successfully");
			fos.write(text.getBytes());// д��
			fos.close(); // �ر������
			// ����Toast��Ϣ
			Toast.makeText(MyFile.this, "д�뵽ϵͳĬ��λ�ñ���ɹ�", Toast.LENGTH_LONG).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������д�뵽sd����
	 * 
	 * @param text
	 *            Ҫ������ַ�
	 */
	public void writeToSd(String text) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File sdCardDir = Environment.getExternalStorageDirectory();// ��ȡSDCardĿ¼
			File sdFile = new File(sdCardDir, "myfirstsd.txt");

			try {
				FileOutputStream fos = new FileOutputStream(sdFile);
				fos.write(text.getBytes());
				fos.close();
				Toast.makeText(MyFile.this, "�ɹ����浽sd��", Toast.LENGTH_LONG)
						.show();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/** ��ȡ�ļ� */
	public void read() {
		try {
			FileInputStream fis = this.openFileInput("myfirst.txt"); // ���������
			// ��������ڴ滺���������ݣ�ת�����ֽ�����
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				stream.write(buffer, 0, length);// ��ȡ�ڴ滺�����е�����
			}
			stream.close(); // �ر�
			fis.close();
			tv.setText(stream.toString()); // �����ı��ؼ���ʾ����
			Toast.makeText(MyFile.this, "��ȡ�ɹ�", Toast.LENGTH_LONG).show();// ����Toast��Ϣ
		} catch (FileNotFoundException e) {
			Toast.makeText(MyFile.this, "�ļ�������", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
