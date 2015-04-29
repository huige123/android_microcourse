package com.dieyidezui.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Video.Thumbnails;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.dieyidezui.course.R;
import com.dieyidezui.util.Constants;

public class OnlineFragment extends Fragment {
	View rootView = null;
	GridView gridview = null;
	private Cursor mCursor;
	private int mDataColumnIndex;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.online_resource, container,false);
	//	setAdapter();
		initVideos();
		// TODO Auto-generated method stub
		return rootView;
	}
	private void initVideos() {
		//gridview = (GridView) rootView.findViewById(R.id.gview);
		try {
			final String orderBy = MediaStore.Video.Media.DATE_TAKEN;

			String[] proj = { MediaStore.Video.Media.DATA,
					MediaStore.Video.Media._ID };

			mCursor = getActivity().getContentResolver().query(
					MediaStore.Video.Media.EXTERNAL_CONTENT_URI, proj, null,
					null, orderBy + " DESC");
			setAdapter();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void setAdapter(){
		int count = mCursor.getCount();
		gridview = (GridView) rootView.findViewById(R.id.gview);
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		Log.d(Constants.LOG, ""+count);
		if (count > 0) {
			mDataColumnIndex = mCursor
					.getColumnIndex(MediaStore.Video.Media.DATA);
			mCursor.moveToFirst();
			
			for (int i = 0; i < count; i++) {
				mCursor.moveToPosition(i);
				String url = mCursor.getString(mDataColumnIndex);
				HashMap<String, Object> map = new HashMap<String, Object>(); 
				
				//Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(url, Thumbnails.MINI_KIND);
				map.put("item_image", R.drawable.abc_spinner_ab_default_holo_dark);
				map.put("item_text", url);
				lstImageItem.add(map);
			}
			 SimpleAdapter sa = new SimpleAdapter(getActivity(), 
                     lstImageItem,
                     R.layout.item,
                     new String[] {"item_image","item_text"},   
                     new int[] {R.id.item_image,R.id.item_text});
			gridview.setAdapter(sa);
		}
		gridview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d(Constants.LOG, "1");
				SimpleAdapter adapter = (SimpleAdapter) parent
						.getAdapter();
				Log.d(Constants.LOG, "1");
				String uri = (String)((HashMap) adapter
						.getItem(position)).get("item_text");
			//	HashMap map = (HashMap)parent.getItemAtPosition(position);
			//	String url = (String)map.get("text");
				Log.d(Constants.LOG, uri);
				 Intent intent = new Intent(Intent.ACTION_VIEW);
	             intent.setDataAndType(Uri.fromFile(new File(uri)), "video/*");
	             getActivity().startActivity(intent);
	             return false;
			}
		});
	}
}
