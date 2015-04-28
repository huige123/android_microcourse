package com.gwp.util;

import android.graphics.Bitmap;
public class MyPicture {
	public float x, y;
	public Bitmap bitmap;
	public MyPicture(float x, float y, Bitmap bitmap){
		this.x = x; this.y = y; 
		this.bitmap = bitmap;
	}
	public MyPicture(Bitmap bitmap){
		this(100, 100, bitmap);
	}
	boolean contains(float x, float y){
		return x >= this.x && x <= this.x + bitmap.getWidth() && y >= this.y && y <= this.y + bitmap.getHeight();
	}
	public void move(float dx, float dy) {
		this.x += dx; this.y += dy;
	}
}
