package com.example.mobilelibrary2333;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import org.apache.http.util.EncodingUtils;
import android.content.Context;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.content.Context;
import android.os.Environment;

public class ReadAndWriteFromFile extends Activity {

	private Context context;

	public void write(String text, String fileName) {

		context = this;
		try {
			// ͨ��openFileOutput�����õ�һ�����������������Ϊ�������ļ�����������б�ܣ�������ģʽ
			FileOutputStream fos = this.openFileOutput(fileName,
					Context.MODE_WORLD_READABLE);
			fos.write(text.getBytes());// д��
			fos.close(); // �ر������
			// ����Toast��Ϣ
			Toast.makeText(context, "����ɹ�", Toast.LENGTH_LONG).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// -------------------------------------------------------

	// ���ļ���./data/data/com.tt/files/����

	public String readFileData(String fileName) {

		String res = "";

		try {

			FileInputStream fin = openFileInput(fileName);

			int length = fin.available();

			byte[] buffer = new byte[length];

			fin.read(buffer);

			res = EncodingUtils.getString(buffer, "UTF-8");

			fin.close();

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return res;

	}
}
