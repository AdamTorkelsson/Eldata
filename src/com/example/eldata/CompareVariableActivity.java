/*package com.example.eldata;

import java.util.ArrayList;

import com.example.eldata.rest.Database;
import com.example.eldata.rest.DatabasePrice;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CompareVariableActivity extends Activity{
	String fast;
	String add;
	Context context;
	Database database = new Database();
	ArrayList<Float> databasearray = new ArrayList<Float>();
	ArrayList<Float> pricesarray = new ArrayList<Float>();
	DatabasePrice prices = new DatabasePrice();
	float cost = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comparevariableaview);
		
		
		context = this;
		//thismonthprice  = prices.getLastMonth();
	
		 Bundle bundle = getIntent().getExtras();
		add =  bundle.getString("textadd", "Error");
		fast = bundle.getString("textfast", "Error");
		Log.d(TEXT_SERVICES_MANAGER_SERVICE, "GetStringAdam " + add + fast);
		databasearray = database.getAll();
		pricesarray = prices.getAll();
		getPrice();
		setBackListener();
		setTextViews();
	}
	
	private void getPrice(){
		
		for(int i = 0;i < databasearray.size()-1; i++){
			cost += databasearray.get(i)*(pricesarray.get(i)+Float.parseFloat(add)/100);
		}
	}
	
	
	
	
	
	private void setTextViews(){
		
		TextView textmedeluse= (TextView) findViewById(R.id.textmedeluse);
		textmedeluse.setText(" " + cost + " Kronor");
		Float temp = cost - Float.parseFloat(fast);
		TextView textmaxused = (TextView) findViewById(R.id.textmaxused);
		textmaxused.setText("" + temp + " Kronor");

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

