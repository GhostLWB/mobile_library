package com.example.mobilelibrary2333;
import java.util.Iterator;
import java.util.Vector;

import android.content.Context;
import android.util.Log;

//安装时，获得书目分类与书的filename
public class StoredItems {
		
	private String cataloge;
	private Vector<Item> storeItems;
	private CommuninateWithFile communinateWithFile;
	private Context contextLocal;
	
	//完成对cataloge,path.borrowedItems的初始化
	public StoredItems(String initialCataloge,Context context) throws Exception{
		storeItems = new Vector<Item>();
		cataloge = initialCataloge;
		contextLocal=context;
		MatchCatalogeWithStoredItems matchCatalogeWithStoredItems =MatchCatalogeWithStoredItems.getSingleInstance(contextLocal);//获取与ID匹配的路径
		communinateWithFile = new CommuninateWithFile(matchCatalogeWithStoredItems.getMacthedPath(cataloge),contextLocal);
		communinateWithFile.writeDataToFile("",contextLocal, true);//确保文件存在
		storeItems = new Vector<Item>();
		finishStoredItems(contextLocal);
	}
	
	
	public String getCataloge() {
		return cataloge;
	}
	
	
	
	//完成对storeItems的初始化
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
	
	//获得遍历器	
	public Iterator<Item> getIterator(){
		return storeItems.iterator();
	}
	
	
	//将storeItems中的items给ToString
	
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
	//向borrowedItems中增加items
	public void addItem(Item item) {
		storeItems.add(item);
	}
	//移除特定的items
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
	public void writeStoredItemsToFile(Context contextLocal) throws Exception{ 
		String text=getString(storeItems);
		communinateWithFile.writeDataToFile(text,contextLocal);
		Log.i("StoredItems", "we have write:"+communinateWithFile.readDataFromFile(contextLocal));
		Log.i("StoredItems", "将书籍的详细信息写入分类文件：we have write:"+text);

	}

	//判断书目是否为空
	public boolean isEmpty(){
		if(storeItems.size()==0){
			return true;
		}else{
			return false;
		}//endif
	}
	
	
	//借书
	public boolean borrowBook(String code)throws Exception{
		Item item = null;
		Iterator<Item> iterator = getIterator();
		while (iterator.hasNext()) {
			item = (Item) iterator.next();
			if(item.getCode().equals(code)){
				iterator.remove();
				item.setAvaliable(false);
				Log.i("3", "更改available:从true改为false：结果是："+item.getAvaliabe());
				addItem(item);
				writeStoredItemsToFile(contextLocal);
				return true;
			}//endif
		}//endwhile
		
		return false;
	}//end
	
	//根据传入ID还书
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