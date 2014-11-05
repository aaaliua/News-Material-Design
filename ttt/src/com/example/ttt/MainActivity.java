package com.example.ttt;


import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

@SuppressLint({ "InlinedApi", "ResourceAsColor", "NewApi" })
public class MainActivity extends ActionBarActivity {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
//			FragmentTransaction transaction = getFragmentManager()
//					.beginTransaction();
//			SlidingTabsBasicFragment fragment = new SlidingTabsBasicFragment();
//			transaction.replace(R.id.sample_content_fragment, fragment);
//			transaction.commit();
			
			getSupportFragmentManager().beginTransaction().replace(R.id.sample_content_fragment, new SlidingTabsBasicFragment()).commit();
		}

		configureToolbar();
		configureDrawer();
	}

	private void configureToolbar() {
		Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);

		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // 顶部
			mainToolbar.setPadding(0, 72, 0, 0);
			// 透明导航栏
			// getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//底部
		} else if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {

			getWindow().setStatusBarColor(R.color.theme_toolbar);
		}

		setSupportActionBar(mainToolbar);
		getSupportActionBar().setTitle("首页");

		mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
					mDrawerLayout.closeDrawer(Gravity.START);
					
				} else {
					mDrawerLayout.openDrawer(Gravity.START);

				}
			}
		});
	}

	private void configureDrawer() {
		// Configure drawer
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.string.drawer_open, R.string.drawer_closed) {

			public void onDrawerClosed(View view) {
				supportInvalidateOptionsMenu();
				getSupportActionBar().setTitle("首页");
			}

			public void onDrawerOpened(View drawerView) {
				supportInvalidateOptionsMenu(); // creates call to
												// onPrepareOptionsMenu()
				getSupportActionBar().setTitle("功能列表");
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			new MaterialDialog.Builder(MainActivity.this)
	        .title("Google Wifi")
	        .positiveText(R.string.accept)
	        .customView(R.layout.dialog_customview)
	        .positiveText("Connect")
	        .positiveColor(Color.parseColor("#03a9f4"))
	        .build()
	        .show();
			return true;
		}
		if (id == R.id.action_settings2) {
			new MaterialDialog.Builder(this)
	        .title("Social Networks")
	        .items(new String[]{"Twitter", "Google+", "Instagram", "Facebook"})
	        .itemsCallbackMultiChoice(new MaterialDialog.ListCallbackMulti() {
	            @Override
	            public void onSelection(MaterialDialog dialog, Integer[] which, String[] text) {
	            }
	        })
	        .positiveText("Choose")
	        .positiveColor(getResources().getColor(R.color.material_pink_500))
	        .build()
	        .show();
		}
		if (id == R.id.menu_search) {
			// AnalyticsManager.sendEvent(SCREEN_LABEL, "launchsearch", "");
			// startActivity(new Intent(this, SearchActivity.class));
			
		
		}
		
		if(id == R.id.About){
			//show Material Dialog
			new MaterialDialog.Builder(MainActivity.this)
	        .title("Permissions")
	        .content("This app determines your phone's location and shares it with Google in order to serve personalized alerts to you. This allows for a better overall app experience.")
	        .theme(Theme.LIGHT)  // the default is light, so you don't need this line
	        .positiveText(R.string.accept)  // the default is 'Accept'
	        .negativeText(R.string.decline)  // leaving this line out will remove the negative button
	        .build()
	        .show();
		}

		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
