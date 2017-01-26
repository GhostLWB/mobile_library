package com.example.mobilelibrary2333;

import java.util.Calendar;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_add extends Activity {

	private static final String m[] = { "�鼮", "��Ƶ" };
	private ArrayAdapter arrayAdapter;
	private Spinner spinner;
	private Button timeSelectAdd;
	private int year = 0, month, day;
	private String publishTime;
	private EditText publishDataAdd, bookName, bookID, bookAuthor, bookCatalog,
			bookPage;
	private TextView pageText, pageEndAdd;
	private DatePickerDialog datePickerDialog;
	private ImageButton addbooks;
	private Context context;
	private ImageButton mainPage, listPage, myPage;
	private SuperAccount superAccount;
	private boolean errflag = false;
	private String itemType = null;
	private Book book;
	private Vedio video;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.add);
		context = this;
		Intent intent = this.getIntent();
		superAccount = (SuperAccount) intent
				.getSerializableExtra("SuperAccount");
		spinner = (Spinner) findViewById(R.id.spinner2);
		publishDataAdd = (EditText) findViewById(R.id.publishDataAdd);
		pageText = (TextView) findViewById(R.id.pageText);
		pageEndAdd = (TextView) findViewById(R.id.pageEndAdd);
		timeSelectAdd = (Button) findViewById(R.id.timeSelectAdd);
		bookName = (EditText) findViewById(R.id.nameAdd);
		bookID = (EditText) findViewById(R.id.idAdd);
		bookAuthor = (EditText) findViewById(R.id.authorAdd);
		bookCatalog = (EditText) findViewById(R.id.catalogAdd);
		addbooks = (ImageButton) findViewById(R.id.addBooksButtonAdd);
		bookPage = (EditText) findViewById(R.id.pageAdd);
		mainPage = (ImageButton) findViewById(R.id.mainPageAdd);
		listPage = (ImageButton) findViewById(R.id.listPageAdd);
		myPage = (ImageButton) findViewById(R.id.myPageAdd);

		arrayAdapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, m);
		arrayAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(arrayAdapter);
		// -----------------------------------------------------------------------------------------------------------
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int i, long l) {
				String t = arrayAdapter.getItem(i).toString();
				// Toast.makeText(Activity_add.this, t,
				// Toast.LENGTH_SHORT).show();
				if (t.equals("�鼮")) {
					itemType = "�鼮";
					pageText.setText("ҳ��");
					pageEndAdd.setText("ҳ");
				} else {
					itemType = "��Ƶ";
					pageText.setText("ʱ��");
					pageEndAdd.setText("����");
				}
			}

			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});
		// -----------------------------------------------------------------------------------------------------------

		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		datePickerDialog = new DatePickerDialog(this,
				new DatePickerDialog.OnDateSetListener() {
					public void onDateSet(DatePicker datePicker, int year,
							int month, int day) {
						publishDataAdd.setText(year + "-" + (month + 1) + "-"
								+ day);
						publishTime = year + "-" + month + "-" + day;
					}
				}, year, month, day);

		timeSelectAdd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				datePickerDialog.show();
			}
		});

		// =======================�ײ�����imageButton============================
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

		// =======================�ײ�����imageButton-End============================

		addbooks.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				String name = null, ID = null, author = null, catalog = null;
				int page = 0;
				name = bookName.getText().toString();
				ID = bookID.getText().toString();
				author = bookAuthor.getText().toString();
				catalog = bookCatalog.getText().toString();
				if ((bookPage.getText().toString()).length() != 0) {
					page = Integer.parseInt(bookPage.getText().toString());
				}
				if (name == null || ID == null || author == null
						|| catalog == null || page == 0 || year == 0) {
					Toast.makeText(context, "����û����������Ŷ", Toast.LENGTH_SHORT)
							.show();
				}
				while (name != null && ID != null && author != null
						&& catalog != null && page != 0 && year != 0) {
					CodeList codeList = new CodeList(context);
					ID = bookID.getText().toString();
					if (codeList.hasCode(ID))// ��������ID�Ѿ�����
					{

						Toast.makeText(context, "�ñ���Ѿ���������", Toast.LENGTH_SHORT)
								.show();
						Intent intent = new Intent(context, Activity_Menu.class);
						Bundle bundle = new Bundle();
						bundle.putSerializable("SuperAccount", superAccount);
						intent.putExtras(bundle);
						startActivity(intent);
						//finish();
						break;
					}

					MatchCatalogeWithStoredItems matchCatalog = null;
					// TODO Auto-generated method stub

					Log.i("Activity_add",
							"***************************************�����ʱ��ĸ�ʽ��(catalog)is:"
									+ catalog);

					try {
						matchCatalog = MatchCatalogeWithStoredItems
								.getSingleInstance(context);
						Log.i("Activity_add",
								"matchcatalogwithstoreitems instance has been made:"
										+ matchCatalog.toString());
						if (!(matchCatalog.hasACalataoge(catalog))) {
							matchCatalog.addCatalogeAndPath(catalog);// ���û�и÷��������÷���
							Log.i("Activity_add",
									"catalog doesn't exist before:has add catalog and path");
							try {
								matchCatalog
										.writeCatalogeAndPathToFile(context);// д���ļ���д������Լ���Ӧ��·�����ļ�
								Log.i("Activity_add",
										"has write catalog and path into file");
								Log.i("Activity_add", "has write :"
										+ matchCatalog.getMacthedPath(catalog));

							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							Log.i("Activity_add", catalog + "�����Ѿ�����");
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// =================================�ж��Ƿ���Ҫ�½������Լ���ID�Ƿ��ظ�===========

					if (itemType == "�鼮") {
						book = new Book(ID, publishTime, name, catalog, author,
								page);
						Log.i("Activity_add", "book content:" + book.toString());
					} else {
						video = new Vedio(ID, publishTime, name, catalog,
								author, page);
					}

					try {
						ItemFactory itemFactory = new ItemFactory(context);// ����ģʽ
						StoredItems store = itemFactory.makeStoreItem(catalog,
								context);
						Log.i("Activity_add", "store instance has been made:"
								+ store.toString());
						if (itemType == "�鼮") {
							store.addItem(book);
						} else {
							store.addItem(video);
						}
						if (store.isEmpty()) {
							Toast.makeText(context,
									"store item is empty : err-------",
									Toast.LENGTH_SHORT).show();
							Log.i("Activity_add",
									"After add items:store item is still empty");
						} else {
							Log.i("Activity_add",
									"store is not empty is content is:"
											+ store.toString());
							store.writeStoredItemsToFile(context);// д���ļ����������ϸ��Ϣд����Ӧ�ķ����ļ�=============
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// =================================�ж��Ƿ���Ҫ�½������Լ���ID�Ƿ��ظ�end===========

					// ========================д���ļ�����====================

					try {
						codeList.addCode(ID);
						codeList.writeDataToFile();// д���ļ������鼮��idд���ļ�
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Toast.makeText(context, "�ɹ����\"" + name + "\"��",
							Toast.LENGTH_SHORT).show();
					// ========================д���ļ�����end====================

					name = null;
					ID = null;
					author = null;
					catalog = null;
					year = 0;
					page = 0;
					Intent intent = new Intent(context, Activity_Menu.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("SuperAccount", superAccount);
					intent.putExtras(bundle);
					startActivity(intent);
					//finish();
				}
			}
		});

	}
}
