package gammalt;
/*package com.example.eldata;



import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class StartActivity extends Activity {
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_choice);
	
		setVariableListener();
		setNonVariableListener();
		context = this;
		
	}
	
	private void setNonVariableListener() {
		Button buttonVariable = (Button) findViewById(R.id.buttonVariable);
		buttonVariable.setClickable(true);
		buttonVariable.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				//Hides the keyboard to get a smoother transition when changing activity
				Intent intent = new Intent(context,VariableCostActivity.class);
				startActivity(intent);
			}
		});
	}

	public void setVariableListener() {
		Button buttonNonVariable = (Button) findViewById(R.id.buttonNonVariable);
		buttonNonVariable.setClickable(true);
		buttonNonVariable.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context,NonVariableActivity.class);
				startActivity(intent);
			}
		});
	}
	


	
	@Override
	//Defines the attributes of the options menu
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		//Inflate the startmenu into the bar
	//	MenuInflater inflater = getMenuInflater();
	//	inflater.inflate(R.menu.buttonmenu, menu);
		// Sets the title and logo to be hidden
		
		return true;

	}
	
	

	
}*/
