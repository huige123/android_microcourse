package com.dieyidezui.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.dieyidezui.course.R;

public class CourseFragment extends Fragment {
	View rootView = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.course, null);
		initView();
		// TODO Auto-generated method stub
		return rootView;
	}
	void initView(){
		Button record_course = (Button)rootView.findViewById(R.id.record_course);
		record_course.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {	
				Intent intent = new Intent(getActivity(), RecordActivity.class);
				getActivity().startActivity(intent);
			}
			
		});
	}
}
