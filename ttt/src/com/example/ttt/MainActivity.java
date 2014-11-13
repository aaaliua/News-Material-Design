package com.example.ttt;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.jakewharton.scalpel.ScalpelFrameLayout;
import com.saulmm.material.slidingtabs.views.SnackBar;
import com.saulmm.material.slidingtabs.views.SnackBar.OnMessageClickListener;

import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

@SuppressLint({ "InlinedApi", "ResourceAsColor", "NewApi" })
public class MainActivity extends ActionBarActivity {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;

	private ScalpelFrameLayout scalpelView;;
	private static boolean first = true;

	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			// FragmentTransaction transaction = getFragmentManager()
			// .beginTransaction();
			// SlidingTabsBasicFragment fragment = new
			// SlidingTabsBasicFragment();
			// transaction.replace(R.id.sample_content_fragment, fragment);
			// transaction.commit();

			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.sample_content_fragment,
							new SlidingTabsBasicFragment()).commit();
		}

		mSnackBar = new SnackBar(this);
		configureToolbar();
		configureDrawer();

		// add scalpelview this is debugview
		scalpelView = (ScalpelFrameLayout) findViewById(R.id.scalpel);
		SwitchCompat enabledSwitch = new SwitchCompat(this);
		enabledSwitch
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (first) {
							first = false;
							Toast.makeText(MainActivity.this, "first_run",
									Toast.LENGTH_LONG).show();
						}

						scalpelView.setLayerInteractionEnabled(isChecked);
						invalidateOptionsMenu();
					}
				});
		getSupportActionBar().setCustomView(enabledSwitch);
		getSupportActionBar().setDisplayOptions(
				getSupportActionBar().DISPLAY_SHOW_TITLE
						| getSupportActionBar().DISPLAY_SHOW_CUSTOM);
	}

	private void configureToolbar() {
		Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar = mainToolbar;
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
//			getWindow().addFlags(
//					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // 顶部
//			mainToolbar.setPadding(0, 72, 0, 0); // 丁图设置透明后 界面会向上顶 让其内填充使其美观
			// 透明导航栏
//			 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//底部
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

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				// TODO Auto-generated method stub
				super.onDrawerSlide(drawerView, slideOffset);
				System.out.println(slideOffset);
//				toolbar.getBackground().setAlpha((int) slideOffset * 20);
			}

		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		// SearchManager searchManager =
		// (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
		// SupportMenuItem searchMenuItem = ((SupportMenuItem)
		// menu.findItem(R.id.menu_search));
		// SearchView searchView = (SearchView) searchMenuItem.getActionView();
		// searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

		// add debug option
		menu.add("Draw Views")
				.setCheckable(true)
				.setChecked(scalpelView.isDrawingViews())
				.setOnMenuItemClickListener(
						new MenuItem.OnMenuItemClickListener() {
							@Override
							public boolean onMenuItemClick(MenuItem item) {
								boolean checked = !item.isChecked();
								item.setChecked(checked);
								scalpelView.setDrawViews(checked);
								return true;
							}
						});
		// drawable id
		menu.add("Draw IDs")
				.setCheckable(true)
				.setChecked(scalpelView.isDrawingIds())
				.setOnMenuItemClickListener(
						new MenuItem.OnMenuItemClickListener() {
							@Override
							public boolean onMenuItemClick(MenuItem item) {
								boolean checked = !item.isChecked();
								item.setChecked(checked);
								scalpelView.setDrawIds(checked);
								return true;
							}
						});
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
			new MaterialDialog.Builder(MainActivity.this).title("Google Wifi")
					.positiveText(R.string.accept)
					.customView(R.layout.dialog_customview)
					.positiveText("Connect")
					.positiveColor(Color.parseColor("#03a9f4")).build().show();
			return true;
		}
		if (id == R.id.action_settings2) {
			new MaterialDialog.Builder(this)
					.title("Social Networks")
					.items(new String[] { "Twitter", "Google+", "Instagram",
							"Facebook" })
					.itemsCallbackMultiChoice(
							new MaterialDialog.ListCallbackMulti() {
								@Override
								public void onSelection(MaterialDialog dialog,
										Integer[] which, String[] text) {
								}
							})
					.positiveText("Choose")
					.positiveColor(
							getResources().getColor(R.color.material_pink_500))
					.build().show();
		}
		if (id == R.id.menu_search) {
			// AnalyticsManager.sendEvent(SCREEN_LABEL, "launchsearch", "");
			// startActivity(new Intent(this, SearchActivity.class));

		}
		
		if(id == R.id.onelogin){
			startActivity(new Intent(this,OneLoginn.class));
		}

		if (id == R.id.About) {
			// show Material Dialog
			new MaterialDialog.Builder(MainActivity.this)
					.title("Permissions")
					.content(
							"This app determines your phone's location and shares it with Google in order to serve personalized alerts to you. This allows for a better overall app experience.")
					.theme(Theme.LIGHT) // the default is light, so you don't
										// need this line
					.positiveText(R.string.accept) // the default is 'Accept'
					.negativeText(R.string.decline) // leaving this line out
													// will remove the negative
													// button
					.build().show();
		}
		if (id == R.id.snack) {
			
			
			mSnackBar.setOnClickListener(new OnMessageClickListener() {

				@Override
				public void onMessageClick(Parcelable token) {
				}
			});
			String message = "";
			int messageRes = -1;
			short duration = 0;
			SnackBar.Style style;

			message = "This message is a lot longer, it should stretch at least two lines. ";
			duration = SnackBar.MED_SNACK;
			style = SnackBar.Style.CONFIRM;
			mSnackBar.show(message, "Action", style, duration);
		}

		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	public SnackBar mSnackBar;    //snakebar必须在oncreate的时候创建  只创建一次 不然会出现奇怪现象 
	public static final String SAVED_SNACKBAR = "SAVED_SNACKBAR";
	@Override
    protected void onSaveInstanceState(Bundle saveState) {
        super.onSaveInstanceState(saveState);
        // use this to save your snacks for later
        saveState.putBundle(SAVED_SNACKBAR, mSnackBar.onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle loadState) {
        super.onRestoreInstanceState(loadState);
        // use this to load your snacks for later
        mSnackBar.onRestoreInstanceState(loadState.getBundle(SAVED_SNACKBAR));
    }
	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
