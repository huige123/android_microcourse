package com.dieyidezui.util;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.dieyidezui.entity.MyPaint;
import com.dieyidezui.entity.Point;
import com.dieyidezui.util.Constants.Mode;

public class DrawableHelper {
	private List<BitmapHelper> bitmapHelpers;
	private List<Bitmap> cacheBitmaps;
	private ColorManager colorManager;
	private PaintManager paintManager;
	private int curPage, width, height, mode;
	public DrawableHelper(int width, int height){
		curPage = -1;
		bitmapHelpers = new ArrayList<BitmapHelper>();
		cacheBitmaps = new ArrayList<Bitmap>();
		this.width = width; this.height = height;
		nextPage();
	}
	public boolean onTouchEvent(MotionEvent event){
		switch(paintManager.getMode()){
		case Mode.ERASER:
		case Mode.PEN:
			return onTouchPaint(event);
		case Mode.HAND:
			return onTouchBitmap(event);
		default:
			return false;
		}
	}
	private boolean onTouchPaint(MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			paintManager.onDown(new Point(event.getX(), event.getY()));
			break;
		case MotionEvent.ACTION_MOVE:
			paintManager.onMove(new Point(event.getX(), event.getY()));
			break;
		case MotionEvent.ACTION_UP:
			paintManager.onUp();
			break;
		default:
			return false;
		}
		return true;
	}
	private boolean onTouchBitmap(MotionEvent ev){
//		switch(ev.getAction()){
//		case MotionEvent.ACTION_DOWN:
//		case MotionEvent.ACTION_POINTER_DOWN:
//		case MotionEvent.ACTION_MOVE:
//		case MotionEvent.ACTION_UP:
//		case MotionEvent.ACTION_POINTER_UP:
//			
			     final int historySize = ev.getHistorySize();
			     final int pointerCount = ev.getPointerCount();
			     for (int h = 0; h < historySize; h++) {
			         System.out.printf("At time %d:", ev.getHistoricalEventTime(h));
			         for (int p = 0; p < pointerCount; p++) {
			             System.out.printf("  pointer %d: (%f,%f)",
			                 ev.getPointerId(p), ev.getHistoricalX(p, h), ev.getHistoricalY(p, h));
			         }
			     }
			     System.out.printf("At time %d:", ev.getEventTime());
			     for (int p = 0; p < pointerCount; p++) {
			         System.out.printf("  pointer %d: (%f,%f)",
			             ev.getPointerId(p), ev.getX(p), ev.getY(p));
			     }
	}
	public void onDraw(Canvas canvas){
		
		
	}
	public void nextPage(){
		if(++curPage == bitmapHelpers.size()){
			bitmapHelpers.add(new BitmapHelper());
			cacheBitmaps.add(Bitmap.createBitmap(width, height, Config.ARGB_4444));
		}
		paintManager.setBitmap(cacheBitmaps.get(curPage));
	}
	public void prePage(){
		if(curPage > 0){
			paintManager.setBitmap(cacheBitmaps.get(--curPage));
		}
	}
	public void setMode(int mode){
		this.mode = mode;
		paintManager.setMode(mode);
	}
}
