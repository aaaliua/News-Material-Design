package com.saulmm.material.slidingtabs.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

public class ToolBarShadown extends Toolbar{

	private boolean flag = true;
	
	public ToolBarShadown(Context context) {
		super(context);
	}

	public ToolBarShadown(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ToolBarShadown(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	
	public void setEnableShadown(boolean flag){
		this.flag = flag;
	}
	
	private static final int DEFAULT_SCRIM_COLOR = 0x99000000;
	private int mScrimColor = DEFAULT_SCRIM_COLOR;
	private Paint mScrimPaint = new Paint();
	@Override
	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
		final boolean result = super.drawChild(canvas, child, drawingTime);
		
		 int clipLeft = 0, clipRight = getWidth();
		 if(flag){
			 final int baseAlpha = (mScrimColor & 0xff000000) >>> 24;
	            final int imag = (int) (baseAlpha * mScrimColor);
	            final int color = imag << 24 | (mScrimColor & 0xffffff);
	            mScrimPaint.setColor(color);
	            canvas.drawRect(clipLeft, 0, clipRight, getHeight(), mScrimPaint);
		 }
		 
		 return result;
	}

}
