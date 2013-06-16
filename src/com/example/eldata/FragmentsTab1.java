package com.example.eldata;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import com.example.eldata.RangeSeekBar.OnRangeSeekBarChangeListener;
import com.example.eldata.rest.Database;
import com.example.eldata.rest.DatabasePrice;
import com.example.eldata.rest.DatabaseStatistics;






import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
 
public class FragmentsTab1 extends Fragment implements ActionBar.TabListener ,OnClickListener, OnDateSetListener,OnTouchListener  {
 
    private Fragment mFragment;
	Database database;
	DatabasePrice prices = new DatabasePrice();
	ArrayList<Float> thismonth = new ArrayList<Float>();
	ArrayList<Float> thismonthprice = new ArrayList<Float>();
	DatabaseStatistics stastistics;
	int totalbill;
    TextView textStartDate;
	TextView textEndDate;
	DatePickerFragment newDateFragment;
	Boolean typeOfDate;
	Calendar startcalendar  = Calendar.getInstance();
	Calendar endcalendar  = Calendar.getInstance();
	boolean isStartDate;
	DatePickerFragment startdate;
	DatePickerFragment enddate;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    	//Sets the animation when opening this activity
        startcalendar  = Calendar.getInstance();
    	endcalendar  = Calendar.getInstance();
        
        // Get the view from fragment1.xml
        getActivity().setContentView(R.layout.compareview);
        
		database = new Database();
		stastistics = new DatabaseStatistics();
		thismonth 		= database.getLastMonth();
		//thismonthprice  = prices.getLastMonth();
		setTextViews(0,0, 0,0);
		  Button buttonAllTime = (Button) getActivity().findViewById(R.id.buttonAllTime);
		    Button buttonMonth = (Button) getActivity().findViewById(R.id.buttonMonth);
		    Button buttonWeek = (Button) getActivity().findViewById(R.id.buttonWeek);
		    Button buttonYesterday = (Button) getActivity().findViewById(R.id.buttonYesterday);
		    Button buttonYear = (Button) getActivity().findViewById(R.id.buttonYear);
		    buttonAllTime.setOnClickListener(this);
		    buttonMonth.setOnClickListener(this);
		    buttonWeek.setOnClickListener(this);
		    buttonYesterday.setOnClickListener(this);
		    buttonYear.setOnClickListener(this);
		    textStartDate= (TextView) getActivity().findViewById(R.id.textStartDate);
		    textEndDate= (TextView) getActivity().findViewById(R.id.textEndDate);
		    textStartDate.setOnClickListener(this);
		    textEndDate.setOnClickListener(this);
		 // create RangeSeekBar as Date range between 1950-12-01 and now
		    
		    
		    try {
				setDatePickerListener();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   View view = getActivity().findViewById(R.id.relativelayout1);	   
		   view.setOnTouchListener(this);
		   
		   
		 
    }
  

    
    
    
    
    


private void setDatePickerListener() throws ParseException {
	Date minDate = new SimpleDateFormat("yyyy-MM-dd").parse("2008-12-01");
    Date maxDate = new Date();
    RangeSeekBar<Long> seekBar = new RangeSeekBar<Long>(minDate.getTime(), maxDate.getTime(), getActivity());
    seekBar.setOnRangeSeekBarChangeListener(new OnRangeSeekBarChangeListener<Long>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Long minValue, Long maxValue) {
                    // handle changed range values
                   Date startvalue = new Date(minValue);
                   Date endvalue = new Date(maxValue);
               	
            		Log.i("hej", "User selected new date range: MIN=" + new Date(minValue) + ", MAX=" + new Date(maxValue));
                	TextView textStartDate= (TextView) getActivity().findViewById(R.id.textStartDate);
                	
                	startcalendar.setTime(startvalue);
       
                	textStartDate.setText(startcalendar.get(Calendar.YEAR) + "-" + startcalendar.get(Calendar.MONTH)+ "-" + startcalendar.get(Calendar.DAY_OF_MONTH) );
                	endcalendar.setTime(endvalue);
                	TextView textEndDate = (TextView) getActivity().findViewById(R.id.textEndDate);
                	textEndDate.setText(endcalendar.get(Calendar.YEAR) + "-" + endcalendar.get(Calendar.MONTH)+ "-" + endcalendar.get(Calendar.DAY_OF_MONTH));
                	
                	setStastics(startvalue,endvalue);
               
                	
            }

			

    });

    // add RangeSeekBar to pre-defined layout
    ViewGroup layout = (ViewGroup) getActivity().findViewById(R.id.linearLayout5);

    layout.addView(seekBar);
	}




private void setStastics(Date startvalue, Date endvalue) {
	// Find the sequential day from a date, essentially resetting time to start of the day
	long startDay = startvalue.getTime() / 1000 / 60 / 60 / 24;
	long endDay = endvalue.getTime() / 1000 / 60 / 60 / 24;

	// Find the difference, duh
	long daysBetween = endDay - startDay;
	
	stastistics.setThisSpecificTime((int) daysBetween);
	 setTextViews(stastistics.getMedel(),stastistics.getMax() , stastistics.getMin(),stastistics.getYesterday());
	
}

public void onClick(View v) {
	
	  switch(v.getId()){
	  
	  case R.id.buttonAllTime:
		  stastistics.setThisAllTime();
		  break;
	  
	  case R.id.buttonYear: /** Start a new Activity MyCards.java */
	      stastistics.setThisYear();
	       break;
	  
	  case R.id.buttonMonth: /** Start a new Activity MyCards.java */
		  stastistics.setThisMonth();
	       break;

	  case R.id.buttonWeek: /** AlerDialog when click on Exit */
		  stastistics.setThisWeek();
	       break;
	       
	  case R.id.buttonYesterday: /** AlerDialog when click on Exit */
		  stastistics.setThisYesterday();
	       break;
	  case R.id.textStartDate:
		    startdate = new DatePickerFragment(this);
		    startdate.show(getFragmentManager(), "StartDate");
		    isStartDate = true;
		    Log.d("heh", "AdamTorkelsson" + startcalendar);
			Log.d("heh", "AdamTorkelsson" + endcalendar);

		    Log.d("geh", "AdamTorkelsson");
		  break;
	  case R.id.textEndDate:
		  Log.d("heh", "AdamTorkelsson" + startcalendar);
			Log.d("heh", "AdamTorkelsson" + endcalendar);
		  enddate =new DatePickerFragment(this);
		  enddate.show(getFragmentManager(), "EndDate");
		  isStartDate = false;

		  break;
	  default:
		  break;}
	  setTextViews(stastistics.getMedel(),stastistics.getMax() , stastistics.getMin(),stastistics.getYesterday());
	 
	  }


@Override
public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3){
	Calendar temp = Calendar.getInstance();
	temp.set(arg1, arg2, arg3);
	if(temp.after(Calendar.getInstance())){
		//Send error message
	}
	else{

	Log.d("adamTorkkk", "adamTorkkk" +  arg1 + arg2 + "");
	if(isStartDate){
		if(temp.before(endcalendar)){
			startcalendar = temp;
			textStartDate.setText(startcalendar.get(Calendar.YEAR) + "-" + startcalendar.get(Calendar.MONTH)+ "-" + startcalendar.get(Calendar.DAY_OF_MONTH) );
			setStastics(startcalendar.getTime(),
					endcalendar.getTime());}
		else{
			//Send error message
		}}
	else if(!isStartDate){
		if(startcalendar.before(temp)){
			endcalendar = temp;

			textEndDate.setText(endcalendar.get(Calendar.YEAR) + "-" + endcalendar.get(Calendar.MONTH)+ "-" + endcalendar.get(Calendar.DAY_OF_MONTH) );
			setStastics(startcalendar.getTime(),
					endcalendar.getTime());}
		else{
			//Send error message
		}}}
			
		}
	


private void setTextViews(float medel,float max , float min , float yesterday){
		
		TextView textmedeluse= (TextView) getActivity().findViewById(R.id.textmedeluse);
		textmedeluse.setText( String.format("%.2f", medel) + " kwh");
		
		TextView textmaxused = (TextView) getActivity().findViewById(R.id.textmaxused);
		textmaxused.setText( String.format("%.2f", max) + " kwh" );
		
		TextView textminuse = (TextView) getActivity().findViewById(R.id.textminuse);
		textminuse.setText(String.format("%.2f", min) + " kwh" );
		
		TextView textyesterdayuse = (TextView) getActivity().findViewById(R.id.textyesterday);
		textyesterdayuse.setText(yesterday + " kwh" );
		
		
		
	}
	

    
    
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        mFragment = new FragmentsTab1();
        // Attach fragment1.xml layout
        ft.add(android.R.id.content, mFragment);
       
        ft.setCustomAnimations(R.anim.map_in,R.anim.other_in);
        ft.attach(mFragment);
       
      
     
    }
 
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        // Remove fragment1.xml layout
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

            if((endX - startX) > 70) { 
            
            	Tab tab = getActivity().getActionBar().getTabAt(3);
            	tab.select();	
           //right 
           } 
           if((endX - startX) < -70) { 
        	   Log.d("hej", "touchlistLeft");
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




}