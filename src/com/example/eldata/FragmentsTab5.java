package com.example.eldata;
 
import java.util.Calendar;

import com.example.eldata.rest.DatabaseCost;
import com.example.eldata.rest.DatabaseStatistics;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.ActionBar.Tab;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
 
@SuppressLint("CutPasteId")
public class FragmentsTab5 extends Fragment implements ActionBar.TabListener, OnDateSetListener {
 
    private Fragment mFragment;
    private static final String PREFS_NAME = "UserInfo";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        // Get the view from fragment3.xml
        getActivity().setContentView(R.layout.fragment5);
        //   View view = getActivity().findViewById(R.id.relativelayout1);
		//   view.setOnTouchListener(this);	   
        
        createText();
        
        
        
    }
    String used = "";
    String used2 = "";
    String bill = "";
    String bill2 = "";
    String dayleft = "";
    String uppskattadslutsumma = "";
    String snitt = "";
    String elpris = "";
    
    private void createText() {
    	//FIXA
    	TextView textUse = (TextView) getActivity().findViewById(R.id.textUse);
        TextView textUse2 = (TextView) getActivity().findViewById(R.id.textUse2);
        TextView textBill = (TextView) getActivity().findViewById(R.id.textBill);
        TextView textBill2 = (TextView) getActivity().findViewById(R.id.textBill2);
        TextView textDayLeft = (TextView) getActivity().findViewById(R.id.textDayLeft);
        TextView textUppskattadSlutsumma = (TextView) getActivity().findViewById(R.id.textUppskattadSlutsumma);
        TextView textSnitt = (TextView) getActivity().findViewById(R.id.textSnitt);
        TextView textElpris = (TextView) getActivity().findViewById(R.id.textCurrentElpris);
        
        
        
    	int daysSinceLastBill = getDaysSinceLastBill();
    	int daysToNextBill = daysSinceLastBill - 21;
    	
    	DatabaseStatistics databaseStat = new DatabaseStatistics() ;
    	databaseStat.setThisSpecificTime(daysSinceLastBill);
    	DatabaseCost databaseCost = new DatabaseCost();
    	
    	//FIXA
    	databaseCost.setAddFast(4, 5);
    	
    	databaseCost.setThisSpecificTime(daysSinceLastBill);

    	
    	used = String.format("%.2f", databaseStat.getTotalSum()) + "kwh";
    	bill = String.format("%.2f", databaseCost.getMin()) + "kr";
    	dayleft = daysToNextBill + "";
    	uppskattadslutsumma = "inte fixad";
    	snitt = "inte fixad";
    	elpris = "inte fixad";
    	
    	if(databaseStat.getTotalSum() - 40 > 0){
    		textUse2.setTextColor(Color.GREEN);
    		used2 = "( +" + String.format("%.0f", (databaseStat.getTotalSum() - 40)) + ")";
    	}
    	else{
    		textUse2.setTextColor(Color.RED);
    		used2 = "( " + String.format("%.0f", (databaseStat.getTotalSum() - 40)) + ")";
    	}
    	
    	if(databaseCost.getMin() - 240 > 0){
    		textBill2.setTextColor(Color.GREEN);
    		bill2 = "( +" + String.format("%.0f", (databaseCost.getMin() - 240)) + ")";
    	}
    	else{
    		textBill2.setTextColor(Color.RED);
    		bill2 = "( " + String.format("%.0f", (databaseCost.getMin() - 240)) + ")";
    		
    	}
    	
    	
    	
    	
    	  textUse.setText(used);
          textUse2.setText(used2);
          textBill.setText(bill);
          textBill2.setText(bill2);
          textDayLeft.setText(dayleft);
          textUppskattadSlutsumma.setText(uppskattadslutsumma);
          textSnitt.setText(snitt);
          textElpris.setText(elpris);
	}

    //Implement Joda time and fix this.
	private int getDaysSinceLastBill() {
		SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

		Calendar bill = Calendar.getInstance();
		bill.set(
				settings.getInt("billDateYear", 0),
				settings.getInt("billDateMonth", 0),
				settings.getInt("billDateDay", 0));
		
		
		int type = settings.getInt("PeriodNumber", 0);
	
		bill.set(
				settings.getInt("billDateYear", 0),
				settings.getInt("billDateMonth", 0),
				settings.getInt("billDateDay", 0));
		int f = (int) ((Calendar.getInstance().getTime().getTime()-bill.getTime().getTime())/(1000*60*60*24));
		
		
	return f;
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        mFragment = new FragmentsTab5();
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
/*
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

            if((endX - startX) > 80) { 
            
            	Tab tab = getActivity().getActionBar().getTabAt(3);
            	tab.select();	
           //right 
           } 
           if((endX - startX) < -80) { 
        	   Tab tab = getActivity().getActionBar().getTabAt(1);
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
*/

	@Override
	public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	


}