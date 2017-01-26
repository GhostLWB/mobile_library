package com.example.mobilelibrary2333;

import java.util.ArrayList;
import java.util.Vector;

import org.apache.http.util.EncodingUtils;

import com.example.mobilelibrary2333.R.layout;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.net.sip.SipAudioCall.Listener;
import android.os.Bundle;
import android.provider.MediaStore.Video;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_Search extends Activity {

	private AutoCompleteTextView inputText;
	private ImageButton searchImageButton;
	private ImageButton mainPage;
	private ImageButton myPage;
	private ImageButton listPage;
	private Context context;
	private String inputString;
	private SuperAccount superAccount;
	private String readFileResult = null;
	private ArrayAdapter<String> arrayAdapterAutoComplete;
	private ArrayAdapter<String> arrayAdapterForDisplay;
	private ArrayAdapter<String> arrayAdapterResult;
	private ListView bookListView;
	private Vector<String> searchBookResult = new Vector<String>();
	private String[] booksDetailTemp;
	private String[] searchResultStrings;
	private BorrowerAccount borrowerAccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.search);
		inputText = (AutoCompleteTextView) findViewById(R.id.searchAutoCompleteTextView);
		searchImageButton = (ImageButton) findViewById(R.id.searchButtonSearch);
		mainPage = (ImageButton) findViewById(R.id.mainpageButtonSearch);
		listPage = (ImageButton) findViewById(R.id.listPageButtonSearch);
		myPage = (ImageButton) findViewById(R.id.mypageButtonSearch);
		bookListView = (ListView) findViewById(R.id.bookListViewSearch);
		context = this;

		Intent intent = this.getIntent();
		superAccount = (SuperAccount) intent
				.getSerializableExtra("SuperAccount");
		if (superAccount == null) {
			Log.i("Activity_search", "7777777777777777777777777superAccount is null");
		}
		Intent intent2 = this.getIntent();
		borrowerAccount = (BorrowerAccount) intent2
				.getSerializableExtra("BorrowerAccount");
		if (borrowerAccount == null) {
			Log.i("Activity_search", "777777777777777777777777777777borrowerAccount is null");
		}

		readFileResult = readDataFromFile();

		Log.i("Activity_search", "readDataFromFile run :SearchActivity ");
		Log.i("Activity_search", "readFileReault:" + readFileResult);
		// ------------------------------------------------------------------

		String[] bookDetailsForPromote = readFileResult.split("\n|_");
		booksDetailTemp = readFileResult.split("\n");
		Vector<String> nameForDisplay = new Vector<String>();
		if(booksDetailTemp.length>1){
		for (int i = 0; i < booksDetailTemp.length; i++) {
			String[] detail = booksDetailTemp[i].split("_");
			nameForDisplay.add(detail[2]);
			searchBookResult.add(booksDetailTemp[i]);
		}
		}
		arrayAdapterAutoComplete = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, bookDetailsForPromote);

		arrayAdapterForDisplay = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, nameForDisplay);

		bookListView.setAdapter(arrayAdapterForDisplay);
		inputText.setAdapter(arrayAdapterAutoComplete);
		Log.i("Activity_search", "set Adapter run");

		// =========================底端三个按钮=======================
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

		searchImageButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i("Activity_search", "searchButton onclickListener run");
				inputString = inputText.getText().toString();
				Log.i("Activity_search", "inputstring:" + inputString);
				Vector<String> searchResult = new Vector<String>();
				if (inputText.getText() == null) {
					Log.i("Activity_search", "inputText.getText() is null");
				} else {
					Log.i("Activity_search", "inputText.getText() is not null");
				}
				if (inputString != null) {
					int dismatch = 0;

					for (int i = 0; i < booksDetailTemp.length; i++, dismatch++) {
						String[] bookNamesForsearch = booksDetailTemp[i]
								.split("_");

						// ==========尝试与输入进行匹配=====================

						if (bookNamesForsearch[1].equals(inputString)
								|| bookNamesForsearch[2].equals(inputString)
								|| bookNamesForsearch[3].equals(inputString)
								|| bookNamesForsearch[4].equals(inputString)
								|| bookNamesForsearch[5].equals(inputString)
								|| bookNamesForsearch[6].equals(inputString)
								|| bookNamesForsearch[7].equals(inputString)) {

							searchResult.add(booksDetailTemp[i]);
							dismatch--;
							searchBookResult = searchResult;
						}
					}
					// searchResultStrings= (String[]) searchResult.toArray();
					if (dismatch == booksDetailTemp.length) {
						Toast.makeText(context, "您要找的书不存在哟", Toast.LENGTH_SHORT)
								.show();
					} else {
						// String []Result=searchResult;
						// Log.i("Test2",searchResult[1]);
						arrayAdapterResult = new ArrayAdapter<String>(context,
								android.R.layout.simple_list_item_1,
								searchResult);
						bookListView.setAdapter(arrayAdapterResult);
						Log.i("Test2", "found");
					}

				} else {
					Toast.makeText(context, "请您先输入内容！", Toast.LENGTH_SHORT)
							.show();
				}
				// ==========尝试与输入进行匹配-end=====================
			}
		});
		// ===========================ListView的点击事件==================
		bookListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						String item = searchBookResult.get(position);
						Log.i("Activity_search",
								"**********************************************************格式为："
										+ item);
						String[] bookDetails = item.split("_");
						Log.i("2", "跳转到图书或视频："+bookDetails[0]);
						if (bookDetails[0].equals("书籍")) {
							Log.i("2", "跳转到了图书56666666666666666666666");
							int page = Integer.parseInt(bookDetails[7]);
							Log.i("Activity_search", "bookDetails[7]格式为："
									+ bookDetails[7]);
							Book book = new Book(bookDetails[1],
									bookDetails[3], bookDetails[2],
									bookDetails[5], bookDetails[6], page);
							String booksAvailable=bookDetails[4];
							// Book book=new Book(initialcode,
							// initialpublishTimeString, initialnameString,
							// initialcategory, initialauthor, initialpage)
							String bookString = book.toString();
							if (borrowerAccount != null) {
								Intent intent = new Intent(context,
										Activity_BookDetailForBorrow.class);
								Log.i("Activity_search",
										"77777777777777777777777777777777777777borrowerAccount is not null");
								Bundle bundle = new Bundle();
								bundle.putSerializable("SuperAccount",
										superAccount);
								intent.putExtras(bundle);
								Bundle bundle2 = new Bundle();
								bundle2.putSerializable("BorrowerAccount",
										borrowerAccount);
								intent.putExtras(bundle2);
								
								intent.putExtra("Books", bookString);
								intent.putExtra("BooksAvailable", booksAvailable);
								startActivity(intent);
								//finish();
							} else {
								Log.i("Activity_search",
										"77777777777777777777777777777777777777borrowerAccount is null");
								Intent intent = new Intent(context,
										Activity_BookDetail.class);
								Bundle bundle = new Bundle();
								bundle.putSerializable("SuperAccount",
										superAccount);
								intent.putExtras(bundle);
								intent.putExtra("Books", bookString);
								intent.putExtra("BooksAvailable", booksAvailable);
								startActivity(intent);
								//finish();
							}
						} 
						if(bookDetails[0].equals("视频")){
							Log.i("2", "跳转到了视频56666666666666666666666");
							int timeVideo = Integer.parseInt(bookDetails[6]);
							Vedio video = new Vedio(bookDetails[1],
									bookDetails[3], bookDetails[2],
									bookDetails[4], bookDetails[7], timeVideo);
							String videoString = video.toString();
							String videoAvailable=bookDetails[5];
							if (borrowerAccount != null) {
								Intent intent = new Intent(context,
										Activity_VideoDetailForBorrow.class);
								Log.i("Activity_search",
										"77777777777777777777777777777777777777borrowerAccount is not null");
								Bundle bundle = new Bundle();
								bundle.putSerializable("SuperAccount",
										superAccount);
								intent.putExtras(bundle);
								Bundle bundle2 = new Bundle();
								bundle2.putSerializable("BorrowerAccount",
										borrowerAccount);
								intent.putExtras(bundle2);
								intent.putExtra("Video", videoString);
								intent.putExtra("VideoAvailable", videoAvailable);
								startActivity(intent);
								//finish();
							} else {
								Log.i("Activity_search",
										"77777777777777777777777777777777777777borrowerAccount is null");
								Intent intent = new Intent(context,
										Activity_VideoDetail.class);
								Bundle bundle = new Bundle();
								bundle.putSerializable("SuperAccount",
										superAccount);
								
								intent.putExtras(bundle);
								intent.putExtra("Video", videoString);
								intent.putExtra("VideoAvailable", videoAvailable);
								startActivity(intent);
								//finish();
							}
						}
					}
				});
	}

	private String readDataFromFile() {
		/*
		 * 1.获得所有分类 2.根据每一个分类获得相应的文件路径 3.从每一个路径读出文件内容，追加到一个StringBuffer中
		 */

		String[] allCatalog;
		StringBuffer allBooks = new StringBuffer();
		String readFileResult;

		Log.i("Activity_search", "readDataFromFile method run");

		try {
			MatchCatalogeWithStoredItems matchCatalog = MatchCatalogeWithStoredItems
					.getSingleInstance(context);
			// ---------------------------------------------------------------------------------------------debug-----------
			Log.i("Activity_search", "matchCatalogWithStoredItems Instans got");
			if (matchCatalog == null) {
				Log.i("Activity_search", "matchCatalog instanse is null");
			} else {
				Log.i("Activity_search", "matchCatalog instanse is not null");

			}
			// ---------------------------------------------------------------------------------------------debug-----------

			allCatalog = matchCatalog.getAllCataloge();
			Log.i("Activity_search", "the length of allCatalog is "
					+ allCatalog.length);

			for (int i = 0; i < allCatalog.length; i++) {
				Log.i("Activity_search", "read catalog from file,catalog " + i
						+ " is " + allCatalog[i]);
				String catalogPath = matchCatalog.getMacthedPath(allCatalog[i]);
				CommuninateWithFile fileOperate = new CommuninateWithFile(
						catalogPath, context);
				readFileResult = fileOperate.readDataFromFile(context);
				allBooks.append(readFileResult);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allBooks.toString();

	}

}
