package com.example.mobilelibrary2333;

import android.content.Context;


public class ItemFactory{
	private Context contextThis;
	
	public ItemFactory(Context  context){
		contextThis=context;
	}
	
	public StoredItems makeStoreItem(String cataloge,Context context) throws Exception{
		return new StoredItems(cataloge,context);
	}//end makeStore
	
	public BorrowedItem makeBorrowedItem(String ID,Context context) throws Exception{
		return new BorrowedItem(ID,context);
	}
}
