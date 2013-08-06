package com.example.eldata;
 
import java.util.Calendar;

import com.example.eldata.rest.DatabaseCost;
import com.example.eldata.rest.DatabaseStatistics;
import com.example.eldata.rest.GraphTenDays;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.ActionBar.Tab;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
 
@SuppressLint("CutPasteId")
public class FragmentsStart extends Fragment implements ActionBar.TabListener, OnDateSetListener, OnClickListener {
 
    private Fragment mFragment;
    private static final String PREFS_NAME = "UserInfo";
    private ImageButton imageButton1;
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private ImageButton imageButton4;
    private ImageButton imageButton5;
    private  ImageButton imageButton6;
    private ImageButton imageButton7;
    private String used = "";
    private String used2 = "";
    private String bill = "";
    private String bill2 = "";
    private String dayleft = "";
    private String uppskattadslutsumma = "";
    private String snitt = "";
    private String elpris = "";
    private Popuphelp pophelp;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        // Get the view from fragment3.xml
        getActivity().setContentView(R.layout.fragmentstart);
        //   View view = getActivity().findViewById(R.id.relativelayout1);
		//   view.setOnTouchListener(this);	   
        pophelp = new Popuphelp(getActivity());
        createText();
        imageButton1 = (ImageButton) getActivity().findViewById(R.id.imageButton1);
        imageButton2 = (ImageButton) getActivity().findViewById(R.id.imageButton2);
        imageButton3 = (ImageButton) getActivity().findViewById(R.id.imageButton3);
        imageButton4 = (ImageButton) getActivity().findViewById(R.id.imageButton4);
        imageButton5 = (ImageButton) getActivity().findViewById(R.id.imageButton5);
        imageButton6 = (ImageButton) getActivity().findViewById(R.id.imageButton6);
        imageButton7 = (ImageButton) getActivity().findViewById(R.id.imageButton7);
   
        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        imageButton4.setOnClickListener(this);
        imageButton5.setOnClickListener(this);
        imageButton6.setOnClickListener(this);
        imageButton7.setOnClickListener(this);
        
        LinearLayout graphview;
        
        View view = getActivity().findViewById(R.id.linearLayout9);	   
      
        DatabaseStatistics stastistics = new DatabaseStatistics();
		   stastistics.setThisAllTime();
			
			  
		   GraphTenDays  graph = new GraphTenDays(getActivity());
		  graph.setTime(stastistics.getMedel(),stastistics.getMax() , stastistics.getMin(),stastistics.getAllMax());
		   graphview = (LinearLayout) getActivity().findViewById(R.id.linearLayout2);
		   graphview.addView(graph);
        
    }
    
    
    
	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		  case R.id.imageButton1:
			  pophelp.newPopup(R.string.imageButton1);
			  break;
		  case R.id.imageButton2:
			  pophelp.newPopup(R.string.imageButton2);
			  break;
		  case R.id.imageButton3:
			  pophelp.newPopup(R.string.imageButton3);
			  break;
		  case R.id.imageButton4:
			  pophelp.newPopup(R.string.imageButton4);
			  break;
		  case R.id.imageButton5:
			  pophelp.newPopup(R.string.imageButton5);
			  break;
		  case R.id.imageButton6:
			  pophelp.newPopup(R.string.imageButton6);
			  break;
		  case R.id.imageButton7:
			  pophelp.newPopup(R.string.imageButton7);
			  break;}
		  
	  
			  


		
	}
	
	
	

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

    	
    	DatabaseStatistics databaseStat = new DatabaseStatistics() ;
    	databaseStat.setThisSpecificTime(daysSinceLastBill);
    	DatabaseCost databaseCost = new DatabaseCost();
    	
    	//FIXA
    	databaseCost.setAddFast(4, 5);
    	
    	databaseCost.setThisSpecificTime(daysSinceLastBill);

    	
    	used = String.format("%.2f", databaseStat.getTotalSum()) + "kwh";
    	bill = String.format("%.2f", databaseCost.getMin()) + "kr";
    	dayleft = getDaysSinceLastBill() + " dagar";
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
		SharedPreferences.Editor editor = settings.edit();

		Calendar now = Calendar.getInstance();
		Calendar lastbill = Calendar.getInstance();
		int day = settings.getInt("billDateDay", 0);
		int year = settings.getInt("billDateYear", 0);
		int month = settings.getInt("billDateMonth", 0);

		while(true){
			int period = settings.getInt("PeriodNumber", 0);
			
			if(period == 0){
				month = month + 1;
				
				if(month > 11){
					month = month - 12;
					year = year + 1;
				}
				lastbill.set(year,
						month,
						day);
			
				}
			if(period == 1){	
				month = month+3;
				if(month > 11){
					month = month - 12;
					year = year + 1;
				}
				lastbill.set(year,
						month,
						day);
			
				}
			if(period == 2){
				year = year + 1;
						lastbill.set(year,
						month,
						day);
			
			}
			
			
			if(lastbill.before(now)){
				editor.putInt("billDateYear", year);
				editor.putInt("billDateMonth", month);
				editor.putInt("billDateDay", day);
				editor.commit();}
			else{
				break;}
			}
				lastbill.set(
					settings.getInt("billDateYear", 0),
					settings.getInt("billDateMonth", 0),
					settings.getInt("billDateDay", 0));
				int f = (int) ((now.getTime().getTime()
						-lastbill.getTime().getTime())/(1000*60*60*24));
		
		
				return f;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        mFragment = new FragmentsStart();
        // Attach fragment3.xml layout
        ft.add(android.R.id.content, mFragment);
        ft.attach(mFragment);
    }
 
    @Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        // Remove fragment3.xml layout
        ft.remove(mFragment);
    }
 
    @Override
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