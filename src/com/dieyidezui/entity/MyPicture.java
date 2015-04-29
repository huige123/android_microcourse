package com.dieyidezui.entity;

import android.graphics.Bitmap;
public class MyPicture {
	public Point leftTopPoint;
	public Bitmap bitmap;
	public MyPicture(Point p, Bitmap bitmap){
		leftTopPoint = p; 
		this.bitmap = bitmap;
	}
	public MyPicture(float x, float y, Bitmap bitmap){
		this(new Point(x, y), bitmap);
	}
	public boolean contains(Point p){
		return p.x >= leftTopPoint.x && p.x <= leftTopPoint.x + bitmap.getWidth() && 
			   p.y >= leftTopPoint.y && p.y <= leftTopPoint.y + bitmap.getHeight();
	}
	public void move(Vector v) {
		leftTopPoint.add(v);
	}
}
