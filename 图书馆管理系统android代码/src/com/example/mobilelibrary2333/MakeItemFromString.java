package com.example.mobilelibrary2333;


public class MakeItemFromString {
	
	//把字符串转化为Item
	public static Item makeItemFromString(String textLine) {
		String[] text =textLine.split("_");
		if(text[0].equals("书籍")){
			return makeBookFromString(text);
		}else{
			return makeVedioFromString(text);
		}
		
	}
	//把字符串转化为Book
	private static Book makeBookFromString(String[] initialtext) {
		return new Book(initialtext[1], initialtext[3],initialtext[2],initialtext[5],initialtext[6],Integer.parseInt(initialtext[7]));
		
	}
	//把字符串转化为Vedio
	private static Vedio makeVedioFromString(String[] initialtext) {
		return new Vedio(initialtext[1], initialtext[3],initialtext[2],initialtext[4],initialtext[7],Integer.parseInt(initialtext[6]));
	}
	
}
