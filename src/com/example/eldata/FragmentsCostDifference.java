package com.example.eldata;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import com.example.eldata.RangeSeekBar.OnRangeSeekBarChangeListener;
import com.example.eldata.rest.Database;
import com.example.eldata.rest.DatabaseCost;
import com.example.eldata.rest.DatabasePrice;
import com.example.eldata.rest.DatabaseStatistics;
import com.example.eldata.rest.Graph;
import com.example.eldata.rest.GraphCost;






import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class FragmentsCostDifference extends Fragment implements ActionBar.TabListener ,OnClickListener, OnDateSetListener,OnTouchListener  {
 
	@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
       
        inflater.inflate(R.menu.main, menu);
    }


	private Fragment mFragment;
	Database database;
	DatabasePrice prices = new DatabasePrice();
	ArrayList<Float> thismonth = new ArrayList<Float>();
	ArrayList<Float> thismonthprice = new ArrayList<Float>();
	DatabaseCost stastistics;
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
    private static final String PREFS_NAME = "UserInfo";
    LinearLayout graphview;
    GraphCost graph;
    Date minDate;

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
		stastistics = new DatabaseCost();
		SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
		stastistics.setAddFast(Float.parseFloat(settings.getString("Add", "Error")),
				Float.parseFloat(settings.getString("FastPris", "Error")));
		thismonth 		= database.getLastMonth();
		//thismonthprice  = prices.getLastMonth();
		setTextViews(0,0, 0,0);
		  Button buttonAllTime = (Button) getActivity().findViewById(R.id.buttonAllTime);
		    Button buttonMonth = (Button) getActivity().findViewById(R.id.buttonMonth);
		    Button buttonWeek = (Button) getActivity().findViewById(R.id.buttonWeek);
		    Button buttonYesterday = (Button) getActivity().findViewById(R.id.buttonYesterday);
		    Button buttonYear = (Button) getActivity().findViewById(R.id.buttonYear);
		    Spinner buttonSpinner= (Spinner) getActivity().findViewById( R.id.switch1);
			
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
		   View view = getActivity().findViewById(R.id.linearLayout9);	   
		   view.setOnTouchListener(this);
		   stastistics.setThisAllTime();
			  setStartAndEndDate(minDate,
		  				Calendar.getInstance().getTime());
			  
		  graph = new GraphCost(getActivity());
		  graph.setTime(stastistics.getMedel(),stastistics.getMax() , stastistics.getMin(),stastistics.getAllMax());
		   graphview = (LinearLayout) getActivity().findViewById(R.id.linearLayout7);
		   graphview.addView(graph);
		   
		 
    }
  


private void setDatePickerListener() throws ParseException {
	minDate = new SimpleDateFormat("yyyy-MM-dd").parse("2008-12-01");
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
       
                	textStartDate.setText(startcalendar.get(Calendar.YEAR) + "-" + (startcalendar.get(Calendar.MONTH) + 1) +  "-" + startcalendar.get(Calendar.DAY_OF_MONTH) );
                	endcalendar.setTime(endvalue);
                	TextView textEndDate = (TextView) getActivity().findViewById(R.id.textEndDate);
                	textEndDate.setText(endcalendar.get(Calendar.YEAR) + "-" + (endcalendar.get(Calendar.MONTH) + 1)+ "-" + endcalendar.get(Calendar.DAY_OF_MONTH));
                	
                	setStastics(startvalue,endvalue);
                	createNewGraph();
                	
            }

			

    });

    // add RangeSeekBar to pre-defined layout
    ViewGroup layout = (ViewGroup) getActivity().findViewById(R.id.linearLayout10);

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

public void setStartAndEndDate(Date startdate,Date enddate){
	startcalendar.setTime(startdate);
	endcalendar.setTime(enddate);
	textStartDate.setText(startcalendar.get(Calendar.YEAR) + "-" + 
					(startcalendar.get(Calendar.MONTH) + 1) + "-" + 
					startcalendar.get(Calendar.DAY_OF_MONTH) );
	textEndDate.setText(endcalendar.get(Calendar.YEAR) + "-" + 
					(endcalendar.get(Calendar.MONTH ) + 1) + "-" + 
					endcalendar.get(Calendar.DAY_OF_MONTH));
}

private void createNewGraph(){
	// graphview.removeAllViews();
	  graph.drawNew(stastistics.getMedel(),stastistics.getMax() , stastistics.getMin());
	//  graph.setTime(stastistics.getMedel(),stastistics.getMax() , stastistics.getMin());
	 // graphview.addView(graph);
}
public void onClick(View v) {
	long ett = 3600 * 24;
	long två = 31*1000;
	long tre = 365 * 1000;
	
	 Calendar startcalendar = Calendar.getInstance();
	 long currentTime = startcalendar.getTime().getTime();
	 
	  switch(v.getId()){
	  case R.id.switch1:
		    
		        // Attach fragment1.xml layout
		        ft.remove(mFragment);
		        mFragment = new FragmentsHighestUse();
		        
		        
		        mFragment = new FragmentsCostDifference();
		        // Attach fragment1.xml layout
		        ft.add(android.R.id.content, mFragment);
		       this.ft = ft;
		        ft.setCustomAnimations(R.anim.map_in,R.anim.other_in);
		        ft.attach(mFragment);
		        
		        
		  break;
		  
	  case R.id.buttonAllTime:
		  stastistics.setThisAllTime();
		  setStartAndEndDate(minDate,
	  				Calendar.getInstance().getTime());
		  createNewGraph();
		  break;
	  
	  case R.id.buttonYear:
	      stastistics.setThisYear();
	 
		  long minus = ett * tre;
		  long date = currentTime - minus;
	      setStartAndEndDate(new Date(date),
	  				Calendar.getInstance().getTime());
	      createNewGraph();
	       break;
	  
	  case R.id.buttonMonth:
		  stastistics.setThisMonth();

		  long minus1 = ett * två;
		 long date1 = currentTime - minus1;

		  setStartAndEndDate(new Date(date1),
				  				Calendar.getInstance().getTime());
		  createNewGraph();
	       break;

	  case R.id.buttonWeek: 
		  stastistics.setThisWeek();
		  setStartAndEndDate(new Date(currentTime - (60 * 60 * 24 * 7* 1000)),
	  				Calendar.getInstance().getTime());
		  createNewGraph();
	
	       break;
	       
	  case R.id.buttonYesterday: 
		  stastistics.setThisYesterday();
		  setStartAndEndDate(new Date(currentTime - (60 * 60 * 24*1000)),
	  				Calendar.getInstance().getTime());
		  createNewGraph();
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
			textStartDate.setText(startcalendar.get(Calendar.YEAR) + "-" + (startcalendar.get(Calendar.MONTH)  + 1)+   "-" + startcalendar.get(Calendar.DAY_OF_MONTH) );
			setStastics(startcalendar.getTime(),
					endcalendar.getTime());
			 createNewGraph();}
		else{
			//Send error message
		}}
	else if(!isStartDate){
		if(startcalendar.before(temp)){
			endcalendar = temp;

			textEndDate.setText(endcalendar.get(Calendar.YEAR) + "-" + (endcalendar.get(Calendar.MONTH) + 1) + "-" + endcalendar.get(Calendar.DAY_OF_MONTH) );
			setStastics(startcalendar.getTime(),
					endcalendar.getTime());
			 createNewGraph();}
		else{
			//Send error message
		}}}
			
		}
	


private void setTextViews(float medel,float max , float min , float yesterday){
		
		TextView textmedeluse= (TextView) getActivity().findViewById(R.id.textmedeluse);
		textmedeluse.setText( String.format("%.2f", medel) + " kr");
		
		TextView textmaxused = (TextView) getActivity().findViewById(R.id.textmaxused);
		textmaxused.setText( String.format("%.2f", max) + " kr" );
		
		TextView textminuse = (TextView) getActivity().findViewById(R.id.textminuse);
		textminuse.setText(String.format("%.2f", min) + " kr" );
		
		
	}
	

    
    FragmentTransaction ft;
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        mFragment = new FragmentsCostDifference();
        // Attach fragment1.xml layout
        ft.add(android.R.id.content, mFragment);
       this.ft = ft;
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