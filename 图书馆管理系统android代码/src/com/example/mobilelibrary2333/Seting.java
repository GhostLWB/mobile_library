package com.example.mobilelibrary2333;
//安装时确定配置文件的路径
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
	
	//获得配置文件路径
	public String getSetingFilename(){
		return setingFilename;
	}
	
	
	
	
	
}
