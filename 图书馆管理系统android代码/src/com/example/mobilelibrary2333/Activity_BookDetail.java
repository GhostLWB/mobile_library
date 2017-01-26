package com.example.mobilelibrary2333;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class Activity_BookDetail extends Activity {
	private Context context;
	private Book book;
	private TextView name, id, publishTime, author, catalog, page, isAvailable;
	private String bookData;
	private String bookAvailable;
	private boolean availableBook;
	private String ava;
	private SuperAccount superAccount;
	private ImageButton mainPage;
	private ImageButton myPage;
	private ImageButton listPage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.bookdetail);
		context = this;
		name = (TextView) findViewById(R.id.bookNameBook);
		id = (TextView) findViewById(R.id.bookIDBook);
		publishTime = (TextView) findViewById(R.id.bookPubBook);
		author = (TextView) findViewById(R.id.bookAuthorBook);
		catalog = (TextView) findViewById(R.id.bookCatalogBook);
		page = (TextView) findViewById(R.id.bookPageBook);
		isAvailable = (TextView) findViewById(R.id.bookAvailaBook);
		mainPage = (ImageButton) findViewById(R.id.mainPageBook);
		listPage = (ImageButton) findViewById(R.id.listPageBook);
		myPage = (ImageButton) findViewById(R.id.myPageBook);

		Intent intent = this.getIntent();
		superAccount = (SuperAccount) intent
				.getSerializableExtra("SuperAccount");

		bookData = getIntent().getStringExtra("Books");
		bookAvailable=getIntent().getStringExtra("BooksAvailable");
		if(bookAvailable.equals("true")){
			availableBook=true;
		}else{
			availableBook=false;
		}
		
		Log.i("Activity_bookDetail", "bookData:" + bookData);
		String[] data = bookData.split("_");
		int pagedata = Integer.parseInt(data[7]);
		Log.i("Activity_bookDetail", "bookPage:" + pagedata);
		book = new Book(data[1], data[3], data[2], data[5], data[6], pagedata);
		book.setAvaliable(availableBook);
		Log.i("Activity_bookDetail", "book content:" + book.toString());

		if (book.getAvaliabe()) {
			ava = "可借";
		} else {
			ava = "不可借";
		}
		name.setText("书籍名称:" + book.getName());
		id.setText("图书编号:" + book.getCode());
		publishTime.setText("出版日期:" + book.getPublishTime());
		author.setText("图书作者:" + book.getAuthor());
		catalog.setText("书籍分类:" + book.getCategory());
		page.setText("图书页数:" + book.getPage());
		isAvailable.setText("可借状态:" + ava);
		// =========================底端三个按钮=======================
		mainPage.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, Activity_Menu.class);

				Bundle bundle = new Bundle();
				bundle.putSerializable("SuperAccount", superAccount);
				intent.putExtras(bundle);
				startActivity(intent);
				////finish();
			}
		});

		myPage.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, Activity_my.class);
				intent.putExtra("superAccountData", superAccount);
				Bundle bundle = new Bundle();
				bundle.putSerializable("SuperAccount", superAccount);
				intent.putExtras(bundle);
				startActivity(intent);
				//finish();
			}
		});

		listPage.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, Activity_List.class);
				intent.putExtra("superAccountData", superAccount);
				Bundle bundle = new Bundle();
				bundle.putSerializable("SuperAccount", superAccount);
				intent.putExtras(bundle);
				startActivity(intent);
				//finish();
			}
		});

		// =========================底端三个按钮end=======================
	}
}
