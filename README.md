# 这是什么？
这是一个模拟图书馆理系统APP，是在JAVA面向对象开发课程学习的时候进行共同开发的成果.
# 该应用能够干嘛？
该应用能够进行常规的用户登陆注册、图书借阅管理、书籍数据管理、读者数据管理等功能。已编译的APP的默认登陆账号为2015141463083，默认密码为123456.
## 添加图书管理员
* 获取：用户名、用户ID、密码
* 逻辑：创建一个管理员对象，创建一个管理员清单对象（工厂模式）、添加管理员对象到清单、将清单写入文件（清单对象自带的方法）
* 主要代码：
```
			SuperAccount superAccount;
			superAccountList.addSuperAccount(superAccount=MakeAccountFromString.getSuperAccount(textLine));
			superAccountList.writeSuperAccountToFile();
```

## 添加借阅者：
* 获取：用户名、用户ID（判断用户ID是否已经存在）、密码
* 逻辑：创建一个借阅者对象、创捷一个借阅者清单（工厂模式）、添加借阅者到借阅者清单、写入文件（自带的方法）
* 主要代码：
```
			BorrowerAccount borrowerAccount;
			borrowedAccountList.addBorrowerAccount(borrowerAccount=MakeAccountFromString.getBorrowerAccount(textLine));//创建借阅者并加入清单
			borrowedAccountList.writeBorrowerAccountToFile();//写入文件			
			//后面为每个借阅者创建一个借阅者清单
			MatchIDWithBorrowerItem matchIDWithBorrowerItem = MatchIDWithBorrowerItem.getSingleInstance();
			matchIDWithBorrowerItem.addIDWithPath(ID);
```

## 添加分类

* 获取：分类（判断是否已经存在）
* 逻辑：创建一个MatchCatalogeWithStoredItems对象、判断分类是否存在（已经有方法）不存在则增加一个分类（已有方法）、
String内容：物理_物理.txt_
* 主要代码：
```
matchCatalogeWithStoredItems.hasACAtaloge(cataloge))//判断是否已有该分类
	
	//增加分类
	matchCatalogeWithStoredItems.addCatalogeAndPath(cataloge);
	matchCatalogeWithStoredItems.writeCatalogeAndPathToFile();
```



## 添加图书项（有一个CodeList清单来判断是否code是否重复）：
* 获取：分类、名字、编号、出版日期、书（作者、页数）、视频（时长、表演者、格式）
* 逻辑：创建一个图书项添加到图书清单中
* 主要代码：
```
				//添加图书
				Book book = new Book(code, publishTime, name, cataloge, author, initialpage);
				storedItems.addItem(book);
				storedItems.writeStoredItemsToFile();


		//添加视频
		Vedio vedio = new Vedio(code, publishTime, name, cataloge, performer, format, time);
		storedItems.addItem(vedio);
		storedItems.writeStoredItemsToFile();

```
## 打印借书清单
* 获取：借阅者ID
* 逻辑：将于ID关联的文件中的内容打印出来



## 借书（查找以后借书）
* 获取：借阅者ID、密码（即登陆）
* 逻辑：将StoredItems中的书的可借与否改为false 、再在BorrowedItem中添加该书、




## 还书（打印借阅者清单以后还书）
* 获取：借阅者ID
* 逻辑：将StoredItems中的书的可借与否改为true 、再在BorrowedItem中删除该书、



# 该应用运行起来是什么样子？
![](https://jiantuku-liwenbin.oss-cn-shanghai.aliyuncs.com/%E6%9D%82/%E5%9B%BE%E4%B9%A6%E9%A6%86%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/Screenshot_2020-12-15-19-50-15-675_com.example.mo.jpg)
![](https://jiantuku-liwenbin.oss-cn-shanghai.aliyuncs.com/%E6%9D%82/%E5%9B%BE%E4%B9%A6%E9%A6%86%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/Screenshot_2020-12-15-19-51-04-727_com.example.mo.jpg)
![](https://jiantuku-liwenbin.oss-cn-shanghai.aliyuncs.com/%E6%9D%82/%E5%9B%BE%E4%B9%A6%E9%A6%86%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/Screenshot_2020-12-15-19-51-09-029_com.example.mo.jpg)
![](https://jiantuku-liwenbin.oss-cn-shanghai.aliyuncs.com/%E6%9D%82/%E5%9B%BE%E4%B9%A6%E9%A6%86%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F%E8%BF%90%E8%A1%8C%E6%88%AA%E5%9B%BE/Screenshot_2020-12-15-19-51-24-468_com.example.mo.jpg)
