package com.example.mobilelibrary2333;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_BookDetailForBorrow extends Activity {
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
	private ImageButton borrImageButtonBorr;
	private BorrowerAccount borrowerAccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.borrow_book);
		context = this;
		name = (TextView) findViewById(R.id.bookNameBorr1);
		id = (TextView) findViewById(R.id.bookIDBorr1);
		publishTime = (TextView) findViewById(R.id.bookPubBorr1);
		author = (TextView) findViewById(R.id.bookAuthorBorr1);
		catalog = (TextView) findViewById(R.id.bookCatalogBorr1);
		page = (TextView) findViewById(R.id.bookPageBorr1);
		isAvailable = (TextView) findViewById(R.id.bookAvailaBorr1);
		mainPage = (ImageButton) findViewById(R.id.mainPageBorr);
		listPage = (ImageButton) findViewById(R.id.listPageBorr);
		myPage = (ImageButton) findViewById(R.id.myPageBorr);
		borrImageButtonBorr = (ImageButton) findViewById(R.id.borrowerBorr);

		Intent intent = this.getIntent();
		superAccount = (SuperAccount) intent
				.getSerializableExtra("SuperAccount");
		Intent intent2 = this.getIntent();
		borrowerAccount = (BorrowerAccount) intent2
				.getSerializableExtra("BorrowerAccount");

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
			ava = "�ɽ�";
		} else {
			ava = "���ɽ�";
		}
		name.setText("�鼮����:" + book.getName());
		id.setText("ͼ����:" + book.getCode());
		publishTime.setText("��������:" + book.getPublishTime());
		author.setText("ͼ������:" + book.getAuthor());
		catalog.setText("�鼮����:" + book.getCategory());
		page.setText("ͼ��ҳ��:" + book.getPage());
		isAvailable.setText("�ɽ�״̬:" + ava);
		// =========================�׶�������ť=======================
		mainPage.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, Activity_Menu.class);

				Bundle bundle = new Bundle();
				bundle.putSerializable("SuperAccount", superAccount);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
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
				finish();
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
				finish();
			}
		});

		// =========================�׶�������ťend=======================

		borrImageButtonBorr.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (book.getAvaliabe()) {
					ItemFactory itemFactory = new ItemFactory(context);// ����ģʽ
					StoredItems storedItems;
					try {
						storedItems = itemFactory.makeStoreItem(
								book.getCategory(), context);
						storedItems.borrowBook(book.getCode());// ��ͼ��Ŀɽ��Ըı�
						ava="���ɽ�";
						isAvailable.setText("�ɽ�״̬:" + ava);
						if (borrowerAccount == null) {
							Log.i("1",
									"888888888888888888888888888borrowerAccount is null");
						}
						String ID = borrowerAccount.getID();
						BorrowedItem borrowedItem = itemFactory
								.makeBorrowedItem(ID, context);// ����ģʽ
						borrowedItem.addItem(book);
						borrowedItem.writeBorrowedItemsToFile(context);// ����鵽�������嵥�ɹ�
						Toast.makeText(context, "����" + book.getName() + "�ɹ���",
								Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					Toast.makeText(context, "�Ȿ���Ѿ�����������", Toast.LENGTH_SHORT)
							.show();
					return;
				}
			}
		});

	}

}
