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

public class Activity_VideoDetailForBorrow extends Activity{
	private Context context;
	private Vedio video;
	private TextView name, id, publishTime, author, catalog, time, isAvailable;
	private String videoData;
	private String videlAvailable;
	private boolean availableVideo;
	private String ava;
	private SuperAccount superAccount;
	private ImageButton mainPage;
	private ImageButton myPage;
	private ImageButton listPage;
	private ImageButton borrow;
	private BorrowerAccount borrowerAccount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.borrow_vedio);
		context = this;
		name = (TextView) findViewById(R.id.nameVideo1);
		id = (TextView) findViewById(R.id.IDVideo1);
		publishTime = (TextView) findViewById(R.id.publishTimeVideo1);
		author = (TextView) findViewById(R.id.authordVideo1);
		catalog = (TextView) findViewById(R.id.catalogVideo1);
		time = (TextView) findViewById(R.id.timeVideo1);
		isAvailable = (TextView) findViewById(R.id.isAvailableVideo1);
		mainPage = (ImageButton) findViewById(R.id.mainPageVideo1);
		listPage = (ImageButton) findViewById(R.id.listPageVideo1);
		myPage = (ImageButton) findViewById(R.id.myPageVideo1);
		borrow=(ImageButton) findViewById(R.id.videoBorrow);

		Intent intent = this.getIntent();
		superAccount = (SuperAccount) intent
				.getSerializableExtra("SuperAccount");

		Intent intent2 = this.getIntent();
		borrowerAccount = (BorrowerAccount) intent2
				.getSerializableExtra("BorrowerAccount");

		
		
		videoData = getIntent().getStringExtra("Video");
		videlAvailable=getIntent().getStringExtra("VideoAvailable");
		if(videlAvailable.equals("true")){
			availableVideo=true;
			
		}else{
			availableVideo=false;
		}

		Log.i("Activity_bookDetail", "bookData:" + videoData);
		String[] data = videoData.split("_");
		int timeData = Integer.parseInt(data[6]);
		Log.i("Activity_bookDetail", "bookPage:" + timeData);
		video = new Vedio(data[1], data[3], data[2], data[4], data[7], timeData);
		video.setAvaliable(availableVideo);
		// video=new Vedio(initialcode, initialpublishTimeString,
		// initialnameString, initialcategory, initialperformer, time);
		Log.i("Activity_bookDetail", "book content:" + video.toString());

		if (video.getAvaliabe()) {
			ava = "�ɽ�";
		} else {
			ava = "���ɽ�";
		}
		name.setText("��Ƶ����:" + video.getName());
		id.setText("��Ƶ���:" + video.getCode());
		publishTime.setText("��������:" + video.getPublishTime());
		author.setText("��Ƶ����:" + video.getPerformer());
		catalog.setText("��Ƶ����:" + video.getCategory());
		time.setText("��Ƶʱ��(����):" + video.getTime());
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
		borrow.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (video.getAvaliabe()) {
					ItemFactory itemFactory = new ItemFactory(context);// ����ģʽ
					StoredItems storedItems;
					try {
						storedItems = itemFactory.makeStoreItem(
								video.getCategory(), context);
						storedItems.borrowBook(video.getCode());// ��ͼ��Ŀɽ��Ըı�
						ava="���ɽ�";
						isAvailable.setText("�ɽ�״̬:" + ava);
						if (borrowerAccount == null) {
							Log.i("1",
									"888888888888888888888888888borrowerAccount is null");
						}
						String ID = borrowerAccount.getID();
						BorrowedItem borrowedItem = itemFactory
								.makeBorrowedItem(ID, context);// ����ģʽ
						borrowedItem.addItem(video);
						borrowedItem.writeBorrowedItemsToFile(context);// ����鵽�������嵥�ɹ�
						Toast.makeText(context, "����" + video.getName() + "�ɹ���",
								Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					Toast.makeText(context, "����Ƶ�Ѿ�����������", Toast.LENGTH_SHORT)
							.show();
					return;
				}
			}
		});

	}

}
