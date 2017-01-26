package com.example.mobilelibrary2333;

import java.util.Iterator;
import java.util.Vector;

import android.content.Context;
import android.util.Log;

public class MatchCatalogeWithStoredItems {

	private static MatchCatalogeWithStoredItems singleIntance = null;
	private CommuninateWithFile communinateWithFile;
	private Vector<String> textLine = new Vector<String>();// ��ʼ������,�������д洢��������Ŀ��·������Ϣ
	private final String filename = "catalogewithstoreditems.txt";
	private Context context;

	// ��õ���
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

	// ���췽��
	private MatchCatalogeWithStoredItems(Context context) throws Exception {
		String[] Array;
		String text;
		
		Log.d("MatchCatalogeWithStoredItems", "initail run");
		communinateWithFile = new CommuninateWithFile(filename, context);
		Log.d("MatchCatalogeWithStoredItems", "new communinateWithFile constructor run,filename is:"+filename);

		communinateWithFile.writeDataToFile("", context,true);// ȷ���ļ�����
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

	// ��ñ�����
	public Iterator<String> getIterator() {
		return textLine.iterator();
	}// end

	// ��ȡ��catalogƥ���path
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

	// �жϷ����Ƿ��Ѿ�����
	public boolean hasACalataoge(String cataloge) {

		String[] IdAndPath = null;
		Iterator<String> iterator = getIterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			IdAndPath = string.split("_");
			if (IdAndPath[0].equals(cataloge)) {
				return true;// ����
			}// end if
		}// end while

		return false;// ������
	}

	public boolean isEmpty() {
		return textLine.isEmpty();
	}// end

	public void addCatalogeAndPath(String cataloge) {
		cataloge += "_" + cataloge + ".txt";
		Log.i("MatchCatalogWithStoredItems", "*******************************************************��������·���������֮��"+cataloge);

		textLine.add(cataloge);
		Log.i("MatchCatalogWithStoredItems", "*******************************************************����������·��ȫ���Ķ��г�����"+getString(textLine));
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

	// ��textLineд���ļ�
	public void writeCatalogeAndPathToFile(Context context) throws Exception {
		String text = getString(textLine);
		communinateWithFile.writeDataToFile(text,context);
		Log.i("WriteFile", "****************************************************************�������Լ�����·��д���ļ�we have write:"+text);

	}// end

	// ������еķ���
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
