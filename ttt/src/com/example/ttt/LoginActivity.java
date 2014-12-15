package com.example.ttt;

import com.aaaliua.application.MyApplication;
import com.aaaliua.typeface.TypefaceHelper;

import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;

public class LoginActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		overridePendingTransition(0, 0);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		TypefaceHelper.typeface(this,((MyApplication)getApplication()).getUbuntuTypeface());
		
		Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mainToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // 顶部
			mainToolbar.setPadding(0, 72, 0, 0);
			// 透明导航栏
			 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//底部
		} else if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {

		}
		
		mainToolbar.setNavigationOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		overridePendingTransition(0, 0);
	}
}
