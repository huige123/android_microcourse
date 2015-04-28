package com.gwp.view;

import java.util.ArrayList;
import java.util.List;

import com.gwp.util.BitmapHelper;
import com.gwp.util.Constants;
import com.gwp.util.Constants.Mode;










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
import android.widget.Toast;

public class DrawView extends View {
	
	int alpal = 200;
	float preX, preY;
	Bitmap cacheBitmap;
	Canvas cacheCanvas;
	int WIDTH,HEIGHT;
	Context context;
	BitmapHelper bitmapHelper;
	int mode;
	Paint linePaint;
	Xfermode xfermode;
	int []colors = new int[]{Color.RED,Color.BLUE,Color.BLACK};
	int cur = 0;
	boolean first = true;
	Handler mhandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			cacheBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
			cacheCanvas = new Canvas(); 
			cacheCanvas.setBitmap(cacheBitmap);
		//	invalidate();
		}
		
	};
	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		mode = Mode.PEN;
		linePaint = new Paint();
		bitmapHelper = new BitmapHelper();
		penMode();
		xfermode = linePaint.getXfermode();

//		mhandler.sendEmptyMessageDelayed(1, 1000);
		//大问题！
	//	clearBoard();
	//	cacheCanvas.drawText(""+getWidth()+" "+getHeight(), 0, 0, linePaint);
	}
	
	
	private void penMode(){
		linePaint.setStyle(Paint.Style.STROKE);
		linePaint.setColor(colors[cur]);
		linePaint.setAlpha(alpal);
		linePaint.setStrokeWidth(5);
		linePaint.setXfermode(xfermode);
	}
	private void eraserMode(){
		linePaint.setColor(Color.TRANSPARENT);
		linePaint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.CLEAR));
		linePaint.setStrokeWidth(50);
	}
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		if(first){
			first = false;
			cacheBitmap = Bitmap.createBitmap(right - left, bottom - top, Config.ARGB_8888);
			cacheCanvas = new Canvas(); 
			cacheCanvas.setBitmap(cacheBitmap);
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
		switch(mode){
		case Mode.ERASER:
		case Mode.PEN:
			touchEventDraw(event);
			break;
		case Mode.HAND:
			touchEventDragScala(event);
			break;
		}
		invalidate();
		return true;
	}
	private void touchEventDraw(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			preX = x; preY = y; break;
		case MotionEvent.ACTION_MOVE:
			cacheCanvas.drawLine(x, y, preX, preY, linePaint);
			preX = x; preY = y; break;
		case MotionEvent.ACTION_UP:
			break;
		}
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
		cur = (cur + 1) % colors.length;
		linePaint.setColor(colors[cur]);
		linePaint.setAlpha(alpal);
	}
	public void changeMode(int mode){
		this.mode = mode;
		if(mode == Mode.ERASER){
			eraserMode();
		}else if(mode == Mode.PEN){
			penMode();
		}
	}
	public void addBitmap(Bitmap bitmap) {	
		bitmapHelper.addBitmap(bitmap);
		invalidate();
	}

	
}
