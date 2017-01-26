package com.example.mobilelibrary2333;

import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_return extends Activity {
	private SuperAccount superAccount;
	private ListView borrowerAccountList;
	private Context context;
	private String readFileResult;
	private String[] borrowerListViewSource;
	private ArrayAdapter<String> borrowerListAdapter;
	private ArrayAdapter<String> borrowerListAdapter2;
	private AutoCompleteTextView input;
	private ImageButton search;
	private String iDinputString;
	private ImageButton mainPage, listPage, myPage;
	private Vector<String>vectorSearchResult=new Vector<String>();
	private Vector<String> borrowerName = new Vector<String>();
	private Vector<String> borrowerUnsearch = new Vector<String>();
	private boolean searchIsClick=false;
	private String itemSelected;
	private long mExitTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.returnfindbyid);
		borrowerAccountList= (ListView) findViewById(R.id.listViewReturn);
		input = (AutoCompleteTextView) findViewById(R.id.inputBorrower);
		search=(ImageButton) findViewById(R.id.searchButtonReaturn);
		mainPage = (ImageButton) findViewById(R.id.mianButtonReturn);
		listPage = (ImageButton) findViewById(R.id.listButtonReturn);
		myPage = (ImageButton) findViewById(R.id.mypageButtonReturn);
		
		context = this;
		Intent intent = this.getIntent();
		superAccount = (SuperAccount) intent
				.getSerializableExtra("SuperAccount");

		// =======================================================
		try {

			BorrowedAccountList borrowedAccountList = new BorrowedAccountList(
					context);
			readFileResult = borrowedAccountList.getString();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		borrowerListViewSource = readFileResult.split("\n");
		Log.i("Avtivity_List", "read file result:" + readFileResult);
		
		Vector<String> borrowerID = new Vector<String>();
		Log.i("Avtivity_List", "borrowerListViewSource's length:"
				+ borrowerListViewSource.length);
		if (borrowerListViewSource.length > 1) {
			Log.i("Avtivity_List", "------------------------run 'if'method ");
			for (int i = 0; i < borrowerListViewSource.length; i++) {
				String[] borrowernameSource = borrowerListViewSource[i]
						.split("_");
				borrowerUnsearch.add(borrowerListViewSource[i]);
				for (int j = 0; j < borrowernameSource.length; j++) {
				}
				borrowerName.add(borrowernameSource[0]);
				borrowerID.add(borrowernameSource[1]);
				
			}

		}

		borrowerListAdapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, borrowerName);
		borrowerListAdapter2 = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, borrowerID);

		borrowerAccountList.setAdapter(borrowerListAdapter);
		input.setAdapter(borrowerListAdapter2);
		//iDinputString=input.getText().toString();
		// ============search的点击事件======================================
		search.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				BorrowedAccountList borrowedAccountList;
				BorrowerAccount borrower;
				searchIsClick=true;
				try {
					iDinputString=input.getText().toString();
					borrowedAccountList = new BorrowedAccountList(context);
					if(borrowedAccountList.searchBorrowerAccountByID(iDinputString)!=null){
					
						borrowedAccountList.searchBorrowerAccountByID(iDinputString);
							 borrower=borrowedAccountList.searchBorrowerAccountByID(iDinputString);
						
//						else{
//							borrower=borrowedAccountList.searchBorrowerAccountByName(iDinputString);
//						}
							 vectorSearchResult.add(borrower.toString());
						Log.i("3", "搜索到的借阅者的资料为："+borrower.toString());
						ArrayAdapter<String>adapterTemp=new ArrayAdapter<String>(context,
								android.R.layout.simple_list_item_1, vectorSearchResult);
						borrowerAccountList.setAdapter(adapterTemp);
			
					}else{
						return;//endif
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		// ============listView的点击事件======================================
		borrowerAccountList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
//						Vector<String> borrowerIDi = new Vector<String>();
//						
//						if (borrowerListViewSource.length > 1) {
//							for (int i = 0; i < borrowerListViewSource.length; i++) {
//								borrowerIDi.add(borrowerListViewSource[i]);
//							}
//
//						}
						
						// TODO Auto-generated method stub
						
						if(searchIsClick==true){
							itemSelected = vectorSearchResult.get(position);
						}else{
							itemSelected=borrowerUnsearch.get(position);
						}
						Log.i("Activity_List", "position:" + position);

						Log.i("Activity_List", "the item being click:" + itemSelected);

						String[] itemsplit = itemSelected.split("_");
						try {
							BorrowedAccountList borrowedAccountListi = new BorrowedAccountList(
									context);
							BorrowerAccount borrower = borrowedAccountListi
									.searchBorrowerAccountByID(itemsplit[1]);
							Log.i("Activity_List", "解析到的查找标准" + itemsplit[1]);
							Intent intent = new Intent(context,
									Activity_borrowerReturn.class);
							Bundle bundle = new Bundle();
							bundle.putSerializable("BorrowerAccount", borrower);
							intent.putExtras(bundle);
							Bundle bundle2= new Bundle();
							bundle2.putSerializable("SuperAccount", superAccount);
							intent.putExtras(bundle2);
							startActivity(intent);
							//finish();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
		// =======================底部三个imageButton============================
				mainPage.setOnClickListener(new OnClickListener() {

					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(context, Activity_Menu.class);
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
						Bundle bundle = new Bundle();
						bundle.putSerializable("SuperAccount", superAccount);
						intent.putExtras(bundle);
						startActivity(intent);
						//finish();
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
						//finish();
					}
				});

				// =======================底部三个imageButton-End============================

	}
	


}
