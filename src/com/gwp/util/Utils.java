package com.gwp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

public class Utils {
	public static int HEIGHT, WIDTH;
	public static Bitmap bitmapFromFile(File file, int width, int height) throws Exception{
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(file.getAbsolutePath(), opts);

		opts.inJustDecodeBounds = false;
		opts.inSampleSize = Math.max(getSuitableSize(width, opts.outWidth), getSuitableSize(height, opts.outHeight));

		opts.inPreferredConfig = Bitmap.Config.ARGB_4444;
		Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
		
	//	Log.d(Constants.LOG, ""+BitmapFactory.decodeStream(is, null, opts).getByteCount());
		return BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
	}
	private static int getSuitableSize(int suit, int cur) {
		int ret = 1;
		while(suit * ret < cur) ret <<= 1; 
		return ret;
	}
	public static Bitmap bitmapFromStream(Context context, InputStream is, int width, int height) throws Exception{
		/*
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(is, null, opts);
		
		Log.d(Constants.LOG, opts.outHeight + " " + opts.outWidth);
		
		int scalaH= (opts.outHeight-1) / height + 1;
		int scalaW = (opts.outWidth-1) / width + 1;
		opts.inSampleSize = (scalaH > scalaW ? scalaH : scalaW);

		opts.inJustDecodeBounds = false;
		opts.inPreferredConfig = Bitmap.Config.ARGB_4444;
		Bitmap b = BitmapFactory.decodeStream(is, null, opts);
		if(b == null) Log.d(Constants.LOG, "er");*/
		
		return bitmapFromFile(createTempFile(context,is), width, height);
	}
	private static File createTempFile(Context context, InputStream is) throws Exception {
		File file = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
		Log.d(Constants.LOG, file.getAbsolutePath());
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
}
