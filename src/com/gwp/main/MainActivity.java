package com.gwp.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.gwp.course.R;


public class MainActivity extends FragmentActivity {
	RadioGroup myTab = null;
	Fragment[] frags = new Fragment[]{new CourseFragment(), new OnlineFragment(), new OneselfFragment()};
	int[] ids = new int[]{R.id.my_course, R.id.online_resource, R.id.oneself};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	public void initView() {
	    getSupportFragmentManager().beginTransaction().
	    	add(R.id.main_content, frags[0]).commit();
	    myTab = (RadioGroup) findViewById(R.id.tab_menu);
	    myTab.setOnCheckedChangeListener(new OnCheckedChangeListener(){
	    	@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedID) {
				for(int i = 0; i < ids.length; i++) if(checkedID == ids[i]){
	                getSupportFragmentManager().beginTransaction().
	                replace(R.id.main_content, frags[i]).commit();
	                break;
				}
			}
	    	
	    });
	}
}
