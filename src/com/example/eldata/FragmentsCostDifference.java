package com.example.eldata;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import com.example.eldata.R.layout;
import com.example.eldata.RangeSeekBar.OnRangeSeekBarChangeListener;
import com.example.eldata.rest.Database;
import com.example.eldata.rest.DatabaseCost;
import com.example.eldata.rest.DatabasePrice;
import com.example.eldata.rest.GraphCost;






import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.R.integer;
import android.app.ActionBar.Tab;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;

public class FragmentsCostDifference extends Fragment implements ActionBar.TabListener ,OnClickListener, OnDateSetListener,OnTouchListener, OnItemSelectedListener  {
 
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
	DatabaseCost databaseCost;
	DatabasePrice databasePrice;
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
    Button buttonAllTime;
    Button buttonMonth;
    Button buttonWeek; 
    Button buttonYesterday; 
    Button buttonYear;
    EditText textcompare;
    LinearLayout view2;
    View myView;
    LayoutInflater factory;
    Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setRetainInstance(true);
    	//Sets the animation when opening this activity
        startcalendar  = Calendar.getInstance();
    	endcalendar  = Calendar.getInstance();
        
        // Get the view from fragment1.xml
        getActivity().setContentView(R.layout.fragment_statistic);
        
		database = new Database();
		databaseCost = new DatabaseCost();
		databasePrice = new DatabasePrice();
		SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
		
		float f = 0;
		databaseCost.setAddFast(Float.parseFloat(settings.getString("Add", f + "")),
				Float.parseFloat(settings.getString("FastPris", f + "")));
		
	
		
		thismonth 		= database.getLastMonth();
		//thismonthprice  = prices.getLastMonth();
		
		 
			textStartDate= (TextView) getActivity().findViewById(R.id.textStartDate);
		    textEndDate= (TextView) getActivity().findViewById(R.id.textEndDate);
		    textStartDate.setOnClickListener(this);
		    textEndDate.setOnClickListener(this);
		
			buttonAllTime = (Button) getActivity().findViewById(R.id.buttonAllTime);
		    buttonMonth = (Button) getActivity().findViewById(R.id.buttonMonth);
		    buttonWeek = (Button) getActivity().findViewById(R.id.buttonWeek);
		    buttonYesterday = (Button) getActivity().findViewById(R.id.buttonYesterday);
		    buttonYear = (Button) getActivity().findViewById(R.id.buttonYear);
		

		    buttonAllTime.setOnClickListener(this);
		    buttonMonth.setOnClickListener(this);
		    buttonWeek.setOnClickListener(this);
		    buttonYesterday.setOnClickListener(this);
		    buttonYear.setOnClickListener(this);

		
		
		    spinner  = (Spinner) getActivity().findViewById(R.id.spinnerStatistic);
		    spinner.setOnItemSelectedListener(this);
		    
		    
		 // create RangeSeekBar as Date range between 1950-12-01 and now
		    
		    
		    try {
				setDatePickerListener();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   View view = getActivity().findViewById(R.id.linearLayout9);	   
		   view.setOnTouchListener(this);
		   databaseCost.setThisAllTime();
			  setStartAndEndDate(minDate,
		  				Calendar.getInstance().getTime());
			  
		  graph = new GraphCost(getActivity());
		  
		  
		  graph.setTime(databaseCost.getMax() , databaseCost.getMin(),databaseCost.getAllMax());
		   graphview = (LinearLayout) getActivity().findViewById(R.id.linearLayout7);
		   graphview.addView(graph);

		   //adds the layout for the specific statistic
		  view2 = (LinearLayout) getActivity().findViewById(R.id.linearLayout1); 
		  factory = LayoutInflater.from(getActivity());
		
		  myView = factory.inflate(R.layout.pricecompare, null);
		  view2.addView(myView);
		
		  
		  
		 
		 
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
    ViewGroup layout = (ViewGroup) getActivity().findViewById(R.id.linearLayout13);

    layout.addView(seekBar);
	}




private void setStastics(Date startvalue, Date endvalue) {
	// Find the sequential day from a date, essentially resetting time to start of the day
	long startDay = startvalue.getTime() / 1000 / 60 / 60 / 24;
	long endDay = endvalue.getTime() / 1000 / 60 / 60 / 24;

	// Find the difference, duh
	long daysBetween = endDay - startDay;
	
	databaseCost.setThisSpecificTime((int) daysBetween);
	 
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
	if(spinner.getSelectedItemPosition() == 0){
		graph.drawNew(databaseCost.getMax() , databaseCost.getMin() , databaseCost.getAllMax(),"priceCompare");
	}
	else if(spinner.getSelectedItemPosition() == 1){
		graph.drawNew( 100,70 , 100, "peaks");
	}
	  

}
@Override
public void onClick(View v) {
	long ett = 3600 * 24;
	long två = 31*1000;
	long tre = 365 * 1000;
	
	 Calendar startcalendar = Calendar.getInstance();
	 long currentTime = startcalendar.getTime().getTime();
	 SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
	 SharedPreferences.Editor editor = settings.edit();
	  switch(v.getId()){       
	  case R.id.buttonKlar:
		  
		  
		  
		  databaseCost.setThisAllTime();
			 
		  setStartAndEndDate(minDate,
	  				Calendar.getInstance().getTime());
		  createNewGraph();
		  break;
	  case R.id.buttonCompare:
		  Editable ed = input.getText();
		  if(ed.length() > 0){
			  editor.putString("Add",ed.toString());
			  float f = 0;
			  Log.d("", "ADAAAAAAM");
			 
			  databaseCost.setAddFast(Float.parseFloat(settings.getString("Add", f + "")),
						Float.parseFloat(settings.getString("FastPris", f + "")));
		  }
		  
		  
		  
		  databaseCost.setThisAllTime();
			 
		  setStartAndEndDate(minDate,
	  				Calendar.getInstance().getTime());
		  createNewGraph();
		  break;
	  case R.id.buttonAllTime:
		  databaseCost.setThisAllTime();
		 
		  setStartAndEndDate(minDate,
	  				Calendar.getInstance().getTime());
		  createNewGraph();
		  
		  break;
	  
	  case R.id.buttonYear:
	      databaseCost.setThisYear();
	 
		  long minus = ett * tre;
		  long date = currentTime - minus;
	      setStartAndEndDate(new Date(date),
	  				Calendar.getInstance().getTime());
	      createNewGraph();
	       break;
	  
	  case R.id.buttonMonth:
		  databaseCost.setThisMonth();

		  long minus1 = ett * två;
		 long date1 = currentTime - minus1;

		  setStartAndEndDate(new Date(date1),
				  				Calendar.getInstance().getTime());
		  createNewGraph();
	       break;

	  case R.id.buttonWeek: 
		  databaseCost.setThisWeek();
		  setStartAndEndDate(new Date(currentTime - (60 * 60 * 24 * 7* 1000)),
	  				Calendar.getInstance().getTime());
		  createNewGraph();
	
	       break;
	       
	  case R.id.buttonYesterday: 
		  databaseCost.setThisYesterday();
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
	

    
    FragmentTransaction ft;
    @Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        mFragment = new FragmentsCostDifference();
        // Attach fragment1.xml layout
        ft.add(android.R.id.content, mFragment);
       this.ft = ft;
        ft.setCustomAnimations(R.anim.map_in,R.anim.other_in);
        ft.attach(mFragment);
       
      
     
    }
 
    @Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        // Remove fragment1.xml layout
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
EditText input;
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
			switch(arg2){
				case 1:
					view2.removeAllViews();
					  myView = factory.inflate(R.layout.highuse, null);
					  view2.addView(myView);
					 Button b1 = (Button) myView.findViewById(R.id.buttonKlar);
					  b1.setOnClickListener(this);	
					 input = (EditText) myView.findViewById(R.id.edittext);
					  break;
					  
				case 0:
					view2.removeAllViews();
					  myView = factory.inflate(R.layout.pricecompare, null);
					  view2.addView(myView);
					  Button b2 = (Button) myView.findViewById(R.id.buttonCompare);
					  b2.setOnClickListener(this);	
					  input = (EditText) myView.findViewById(R.id.edittext);
					  break;
	
			}
			 
	}




	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}


}