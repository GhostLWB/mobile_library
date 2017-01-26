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
	
	//��ɶ�borrowedAccountList�ĳ�ʼ��
	public BorrowedAccountList(Context context) throws Exception{
		
		contextLocal=context;
		borrowedAccountList = new Vector<BorrowerAccount>();
		communinateWithFile = new CommuninateWithFile(fileName, contextLocal);
		finishBoorowedAccountList(contextLocal);
	}
	
	
	
	//��ɶ�borrowedAccountList�ĳ�ʼ��
	private void finishBoorowedAccountList(Context context) throws Exception{
		String[] textLine;
		String text=null;
		communinateWithFile.writeDataToFile("",context, true);//ȷ���ļ�����
		text = communinateWithFile.readDataFromFile(context);
		Log.i("BorrowerAccountList", "���ļ��ж�ȡ���ݣ�result is :"+text);
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
	//��borrowedAccountList�е�BorrowedAccount��ToString
	public String getString(){
		String text = "";
		Iterator<BorrowerAccount> iterator = getIterator();
		while (iterator.hasNext()) {
			BorrowerAccount borrowerAccount = (BorrowerAccount) iterator.next();
			text += borrowerAccount.toString()+"\n";
		}
		return text;
	}
	//��borrowedAccountList������BorrowedAccount
	public void addBorrowerAccount(BorrowerAccount borrowedAccount) {
		borrowedAccountList.add(borrowedAccount);
	}
	
	//�Ƴ��ض���BorrowedAccount
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
	//�������ֲ����ض����˻�
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
	
	//����ID�����ض��˻�
	public BorrowerAccount searchBorrowerAccountByID(String ID) {
		BorrowerAccount borrowerAccount; 
		Iterator<BorrowerAccount> iterator = getIterator();
		Log.i("BorrowerAccountList", "ID ����� is:"+ID);
		while (iterator.hasNext()) {
			borrowerAccount = (BorrowerAccount) iterator.next();
			if (borrowerAccount.getID().equals(ID)) {
				Log.i("BorrowerAccountList", "����ID :"+borrowerAccount.getID());
				return borrowerAccount;
			}
		}
		return null;
	}
	
	//���˻�д���ض����ļ���
	public void writeBorrowerAccountToFile(Context context) throws Exception{
		String text = getString();
		Log.i("BorrowerAccountList", "���˻�д���ļ���want to  write:"+text);
		communinateWithFile.writeDataToFile(text, context);
	}
}
