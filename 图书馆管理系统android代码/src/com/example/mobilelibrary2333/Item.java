package com.example.mobilelibrary2333;
public abstract class Item {

	private String code;
	private String publishTime;
	private String name;
	private boolean isAvaliable;
	private String mainContext;
	private String category;
	public Item(String initialcode,String initialpublishTimeString,String initialnameString,String initialcategory)
	{
		code=initialcode;
		publishTime=initialpublishTimeString;
		name=initialnameString;
		category=initialcategory;
		isAvaliable=true;
		
	}
	public String getCode() {
		return code;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public String getName() {
		return name;
	}
	public String getMainContext() {
		return mainContext;
	}
	public boolean setMainContext(String newContext){
		mainContext = newContext;
		return true;
	}
	public String getCategory() {
		return category;
	}
	public void setAvaliable(boolean avaliable) {
		this.isAvaliable=avaliable;
	}
	public boolean getAvaliabe()
	{
		return isAvaliable;
	}
	public boolean equals(Object object)
	{
		return object instanceof Item&&this.code.equals(((Item)object).getCode());
		
	}

}
