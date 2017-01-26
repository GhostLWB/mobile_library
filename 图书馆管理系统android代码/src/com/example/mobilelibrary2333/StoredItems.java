package com.example.mobilelibrary2333;
import java.util.Iterator;
import java.util.Vector;

import android.content.Context;
import android.util.Log;

//��װʱ�������Ŀ���������filename
public class StoredItems {
		
	private String cataloge;
	private Vector<Item> storeItems;
	private CommuninateWithFile communinateWithFile;
	private Context contextLocal;
	
	//��ɶ�cataloge,path.borrowedItems�ĳ�ʼ��
	public StoredItems(String initialCataloge,Context context) throws Exception{
		storeItems = new Vector<Item>();
		cataloge = initialCataloge;
		contextLocal=context;
		MatchCatalogeWithStoredItems matchCatalogeWithStoredItems =MatchCatalogeWithStoredItems.getSingleInstance(contextLocal);//��ȡ��IDƥ���·��
		communinateWithFile = new CommuninateWithFile(matchCatalogeWithStoredItems.getMacthedPath(cataloge),contextLocal);
		communinateWithFile.writeDataToFile("",contextLocal, true);//ȷ���ļ�����
		storeItems = new Vector<Item>();
		finishStoredItems(contextLocal);
	}
	
	
	public String getCataloge() {
		return cataloge;
	}
	
	
	
	//��ɶ�storeItems�ĳ�ʼ��
	private void finishStoredItems(Context contextLocal) throws Exception{
		String[] textLine;
		String text=null;
		
		text = communinateWithFile.readDataFromFile(contextLocal);
		if(text.length()!=0){
			textLine = text.split("\n");
			for (int i = 0; i < textLine.length; i++) {
				storeItems.add(MakeItemFromString.makeItemFromString(textLine[i]));
			}//endfor
		}//endif
	}
	
	//��ñ�����	
	public Iterator<Item> getIterator(){
		return storeItems.iterator();
	}
	
	
	//��storeItems�е�items��ToString
	
	public String getString(Vector<Item>vector){
		String text = "";
		Iterator<Item> iterator = vector.iterator();
		while (iterator.hasNext()) 
		{
			Item item = (Item) iterator.next();
			text += item.toString()+"_\n";
		}
		return text;
	}
	//��borrowedItems������items
	public void addItem(Item item) {
		storeItems.add(item);
	}
	//�Ƴ��ض���items
	public boolean removeItem(String ID) {
		
		Item item;
		Iterator<Item> iterator = getIterator();
		
		while (iterator.hasNext()) {
			item = (Item) iterator.next();
			if (item.equals(ID)) {
				iterator.remove();
				return true;
			}
		}
		
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
	public void writeStoredItemsToFile(Context contextLocal) throws Exception{ 
		String text=getString(storeItems);
		communinateWithFile.writeDataToFile(text,contextLocal);
		Log.i("StoredItems", "we have write:"+communinateWithFile.readDataFromFile(contextLocal));
		Log.i("StoredItems", "���鼮����ϸ��Ϣд������ļ���we have write:"+text);

	}

	//�ж���Ŀ�Ƿ�Ϊ��
	public boolean isEmpty(){
		if(storeItems.size()==0){
			return true;
		}else{
			return false;
		}//endif
	}
	
	
	//����
	public boolean borrowBook(String code)throws Exception{
		Item item = null;
		Iterator<Item> iterator = getIterator();
		while (iterator.hasNext()) {
			item = (Item) iterator.next();
			if(item.getCode().equals(code)){
				iterator.remove();
				item.setAvaliable(false);
				Log.i("3", "����available:��true��Ϊfalse������ǣ�"+item.getAvaliabe());
				addItem(item);
				writeStoredItemsToFile(contextLocal);
				return true;
			}//endif
		}//endwhile
		
		return false;
	}//end
	
	//���ݴ���ID����
	public boolean returnBook(String code) throws Exception{
		Iterator<Item> iterator = storeItems.iterator();
		while (iterator.hasNext()) {
			Item item = (Item) iterator.next();
			if(item.getCode().equals(code)){
				iterator.remove();
				item.setAvaliable(true);
				storeItems.add(item);
				writeStoredItemsToFile(contextLocal);
				return true;
			}//endif
		}//endwhile
		
		return false;
	}//end
}