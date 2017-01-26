package com.example.mobilelibrary2333;

import java.io.Serializable;

public class Book extends Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5876060146521397532L;
	private String author;
	private int page;
	//private String PWD;

	public Book(String initialcode, String initialpublishTimeString,
			String initialnameString, String initialcategory,
			String initialauthor, int initialpage) {
		super(initialcode, initialpublishTimeString, initialnameString,
				initialcategory);
		this.author = initialauthor;
		this.page = initialpage;
	}

	public String getAuthor() {
		return author;
	}

	public int getPage() {
		return page;
	}

	public String toString() {
		return " ÈºÆ" + "_" + this.getCode() + "_" + this.getName() + "_"
				+ this.getPublishTime() + "_" + this.getAvaliabe() + "_"
				+ this.getCategory() + "_" + this.getAuthor() + "_"
				+ this.getPage();
	}
//	public final void setPWD(String value)
//    {
//        PWD = value;
//    }
//
//	public final String getPWD() {
//		return PWD;
//	}

}
