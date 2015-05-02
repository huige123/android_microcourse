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

public class BitmapHelper {
	List<MyPicture> list;
	int lock;
	float preX, preY;
	Random r;
	public BitmapHelper(){
		list = new ArrayList<MyPicture>();
		r = new Random();
		lock = -1;
	}
	public void addBitmap(Bitmap bitmap){
		list.add(new MyPicture(r.nextFloat() * 300, r.nextFloat() * 300,bitmap));
	}
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return true;
	}
	public void touchDown(float x, float y){
		for(int i = list.size() - 1; i >= 0; i--){
			if(list.get(i).contains(new Point(x, y))){
				lock = i;
				break;
			}
		}
		preX = x; preY = y;
	}
	public void touchMove(float x, float y){
		if(lock >= 0){
			list.get(lock).move(new Point(x, y).minus(new Point(preX, preY)));
			preX = x; preY = y;
		}
	}
	public void touchUp(){
		lock = -1;
	}
	public void onDraw(Canvas canvas){
		for(int i = 0; i < list.size(); i++){
			MyPicture pic = list.get(i);
			canvas.drawBitmap(pic.bitmap, pic.leftTopPoint.x, pic.leftTopPoint.y, null);
		}
	}
	public void clear() {
		list.clear();
		lock = -1;
	}

}
