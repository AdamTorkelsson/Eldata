package com.example.eldata;
 
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
 
public class MainActivity extends Activity {
    protected static final String PREFS_NAME = "UserInfo";
	// Declare Tab Variable
    Tab tab1;
    Tab tab2;
    Tab tab3;
    Tab tab4;
    Tab tab5;
    Context context;
    FragmentsStart ftab1;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        
        // Create the actionbar
    
       ActionBar actionBar = getActionBar();
 
        // Hide Actionbar Icon
        actionBar.setDisplayShowHomeEnabled(false);
 
        // Hide Actionbar Title
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(false);
        // Create Actionbar Tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setCustomView(R.layout.tablayout);
        ftab1 = new FragmentsStart();
        
        // Create first Tab
        tab1 = actionBar.newTab().setTabListener(ftab1);
        // Create your own custom icon
        tab1.setText("Home");
 
        actionBar.addTab(tab1);
 
        // Create Second Tab
        tab2 = actionBar.newTab().setTabListener(new FragmentsCostDifference());
        // Set Tab Title
        tab2.setText("Compare");
        actionBar.addTab(tab2);
        
        // Create Third Tab
        tab3 = actionBar.newTab().setTabListener(new FragmentsSaveEnergy());
        // Set Tab Title
        tab3.setText("Tips");
        actionBar.addTab(tab3);
 
        // Create Fourth Tab
        tab4 = actionBar.newTab().setTabListener(new FragmentsSettings());
        // Set Tab Title
        tab4.setText("Settings");
        actionBar.addTab(tab4);
        

      // standardDialog(R.string.SetValues, "Klar");
       
       
    }
    


public void selecttabs(int i){
	switch (i){
	case 1:
		tab1.select();

		break;
	case 2:
		tab2.select();
		break;
	case 3:
		tab3.select();
		break;
	case 4:
		tab4.select();
		break;
	default:
		break;
	}
}
    


	private void setDates() {
		// TODO Auto-generated method stub
		
	}

}
    
