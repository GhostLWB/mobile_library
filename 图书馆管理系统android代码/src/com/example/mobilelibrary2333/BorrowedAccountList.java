package com.example.mobilelibrary2333;
import java.util.Iterator;
import java.util.Vector;

import android.content.Context;
import android.util.Log;



public class BorrowedAccountList {
	
	private Vector<BorrowerAccount> borrowedAccountList = null;
	private CommuninateWithFile communinateWithFile;
	private Context contextLocal;
	private final String fileName= "borrowedaccountlist.txt";
	
	//完成对borrowedAccountList的初始化
	public BorrowedAccountList(Context context) throws Exception{
		
		contextLocal=context;
		borrowedAccountList = new Vector<BorrowerAccount>();
		communinateWithFile = new CommuninateWithFile(fileName, contextLocal);
		finishBoorowedAccountList(contextLocal);
	}
	
	
	
	//完成对borrowedAccountList的初始化
	private void finishBoorowedAccountList(Context context) throws Exception{
		String[] textLine;
		String text=null;
		communinateWithFile.writeDataToFile("",context, true);//确保文件存在
		text = communinateWithFile.readDataFromFile(context);
		Log.i("BorrowerAccountList", "从文件中读取数据：result is :"+text);
		if(text.length()!=0){
			textLine = text.split("\n");
			for (int i = 0; i < textLine.length; i++) {
				borrowedAccountList.addElement(MakeAccountFromString.getBorrowerAccount(textLine[i]));
			}//endfor
		}//endif
	}
	
	
	public Iterator<BorrowerAccount> getIterator(){
		return borrowedAccountList.iterator();
	}
	//将borrowedAccountList中的BorrowedAccount给ToString
	public String getString(){
		String text = "";
		Iterator<BorrowerAccount> iterator = getIterator();
		while (iterator.hasNext()) {
			BorrowerAccount borrowerAccount = (BorrowerAccount) iterator.next();
			text += borrowerAccount.toString()+"\n";
		}
		return text;
	}
	//向borrowedAccountList中增加BorrowedAccount
	public void addBorrowerAccount(BorrowerAccount borrowedAccount) {
		borrowedAccountList.add(borrowedAccount);
	}
	
	//移除特定的BorrowedAccount
	public boolean removeBorrowerAccount(String ID) {
		
		BorrowerAccount borrowerAccount;
		Iterator<BorrowerAccount> iterator = getIterator();
		
		while (iterator.hasNext()) {
			borrowerAccount = (BorrowerAccount) iterator.next();
			if (borrowerAccount.equals(ID)) {
				iterator.remove();
				return true;
			}
		}
		
		return false;
	}
	//根据名字查找特定的账户
	public BorrowerAccount searchBorrowerAccountByName(String borrowerAccountName) {
		BorrowerAccount borrowerAccount; 
		Iterator<BorrowerAccount> iterator = getIterator();
		
		while (iterator.hasNext()) {
			borrowerAccount = (BorrowerAccount) iterator.next();
			if (borrowerAccount.getName().equals(borrowerAccountName)) {
				return borrowerAccount;
			}
		}
		return null;
	}
	
	//根据ID查找特定账户
	public BorrowerAccount searchBorrowerAccountByID(String ID) {
		BorrowerAccount borrowerAccount; 
		Iterator<BorrowerAccount> iterator = getIterator();
		Log.i("BorrowerAccountList", "ID 待检查 is:"+ID);
		while (iterator.hasNext()) {
			borrowerAccount = (BorrowerAccount) iterator.next();
			if (borrowerAccount.getID().equals(ID)) {
				Log.i("BorrowerAccountList", "已有ID :"+borrowerAccount.getID());
				return borrowerAccount;
			}
		}
		return null;
	}
	
	//将账户写在特定的文件中
	public void writeBorrowerAccountToFile(Context context) throws Exception{
		String text = getString();
		Log.i("BorrowerAccountList", "将账户写入文件：want to  write:"+text);
		communinateWithFile.writeDataToFile(text, context);
	}
}
