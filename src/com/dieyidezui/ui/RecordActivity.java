package com.dieyidezui.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dieyidezui.util.Constants;
import com.dieyidezui.util.Utils;
import com.dieyidezui.util.Constants.Mode;
import com.dieyidezui.view.DrawView;
import com.dieyidezui.course.R;

public class RecordActivity extends Activity implements OnClickListener{
	DrawView drawview;
	ImageButton pen, eraser, camera, picture, hand;
	private File file;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,  
				~0);//设置成全屏常亮模式  
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏
		requestWindowFeature(Window.FEATURE_NO_TITLE);  //去标题
		setContentView(R.layout.activity_record);
		drawview = (DrawView)findViewById(R.id.draw_view);
		pen = (ImageButton)findViewById(R.id.record_pen);
		eraser = (ImageButton)findViewById(R.id.record_eraser);
		camera = (ImageButton)findViewById(R.id.record_camera);
		picture = (ImageButton)findViewById(R.id.record_picture);
		hand = (ImageButton)findViewById(R.id.record_hand);
		pen.setOnClickListener(this);
		eraser.setOnClickListener(this);
		camera.setOnClickListener(this);
		picture.setOnClickListener(this);
		hand.setOnClickListener(this);
		
		
	}
	@Override
	public void onClick(View view) {
		switch(view.getId()){
		case R.id.record_eraser:
			drawview.setMode(Mode.ERASER);
			break;
		case R.id.record_pen:
			drawview.setMode(Mode.PEN);
			break;
		case R.id.record_camera:
			callCamera();
			break;
		case R.id.record_picture:
			callPicture();
			break;
		case R.id.record_hand:
			drawview.setMode(Mode.HAND);
			break;
		}
	}
	
	private void callPicture() {
	//	Intent intent = new Intent(Intent.ACTION_GET_CONTENT); 
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);  
        startActivityForResult(intent, Constants.PICTURE_CODE);
	}
	private void callCamera(){		 
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//调用android自带的照相机 
	//	intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);  
	//	intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(intent, Constants.CAMERA_CODE); 
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
		case Constants.CAMERA_CODE:
			if(resultCode == Activity.RESULT_OK){
				//drawview.addBitmap((Bitmap)data.getExtras().get("data"));
			}
		case Constants.PICTURE_CODE:
			if(resultCode == Activity.RESULT_OK){
				try {
					ContentResolver cr = getContentResolver();
					drawview.addBitmap(Utils.bitmapFromStream(this,
							cr.openInputStream(data.getData()), drawview.getWidth(), drawview.getHeight()));
				} catch (Exception e) {
					Log.d(Constants.LOG, e.getMessage().toString());
				}
			}
		}
	}
}