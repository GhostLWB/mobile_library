package com.example.mobilelibrary2333;
public class BorrowerAccount extends Account {
	final private boolean isSuper = false;
	public BorrowerAccount(String initialID, String initialname, String initialpassWords) {
		super(initialID, initialname, initialpassWords);
	}
	//>��ӡ�����嵥
	//>���ļ��ж�ȡ�����嵥
	//>�����鼮�������嵥
	//>�ӽ����嵥��ɾ���ض�����
	//>�ڽ����嵥�в����ض�����
	//>�������嵥д���ض��ļ�
	//>��ȡ�ļ��е���Ŀ�嵥
	//>����Ŀ�嵥�в����ض�����
	//>�޸���Ŀ�嵥�е����isAvailable����
	public boolean getIsSuper(){
		return isSuper;
	}
	
	public String toString(){
		return super.getName()+"_"+super.getID()+"_"+super.getPassWords()+"_"+isSuper;
	}

}
