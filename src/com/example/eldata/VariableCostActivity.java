/*package com.example.eldata;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class VariableCostActivity extends Activity {
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		setbtnNext24Listener();
		setbtnNextBillListener();
		setbtnStatisticListener();
		setbtnLastYearListener();
		
	}
	
	private void setbtnNext24Listener() {
		Button btnNext24 = (Button) findViewById(R.id.btnNext24);
		btnNext24.setClickable(true);
		btnNext24.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				//Hides the keyboard to get a smoother transition when changing activity
				Intent intent = new Intent(context,GraphActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private void setbtnNextBillListener() {
		Button btnNextBill = (Button) findViewById(R.id.btnNextBill);
		btnNextBill.setClickable(true);
		btnNextBill.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				//Hides the keyboard to get a smoother transition when changing activity
				Intent intent = new Intent(context,BillActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private void setbtnStatisticListener() {
		Button btnStatistic = (Button) findViewById(R.id.btnStatistic);
		btnStatistic.setClickable(true);
		btnStatistic.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				//Hides the keyboard to get a smoother transition when changing activity
				Intent intent = new Intent(context,CompareActivity.class);
				startActivity(intent);
			}
		});
	}
	private void setbtnLastYearListener() {
		Button btnLastYear = (Button) findViewById(R.id.btnLastYear);
		btnLastYear.setClickable(true);
		btnLastYear.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				//Hides the keyboard to get a smoother transition when changing activity
				Intent intent = new Intent(context,VariableCostActivity.class);
				startActivity(intent);
			}
		});
	}
	
	
}*/
