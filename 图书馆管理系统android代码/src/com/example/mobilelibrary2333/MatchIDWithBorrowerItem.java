package com.example.mobilelibrary2333;

import java.util.Iterator;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

//��װʱȷ��IDƥ��Ľ����嵥·���Ĵ洢λ��


public class MatchIDWithBorrowerItem extends Activity{
	
	private static MatchIDWithBorrowerItem singleInstance = null;
	private CommuninateWithFile communinateWithFile;
	private final String filename = "idwithborroweritem.txt";
	private Vector<String> IdwithPath = new Vector<String>();
	private Context contextLocal;
	
	
	//��ȡ����
	public static MatchIDWithBorrowerItem getSingleInstance(Context context) throws Exception{
		if(singleInstance==null){
			return new MatchIDWithBorrowerItem(context);
		}else{
			return singleInstance;
		}//endif
	}//end
	
	//���췽��
	private MatchIDWithBorrowerItem(Context context) throws Exception {
		communinateWithFile = new CommuninateWithFile(filename, context);
		finishDone(context);
	}
	
	//���IDwithPath�ĳ�ʼ��
	private void finishDone(Context context) throws Exception{
		String text;
		String[] textLine;
		communinateWithFile.writeDataToFile("", context,true);
		text = communinateWithFile.readDataFromFile(context);
		Log.i("1", "���������������嵥�ļ��������Ѿ�������"+text);
		
		if(text!=""){
			textLine = text.split("\n");
			for(int i = 0;i<textLine.length;i++){
				IdwithPath.add(textLine[i]);
			}//endfor
		}//endif
	}//end
	
	
	//��ȡ��IDƥ�����Ŀ��ַ
	public String getMacthedPath(String ID) throws Exception{
		Iterator<String> iterator = IdwithPath.iterator();
			
			
		String path = null;
		String[] IdAndPath = null; 
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			IdAndPath = string.split("_");
			
			if(IdAndPath[0].equals(ID)){
				path = IdAndPath[1];
			}//endif
		}
		
		return path;
	}//end
	
	public boolean isEmpty(Context  context )throws Exception{
		communinateWithFile.writeDataToFile("",context,true);//ȷ���ļ�����
		if(communinateWithFile.readDataFromFile(context)==null){
			return true;
		}else{
			return false;
		}
	}//end
	
	//�����µ�·��
	public void addIDWithPath(String ID){
		String textLine;
		textLine = ID+"_"+ID+".txt"+"_\n";
		IdwithPath.add(textLine);
	}
	
	//toString
	public String toString(){
		String text = "";
		Iterator<String> iterator = IdwithPath.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			text += string+"\n"; 
		}//endwhile
		return text;
	}//end
	
	//��IDwithPathд���ļ�
	public void WriteDataToFile(Context context) throws Exception{
		String text = toString();
		Log.i("1", "�������ߺ������嵥д���ļ�����Ҫд�룺"+text);
		communinateWithFile.writeDataToFile(text,context);
		Log.i("1", "�������ߺ������嵥д���ļ����Ѿ�д�룺"+communinateWithFile.readDataFromFile(context));
		
	}//end
}
