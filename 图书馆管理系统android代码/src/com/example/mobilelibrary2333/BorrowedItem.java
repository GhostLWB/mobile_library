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
	
	//完成对ID,path.borrowedItems的初始化
	public BorrowedItem(String initialID,Context context) throws Exception{
		ID = initialID;
		contextLocal=context;
		matchIDWithBorrowerItem = MatchIDWithBorrowerItem.getSingleInstance(context);
		communinateWithFile = new CommuninateWithFile(matchIDWithBorrowerItem.getMacthedPath(initialID),context);//获取与ID匹配的路径
		Log.i("BorrowerItem", "与ID匹配的路径是："+matchIDWithBorrowerItem.getMacthedPath(initialID));
		finishBoorowedItems();
	}
	
	
	public String getID() {
		return ID;
	}
	
	
	
	//完成对BorrowedItems的初始化
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
	//将borrowedItems中的items给ToString
	public String getString(){
		String text = "";
		Iterator<Item> iterator = getIterator();
		while (iterator.hasNext()) {
			Item item = (Item) iterator.next();
			text += item.toString()+"_\n";
		}
		return text;
	}
	//向borrowedItems中增加items
	public void addItem(Item item) {
		borrowedItems.add(item);
	}
	//移除特定的items
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
	//根据名字查找特定的书目项
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
	
	//将书目写在特定的文件中
	public void writeBorrowedItemsToFile(Context context) throws Exception{
		String text = getString();
		communinateWithFile.writeDataToFile(text,context);
		Log.i("BorrowerItem", "将借到的书写在借书者清单中 要写的是："+text);
		
	}
	
	
}
