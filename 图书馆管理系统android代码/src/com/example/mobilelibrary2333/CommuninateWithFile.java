package com.example.mobilelibrary2333;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

//import java.io.ByteArrayOutputStream;


public class CommuninateWithFile {
	private String filename;
	private BufferedReader br = null;
	private FileReader fr = null;
	private FileWriter fWriter = null;
	private BufferedWriter bufferedWriter = null;
	private Context contextThis;

	public CommuninateWithFile(String initalFilename, Context context) {
		filename = initalFilename;
		contextThis = context;
	}

	// ����ļ�·��
	public String getFilename() {
		return filename;
	}

	// ȷ���ļ�·��
	public void setFilename(String newFilename) {
		filename = newFilename;
	}

	// ���ļ�����ͷ��ʼ���ļ�ȫ��������
	public String readDataFromFile(Context contextThis) throws Exception {
		String text = null;
		try {
			FileInputStream fis = contextThis.openFileInput(filename); // ���������
			Log.i("CommunicateWithFile", "readDataFromFile method:filename: "+filename);
			
			
			// ��������ڴ滺���������ݣ�ת�����ֽ�����
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				stream.write(buffer, 0, length);// ��ȡ�ڴ滺�����е�����
			}
			stream.close(); // �ر�
			fis.close();
			text=stream.toString();
		} catch (FileNotFoundException e) {
			Log.i("CommunicateWithFile", "file doesn't exist");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i("CommunicateWithFile", "readDataFromFile successfully run");
		Log.i("CommunicateWithFile", "read file result is:"+text);
		return text;
	}
		

	// д���ļ���appendΪtrue��ͷ��ʼд��,appendΪfalse��ĩβ��ʼд��
	public void writeDataToFile(String text,Context contextThis, boolean append) {
		try {
			Log.i("CommunicateWithFile", "writeDataToFile method ready to run(append)");// has run;
			Log.i("CommunicateWithFile", "filename is:"+filename);
			// ͨ��openFileOutput�����õ�һ�����������������Ϊ�������ļ�����������б�ܣ�������ģʽ
			FileOutputStream fos = contextThis.openFileOutput(filename,
					Context.MODE_APPEND);
			Log.i("CommunicateWithFile", "FileOutputStream is created");
			fos.write(text.getBytes());// д��
			Log.i("CommunicateWithFile", "fos has written data in storage,has write:"+text);
			fos.close(); // �ر������
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ��ͷ��ʼ��������

	public void writeDataToFile(String text,Context contextThis) {

		
		try {
			Log.i("CommunicateWithFile", "writeDataToFile method ready to run(rewrite),filename is:"+filename);
			// ͨ��openFileOutput�����õ�һ�����������������Ϊ�������ļ�����������б�ܣ�������ģʽ
			FileOutputStream fos = contextThis.openFileOutput(filename,
					Context.MODE_WORLD_READABLE);
			Log.i("CommunicateWithFile", "creat fos successfully,filename is :"+filename);
			fos.write(text.getBytes());// д��
			Log.i("CommunicateWithFile", "write data  successfully,has write:"+text);
			fos.close(); // �ر������
			// ����Toast��Ϣ
			//Toast.makeText(CommuninateWithFile.this, "д�뵽ϵͳĬ��λ�ñ���ɹ�", Toast.LENGTH_LONG).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

}
