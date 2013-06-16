package com.example.eldata;
 
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
 
public class FragmentsTab4 extends Fragment 
implements ActionBar.TabListener, OnTouchListener,
OnClickListener,OnKeyListener{
 
    private static final String PREFS_NAME = "UserInfo";
	private Fragment mFragment;
	
    private Boolean textfastboolean = false;
	private Boolean textaddboolean = false;
	private Boolean texthighuseboolean = false;
	private Boolean texthighbillboolean = false;
	private Boolean texthighpriceboolean = false;
	
	private Boolean thingshavechanged = false;
	
	private EditText textfast;
	private EditText textadd;
	private EditText texthighbill;
	private EditText texthighprice;
	private EditText texthighuse;
	private ToggleButton tooglehighbill;
	private ToggleButton tooglehighprice;
	private ToggleButton tooglehighuse;
	
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        // Get the view from fragment3.xml
        getActivity().setContentView(R.layout.settingsview);
        View view = getActivity().findViewById(R.id.relativelayout1);
		   view.setOnTouchListener(this);

		 
		  
		   thingshavechanged = false;
		  
		   textfast= (EditText) getActivity().findViewById(R.id.textfast);
		   textadd = (EditText) getActivity().findViewById(R.id.textadd);
		   texthighbill = (EditText) getActivity().findViewById(R.id.textHighBill);
		   texthighprice = (EditText) getActivity().findViewById(R.id.textHighPrice);
		   texthighuse = (EditText) getActivity().findViewById(R.id.textHighUse);
		   tooglehighbill = (ToggleButton) getActivity().findViewById(R.id.booleanHighBill);
		   tooglehighprice = (ToggleButton) getActivity().findViewById(R.id.booleanHighPrice);
		   tooglehighuse = (ToggleButton) getActivity().findViewById(R.id.booleanHighUse);
		   textfast.setOnKeyListener(this);
		   textadd.setOnKeyListener(this);
		   texthighbill.setOnKeyListener(this);
		   texthighprice.setOnKeyListener(this);
		   texthighuse.setOnKeyListener(this);
		
		   tooglehighbill.setOnClickListener(this);
		   tooglehighprice.setOnClickListener(this);
		   tooglehighuse.setOnClickListener(this);
		   
		   settings = getActivity().getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
		   editor = settings.edit();
		   
		   textfast.setText(settings.getString("FastPris", ""));
		   textadd.setText(settings.getString("Add", ""));
		   texthighbill.setText(settings.getString("highbill", ""));
		   texthighprice.setText(settings.getString("highprice", ""));
		   texthighuse.setText(settings.getString("highuse", ""));
			
		  tooglehighbill.setChecked(settings.getBoolean("highbilltoogle",false));
		  tooglehighprice.setChecked(settings.getBoolean("highpricetoogle",false));
		  tooglehighuse.setChecked(settings.getBoolean("highusetoogle",false));
			
		  
		  
    }
 
	@Override
	public void onClick(View arg0) {
	
		switch(arg0.getId()){

		case R.id.booleanHighBill:
			editor.putBoolean("highbilltoogle", tooglehighbill.isChecked());
			break;
		case R.id.booleanHighPrice:
			editor.putBoolean("highpricetoogle", tooglehighprice.isChecked());
			break;
		case R.id.booleanHighUse:
			editor.putBoolean("highusetoogle", tooglehighuse.isChecked());
		
			break;}
		editor.commit();
			}
		

		
		
	
	
   
	

    
   

	@Override
	public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
	 Editable  temp;
	switch (arg0.getId()){
	case R.id.textadd:
		
		temp = textadd.getText();
		editor.putString("Add", temp.toString());
		break;
	case R.id.textfast:
		temp = textfast.getText();
		 editor.putString("FastPris", temp.toString());
		break;
	case R.id.textHighBill:
		
			temp = texthighbill.getText();
		   editor.putString("highbill", temp.toString());
	
		   if(texthighbill.getText().length() >= 1){
			   tooglehighbill.setChecked(true);
			   editor.putBoolean("highbilltoogle", true);
		}
		else{
			tooglehighbill.setChecked(false);
			editor.putBoolean("highbilltoogle", false);
		}
		break;
	case R.id.textHighPrice:
		temp = texthighprice.getText();
		   editor.putString("highprice", temp.toString());
	
		   if(texthighprice.getText().length() >= 1){
			   tooglehighprice.setChecked(true);
			   editor.putBoolean("highpricetoogle", true);
		}
		else{
			tooglehighprice.setChecked(false);
			editor.putBoolean("highpricetoogle", false);
		}
		break;
	case R.id.textHighUse:
		temp = texthighuse.getText();
		   editor.putString("highuse", temp.toString());
	
		   if(texthighuse.getText().length() >= 1){
			   tooglehighuse.setChecked(true);
			   editor.putBoolean("highusetoogle", true);
		}
		else{
			tooglehighuse.setChecked(false);
			editor.putBoolean("highusetoogle", false);
		}
		 break;}
	   editor.commit();
	return false;
	}

	
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





 
}