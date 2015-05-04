package com.dieyidezui.util;

import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;

public class Constants {
	//tag
	public static String LOG = "microcourse";
	//record
	public static final int CAMERA_CODE = 0;
	public static final int PICTURE_CODE = 1;
	//drawview
	public class Mode{
		public static final int PEN = 0;
		public static final int ERASER = 1;
		public static final int HAND = 2;
	}
	public static final Xfermode DEFAULT_XFERMODE = new Paint().getXfermode();
	public static final Xfermode CLEAR_XFERMODE = new PorterDuffXfermode(android.graphics.PorterDuff.Mode.CLEAR);
	public static final int ALPAL = 240;
	public static final int PEN_STROKE_WIDTH = 5;
	public static final int ERASER_STROKE_WIDTH = 60;
	//pointer
	public static final int MAX_POINTER_NUM = 15;
	public static final int INVALID_BITMAP_ID = -1;
	public static final int INVALID_POINTER_INDEX = -1;
}
