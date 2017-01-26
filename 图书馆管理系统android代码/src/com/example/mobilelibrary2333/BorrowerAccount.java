package com.example.mobilelibrary2333;
public class BorrowerAccount extends Account {
	final private boolean isSuper = false;
	public BorrowerAccount(String initialID, String initialname, String initialpassWords) {
		super(initialID, initialname, initialpassWords);
	}
	//>打印借书清单
	//>从文件中读取借书清单
	//>增加书籍到借书清单
	//>从借书清单中删除特定的书
	//>在借书清单中查找特定的书
	//>将借书清单写入特定文件
	//>读取文件中的书目清单
	//>从书目清单中查找特定的书
	//>修改书目清单中的书的isAvailable属性
	public boolean getIsSuper(){
		return isSuper;
	}
	
	public String toString(){
		return super.getName()+"_"+super.getID()+"_"+super.getPassWords()+"_"+isSuper;
	}

}
