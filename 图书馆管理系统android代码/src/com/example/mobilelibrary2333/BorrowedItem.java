package com.example.mobilelibrary2333;

import java.util.Iterator;
import java.util.Vector;

import android.content.Context;
import android.util.Log;


public class BorrowedItem {
	private String ID;
	private Vector<Item> borrowedItems = new Vector<Item>();
	private CommuninateWithFile communinateWithFile;
	private Context contextLocal;
	private MatchIDWithBorrowerItem matchIDWithBorrowerItem;
	
	//��ɶ�ID,path.borrowedItems�ĳ�ʼ��
	public BorrowedItem(String initialID,Context context) throws Exception{
		ID = initialID;
		contextLocal=context;
		matchIDWithBorrowerItem = MatchIDWithBorrowerItem.getSingleInstance(context);
		communinateWithFile = new CommuninateWithFile(matchIDWithBorrowerItem.getMacthedPath(initialID),context);//��ȡ��IDƥ���·��
		Log.i("BorrowerItem", "��IDƥ���·���ǣ�"+matchIDWithBorrowerItem.getMacthedPath(initialID));
		finishBoorowedItems();
	}
	
	
	public String getID() {
		return ID;
	}
	
	
	
	//��ɶ�BorrowedItems�ĳ�ʼ��
	private void finishBoorowedItems() throws Exception{
		String[] textLine;
		String text=null;
		communinateWithFile.writeDataToFile("",contextLocal, true);
		text = communinateWithFile.readDataFromFile(contextLocal);
			if(text.length()!=0){
				textLine = text.split("\n");
				for (int i = 0; i < textLine.length; i++) {
					borrowedItems.add(MakeItemFromString.makeItemFromString(textLine[i]));
				}//endfor
			}//endif
	}
	
	
	public Iterator<Item> getIterator(){
		return borrowedItems.iterator();
	}
	//��borrowedItems�е�items��ToString
	public String getString(){
		String text = "";
		Iterator<Item> iterator = getIterator();
		while (iterator.hasNext()) {
			Item item = (Item) iterator.next();
			text += item.toString()+"_\n";
		}
		return text;
	}
	//��borrowedItems������items
	public void addItem(Item item) {
		borrowedItems.add(item);
	}
	//�Ƴ��ض���items
	public boolean removeItem(String code) throws Exception {
		
		Item item;
		Iterator<Item> iterator = getIterator();
		
		while (iterator.hasNext()) {
			item = (Item) iterator.next();
			if (item.getCode().equals(code)) {
				iterator.remove();
				writeBorrowedItemsToFile(contextLocal);
				return true;
			}//endif
		}//endif
		
		return false;
	}
	//�������ֲ����ض�����Ŀ��
	public Vector<Item> searchItem(String itemName) {
		Item  item;
		Vector<Item> items = new Vector<Item>();
		Iterator<Item> iterator = getIterator();
		
		while (iterator.hasNext()) {
			item = (Item) iterator.next();
			if (item.getName().equals(itemName)) {
				items.add(item);
			}
		}
		return items;
	}
	
	//����Ŀд���ض����ļ���
	public void writeBorrowedItemsToFile(Context context) throws Exception{
		String text = getString();
		communinateWithFile.writeDataToFile(text,context);
		Log.i("BorrowerItem", "���赽����д�ڽ������嵥�� Ҫд���ǣ�"+text);
		
	}
	
	
}
