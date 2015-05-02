package com.dieyidezui.view;

import com.dieyidezui.util.BitmapHelper;
import com.dieyidezui.util.Constants.Mode;
import com.dieyidezui.util.DrawableHelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
	

	Context context;

	DrawableHelper drawableHelper;
	boolean first = true;

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}
	
	

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		if(first){
			first = false;
			drawableHelper = new DrawableHelper(right-left, bottom-top);
			drawableHelper.setMode(Mode.HAND);
		}
		super.onLayout(changed, left, top, right, bottom);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		drawableHelper.onDraw(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(drawableHelper.onTouchEvent(event))
			invalidate();
		return true;
	}


	public void clearBoard(){
		drawableHelper.clearCurPage();
	}
	public void setMode(int mode){
		drawableHelper.setMode(mode);
	}
	
	public void addBitmap(Bitmap bitmap) {	
		drawableHelper.addBitmap(bitmap);
		invalidate();
	}
	public void nextPage(){
		drawableHelper.nextPage();
	}
	public void prePage(){
		drawableHelper.prePage();
	}
}
