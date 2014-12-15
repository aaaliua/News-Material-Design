package com.aaaliua.load.library;

import com.example.ttt.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class LoadView extends ImageView {

	private Context mContext;
	private LoaderType mLoaderType;
	private int mFrameDelay = 50;
	
	public LoadView(Context context) {
		super(context);
		mContext = context;
	}

	public LoadView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		
		TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.LoaderView,0,0);
		if(ta.hasValue(R.styleable.LoaderView_loaderType)){
			mLoaderType = LoaderType.getLoaderByName(ta.getString(R.styleable.LoaderView_loaderType));
		}
		if(ta.hasValue(R.styleable.LoaderView_loaderFrameDelay)){
			mFrameDelay = ta.getInt(R.styleable.LoaderView_loaderFrameDelay, 50);
		}
		ta.recycle();
		initWithLoader();
	}
	

	private void initWithLoader(){
		if(mLoaderType != null){
			setAnimation(new LoaderFactory(mContext).getloader(mLoaderType));
		}
	}
	
	public void setLoader(LoaderType type) {
        mLoaderType = type;
        initWithLoader();
    }

    public void setDelay(int delay) {
        mFrameDelay = delay;
        initWithLoader();
    }

    public void setLoaderWithDelay(LoaderType type, int delay) {
        mLoaderType = type;
        mFrameDelay = delay;
        initWithLoader();
    }
	
	
	
	
	private void setAnimation(AnimationDrawable animationDrawable){
		if(Build.VERSION.SDK_INT > 16){
			setBackground(animationDrawable);
		}else{
			setBackgroundDrawable(animationDrawable);
		}
		animationDrawable.start();
	}
}
