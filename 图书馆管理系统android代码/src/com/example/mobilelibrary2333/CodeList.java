package com.example.mobilelibrary2333;

import java.util.Iterator;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class CodeList {
	private CommuninateWithFile communinateWithFile ;
	private Vector<String> vector = new Vector<String>();
	private Context contextLocal;
	private final String codeFilePath="CodeList.txt";
	
	
	public CodeList (Context context){
		String text=null;
		String[] bookArray;
	
		contextLocal=context;
		communinateWithFile=new CommuninateWithFile(codeFilePath, contextLocal);
		
		communinateWithFile.writeDataToFile("",contextLocal,true);//确保文件存在
		try {
			Log.i("CodeList","before:"+ text);
			text = communinateWithFile.readDataFromFile(contextLocal);
			Log.i("CodeList","after"+ text+"===========");
			Log.i("Code","text's length:"+ text.length());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(text.length()!=0)
		{
			Log.i("CodeList", "run' if 'method");
			bookArray = text.split("\n");
			for(int i = 0;i<bookArray.length;i++)
			{
				vector.add(bookArray[i]);
			}//endfor
		}//endif
	}//end
	
	
	public boolean hasCode(String initialcode){
		Iterator<String> iterator = vector.iterator();
		
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			if(string.equals(initialcode)){
				return true;
			}
		
		}//endwhile}
		return false;
	}//end
	
	public void addCode(String code){
		vector.add(code);
	}//end
	
	public String getString(Vector<String>vector){
		Iterator<String> iterator = vector.iterator();
		String text = "";
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			text += string+"\n";
		}
		
		return text;
	}//end
	
	
	
	public void writeDataToFile()throws Exception{
		communinateWithFile= new CommuninateWithFile(codeFilePath,contextLocal);
		String text = getString(vector);
		communinateWithFile.writeDataToFile(text,contextLocal);
		Log.i("CodeList", "将书籍的编号写入文件：we have write:"+communinateWithFile.readDataFromFile(contextLocal));

	}//end
}