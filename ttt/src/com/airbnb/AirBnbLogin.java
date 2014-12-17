package com.airbnb;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.aaaliua.application.MyApplication;
import com.aaaliua.typeface.TypefaceHelper;
import com.aaaliua.utils.Utils;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.ttt.MainActivity;
import com.example.ttt.R;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AirBnbLogin extends ActionBarActivity implements OnClickListener{

	@InjectView(R.id.more)View more;
	@InjectView(R.id.google)View google;
	@InjectView(R.id.facebook)View facebook;
	@InjectView(R.id.weibo)View weibo;
	@InjectView(R.id.xieyi)TextView t;
	
	public static AlertDialog dlg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		setTheme(android.R.style.Theme_Material_Light_NoActionBar);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.airbnb_login);
		ButterKnife.inject(this);
		
		
		TypefaceHelper.typeface(this,((MyApplication)getApplication()).getUbuntuTypeface());
		
		Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mainToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		getSupportActionBar().setTitle("登录或注册账号");
		
		dlg = new AlertDialog.Builder(this).create();
		
		t.setText(Html.fromHtml(getString(R.string.xieyi,
				Utils.getVersionName(this))));
		
		
		
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // 顶部
			mainToolbar.setPadding(0, 72, 0, 0);
			// 透明导航栏
			 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//底部
			 t.setPadding(0, 0, 0, 40);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.air_main, menu);

		return true;
	}

	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

	
	private AnimationDrawable animationDrawable; 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.bangzhu) {
			dlg.setCancelable(true);
			dlg.show();
			Window window = dlg.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
			window.setContentView(R.layout.dialog);
			ImageView im = (ImageView)window.findViewById(R.id.image);
			im.setImageResource(R.drawable.bnb_animation);
			
			animationDrawable = (AnimationDrawable)im.getDrawable();
			animationDrawable.start();
			return true;
		}
		if(id == R.id.fuwu){
			google.setVisibility(View.GONE);
			facebook.setVisibility(View.GONE);
			more.setVisibility(View.VISIBLE);
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	
	@OnClick(R.id.more)
	public void more(View v){
		more.setVisibility(View.GONE);
		google.setVisibility(View.VISIBLE);
		facebook.setVisibility(View.VISIBLE);
	}
	@OnClick(R.id.weibo)
	public void weibo(View v){
		dlg.setCancelable(true);
		dlg.show();
		Window window = dlg.getWindow();
		window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		window.setContentView(R.layout.dialog);
		ImageView im = (ImageView)window.findViewById(R.id.image);
		im.setImageResource(R.drawable.bnb_animation);
		
		animationDrawable = (AnimationDrawable)im.getDrawable();
		animationDrawable.start();
	}
	
	
	@Override
	public void onClick(View v) {
	}
}
