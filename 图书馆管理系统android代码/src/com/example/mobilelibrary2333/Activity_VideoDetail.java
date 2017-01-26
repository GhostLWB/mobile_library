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

public class Activity_VideoDetail extends Activity {
	private Context context;
	private Vedio video;
	private TextView name, id, publishTime, author, catalog, time, isAvailable;
	private String videoData;
	private String videoAvailable;
	private boolean availableVideo;
	private String ava;
	private SuperAccount superAccount;
	private ImageButton mainPage;
	private ImageButton myPage;
	private ImageButton listPage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.videodetail);
		context = this;
		name = (TextView) findViewById(R.id.nameVideo2);
		id = (TextView) findViewById(R.id.IDVideo2);
		publishTime = (TextView) findViewById(R.id.publishTimeVideo2);
		author = (TextView) findViewById(R.id.authordVideo2);
		catalog = (TextView) findViewById(R.id.catalogVideo2);
		time = (TextView) findViewById(R.id.timeVideo2);
		isAvailable = (TextView) findViewById(R.id.isAvailableVideo2);
		mainPage = (ImageButton) findViewById(R.id.mainPageVideo);
		listPage = (ImageButton) findViewById(R.id.listPageVideo);
		myPage = (ImageButton) findViewById(R.id.myPageVideo);

		Intent intent = this.getIntent();
		superAccount = (SuperAccount) intent
				.getSerializableExtra("SuperAccount");

		videoData = getIntent().getStringExtra("Video");
		videoAvailable=getIntent().getStringExtra("VideoAvailable");
		if(videoAvailable.equals("true")){
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
			ava = "可借";
		} else {
			ava = "不可借";
		}
		
		
		name.setText("视频名称:" );
		name.setText("视频名称:" + video.getName());
		id.setText("视频编号:" + video.getCode());
		publishTime.setText("出版日期:" + video.getPublishTime());
		author.setText("视频作者:" + video.getPerformer());
		catalog.setText("视频分类:" + video.getCategory());
		time.setText("视频时长(分钟):" + video.getTime());
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
	}
}
