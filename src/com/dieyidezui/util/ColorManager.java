package com.dieyidezui.util;

import android.graphics.Color;

public class ColorManager {
	static private int[] colors = new int[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,Color.BLACK};;
	static private int cur = 0;
	private ColorManager() {}
	public static void nextColor(){
		cur = (cur + 1) % colors.length;
	}
	public static int getColor(){
		return colors[cur];
	}
}
