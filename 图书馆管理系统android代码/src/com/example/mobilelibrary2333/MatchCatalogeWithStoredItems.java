package com.example.mobilelibrary2333;

import java.util.Iterator;
import java.util.Vector;

import android.content.Context;
import android.util.Log;

public class MatchCatalogeWithStoredItems {

	private static MatchCatalogeWithStoredItems singleIntance = null;
	private CommuninateWithFile communinateWithFile;
	private Vector<String> textLine = new Vector<String>();// 初始化容器,容器按行存储着所有书目与路径的信息
	private final String filename = "catalogewithstoreditems.txt";
	private Context context;

	// 获得单例
	public static MatchCatalogeWithStoredItems getSingleInstance(Context context)
			throws Exception {
		if (singleIntance == null) {
			Log.i("MatchCatalogeWithStoredItems", "singleInstance is null");
			return new MatchCatalogeWithStoredItems(context);

		} else {
			Log.i("MatchCatalogeWithStoredItems", "singleInstance is not null");
			return singleIntance;
		}// endif
	}// end

	// 构造方法
	private MatchCatalogeWithStoredItems(Context context) throws Exception {
		String[] Array;
		String text;
		
		Log.d("MatchCatalogeWithStoredItems", "initail run");
		communinateWithFile = new CommuninateWithFile(filename, context);
		Log.d("MatchCatalogeWithStoredItems", "new communinateWithFile constructor run,filename is:"+filename);

		communinateWithFile.writeDataToFile("", context,true);// 确保文件存在
		Log.d("MatchCatalogeWithStoredItems", "writeDataToFile method run");

		text = communinateWithFile.readDataFromFile(context);
		Log.d("MatchCatalogeWithStoredItems", "ReadDataFromFile method run");
		Log.i("MatchCatalogeWithStoredItems", "MatchCatalogWithStoredItems class:read file result :text="+text);

		if (text.length()!=0) {
			Array = text.split("\n");

			for (int i = 0; i < Array.length; i++) {
				textLine.add(Array[i]);

			}// endfor
		}// endif
	}

	// 获得遍历器
	public Iterator<String> getIterator() {
		return textLine.iterator();
	}// end

	// 获取与catalog匹配的path
	public String getMacthedPath(String catalog) {
		String path = null;
		String[] IdAndPath = null;
		Iterator<String> iterator = getIterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			IdAndPath = string.split("_");
			if (IdAndPath[0].equals(catalog)) {
				path = IdAndPath[1];
			}
		}
		return path;
	}// end

	// 判断分类是否已经存在
	public boolean hasACalataoge(String cataloge) {

		String[] IdAndPath = null;
		Iterator<String> iterator = getIterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			IdAndPath = string.split("_");
			if (IdAndPath[0].equals(cataloge)) {
				return true;// 存在
			}// end if
		}// end while

		return false;// 不存在
	}

	public boolean isEmpty() {
		return textLine.isEmpty();
	}// end

	public void addCatalogeAndPath(String cataloge) {
		cataloge += "_" + cataloge + ".txt";
		Log.i("MatchCatalogWithStoredItems", "*******************************************************将分类与路径结合起来之后："+cataloge);

		textLine.add(cataloge);
		Log.i("MatchCatalogWithStoredItems", "*******************************************************将分类与其路径全部的都列出来："+getString(textLine));
	}// end

	// toString
	public String getString(Vector<String> vector) {
		String text = "";
		Iterator<String> iterator = vector.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			text += string + "_\n";
		}

		return text;
	}// end

	// 将textLine写入文件
	public void writeCatalogeAndPathToFile(Context context) throws Exception {
		String text = getString(textLine);
		communinateWithFile.writeDataToFile(text,context);
		Log.i("WriteFile", "****************************************************************将分类以及它的路径写入文件we have write:"+text);

	}// end

	// 获得所有的分类
	public String[] getAllCataloge() {
		String[] IdAndPath = null;
		int count = 0;
		String[] cataloge = new String[textLine.size()];
		Iterator<String> iterator = getIterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			IdAndPath = string.split("_");
			cataloge[count++] = IdAndPath[0];
		}// end while
		return cataloge;
	}// end
}
