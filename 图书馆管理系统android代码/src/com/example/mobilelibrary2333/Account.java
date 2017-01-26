package com.example.mobilelibrary2333;

import java.io.Serializable;

public abstract class Account implements Serializable{
	private String ID;
	private String name;
	private String passWords;
	private String PWD; 
	
	public Account(String initialID,String initialname,String initialpassWords){
		ID = initialID;
		name = initialname;
		passWords = initialpassWords;
	}
	
	public String getID(){
		return ID;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPassWords() {
		return passWords;
	}
	
	public boolean setPassWords(String newPassWords){
		passWords = newPassWords;
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(((Account)obj).getID().equals(ID))
			return true; 
		return false;
	}
	
	
	
	public String toString(){
		return name+"_"+ID+"_"+passWords;
	}
	public void setPWD(String value)
    {
        PWD = value;
    }

    public String getPWD()
    {
        return PWD;
    }    
}	
