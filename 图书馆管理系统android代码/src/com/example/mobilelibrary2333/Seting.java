package com.example.mobilelibrary2333;
//��װʱȷ�������ļ���·��
public class Seting {
	private String setingFilename = ".\\Seting";
	private CommuninateWithFile communinateWithFile = null;
	private static Seting singleInstance = null;
	private Seting() {}
	
	
	public static Seting getSingleInstance() {
		if(singleInstance ==null){
			return singleInstance = new Seting();
		}else{
			return singleInstance;
		}
	}
	
	//��������ļ�·��
	public String getSetingFilename(){
		return setingFilename;
	}
	
	
	
	
	
}
