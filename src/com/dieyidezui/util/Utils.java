
package com.dieyidezui.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.dieyidezui.entity.Point;
import com.dieyidezui.entity.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class Utils {
	public static int HEIGHT, WIDTH;
	public static Bitmap bitmapFromFile(File file, int width, int height) throws Exception{
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(file.getAbsolutePath(), opts);

		opts.inJustDecodeBounds = false;
		opts.inSampleSize = Math.max(getSuitableSize(width, opts.outWidth), getSuitableSize(height, opts.outHeight));
		opts.inPreferredConfig = Bitmap.Config.ARGB_4444;
		
		return BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
	}
	private static int getSuitableSize(int suit, int cur) {
		int ret = 1;
		while(suit * ret < cur) ret <<= 1; 
		return ret;
	}
	/**
	 * 根据输入流创建一个位图并压缩成合适的宽与高
	 * @param context 上下文
	 * @param is 输入流
	 * @param width 期望的宽
	 * @param height 期望的高
	 * @return 位图
	 * @throws Exception
	 */
	public static Bitmap bitmapFromStream(Context context, InputStream is, int width, int height) throws Exception{
		return bitmapFromFile(createTempFile(context,is), width, height);
	}
	private static File createTempFile(Context context, InputStream is) throws Exception {
		File file = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
	//	Log.d(Constants.LOG, file.getAbsolutePath());
		FileOutputStream fs = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int len = -1;
		while((len = is.read(buffer)) >= 0){
			fs.write(buffer, 0, len);
		}
		is.close();	
		fs.close();
		return file;
	}
	/*private static byte[] streamToByte(InputStream is) throws Exception{
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while((len = is.read(buffer)) >= 0){
			bs.write(buffer, 0, len);
		}
		is.close(); bs.close();
		return bs.toByteArray();     
	}*/
	static public void showToast(Context context, String text){
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
	static public double getDistance(Point a, Point b){
		return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
	}
}
