package com.example.mobilelibrary2333;

import java.util.Iterator;
import java.util.Vector;


public class BookCodeList {

	
	Vector<String> booCodeList; 
	CommuninateWithFile communinateWithFile;
	public BookCodeList()throws Exception{
		//communinateWithFile = new CommuninateWithFile("ͼ����.txt");
		booCodeList = new Vector<String>();		
		finishBookCodeList();
	}
	
	//��ɶ�ͼ���ŵĳ�ʼ��
	private void finishBookCodeList() throws Exception{
	//	String[] strings = communinateWithFile.readDataFromFile().split("\n");
		String[]strings=null;
		//��ӵ�bookCodeList
		for(int i=0;i<strings.length;i++){
			addCode(strings[i]);
		}
	}
	
	//���code��bookCodeList
	public void addCode(String code){
		booCodeList.add(code);
	}
	
	//���ر�����
	private Iterator<String> getIterator() {
		return booCodeList.iterator();
	}
	
	
	//�ж��Ƿ��Ѿ�����
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
	
	//��BookCodeListд���ļ�
	public void WriteBookCodeListToFile() throws Exception{
		//communinateWithFile.writeDataToFile(toString());
	}
	
	//ɾ��code,�޴�code����false
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
