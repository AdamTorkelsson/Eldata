package com.example.eldata;
 
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
 
public class FragmentsTab4 extends Fragment 
implements ActionBar.TabListener, OnTouchListener,
OnClickListener{
 
    private static final String PREFS_NAME = "UserInfo";
	private Fragment mFragment;
    private Boolean textfastboolean = false;
	private Boolean textaddboolean = false;
	private EditText textfast;
	private EditText textadd;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from fragment3.xml
        getActivity().setContentView(R.layout.settingsview);
        View view = getActivity().findViewById(R.id.relativelayout1);
		   view.setOnTouchListener(this);
		   Button buttonAdd = (Button) getActivity().findViewById(R.id.buttonAdd);
		   Button buttonCancel = (Button) getActivity().findViewById(R.id.buttonCancel);
		   buttonAdd.setOnClickListener(this);
		   buttonCancel.setOnClickListener(this);
		  
		   textfast= (EditText) getActivity().findViewById(R.id.textfast);
		   textadd = (EditText) getActivity().findViewById(R.id.textadd);
		   textfast.setOnClickListener(this);
		   textadd.setOnClickListener(this);
    }
 
	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.buttonAdd:
			onAdd();
			break;
		case R.id.buttonCancel:	
			break;
		case R.id.textfast:
			textfastboolean = true;
		case R.id.textadd:
			textaddboolean = true;}
		

		}
		
	
	
   public void onAdd(){
	   SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		
	   if(textfastboolean = true){
		   Editable  fast = textfast.getText();
		   editor.putString("FastPris", fast.toString());
		   textfastboolean = false;
		   
	   }
	   if(textaddboolean = true){
		   Editable add = textadd.getText();
		   editor.putString("Add", add.toString());
		   textaddboolean = false;
	   }
	
editor.commit();
    
   }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        mFragment = new FragmentsTab4();
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
	public boolean onTouch(View v, MotionEvent event) {
	
		
		switch (event.getAction()) { 
        case MotionEvent.ACTION_DOWN: 
        	
           startX = (int)event.getX(); 
           startY = (int)event.getY(); 
  
           return true; 
        case MotionEvent.ACTION_MOVE: 
           endX = (int)event.getX(); 
           endY = (int)event.getY(); 

            if((endX - startX) > 40) { 
            
            	Tab tab = getActivity().getActionBar().getTabAt(2);
            	tab.select();	
           //right 
           } 
           if((endX - startX) < -40) { 
        	   Log.d("hej", "touchlistLeft");
        	   Tab tab = getActivity().getActionBar().getTabAt(0);
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