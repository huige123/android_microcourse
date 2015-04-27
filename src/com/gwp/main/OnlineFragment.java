package com.gwp.main;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.gwp.course.R;

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
	

	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.online_resource, container,false);
	//	setAdapter();//initVideos();
		// TODO Auto-generated method stub
		return rootView;
	}*/
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
	//	int count = mCursor.getCount();
		gridview = (GridView) rootView.findViewById(R.id.gview);
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		
//		if (count > 0) {
//			mDataColumnIndex = mCursor
//					.getColumnIndex(MediaStore.Video.Media.DATA);
//			mCursor.moveToFirst();
//			
//			for (int i = 0; i < count; i++) {
//				mCursor.moveToPosition(i);
//				String url = mCursor.getString(mDataColumnIndex);
//				HashMap<String, Object> map = new HashMap<String, Object>(); 
//				
//				//Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(url, Thumbnails.MINI_KIND);
//				map.put("item_image", R.id.icon);
//				map.put("item_text", url);
//			}
//			 SimpleAdapter sa = new SimpleAdapter(getActivity(), 
//                     lstImageItem,
//                     R.layout.item,
//                     new String[] {"item_image","item_text"},   
//                     new int[] {R.id.item_image,R.id.item_text});
//			gridview.setAdapter(sa);
//		}else
		
			for (int i = 0; i < 5; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>(); 
				
				//Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(url, Thumbnails.MINI_KIND);
				map.put("image", R.id.icon);
				map.put("text", "test"+i);
			}
			 SimpleAdapter sa = new SimpleAdapter(getActivity(), 
                     lstImageItem,	
                     R.layout.item,
                     new String[] {"image","text"},   
                     new int[] {R.id.image,R.id.text});
			gridview.setAdapter(sa);
		
		gridview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// update the mStatus of each category in the adapter
			//	SimpleAdapter adapter = (SimpleAdapter) parent.getAdapter();
				HashMap map = (HashMap)parent.getItemAtPosition(position);
				String url = (String)map.get("text");
				 Intent intent = new Intent(Intent.ACTION_VIEW);
	             intent.setDataAndType(Uri.parse(url), "video/*");
	             startActivity(intent);
			}
		});
	}
}
