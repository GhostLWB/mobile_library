package com.example.mobilelibrary2333;

import java.util.Iterator;
import java.util.Vector;

public class SuperAccountList {

	private Vector<SuperAccount> superAccountList = null;
	private CommuninateWithFile communinateWithFile;
	
	//完成对superAccountList的初始化
	public SuperAccountList() throws Exception{
		superAccountList = new Vector<SuperAccount>();
//		communinateWithFile = new CommuninateWithFile(".\\guanliyuan.txt");
		finishSuperAccountList();
	}
	
	
	
	//完成对superAccountList的初始化
	private void finishSuperAccountList() throws Exception{
		String[] textLine=null;
		//textLine = communinateWithFile.readDataFromFile().split("\n");
		for (int i = 0; i < textLine.length; i++) {
			superAccountList.addElement(MakeAccountFromString.getSuperAccount(textLine[i]));
		}
	}
	
	
	public Iterator<SuperAccount> getIterator(){
		return superAccountList.iterator();
	}
	//将superAccountList中的SuperAccount给ToString
	public String toString(){
		String text = "";
		Iterator<SuperAccount> iterator = getIterator();
		while (iterator.hasNext()) {
			SuperAccount superAccount = (SuperAccount) iterator.next();
			text += superAccount.toString()+"\n";
		}
		return text;
	}
	//向superAccountList中增加SuperAccount
	public void addSuperAccount(SuperAccount superAccount) {
		superAccountList.add(superAccount);
	}
	
	//移除特定的superAccount
	public boolean removeSuperAccount(String ID) {
		
		SuperAccount superAccount;
		Iterator<SuperAccount> iterator = getIterator();
		
		while (iterator.hasNext()) {
			superAccount = (SuperAccount) iterator.next();
			if (superAccount.equals(ID)) {
				iterator.remove();
				return true;
			}
		}
		
		return false;
	}
	//根据名字查找特定的账户
	public SuperAccount searchAccountByName(String superAccountName) {
		SuperAccount superAccount; 
		Iterator<SuperAccount> iterator = getIterator();
		
		while (iterator.hasNext()) {
			superAccount = (SuperAccount) iterator.next();
			if (superAccount.getName().equals(superAccountName)) {
				return superAccount;
			}
		}
		return null;
	}
	//根据ID查找特定账户
	public SuperAccount searchAccountByID(String ID) {
		SuperAccount superAccount; 
		Iterator<SuperAccount> iterator = getIterator();
		
		while (iterator.hasNext()) {
			superAccount = (SuperAccount) iterator.next();
			if (superAccount.getID().equals(ID)) {
				return superAccount;
			}
		}
		return null;
	}
	
	//将账户写在特定的文件中
	public void writeSuperAccountToFile() throws Exception{
		String text = toString();
		//scommuninateWithFile.writeDataToFile(text);
	}
}
