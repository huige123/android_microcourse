package com.dieyidezui.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.dieyidezui.entity.Point;
import com.dieyidezui.util.Constants;

public class PaintManager {
	private Point prePoint;
	private Paint drawPaint;
	private Canvas cacheCanvas;
	private Bitmap cacheBitmap;
	public PaintManager(){
		drawPaint = new Paint();
		cacheCanvas = new Canvas();
		setPenMode();
	}
	public void setColor(int color){
		drawPaint.setColor(color);
	}
	public void setBitmap(Bitmap bitmap){
		cacheBitmap = bitmap;
		cacheCanvas.setBitmap(bitmap);
	}
	public boolean onTouchEvent(MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			return onDown(event);
		case MotionEvent.ACTION_MOVE:
			return onMove(event);
		case MotionEvent.ACTION_UP:
			return onUp(event);
		}
		return false;
	}
	public boolean onDown(MotionEvent event) {
		prePoint = new Point(event.getX(), event.getY());
		return false;
	}
	public boolean onMove(MotionEvent event) {
		Point curPoint = new Point(event.getX(), event.getY());
		cacheCanvas.drawLine(prePoint.x, prePoint.y, curPoint.x, curPoint.y, drawPaint);
		prePoint = curPoint;
		return true;
	}
	public boolean onUp(MotionEvent event) {
		return false;
	}
	public void clear() {
		cacheCanvas.drawColor(Color.TRANSPARENT);
	}
	public void setEraserMode() {
		drawPaint.setColor(Color.TRANSPARENT);
		drawPaint.setStrokeWidth(Constants.ERASER_STROKE_WIDTH);
		drawPaint.setXfermode(Constants.CLEAR_XFERMODE);
	}
	public void setPenMode() {
		drawPaint.setStyle(Paint.Style.STROKE);
		//drawPaint.setColor(colors[cur]);
		drawPaint.setAlpha(Constants.ALPAL);
		drawPaint.setStrokeWidth(Constants.PEN_STROKE_WIDTH);
		drawPaint.setXfermode(Constants.DEFAULT_XFERMODE);
		//drawPaint.setAntiAlias(true); 
	}
	public void onDraw(Canvas canvas) {
		canvas.drawBitmap(cacheBitmap, 0, 0, null);
	}
}
