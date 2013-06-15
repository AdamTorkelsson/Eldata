package com.example.eldata;
 
import java.util.ArrayList;

import com.example.eldata.rest.Database;
import com.example.eldata.rest.DatabasePrice;
import com.example.eldata.rest.DatabaseStatistics;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
 
public class FragmentsTab2 extends Fragment implements ActionBar.TabListener,OnTouchListener {
 
    private static final String PREFS_NAME = "UserInfo";
	private Fragment mFragment;
	String fast;
	String add;
	Context context;
	Database database;
	ArrayList<Float> databasearray = new ArrayList<Float>();
	ArrayList<Float> pricesarray = new ArrayList<Float>();
	DatabasePrice prices = new DatabasePrice();
	DatabaseStatistics ds; 
  
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from fragment2.xml
    	database = new Database();
 		ds = new DatabaseStatistics();
        getActivity().setContentView(R.layout.comparevariableaview);
        View view = getActivity().findViewById(R.id.relativelayout1);
		   view.setOnTouchListener(this);
        databasearray = database.getAll();
     	pricesarray = prices.getAll();
     
     	setTextViews(); 
     	
     	
    }
 
	
	
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        mFragment = new FragmentsTab2();
        // Attach fragment2.xml layout
        ft.add(android.R.id.content, mFragment);
        ft.attach(mFragment);
        
    }

	private void setTextViews(){
		Log.d("Hej", "SetTextViews1");
		SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
		Log.d("Hej", "SetTextViews2" + settings.getString("Add", "Error"));
		float variablecost = ds.getVariableCost(Float.parseFloat(settings.getString("Add", "Error")));
		Log.d("Hej", "SetTextViews3");
		float fastcost = Float.parseFloat(settings.getString("FastPris", "Error"));
		Log.d("Hej", "SetTextViews4");
		float saved = fastcost -variablecost;
		
		TextView textFastPris= (TextView) getActivity().findViewById(R.id.textFastPris);
		textFastPris.setText(" "  + fastcost + " Kronor");
		
		TextView textVariablePris= (TextView) getActivity().findViewById(R.id.textVariablePris);
		textVariablePris.setText("" + variablecost + " Kronor");
		
		TextView textDifferencePris = (TextView) getActivity().findViewById(R.id.textDifferencePris);
		textDifferencePris.setText("" + saved + " Kronor");

	}
    
    
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        // Remove fragment2.xml layout
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
	public boolean onTouch(View v, MotionEvent event) {
	
		
		switch (event.getAction()) { 
        case MotionEvent.ACTION_DOWN: 
        	
           startX = (int)event.getX(); 
           startY = (int)event.getY(); 
  
           return true; 
        case MotionEvent.ACTION_MOVE: 
           endX = (int)event.getX(); 
           endY = (int)event.getY(); 

            if((endX - startX) >70) { 
            
            	Tab tab = getActivity().getActionBar().getTabAt(0);
            	tab.select();	
           //right 
           } 
           if((endX - startX) < -70) { 
        	   Log.d("hej", "touchlistLeft");
        	   Tab tab = getActivity().getActionBar().getTabAt(2);
        	   tab.select();	
        	    
           } 
           startX = (int)event.getX(); 
           startY = (int)event.getY(); 

           return true; 
        case MotionEvent.ACTION_UP: 
           endX = (int)event.getX(); 
           endY = (int)event.getY(); 
           return true; 
        } 
		return true;
	}


 
}