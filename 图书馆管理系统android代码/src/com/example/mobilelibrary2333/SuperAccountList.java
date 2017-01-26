package com.example.mobilelibrary2333;

import java.util.Iterator;
import java.util.Vector;

public class SuperAccountList {

	private Vector<SuperAccount> superAccountList = null;
	private CommuninateWithFile communinateWithFile;
	
	//��ɶ�superAccountList�ĳ�ʼ��
	public SuperAccountList() throws Exception{
		superAccountList = new Vector<SuperAccount>();
//		communinateWithFile = new CommuninateWithFile(".\\guanliyuan.txt");
		finishSuperAccountList();
	}
	
	
	
	//��ɶ�superAccountList�ĳ�ʼ��
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
	//��superAccountList�е�SuperAccount��ToString
	public String toString(){
		String text = "";
		Iterator<SuperAccount> iterator = getIterator();
		while (iterator.hasNext()) {
			SuperAccount superAccount = (SuperAccount) iterator.next();
			text += superAccount.toString()+"\n";
		}
		return text;
	}
	//��superAccountList������SuperAccount
	public void addSuperAccount(SuperAccount superAccount) {
		superAccountList.add(superAccount);
	}
	
	//�Ƴ��ض���superAccount
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
	//�������ֲ����ض����˻�
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
	//����ID�����ض��˻�
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
	
	//���˻�д���ض����ļ���
	public void writeSuperAccountToFile() throws Exception{
		String text = toString();
		//scommuninateWithFile.writeDataToFile(text);
	}
}
