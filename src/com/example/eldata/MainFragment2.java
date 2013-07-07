package com.example.eldata;
 
import java.util.Calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
 
public class MainFragment2 extends Fragment implements ActionBar.TabListener ,OnClickListener {
 
    private Fragment mFragment;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        // Get the view from fragment3.xml
        getActivity().setContentView(R.layout.fragmenthandler);
     //   View view = getActivity().findViewById(R.id.relativelayout1);
		//   view.setOnTouchListener(this);
        
        TextView buttonSwitch = (TextView) getActivity().findViewById( R.id.switch1);
		 buttonSwitch.setOnClickListener(this);
        
		   
    }
 
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        mFragment = new FragmentsTab2();
        // Attach fragment3.xml layout
        ft.add(android.R.id.content, mFragment);
        ft.attach(mFragment);
    }
 
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        // Remove fragment3.xml layout
        ft.remove(mFragment);
    }
 
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
 
    }
	int startX = 0;
	int startY = 0;
	int endX = 0;
	int endY = 0;

	@Override
	public void onClick(View v) {
		long ett = 3600 * 24;
		long två = 31*1000;
		long tre = 365 * 1000;
		
		 Calendar startcalendar = Calendar.getInstance();
		 long currentTime = startcalendar.getTime().getTime();
		 
		  switch(v.getId()){
		  case R.id.switch1:
			 
			  	
			  break;}



}}