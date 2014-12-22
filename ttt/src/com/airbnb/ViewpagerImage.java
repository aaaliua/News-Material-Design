package com.airbnb;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


import com.aaaliua.application.MyApplication;
import com.aaaliua.typeface.TypefaceHelper;
import com.aaaliua.utils.FragmentViewPagerAdapter;
import com.aaaliua.view.CirclePageIndicator;
import com.aaaliua.view.HackyViewPager;
import com.example.ttt.R;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher.ViewFactory;

public class ViewpagerImage extends ActionBarActivity implements ViewFactory , ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener{

	@InjectView(R.id.content)HackyViewPager pagers;
	@InjectView(R.id.background)LinearLayout background;
	@InjectView(R.id.indicator)CirclePageIndicator mIndicator;
	List<Fragment> fragments;
	
	ValueAnimator colorAnim = null;
	private static final int RED = 0xffe84e40;
	private static final int BLUE = 0xffF4B400;  	
	private static final int WHITE = 0xff4285F4;
	private static final int GREEN = 0xff0B8043;
	private static final int DURATION = 3000;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.airbnb_viewpager);
		ButterKnife.inject(this);
		
		TypefaceHelper.typeface(this,((MyApplication)getApplication()).getUbuntuTypeface());
		Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mainToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		mainToolbar.setNavigationOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		final ImageSwitcher imageswitcher = (ImageSwitcher)findViewById(R.id.image);
		imageswitcher.setFactory(this);  
        imageswitcher.setImageResource(R.drawable.appintro1);  
        imageswitcher.setInAnimation(getApplicationContext(),R.drawable.switcher_in);
		imageswitcher.setOutAnimation(getApplicationContext(),R.drawable.switcher_out);
        
        
    	fragments = new ArrayList<Fragment>();
		for(int i = 0 ;i<4;i++){
				Fragment fm =com.airbnb.ContentFragment.newInstance(i);
				fragments.add(fm);
		}
        pagers.setAdapter(new MyframPagerAdapter(this.getSupportFragmentManager(),pagers,fragments));
        
        
        mIndicator.setViewPager(pagers);
        
        mIndicator.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
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
			}
			
			public int getViewPagerChildCount() {
				return fragments.size();
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				int count = getViewPagerChildCount() - 1;
				if (count != 0) {
					float length = (arg0 + arg1) / (count);
					int progress = (int) (length * DURATION);
					seek(progress);
				}
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
		
			}
		});
        
        
		
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // 顶部
			mainToolbar.setPadding(0, 72, 0, 0);
			// 透明导航栏
//			 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//底部
		} else if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {

		}
	}
	
	
	private void seek(long seekTime){
		if (colorAnim == null) {
			createDefaultAnimation();
		}
		colorAnim.setCurrentPlayTime(seekTime);
	}
	private void createDefaultAnimation() {
		colorAnim = ObjectAnimator.ofInt(background,
				"backgroundColor", RED, BLUE, WHITE, GREEN,BLUE);
		colorAnim.setEvaluator(new ArgbEvaluator());
		colorAnim.setDuration(DURATION);
		colorAnim.addUpdateListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pager_main, menu);
		
		return true;
	}

	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

	
	private AnimationDrawable animationDrawable; 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		return super.onOptionsItemSelected(item);
	}
	
	
	  public class MyframPagerAdapter extends FragmentViewPagerAdapter {

	    	
			
			public MyframPagerAdapter(FragmentManager fragmentManager,
					ViewPager viewPager, List<Fragment> fragments) {
				super(fragmentManager, viewPager, fragments);
			}

		}
	
	@Override
	public View makeView() {
		//创建一个ImageView对象 
		ImageView imageView = new ImageView(this); 
		//设置图片的拉伸方式 
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); 
		//设置图片的填充方式
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT ,LayoutParams.WRAP_CONTENT) );
		 return imageView;
	}
	
	
	@Override
	public void onAnimationStart(Animator animation) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationEnd(Animator animation) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationCancel(Animator animation) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationRepeat(Animator animation) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationUpdate(ValueAnimator animation) {
//		invalidate();
		// TODO Auto-generated method stub
		
	}
}
