package com.example.eldata;
 
import android.os.Bundle;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
 
public class FragmentsSaveEnergy extends Fragment implements ActionBar.TabListener {
 
    private Fragment mFragment;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        // Get the view from fragment3.xml
        getActivity().setContentView(R.layout.energysave);
     //   View view = getActivity().findViewById(R.id.relativelayout1);
		//   view.setOnTouchListener(this);
		
		   
    }
 
    @Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
        mFragment = new FragmentsSaveEnergy();
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
	


}