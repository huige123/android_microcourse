package com.dieyidezui.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dieyidezui.entity.MyPicture;
import com.dieyidezui.entity.Point;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

public class BitmapHelper{
	List<MyPicture> list;
	Random r;
	PointerIDManager pointerIDManager;
	int lock;
	public BitmapHelper(){
		list = new ArrayList<MyPicture>();
		r = new Random(System.currentTimeMillis());
		pointerIDManager = PointerIDManager.getInstance();
		lock = 0;
	}
	public void addBitmap(Bitmap bitmap){
		list.add(new MyPicture(r.nextFloat() * 300, r.nextFloat() * 300,bitmap));
	}
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction() & MotionEvent.ACTION_MASK){
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
			return onDown(event);
		case MotionEvent.ACTION_MOVE:
			return onMove(event); 
		case MotionEvent.ACTION_POINTER_UP:
		case MotionEvent.ACTION_UP:
			return onUp(event);
		}
		return false;
	}
	
	private boolean onDown(MotionEvent event){
		int index = event.getAction() >>> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
		int id = event.getPointerId(index);
		
		for(int i = list.size() - 1; i >= 0; i--){
			MyPicture pic = list.get(i);
			if(pic.contains(new Point(event.getX(index), event.getY(index)))){
				if(pic.addPointer(id)){
					lock++;
					pointerIDManager.insertPointer(id, i, index, 
							new Point(event.getX(index), event.getY(index)));
					return false;
				}
				break;
			}
		}
		pointerIDManager.insertPointer(id, Constants.INVALID_BITMAP_ID, 
				index, null);
		return false;
	}
	private boolean onMove(MotionEvent event){
		if(lock <= 0) return false;
		for(int i = 0; i < list.size(); i++){
			list.get(i).dealMoveEvent(event);
		}
		return true;
	}
	private boolean onUp(MotionEvent event){
		int index = event.getAction() >>> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
		int id = event.getPointerId(index);
		int bitmapID = pointerIDManager.getBitmapID(id);
	//	Log.e(Constants.LOG, bitmapID+" " + id);
		if(bitmapID != Constants.INVALID_BITMAP_ID){
			list.get(bitmapID).remove(id);
			pointerIDManager.remove(id);
			lock--;
		}
		return false;
	}
	public void onDraw(Canvas canvas){
		for(int i = 0; i < list.size(); i++){
			list.get(i).onDraw(canvas);
		}
	}
	public void clear(){
		list.clear();
	}

}
