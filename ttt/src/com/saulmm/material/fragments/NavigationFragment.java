package com.saulmm.material.fragments;

import com.aaaliua.utils.ImageViewUtils;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;
import com.example.ttt.LoginActivity;
import com.example.ttt.R;
import com.saulmm.material.slidingtabs.views.ImageUtils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;



public class NavigationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    
    private AnimationDrawable animationDrawable;
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		View v = view.findViewById(R.id.userHeader);
		View vs = view.findViewById(R.id.select);
		ImageView use = (ImageView)view.findViewById(R.id.userPhoto);
		use.setImageBitmap(ImageViewUtils.getCroppedBitmap(new BitmapFactory().decodeResource(getResources(), R.drawable.photo)));
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().startActivity(new Intent(getActivity(),LoginActivity.class));
			}
		});
		
		vs.setSelected(true);
		
	}
	private ImageView load;
	@Override
	public void onResume() {
		super.onResume();
		
//		load.setImageResource(R.drawable.mid_animation);
//		animationDrawable = (AnimationDrawable) load.getDrawable();
//		animationDrawable.start();
//		AnimationDrawable animationDrawable =
//				new AnimationDrawable();
//		try {
//			SVG svgImage1 = SVG.getFromResource(getActivity(), 
//					R.raw.acid1_embedcss);
//			animationDrawable.addFrame(new PictureDrawable(svgImage1.renderToPicture()), 150);
//			SVG svgImage2 = SVG.getFromResource(getActivity(),
//					 R.raw.logo_ji);
//			animationDrawable.addFrame(new PictureDrawable(svgImage2.renderToPicture()), 150);
//			SVG svgImage3 = SVG.getFromResource(getActivity(),
//					 R.raw.logo_tao);
//			animationDrawable.addFrame(new PictureDrawable(svgImage3.renderToPicture()), 150);
//			SVG svgImage4 = SVG.getFromResource(getActivity(),
//					 R.raw.logo_bao);
//			animationDrawable.addFrame(new PictureDrawable(svgImage4.renderToPicture()), 150);
//			animationDrawable.setOneShot(true);
//			load.setImageDrawable(animationDrawable);
//			animationDrawable.start();
//		} catch (SVGParseException e) {
//			e.printStackTrace();
//		}
		
	}
    
    
}