package com.dieyidezui.util;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.dieyidezui.util.Constants.Mode;

public class DrawableHelper {
	private List<BitmapHelper> bitmapHelpers;
	private List<Bitmap> cacheBitmaps;
	private PaintManager paintManager;
	private int curPage, width, height, mode;
	public DrawableHelper(int width, int height){
		curPage = -1;
		bitmapHelpers = new ArrayList<BitmapHelper>();
		cacheBitmaps = new ArrayList<Bitmap>();
		paintManager = new PaintManager();
		this.width = width; this.height = height;
		mode = Mode.HAND;
		nextPage();
		
	}
	public boolean onTouchEvent(MotionEvent event){
		switch(mode){
		case Mode.ERASER:
		case Mode.PEN:
			return paintManager.onTouchEvent(event);
		case Mode.HAND:
			return bitmapHelpers.get(curPage).onTouchEvent(event);
		default:
			return false;
		}
	}
	public void onDraw(Canvas canvas){
		bitmapHelpers.get(curPage).onDraw(canvas);
		paintManager.onDraw(canvas);
	}
	public void nextPage(){
	//	Log.d(Constants.LOG, bitmapHelpers.size() + " " + curPage+1);
		if(++curPage == bitmapHelpers.size()){
		//	Log.d(Constants.LOG, bitmapHelpers.size() + " " + curPage);
			bitmapHelpers.add(new BitmapHelper());
			cacheBitmaps.add(Bitmap.createBitmap(width, height, Config.ARGB_4444));
		}
	//	Log.d(Constants.LOG, cacheBitmaps.get(curPage).toString());
		paintManager.setBitmap(cacheBitmaps.get(curPage));
	}
	public void prePage(){
		if(curPage > 0){
			paintManager.setBitmap(cacheBitmaps.get(--curPage));
		}
	}
	public void clearCurPage(){
		paintManager.clear();
		bitmapHelpers.get(curPage).clear();
	}
	public void setMode(int mode){
		this.mode = mode;
		switch(mode){
		case Mode.ERASER:
			paintManager.setEraserMode(); break;
		case Mode.PEN:
			paintManager.setColor(ColorManager.getColor());
			paintManager.setPenMode(); break;
		default:
			break;
		}
	}
	public void addBitmap(Bitmap bitmap) {
		bitmapHelpers.get(curPage).addBitmap(bitmap);
	}
}
