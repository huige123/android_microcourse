package com.dieyidezui.entity;

public class Point {
	public float x, y;
	public Point(float x, float y){
		this.x = x; this.y = y;
	}
	public Point(){
		this(0f, 0f);
	}
	public Vector minus(Point from){
		return new Vector(x - from.x, y - from.y);
	}
	public void add(Vector v) {
		x += v.x; y += v.y;
	}
	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}
	
}
