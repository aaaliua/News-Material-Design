package com.aaaliua.load.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;

public class LoaderDrawable extends AnimationDrawable{

	public LoaderDrawable(Context mContext,Bitmap[] arrayBitmaps,int delay) {
		
		for(int i =0;i<arrayBitmaps.length;i++){
			addFrame(new BitmapDrawable(mContext.getResources(),arrayBitmaps[i]), delay);
		}
		
		setOneShot(false);
	}
}
