package com.example.eldata.rest;

import java.util.ArrayList;
import java.util.Random;

public class DatabasePrice {
	ArrayList<Float> userhistory = new ArrayList<Float>();
	ArrayList<Float> weekhistory;
	ArrayList<Float> yearhistory;
	
	public DatabasePrice(){
		fakeHistory();
	
}
	private void fakeHistory() {
		Random rg = new Random();
		for(int i = 0; i<365*2;i++){
			userhistory.add(rg.nextFloat());
		}
		
	}
	
	
	public ArrayList getAll(){
		return userhistory;
		
	}
	public ArrayList<Float> getLastWeek(){
		ArrayList<Float> lastweek = new ArrayList<Float>();
		for(int i = userhistory.size(); i > userhistory.size()-7;i--){
			lastweek.add(userhistory.get(i));}
		return lastweek;
	}
	
	public ArrayList getLastMonth(){
		ArrayList<Float> lastmonth = new ArrayList<Float>();
		for(int i = userhistory.size()-1; i > userhistory.size()-31;i--){
			lastmonth.add(userhistory.get(i));}
		return lastmonth;
		
	}
	public ArrayList getDay(int day){
		ArrayList<Float> dayhistory = new ArrayList<Float>();
		Random rg = new Random();
		for(int i = 0; i < 25;i++){
			dayhistory.add(rg.nextFloat());}
		return dayhistory;
	}
}
