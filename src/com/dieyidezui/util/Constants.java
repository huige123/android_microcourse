package com.dieyidezui.util;

import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;

public class Constants {
	//TAG
	static public String LOG = "microcourse";
	//record
	static public final int CAMERA_CODE = 0;
	static public final int PICTURE_CODE = 1;
	//drawview
	public class Mode{
		static public final int PEN = 0;
		static public final int ERASER = 1;
		static public final int HAND = 2;
	}
	static public final Xfermode DEFAULT_XFERMODE = new Paint().getXfermode();
	static public final Xfermode CLEAR_XFERMODE = new PorterDuffXfermode(android.graphics.PorterDuff.Mode.CLEAR);
	static public final int ALPAL = 240;
	static public final int PEN_STROKE_WIDTH = 5;
	static public final int ERASER_STROKE_WIDTH = 60;
}
