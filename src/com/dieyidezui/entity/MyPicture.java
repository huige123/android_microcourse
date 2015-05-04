package com.dieyidezui.entity;

import com.dieyidezui.util.Constants;
import com.dieyidezui.util.PointerIDManager;
import com.dieyidezui.util.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
public class MyPicture {
	private Bitmap bitmap;
	private int count;
	private int pointerID[];
	private Matrix matrix;
	private Vector vector;
	private float scale;
	public MyPicture(Vector vector, Bitmap bitmap){
		this.bitmap = bitmap;
		this.vector = vector;
		matrix = new Matrix();
		scale = (float) 1.0;
		count = 0;
		pointerID = new int[2];
		resetMatrix();
	}
	private void resetMatrix(){
		matrix.reset(); 
		matrix.postScale(scale, scale);
		matrix.postTranslate(vector.x, vector.y);
	}
	public MyPicture(float x, float y, Bitmap bitmap){
		this(new Vector(x, y), bitmap);
	}
	public boolean contains(Point p){
		return p.x >= vector.x && p.x <= vector.x + bitmap.getWidth()*scale && 
			   p.y >= vector.y && p.y <= vector.y + bitmap.getHeight()*scale;
	}
	public void onDraw(Canvas canvas){
		canvas.drawBitmap(bitmap, matrix, null);
	}
	public void dealMoveEvent(MotionEvent event){
		PointerIDManager instance = PointerIDManager.getInstance();
		if(count == 1){//drag
			int index = instance.getPointerIndex(pointerID[0]);
			Point prePoint = instance.getPrePoint(pointerID[0]);
			Point curPoint = new Point(event.getX(index), event.getY(index));
			instance.updatePointer(pointerID[0], curPoint);
			vector.add(curPoint.minus(prePoint));
			resetMatrix();
		}else if(count == 2){//scale
			Point p0 = instance.getPrePoint(pointerID[0]);
			Point p1 = instance.getPrePoint(pointerID[1]);
			int index0 = instance.getPointerIndex(pointerID[0]);
			int index1 = instance.getPointerIndex(pointerID[1]);
			
			double dist0 = Utils.getDistance(p0, p1);
			p0 = new Point(event.getX(index0), event.getY(index0));
			p1 = new Point(event.getX(index1), event.getY(index1));
			double dist1 = Utils.getDistance(p0, p1);
			float rate =  (float)(dist1 / dist0);
			instance.updatePointer(pointerID[0], p0);
			instance.updatePointer(pointerID[1], p1);
			float diffW = (float)(bitmap.getWidth() * (1.0 - rate) * scale / 2);
			float diffH = (float)(bitmap.getHeight() * (1.0 - rate) * scale / 2);
			vector.add(new Vector(diffW, diffH));
			scale *= rate;
			resetMatrix();
			/*Log.d(Constants.LOG,"" + dist0 + " " + dist1+
					" "+bitmap.getWidth()+" "+bitmap.getHeight()); */
			/*bitmap = Bitmap.createScaledBitmap(
					originBitmap, 
					(int)(bitmap.getWidth() * rate), 
					(int)(bitmap.getHeight() * rate), 
					false);
			double diffW = bitmap.getWidth() * (1.0 - rate) / 2;
			double diffH = bitmap.getHeight() * (1.0 - rate) / 2;*/
 			
		}
	}
	public boolean addPointer(int id){
		if(count >= pointerID.length) return false;
		pointerID[count++] = id;
		return true;
	}
	public void remove(int id) {
		if(count == 2 && id == pointerID[0]){
			pointerID[0] = pointerID[1]; 
		}
		count--;
	}
}
