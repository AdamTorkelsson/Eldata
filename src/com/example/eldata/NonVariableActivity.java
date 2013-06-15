package com.example.eldata;
/*
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NonVariableActivity extends Activity {
	private final String PREFS_NAME = "MySettings";
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fill_info);
		context = this;
	
		setTagListener();
	}
	
	/**
	 * Sets the listener to the tag button
	 *//*
	public void setTagListener() {
		Button buttonCompare	 = (Button) findViewById(R.id.btnNext24);
		buttonCompare.setClickable(true);
		buttonCompare.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				EditText numberAdd = (EditText) findViewById(R.id.textadd);
				EditText numberFast = (EditText) findViewById(R.id.textfast);

				String temp1 = numberAdd.getText().toString();
				String temp2 = numberFast.getText().toString();

					//Saves the markerTitle, markerDescription, Start- and end dates/times in the shared preferences to use in AddTagActivity
					SharedPreferences sharedprefs = context.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedprefs.edit();
					editor.putString("txtAdd", temp1);
					editor.putString("markerDescription", temp2);
					editor.commit();
					//Start the next step,
					Intent intent = new Intent(context, CompareVariableActivity.class);
					intent.putExtra("textadd", temp1);
					intent.putExtra("textfast", temp2);
					startActivity(intent);
					
					//Finish the activity
					finish();
				}
			});}}
	
	*/
