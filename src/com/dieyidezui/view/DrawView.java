package com.dieyidezui.view;

import com.dieyidezui.util.BitmapHelper;
import com.dieyidezui.util.Constants.Mode;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
	
	Bitmap cacheBitmap;
	Canvas cacheCanvas;
	Context context;
	BitmapHelper bitmapHelper;
	int mode;

	boolean first = true;

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;


		bitmapHelper = new BitmapHelper();


	}
	
	

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		if(first){
			first = false;
			
		}
		super.onLayout(changed, left, top, right, bottom);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		bitmapHelper.onDraw(canvas);
		canvas.drawBitmap(cacheBitmap, 0, 0, null);//
//		Toast.makeText(getContext(), t1 + " " + t2 + " " + t3 + " " + t4, 0).show();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		invalidate();
		return true;
	}
	private void touchEventDraw(MotionEvent event) {

		invalidate();
	}
	
	private void touchEventDragScala(MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			bitmapHelper.touchDown(event.getX(), event.getY());
			break;
		case MotionEvent.ACTION_MOVE:
			bitmapHelper.touchMove(event.getX(), event.getY());
			break;
		case MotionEvent.ACTION_UP:
			bitmapHelper.touchUp();
			break;
		}
		invalidate();
	}
	public void clearBoard(){
		cacheCanvas.drawColor(Color.TRANSPARENT);
	}
	public int getMode(){
		return mode;
	}
	public void changeColor(){

	}
	public void changeMode(int mode){
		this.mode = mode;
		if(mode == Mode.ERASER){
		}else if(mode == Mode.PEN){
		}
	}
	public void addBitmap(Bitmap bitmap) {	
		bitmapHelper.addBitmap(bitmap);
		invalidate();
	}

	
}
