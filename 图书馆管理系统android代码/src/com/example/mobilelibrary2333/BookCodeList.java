package com.example.mobilelibrary2333;

import java.util.Iterator;
import java.util.Vector;


public class BookCodeList {

	
	Vector<String> booCodeList; 
	CommuninateWithFile communinateWithFile;
	public BookCodeList()throws Exception{
		//communinateWithFile = new CommuninateWithFile("图书编号.txt");
		booCodeList = new Vector<String>();		
		finishBookCodeList();
	}
	
	//完成对图书编号的初始化
	private void finishBookCodeList() throws Exception{
	//	String[] strings = communinateWithFile.readDataFromFile().split("\n");
		String[]strings=null;
		//添加到bookCodeList
		for(int i=0;i<strings.length;i++){
			addCode(strings[i]);
		}
	}
	
	//添加code到bookCodeList
	public void addCode(String code){
		booCodeList.add(code);
	}
	
	//返回遍历器
	private Iterator<String> getIterator() {
		return booCodeList.iterator();
	}
	
	
	//判断是否已经存在
	public boolean hasCode(String code) {
		Iterator<String> iterator = getIterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			if(string.equals(code)){
				return true;
			}//endif
		}//endwhile
		return false;
	}
	//toString
	public String toString() {
		String text = "";
		
		Iterator<String> iterator = getIterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			text += string+"\n";
		}//end while
		
		return text;
	}
	
	//将BookCodeList写入文件
	public void WriteBookCodeListToFile() throws Exception{
		//communinateWithFile.writeDataToFile(toString());
	}
	
	//删除code,无此code返回false
	public boolean removeCode(String code) {
		Iterator<String> iterator = getIterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			if(string.equals(code)){
				iterator.remove();
				return true;
			}//endif
		}//endwhile
		return false;
	}

}
