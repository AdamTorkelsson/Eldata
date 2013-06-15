package com.example.eldata.rest;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GraphOld extends View{
	float days = 28;
	Bitmap cloud;
	float x = 0;
	float y = 0;
	int xb = 0;
	int yb = 1;
	Paint paint = new Paint();
	Random rg;
	float[] usedEnergy = new float[400];
	float[] costEnergy = new float[400];
	float[] currentCost = new float[400];
	float[] energyCost = new float[400];
	float k =0;
	int a = 10;
	ArrayList<Float> number;
	ArrayList<Float> number2;
	float[] energyUse;
	int width;
	int difference = 0;
	boolean first = true;


	public GraphOld(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Database database = new Database();
		number = database.getAll();
		Database database2 = new Database();
		number2 = database2.getAll();
		days = number.size();
		days = 24;
	}
	

	public GraphOld(Context context, AttributeSet attrs) {
		super( context, attrs );
		DatabaseStatistics database = new DatabaseStatistics();
		float variablecost = database.getVariableCost();
		//number = database.getAll();
		Database database2 = new Database();
		number2 = database2.getAll();
		days = number.size();
		days = 24;
	}
 
	public GraphOld(Context context, AttributeSet attrs, int defStyle) {
 
		super( context, attrs, defStyle );
		Database database = new Database();
		number = database.getAll();
		Database database2 = new Database();
		number2 = database2.getAll();
		days = number.size();
		days = 24;
	}

	public void setLength(int days){
		this.days = days;
	}




	@Override
	protected void onDraw(Canvas canvas) {

		
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if(first){
		//	difference = canvas.getHeight()/4;
			first = false;
		}


		Paint pBlue = new Paint();
		
		drawUsedEnergy(canvas,pBlue);
		
	//	drawCostOfEnergy(canvas,pBlue);
		

		if(xb < days*4 - 4){
		xb = xb+4;}
		else{
			if ( difference != 0){
				Log.d("", "difference" + difference);
				difference = difference - 1;}
		}





		invalidate();

	}


	private void drawUsedEnergy(Canvas canvas, Paint pBlue) {
		float[] temp = new float[xb*4];
		//float line = 10;
		float k = 15;
		int j = 0;

		float t = number.get(j)+canvas.getHeight()/3+difference;

		for(int i = 0; i < xb; i++){
			temp[i] = k;
			i++;
			temp[i] = t;
			j++;
			k = k + (canvas.getWidth()- 15)/days;
			Log.d("ADAM", "canvaswidth" + k);
			t = number.get(j)+canvas.getHeight()/3+difference;
			i++;
			temp[i] = k;
			i++;
			temp[i] = t;
		}
		pBlue.setColor(Color.BLACK);
	//	pBlue.setStrokeWidth(20);
		//canvas.drawPoints(temp, pBlue);
		pBlue.setStrokeWidth(5);
		pBlue.setTextSize(20);
		j = 0;
		canvas.drawLines(temp, pBlue);
		pBlue.setStrokeWidth(40);
		canvas.drawPoints(temp, pBlue);
		pBlue.setColor(Color.GRAY);
		pBlue.setTextAlign(Align.CENTER);
		for(int i = 0; i<xb;i++){
		
		canvas.drawText(" " + Math.round(number.get(j)) + " ", temp[i],temp[i +1], pBlue);
		i += 3;
		j++;}
		
	//	canvas.drawText(" " + Math.round(number.get(number.size()-1)) + " ", temp[temp.length-2],temp[temp.length-1], pBlue);
		
	}
	
	private void drawCostOfEnergy(Canvas canvas, Paint pBlue) {
		float[] temp = new float[xb*4];
		//float line = 10;
		float k = 15;
		int j = 0;

		float t = number2.get(j)+canvas.getHeight()/3-difference;

		for(int i = 0; i < xb; i++){
			temp[i] = k;
			i++;
			temp[i] = t;
			j++;
			k = k + (canvas.getWidth()- 15)/days;
			Log.d("ADAM", "canvaswidth" + k);
			t = number2.get(j)+canvas.getHeight()/3-difference;
			i++;
			temp[i] = k;
			i++;
			temp[i] = t;
		}
		pBlue.setColor(Color.RED);
	//	pBlue.setStrokeWidth(20);
		//canvas.drawPoints(temp, pBlue);
		pBlue.setStrokeWidth(5);
		canvas.drawLines(temp, pBlue);
		
	}



}
