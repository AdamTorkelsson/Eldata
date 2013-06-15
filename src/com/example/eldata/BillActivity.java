/*package com.example.eldata;

import java.util.ArrayList;

import com.example.eldata.rest.Database;
import com.example.eldata.rest.DatabasePrice;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BillActivity extends Activity{
	Database adam = new Database();
	DatabasePrice prices = new DatabasePrice();
	ArrayList<Float> thismonth = new ArrayList<Float>();
	ArrayList<Float> thismonthprice = new ArrayList<Float>();
	Context context;
	int totalbill;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.billview);
		thismonth 		= adam.getLastMonth();
		context = this;
		//thismonthprice  = prices.getLastMonth();
		setTotalBill();
		setBackListener();
	//	setTextViews();
		
		
	}
	
	private void setTextViews(){
		TextView totalbillview = (TextView) findViewById(R.id.textBillSize);
		totalbillview.setText("" + totalbill + " Kronor" );
		
		TextView daysleftview = (TextView) findViewById(R.id.textdaysleft);
		daysleftview.setText("" + "15" );
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
	
	private void setTotalBill(){
		float totalbill = 0;
		for(int i = 0; i < thismonth.size(); i++){
			totalbill = totalbill + thismonth.get(i);/**thismonthprice.get(i);	*/
	/*	}
		this.totalbill = (int) totalbill;

	}
}
*/