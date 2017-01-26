package com.example.mobilelibrary2333;

import java.io.Serializable;

public class SuperAccount extends BorrowerAccount implements Serializable{

	private final boolean isSuper = true;//是否为超级管理员
	private String PWD; 
	
	 public SuperAccount(String initialID, String initialname, String initialpassWords) {
		super(initialID, initialname, initialpassWords);	
	}
	

	public boolean getIsSuper() {
		return isSuper;
	}
	
	public String toString(){
		return super.getName()+"_"+super.getID()+"_"+super.getPassWords()+"_"+isSuper;
	}
	
	public final void setPWD(String value)
    {
        PWD = value;
    }

    public final String getPWD()
    {
        return PWD;
    }    
    
}
