package com.dieyidezui.entity;

public class Vector {
	public float x,y;
	public Vector(float x, float y){
		this.x = x; this.y = y;
	}
	public Vector(){
		this(0f, 0f);
	}
	void add(Vector other){
		x += other.x;
		y += other.y;
	}
}
