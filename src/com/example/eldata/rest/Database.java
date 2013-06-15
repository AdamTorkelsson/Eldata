package com.example.eldata.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;

public class Database {
	private static final String PREFS_NAME = "UserInfo";
	ArrayList<Float> userhistory = new ArrayList<Float>();
	ArrayList<Float> weekhistory;
	ArrayList<Float> yearhistory;

	public Database(){
		fakeHistory();
		
	
}
	private void downloadNew(){
		Calendar calendar = Calendar.getInstance();
		
		
	}
	public ArrayList getAll(){
		return userhistory;
		
	}
	
	

	private void fakeHistory() {
		Random rg = new Random();
		
		for(int i = 0; i<2000;i++){
			userhistory.add(rg.nextFloat()*7 + 1);
		}
	

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
			dayhistory.add(rg.nextFloat()*200);}
		return dayhistory;
	}
}
