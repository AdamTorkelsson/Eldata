package com.example.eldata;
 
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
 
public class MainActivity extends Activity {
    protected static final String PREFS_NAME = "UserInfo";
	// Declare Tab Variable
    Tab tab1;
    Tab tab2;
    Tab tab3;
    Tab tab4;
    Context context;
    FragmentsTab1 ftab1;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       context = this;
        
        // Create the actionbar
    
       ActionBar actionBar = getActionBar();
 
        // Hide Actionbar Icon
        actionBar.setDisplayShowHomeEnabled(false);
 
        // Hide Actionbar Title
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        // Create Actionbar Tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
       

        ftab1 = new FragmentsTab1();
        
        // Create first Tab
        tab1 = actionBar.newTab().setTabListener(ftab1);
        // Create your own custom icon
        tab1.setText("Home");
      
        actionBar.addTab(tab1);
 
        // Create Second Tab
        tab2 = actionBar.newTab().setTabListener(new FragmentsTab2());
        // Set Tab Title
        tab2.setText("Compare");
        actionBar.addTab(tab2);
        
        // Create Third Tab
        tab3 = actionBar.newTab().setTabListener(new FragmentsTab3());
        // Set Tab Title
        tab3.setText("Tips");
        actionBar.addTab(tab3);
 
        // Create Third Tab
        tab4 = actionBar.newTab().setTabListener(new FragmentsTab4());
        // Set Tab Title
        tab4.setText("Settings");
        actionBar.addTab(tab4);
        
        
        
       standardDialog(R.string.SetValues, "Klar");
       
       
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
    
    public void standardDialog(int stringValue, String positiveButton){
		//Gets the layout to be set in the dialog
		LayoutInflater li = LayoutInflater.from(this);
		View tutorialView= li.inflate(R.layout.billview, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		
		final EditText numberAdd = (EditText) tutorialView.findViewById(R.id.textPålägg);
		final EditText numberFast = (EditText) tutorialView.findViewById(R.id.textFastPris);
		

		//Sets the view of the dialog
		alertDialogBuilder.setView(tutorialView);
		//Sets the name of the buttons of the dialog
		alertDialogBuilder
		.setCancelable(true)
		.setPositiveButton(positiveButton,
				new DialogInterface.OnClickListener() {		
			public void onClick(DialogInterface dialog,int id) {
				//If createloop is set true, which it only should be when called from tutorialDialog, starts the next tutorialDialog
				Log.d("geh", "heh1");
				
				String temp1 = numberAdd.getText().toString();
				Log.d("geh", "heh0");
				String temp2 = numberFast.getText().toString();
				Log.d("geh", "heh2");
			
				
				SharedPreferences settings = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
				
				SharedPreferences.Editor editor = settings.edit();
				editor.putString("Add", temp1);
				editor.putString("FastPris", temp2);
				editor.commit();

				dialog.cancel();
			}

		})
		//Sets the negative button to cancel
		.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				
				dialog.cancel();

			}
		});

		//Creates the alertDialog and sets it cancelable
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.setCancelable(true);


		//Displays the alertDialog
		alertDialog.show();
		//Sets the background of the buttons
		Button bn = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
		bn.setBackgroundColor(Color.parseColor("#FFFFFF"));
		Button bp = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
		bp.setBackgroundColor(Color.parseColor("#FFFFFF"));

	}

	private void setDates() {
		// TODO Auto-generated method stub
		
	}

}
    
