package com.dieyidezui.entity;

public class Point {
	public float x, y;
	public Point(float x, float y){
		this.x = x; this.y = y;
	}
	public Point(){
		this(0f, 0f);
	}
	Vector minus(Point from){
		return new Vector(x - from.x, y - from.y);
	}
	public void add(Vector v) {
		x += v.x; y += v.y;
	}
}
