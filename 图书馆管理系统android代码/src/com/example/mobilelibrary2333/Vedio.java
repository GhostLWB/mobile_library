package com.example.mobilelibrary2333;

import java.io.Serializable;

public class Vedio extends Item implements Serializable {
	private String PWD; 

	private String performer;
	

	private int time;
	public Vedio(String initialcode, String initialpublishTimeString,
			String initialnameString,
			String initialcategory,String initialperformer,int time) {
		super(initialcode, initialpublishTimeString, initialnameString,
				initialcategory);
		this.performer=initialperformer;
		
		this.time=time;
	}

	public int getTime()
	{
		return time;
	}
	public String toString()
	{
		return "สำฦต"+"_"+this.getCode()+"_"+this.getName()+"_"+this.getPublishTime()+"_"+this.getCategory()+"_"+this.getAvaliabe()+"_"+this.time+"_"+this.performer;
				
	}
	public String getPerformer() {
		// TODO Auto-generated method stub
		return performer;
	}
	
}
