package com.dieyidezui.entity;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.dieyidezui.util.Constants;
import com.dieyidezui.util.Constants.Mode;

public class MyPaint {
	private Bitmap cacheBitmap;
	private int preX, preY;
	Paint drawPaint;
	public MyPaint(int width, int height){
		drawPaint = new Paint();
		cacheBitmap = Bitmap.createBitmap(width, height, Config.ARGB_4444);
		setMode(Mode.PEN);
	}
	public void setMode(int mode){
		switch(mode){
		case Mode.PEN:
			drawPaint.setStyle(Paint.Style.STROKE);
			//drawPaint.setColor(colors[cur]);
			drawPaint.setAlpha(Constants.ALPAL);
			drawPaint.setStrokeWidth(Constants.PEN_STROKE_WIDTH);
			drawPaint.setXfermode(Constants.DEFAULT_XFERMODE);
			break;
		case Mode.ERASER:
			drawPaint.setColor(Color.TRANSPARENT);
			drawPaint.setStrokeWidth(Constants.ERASER_STROKE_WIDTH);
			drawPaint.setXfermode(Constants.CLEAR_XFERMODE);
			break;
		default:
		}
	}
	public void setColor(int color){
		drawPaint.setColor(color);
	}
	public boolean onTouchEvent(MotionEvent event){
		switch(event.getAction()){
			
		}
		
		return true;
	}
}
