package com.dieyidezui.util;

import android.graphics.Color;

public class ColorManager {
	private int[] colors;
	private int cur;
	public ColorManager() {
		 colors = new int[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,Color.BLACK};
		 cur = 0;
	}
	public void nextColor(){
		cur = (cur + 1) % colors.length;
	}
	public int getColor(){
		return colors[cur];
	}
}
