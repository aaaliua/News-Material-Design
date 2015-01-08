package com.example.ttt;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class TestMain extends ActionBarActivity implements ViewFactory {

	public static int i =0;
	
	
	private  ImageSwitcher imageswitcher;
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				imageswitcher.setImageResource(R.drawable.appintro1);
				break;
			case 1:
				imageswitcher.setImageResource(R.drawable.appintro2);
				break;
			case 2:
				imageswitcher.setImageResource(R.drawable.appintro3);
				break;
			case 3:
				imageswitcher.setImageResource(R.drawable.appintro4);
				break;

			default:
				break;
			}
			if(i == 3){
				i = 0;
			}else{
				i++;
			}
			Message mes = new Message();
			mes.what = i;
			handler.sendMessageDelayed(mes, 8000);
		}
		
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_main);
		Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mainToolbar);
		getSupportActionBar().setTitle("首页");

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// getSupportActionBar().setDisplayShowTitleEnabled(false);

		 imageswitcher = (ImageSwitcher) findViewById(R.id.image);
		imageswitcher.setFactory(this);
		imageswitcher.setImageResource(R.drawable.appintro1);
		imageswitcher.setInAnimation(getApplicationContext(),
				R.drawable.switcher_in);
		imageswitcher.setOutAnimation(getApplicationContext(),
				R.drawable.switcher_out);
		
		i++;
		Message mes = new Message();
		mes.what = i;
		handler.sendMessageDelayed(mes, 5000);
	}

	@Override
	public View makeView() {
		// 创建一个ImageView对象
		ImageView imageView = new ImageView(this);
		// 设置图片的拉伸方式
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		// 设置图片的填充方式
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		return imageView;
	}
}
