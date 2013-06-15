/*package com.example.eldata;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;

import com.example.eldata.rest.Database;
import com.example.eldata.rest.DatabasePrice;
import com.example.eldata.rest.DatabaseStatistics;



import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CompareActivity extends Activity{
	Database adam = new Database();
	DatabasePrice prices = new DatabasePrice();
	ArrayList<Float> thismonth = new ArrayList<Float>();
	ArrayList<Float> thismonthprice = new ArrayList<Float>();
	DatabaseStatistics stastistics = new DatabaseStatistics();
	Context context;
	int totalbill;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compareview);
		thismonth 		= adam.getLastMonth();
		context = this;
		//thismonthprice  = prices.getLastMonth();
		setBackListener();
		setTextViews();
		
		
	}
	
	private void setTextViews(){
		
		TextView textmedeluse= (TextView) findViewById(R.id.textmedeluse);
		textmedeluse.setText((int) stastistics.getMedel() + " kwh");
		
		TextView textmaxused = (TextView) findViewById(R.id.textmaxused);
		textmaxused.setText((int) stastistics.getMax() + " kwh" );
		
		TextView textminuse = (TextView) findViewById(R.id.textminuse);
		textminuse.setText((int) stastistics.getMin() + " kwh" );
	}
	
	private void setBackListener() {
		Button btnBack = (Button) findViewById(R.id.btnBack);
		btnBack.setClickable(true);
		btnBack.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				//Hides the keyboard to get a smoother transition when changing activity
			finish();
			}
		});
	}
	

}*/


