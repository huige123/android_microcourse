package com.dieyidezui.util;

import com.dieyidezui.entity.Point;

public class PointerIDManager {
	static private PointerIDManager instance;
	private class PointerData{
		public int bitmapID;
		public int pointerIndex;
		public Point prePoint;
	}
	
	private PointerData pointers[];
	
	private PointerIDManager(){
		pointers = new PointerData[Constants.MAX_POINTER_NUM];
		for(int i = 0; i < pointers.length; i++){
			pointers[i] = new PointerData();
		}
	}
	static public PointerIDManager getInstance(){
		if(instance == null) instance = new PointerIDManager();
		return instance;
	}
	void insertPointer(int id, int bitmapID, int pointerIndex, Point p){
		 pointers[id].bitmapID = bitmapID;
		 pointers[id].pointerIndex = pointerIndex;
		 pointers[id].prePoint = p;
	}
	public void updatePointer(int id, Point p){
		pointers[id].prePoint = p;
	}
	public int getBitmapID(int id){
		return pointers[id].bitmapID;
	}
	public int getPointerIndex(int id){
		return pointers[id].pointerIndex;
	}
	public Point getPrePoint(int id){
		return pointers[id].prePoint;
	}
	public void remove(int id){
		int index = pointers[id].pointerIndex;
		for(int i = 0; i < pointers.length; i++){
			if(pointers[i].pointerIndex > index){
				pointers[i].pointerIndex--;
			}
		}
		pointers[id].bitmapID = Constants.INVALID_BITMAP_ID;
		pointers[id].pointerIndex = Constants.INVALID_POINTER_INDEX;
		pointers[id].prePoint = null;
	}
}
