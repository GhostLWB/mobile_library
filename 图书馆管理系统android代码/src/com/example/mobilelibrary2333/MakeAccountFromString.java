package com.example.mobilelibrary2333;




public class MakeAccountFromString {
	public static BorrowerAccount getBorrowerAccount(String textLine) {
		String[] textArray;
		textArray = textLine.split("_");
		if(textArray[3].equals("false")){
			return new BorrowerAccount(textArray[1], textArray[0], textArray[2]);
		}else{
			return null;
		}		
	} 
	public static SuperAccount getSuperAccount(String textLine){
		String[] textArray;
		textArray = textLine.split("_");
		if(textArray[3].equals("true")){
			return new SuperAccount(textArray[1], textArray[0], textArray[2]);
		}else{
			return null;
		}
	}
}
//	return sup0er.getName()+"_"+sup1er.getID()+"_"+super.get2PassWords()+"_"+is3Super;