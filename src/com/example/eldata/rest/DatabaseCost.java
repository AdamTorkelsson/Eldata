package com.example.eldata.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

public class DatabaseCost {
	Database database;
	float variablereturn = 0;
	float fastreturn = 0;
	float totalsum = 0;
	float medel = 0;
	ArrayList<Float> databasearray = new ArrayList<Float>();
	Calendar c; 
	float variablecost;
	float allMax = 0;
	
	public DatabaseCost(){
		database = new Database();
		databasearray = database.getAll();
		c = Calendar.getInstance(); 
		Date date = c.getTime();
		Date datecurrent = new Date();
	
		
	}
	float add;
	float fast;
	
	public void setAddFast(float add, float fast){
		this.add = add;
		this.fast = fast;
		
	}
	float fastcost;
	public void setCost(int days){
		variablecost =0;
		fastcost = 0;
		Log.d("Hej", "SetTextViews6");
		//Add the cost to add here 
		int i = 0;
		for(float l : databasearray){
			variablecost += l * add;
			fastcost += l * fast;
			i++;
			if(i == days){
				
				break;
			}
		}
		Log.d("Hej", "SetTextViews7");
		this.variablereturn = variablecost;
		
		this.fastreturn = fastcost;
		
		
		if( Math.max(variablereturn, fastreturn) > allMax){
			allMax = Math.max(variablereturn,fastreturn);
			
		}
	}
	

	
	
	
	public float getMax(){
		return variablereturn;
	}
	public float getMin(){
		return fastreturn;
	}
	
	public float getMedelMonth(){
		return medel;
	}
	public float getMaxMonth(){
		return variablereturn;
	}
	public float getMinMonth(){
		return fastreturn;
	}
	
	public float getAllMax() {
		return allMax;
	}

	
	
	public float getVariableCost(){
		return variablecost;
	}
	
	public float getYesterday() {

		return databasearray.get(databasearray.size()-1);
	}

	public void setThisMonth() {
		setCost(31);
		
	}

	public void setThisYear() {
		setCost(365);
		
	}

	public void setThisWeek() {
		setCost(7);
		
	}

	public void setThisYesterday() {
		setCost(1);
		
	}

	public void setThisAllTime() {
		setCost(1000000000);
		
	}

	public void setThisSpecificTime(int daysBetween) {
		setCost(daysBetween);
		
	}





	public void clean() {
		variablereturn = 0;
		fastreturn = 0;
		allMax = 0;
		// TODO Auto-generated method stub
		
	}




	

	

	
}


