package com.aaaliua.load.library;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;

public class LoaderFactory {

	public static final int DEFAULT_DELAY = 70;
	
	public static Context mContext;
	public static Resources mResources;
	
	private Bitmap bitmap;
	
	public LoaderFactory(Context mContext) {
		this.mContext = mContext;
		mResources = mContext.getResources();
	}
	
	public AnimationDrawable getloader(LoaderType loaderType){
		bitmap = getBitmapFromDrawable(loaderType);
		return new LoaderDrawable(mContext, getBitmaps(loaderType), DEFAULT_DELAY);
	}
	
	private Bitmap[] getBitmaps(LoaderType loaderType){
		final int mCount = loaderType.getFrames();
		Bitmap[] bmp = new Bitmap[mCount];
		final int width = bitmap.getWidth() / mCount;
		
		int offset = 0;
		for(int i = 0;i<mCount;i++){
			bmp[i] = Bitmap.createBitmap(bitmap, offset, 0, width, bitmap.getHeight());
			offset += width;
		}
		
		return bmp;
	}
	
	
	private Bitmap getBitmapFromDrawable(LoaderType loaderType){
		final int resourcesID = mResources.getIdentifier(loaderType.getSpriteName(), "drawable", mContext.getPackageName());
		return BitmapFactory.decodeResource(mResources, resourcesID);
		
	}
}
