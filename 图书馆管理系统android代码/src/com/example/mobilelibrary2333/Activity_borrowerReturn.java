package com.example.mobilelibrary2333;

import java.util.Vector;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_borrowerReturn extends Activity {
	private BorrowerAccount borrower;
	private TextView text;
	private Book book;
	private Context context;
	private String[] borrowerItems;
	private ListView itemList;
	private ArrayAdapter<String> borroweritemListAdapter;
	private SuperAccount superAccount;
	private ImageButton mainPage;
	private ImageButton myPage;
	private ImageButton listPage;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.borrowerreturn);
		text = (TextView) findViewById(R.id.userNameBorList);
		itemList = (ListView) findViewById(R.id.booksBorrowedList);

		context = this;
		mainPage = (ImageButton) findViewById(R.id.mainPageBorrower1);
		listPage = (ImageButton) findViewById(R.id.listPageBorrower1);
		myPage = (ImageButton) findViewById(R.id.myPageBorrower1);

		Intent intent = this.getIntent();
		borrower = (BorrowerAccount) intent
				.getSerializableExtra("BorrowerAccount");

		Intent intent2 = this.getIntent();
		superAccount = (SuperAccount) intent2
				.getSerializableExtra("SuperAccount");

		String borrowerData = borrower.toString();
		String[] data = borrowerData.split("_");
		text.setText("读者名称:" + data[0] + "读者编号：" + data[1]);

		try {
			String ID = borrower.getID();
			ItemFactory itemFactory = new ItemFactory(context);// 工厂模式
			BorrowedItem borrowedItem = itemFactory.makeBorrowedItem(ID,
					context);// 工厂模式
			borrowerItems = borrowedItem.getString().split("\n");
			Log.i("3",
					"从文件中读到的读者的借阅清单borrowedItem.getString()"
							+ borrowedItem.getString());
			borroweritemListAdapter = new ArrayAdapter<String>(context,
					android.R.layout.simple_list_item_1, borrowerItems);
			itemList.setAdapter(borroweritemListAdapter);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ============listView的点击事件======================================
		itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Vector<String> borrowerITEM = new Vector<String>();

				if (borrowerItems.length > 0) {
					for (int i = 0; i < borrowerItems.length; i++) {
						borrowerITEM.add(borrowerItems[i]);
					}

				}

				// TODO Auto-generated method stub
				String item = borrowerITEM.get(position);
				String[] itemDetail = item.split("_");
				Log.i("Activity_List", "position:" + position);

				ItemFactory itemFactory = new ItemFactory(context);// 工厂模式
				BorrowedItem borrowedItem;
				try {
					borrowedItem = itemFactory.makeBorrowedItem(
							borrower.getID(), context);
					borrowedItem.removeItem(itemDetail[1]);// 从借书者清单中移除并重写;

					String[] catalogeArray;
					MatchCatalogeWithStoredItems matchCatalogeWithStoredItems = MatchCatalogeWithStoredItems
							.getSingleInstance(context);

					catalogeArray = matchCatalogeWithStoredItems
							.getAllCataloge();

					StoredItems storedItems;

					for (int i = 0; i < catalogeArray.length; i++) {
						storedItems = itemFactory.makeStoreItem(
								catalogeArray[i], context);// 工厂模式
						if (storedItems.returnBook(itemDetail[1])) {// 将书目的可借阅属性设置为可借
							Toast.makeText(context, "成功归还" + itemDetail[2],
									Toast.LENGTH_SHORT).show();
							borrowerITEM.remove(position);
							ArrayAdapter<String>itemNew = new ArrayAdapter<String>(context,
									android.R.layout.simple_list_item_1, borrowerITEM);
							itemList.setAdapter(itemNew);
							finish();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// 工厂模式

			}
		});

		// =========================底端三个按钮=======================
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

				Bundle bundle = new Bundle();
				bundle.putSerializable("SuperAccount", superAccount);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		});

	}
}
