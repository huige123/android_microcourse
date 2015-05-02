package com.dieyidezui.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import com.dieyidezui.entity.Point;
import com.dieyidezui.util.Constants;
import com.dieyidezui.util.Constants.Mode;

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
			onDown(new Point(event.getX(), event.getY()));
			break;
		case MotionEvent.ACTION_MOVE:
			onMove(new Point(event.getX(), event.getY()));
			break;
		case MotionEvent.ACTION_UP:
			onUp();
			break;
		default:
			return false;
		}
		return true;
	}
	public void onDown(Point point) {
		Log.d(Constants.LOG, "down");
		prePoint = point;
	}
	public void onMove(Point point) {
		Log.d(Constants.LOG, "move");
		cacheCanvas.drawLine(prePoint.x, prePoint.y, point.x, point.y, drawPaint);
		prePoint = point;
	}
	public void onUp() {
		Log.d(Constants.LOG, "up");
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
