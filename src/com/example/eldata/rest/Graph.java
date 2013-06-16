package com.example.eldata.rest;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Graph extends View{
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
	int variablecost;
	int fastcost;
	DatabaseStatistics ds;
    private static final String PREFS_NAME = "UserInfo";
    
	public Graph(Context context) {
		super(context);
		//WATCH AS IT IS CHANGED TO INT
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
		Log.d("fastpris", "fastpris" + settings.getString("FastPris", "error"));
		ds = new DatabaseStatistics();
		variablecost = (int) ds.getCost(Float.parseFloat(settings.getString("Add", "Error")));
		fastcost = (int) ds.getCost(Float.parseFloat(settings.getString("FastPris", "Error")));
	}
	

	public Graph(Context context, AttributeSet attrs) {
		super( context, attrs );
	//WATCH AS IT IS CHANGED TO INT
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
		Log.d("fastpris", "fastpris" + settings.getString("FastPris", "error"));
		ds = new DatabaseStatistics();
		variablecost = (int) ds.getCost(Float.parseFloat(settings.getString("Add", "Error")));
		fastcost = (int) ds.getCost(Float.parseFloat(settings.getString("FastPris", "Error")));
	}
 
	public Graph(Context context, AttributeSet attrs, int defStyle) {
 
		super( context, attrs, defStyle );
		//WATCH AS IT IS CHANGED TO INT
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
		Log.d("fastpris", "fastpris" + settings.getString("FastPris", "error"));
		ds = new DatabaseStatistics();
		variablecost = (int) ds.getCost(Float.parseFloat(settings.getString("Add", "Error")));
		fastcost = (int) ds.getCost(Float.parseFloat(settings.getString("FastPris", "Error")));
	}





	@Override
	protected void onDraw(Canvas canvas) {

		
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		Log.d("AdamTorkelsson", "AdamPaint1");
		Paint pBlue = new Paint();
		pBlue.setStrokeWidth(5);

		
		switch (turn){
		case 1:
			
			tabellheight1 += fastcost/80;
			drawCostFast(canvas,pBlue);
			if(tabellheight1 >= fastcost){
				tabellheight1 = fastcost;
				turn = 2;
			}
			break;
		case 2:
			tabellheight2 += variablecost/80;
			drawCostFast(canvas,pBlue);
			drawCostVariable(canvas,pBlue);
			if(tabellheight2 >= variablecost){
				tabellheight2 = variablecost;
				turn = 3;
			}
			break;
		case 3:
			Log.d("AdamTorkelsson", "AdamPaint2");
			drawText(canvas,pBlue);
			
			drawCostFast(canvas,pBlue);
			drawCostVariable(canvas,pBlue);}
		

		
		


		invalidate();

	}
		private void drawText(Canvas canvas, Paint pBlue) {
			double m = Math.max(variablecost, fastcost);
			double c = canvas.getHeight() - 70;
			double fixedheight =c/m;
			pBlue.setColor(Color.BLACK);
			pBlue.setTextSize(50);
			int fixedwidth = canvas.getWidth()/9;
			canvas.drawText("" + fastcost + "kr", fixedwidth*5, (float) (canvas.getHeight() - fixedheight*fastcost), pBlue);
			pBlue.setTextSize(20);
			canvas.drawText("Fast", fixedwidth*5, (float) (canvas.getHeight() - fixedheight*fastcost-50), pBlue);
			pBlue.setTextSize(50);
			canvas.drawText("" + variablecost + "kr", fixedwidth*2, (float) (canvas.getHeight() - fixedheight*variablecost), pBlue);
			pBlue.setTextSize(20);
			canvas.drawText("Rörlig", fixedwidth*2, (float) (canvas.getHeight() - fixedheight*variablecost-50), pBlue);
			
		}
		int tabellheight1 = 0;
		int tabellheight2 = 0;
		int turn = 1;


		
		
		private void drawCostFast(Canvas canvas, Paint pBlue) {
			pBlue.setColor(Color.parseColor("#127990"));
			
			double m = Math.max(variablecost, fastcost);
			double c = canvas.getHeight() -70;
			double fixedheight =c/m;
			int fixedwidth = canvas.getWidth()/9;
			Log.d("Adam", "AdamPaint6" + fixedheight);
			Log.d("Adam", "AdamPaint7" + canvas.getHeight());
			Log.d("Adam", "AdamPaint8" + Math.max(variablecost, fastcost));
			Rect r = new Rect();
			r.set(fixedwidth*5, (int) (canvas.getHeight()-fixedheight*tabellheight1), fixedwidth*7, canvas.getHeight());
			canvas.drawRect(r, pBlue);
		}

		

	private void drawCostVariable(Canvas canvas, Paint pBlue) {
		pBlue.setColor(Color.parseColor("#72336b"));
		double m = Math.max(variablecost, fastcost);
		double c = canvas.getHeight() - 70;
		double fixedheight =c/m;
		int fixedwidth = canvas.getWidth()/9;
		Rect r = new Rect();
		r.set(fixedwidth*2, (int) (canvas.getHeight()-fixedheight*tabellheight2), fixedwidth*4, canvas.getHeight());
		canvas.drawRect(r, pBlue);
		
	}
	
	



}
