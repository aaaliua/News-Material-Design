package com.example.ttt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.aaaliua.utils.FragmentViewPagerAdapter;
import com.example.ttt.SlidingTabsBasicFragment.MyframPagerAdapter;
import com.saulmm.material.fragments.ContentFragment;
import com.saulmm.material.fragments.oneFragment;
import com.saulmm.material.fragments.threeFragment;
import com.saulmm.material.fragments.twoFragment;
import com.saulmm.material.slidingtabs.views.Blur;
import com.saulmm.material.slidingtabs.views.ImageUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class OneLoginn extends ActionBarActivity{

	
	private ViewPager mvPager;
	
	List<Fragment> fragments;
	
	private static final String BLURRED_IMG_PATH = "blurred_image.png";
	private ImageView mBlurredImage;
	private ImageView mNormalImage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragment_onelogin);
		
		mBlurredImage = (ImageView) findViewById(R.id.blurred_image);
		mNormalImage = (ImageView) findViewById(R.id.normal_image);
		
		
		
		
//		Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.guide_bg_sync);
//		Palette.generateAsync(bm,
//		        new Palette.PaletteAsyncListener() {
//		    @Override
//		    public void onGenerated(Palette palette) {
//		         Palette.Swatch vibrant =
//		                 palette.getVibrantSwatch();
////		          if (swatch != null) {
////		              // If we have a vibrant color
////		              // update the title TextView
////		              titleView.setBackgroundColor(
////		                  vibrant.getRgb());
////		              titleView.setTextColor(
////		                  vibrant.getTitleTextColor());
////		          }
//		         getWindow().setStatusBarColor(vibrant.getRgb());
//		    }
//		});
		
		
		fragments = new ArrayList<Fragment>();
		mvPager = (ViewPager)findViewById(R.id.oneLogin_viewpager);
		oneFragment f = new oneFragment();
		twoFragment d = new twoFragment();
		threeFragment t= new threeFragment();
		fragments.add(f);
		fragments.add(d);
		fragments.add(t);
		
		mvPager.setAdapter(new MyframPagerAdapter(this.getSupportFragmentManager(),mvPager,fragments));
		
		mvPager.setOnPageChangeListener(new pagechange());
		
//		mBlurredImage.setAlpha(255);
		
		
		final int screenWidth = ImageUtils.getScreenWidth(this);
		final File blurredImage = new File(getFilesDir() + BLURRED_IMG_PATH);
		System.out.println(blurredImage.toString());
		if (blurredImage.exists()) 
			blurredImage.delete();
			// launch the progressbar in ActionBar
			setProgressBarIndeterminateVisibility(true);

			new Thread(new Runnable() {

				@Override
				public void run() {

					// No image found => let's generate it!
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inSampleSize = 2;
					Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.guide_bg_sync, options);
					Bitmap newImg = Blur.fastblur(OneLoginn.this, image, 14);
					ImageUtils.storeImage(newImg, blurredImage);
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							System.out.println("run-----------------");
							updateView(screenWidth);

							// And finally stop the progressbar
							setProgressBarIndeterminateVisibility(false);
						}
					});

				}
			}).start();

//		} else {
//			System.out.println("Norun-----------------");
//			// The image has been found. Let's update the view
//			updateView(screenWidth);
//
//		}
	}
	
	public class pagechange implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			System.out.println(arg1 +"------------->" +arg2    +"==========" + (int)(arg1 * 10));
			
			if(arg0 >0 ){
				return;
			}
			mBlurredImage.setAlpha(arg1);
//			Bitmap bmp = ((BitmapDrawable)img.getDrawable()).getBitmap();
//			 if(bmp == null)
//		            return;
//			 Bitmap bm;
//			 if (arg0 == 0)
//			 {
//				  bm = Blur.apply(OneLoginn.this, bmp, (int)(arg1 * 10) == 0?1:(int)(arg1 * 10)); 
//			 }else{
//				 
//				  bm = Blur.apply(OneLoginn.this, bmp, (int)(arg1 * 10) == 0?1:(int)(arg1 * 10));
//			 }
//				 
//		        img.setImageBitmap(bm);
		}

		@Override
		public void onPageSelected(int arg0) {
			
		}
		
	}
	
	  public class MyframPagerAdapter extends FragmentViewPagerAdapter {

	    	
			
			public MyframPagerAdapter(FragmentManager fragmentManager,
					ViewPager viewPager, List<Fragment> fragments) {
				super(fragmentManager, viewPager, fragments);
			}
			

		}
	  
	  
		private void updateView(final int screenWidth) {
			Bitmap bmpBlurred = BitmapFactory.decodeFile(getFilesDir() + BLURRED_IMG_PATH);
			bmpBlurred = Bitmap.createScaledBitmap(bmpBlurred, screenWidth, (int) (bmpBlurred.getHeight()
					* ((float) screenWidth) / (float) bmpBlurred.getWidth()), false);

			mBlurredImage.setImageBitmap(bmpBlurred);

		}
}
