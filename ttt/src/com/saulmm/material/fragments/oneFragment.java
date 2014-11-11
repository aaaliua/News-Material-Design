package com.saulmm.material.fragments;

import com.example.ttt.R;

import android.R.animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;



public class oneFragment extends Fragment {

	
	ImageView v;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.one_drawer, container, false);
    }

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		v = (ImageView)view.findViewById(R.id.swipe);
		Animation translateAnimation = new TranslateAnimation(0.1f, -360.0f,0.9f,0.0f);  
		Animation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
		alphaAnimation.setDuration(1000);
		alphaAnimation.setRepeatCount(100);
		alphaAnimation.setRepeatMode(Animation.RESTART);
		
		//设置动画时间               
		translateAnimation.setDuration(1000);
		translateAnimation.setRepeatCount(100);
		translateAnimation.setRepeatMode(Animation.RESTART);
		AnimationSet set = new AnimationSet(true);  
		set.addAnimation(translateAnimation);
		set.addAnimation(alphaAnimation);
		                            v.startAnimation(set);
		
		                            
	}

	@Override
	public void onResume() {
		super.onResume();
		Animation translateAnimation = new TranslateAnimation(0.1f, -360.0f,0.9f,0.0f);  
		Animation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
		alphaAnimation.setDuration(1000);
		alphaAnimation.setRepeatCount(100);
		alphaAnimation.setRepeatMode(Animation.RESTART);
		
		//设置动画时间               
		translateAnimation.setDuration(1000);
		translateAnimation.setRepeatCount(100);
		translateAnimation.setRepeatMode(Animation.RESTART);
		AnimationSet set = new AnimationSet(true);  
		set.addAnimation(translateAnimation);
		set.addAnimation(alphaAnimation);
		                            v.startAnimation(set);
	}


	
	
	
}