package com.example.mobilelibrary2333;

import java.util.Iterator;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

//安装时确定ID匹配的借书清单路径的存储位置


public class MatchIDWithBorrowerItem extends Activity{
	
	private static MatchIDWithBorrowerItem singleInstance = null;
	private CommuninateWithFile communinateWithFile;
	private final String filename = "idwithborroweritem.txt";
	private Vector<String> IdwithPath = new Vector<String>();
	private Context contextLocal;
	
	
	//获取单例
	public static MatchIDWithBorrowerItem getSingleInstance(Context context) throws Exception{
		if(singleInstance==null){
			return new MatchIDWithBorrowerItem(context);
		}else{
			return singleInstance;
		}//endif
	}//end
	
	//构造方法
	private MatchIDWithBorrowerItem(Context context) throws Exception {
		communinateWithFile = new CommuninateWithFile(filename, context);
		finishDone(context);
	}
	
	//完成IDwithPath的初始化
	private void finishDone(Context context) throws Exception{
		String text;
		String[] textLine;
		communinateWithFile.writeDataToFile("", context,true);
		text = communinateWithFile.readDataFromFile(context);
		Log.i("1", "将借阅者与他的清单文件读出，已经读出："+text);
		
		if(text!=""){
			textLine = text.split("\n");
			for(int i = 0;i<textLine.length;i++){
				IdwithPath.add(textLine[i]);
			}//endfor
		}//endif
	}//end
	
	
	//获取与ID匹配的书目地址
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
		communinateWithFile.writeDataToFile("",context,true);//确保文件存在
		if(communinateWithFile.readDataFromFile(context)==null){
			return true;
		}else{
			return false;
		}
	}//end
	
	//增加新的路径
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
	
	//将IDwithPath写入文件
	public void WriteDataToFile(Context context) throws Exception{
		String text = toString();
		Log.i("1", "将借阅者和他的清单写入文件，将要写入："+text);
		communinateWithFile.writeDataToFile(text,context);
		Log.i("1", "将借阅者和他的清单写入文件，已经写入："+communinateWithFile.readDataFromFile(context));
		
	}//end
}
