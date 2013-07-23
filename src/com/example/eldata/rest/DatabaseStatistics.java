package com.example.eldata.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class DatabaseStatistics {
	Database database;
	float max = 0;
	float min = 100000;
	float totalsum = 0;
	float medel = 0;
	ArrayList<Float> databasearray = new ArrayList<Float>();
	Calendar c; 
	float allMax = 0;
	
	public DatabaseStatistics(){
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
		this.max = variablecost;
		
		this.min = fastcost;
		this.medel = 3000;
		if(Math.max(max, Math.max(medel, min)) > allMax){
			allMax = Math.max(max, Math.max(medel, min));
			
		}
	}
	
	private void setStastic(int days){
		max = 0;
		min = databasearray.get(0);
		medel = 0;
		totalsum = 0;
		int i = 0;
		for(float l: databasearray){
			if(l > max){
				max = l;
			}
			if(l < min){
				min = l;
			}
			totalsum += l;
			i++;
			if(days == i){
				break;
			}
		}
		if(i == days){
			medel =totalsum/days;
		}
		else{
		medel = totalsum/databasearray.size();}
		if(max > allMax){
			allMax = max;
		}
		
	}
	
	public float getTotalSum(){
		return totalsum;
	}
	public float getMedel(){
		return medel;
	}
	public float getMax(){
		return max;
	}
	public float getMin(){
		return min;
	}
	
	public float getMedelMonth(){
		return medel;
	}
	public float getMaxMonth(){
		return max;
	}
	public float getMinMonth(){
		return min;
	}
	
	public float getAllMax() {
		
		return allMax;
	}

	float variablecost;
	public float getCost(float add){
		variablecost =0;
		Log.d("Hej", "SetTextViews6");
		//Add the cost to add here 
		int i = 0;
		for(float l : databasearray){
			variablecost += l * add;
			i++;
			if(i == 365){
				break;
			}
		}
		Log.d("Hej", "SetTextViews7");
		return variablecost;
	}
	
	public float getVariableCost(){
		return variablecost;
	}
	
	public float getYesterday() {

		return databasearray.get(databasearray.size()-1);
	}

	public void setThisMonth() {
		setStastic(31);
		
	}

	public void setThisYear() {
		setStastic(365);
		
	}

	public void setThisWeek() {
		setStastic(7);
		
	}

	public void setThisYesterday() {
		setStastic(1);
		
	}

	public void setThisAllTime() {
		setStastic(1000000000);
		
	}

	public void setThisSpecificTime(int daysBetween) {
		setStastic(daysBetween);
		
	}


	

	
}


