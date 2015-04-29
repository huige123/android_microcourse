package com.dieyidezui.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.dieyidezui.entity.Point;
import com.dieyidezui.util.Constants;
import com.dieyidezui.util.Constants.Mode;

public class PaintManager {
	private Point prePoint;
	private Paint drawPaint;
	private Canvas cacheCanvas;
	public PaintManager(){
		drawPaint = new Paint();
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
			drawPaintpaint.setAntiAlias(true);  
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
	public void setBitmap(Bitmap bitmap){
		cacheCanvas.setBitmap(bitmap);
	}
	public boolean onTouchEvent(MotionEvent event){
		
		return true;
	}
	public void onDown(Point point) {
		prePoint = point;
	}
	public void onMove(Point point) {
		cacheCanvas.drawLine(prePoint.x, prePoint.y, point.x, point.y, drawPaint);
		prePoint = point;
	}
	public void onUp() {
		
	}
	
	
}
