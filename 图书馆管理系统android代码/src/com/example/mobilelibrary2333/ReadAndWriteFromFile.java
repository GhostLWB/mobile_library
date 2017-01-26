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
			// 通过openFileOutput方法得到一个输出流，方法参数为创建的文件名（不能有斜杠），操作模式
			FileOutputStream fos = this.openFileOutput(fileName,
					Context.MODE_WORLD_READABLE);
			fos.write(text.getBytes());// 写入
			fos.close(); // 关闭输出流
			// 弹出Toast消息
			Toast.makeText(context, "保存成功", Toast.LENGTH_LONG).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// -------------------------------------------------------

	// 读文件在./data/data/com.tt/files/下面

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
