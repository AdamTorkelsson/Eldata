package com.example.eldata.rest;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
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

	public Graph(Context context) {
		super(context);
		variablecost = 1000;
		fastcost = 500;
	}
	

	public Graph(Context context, AttributeSet attrs) {
		super( context, attrs );
		variablecost = 1000;
		fastcost = 500;
	}
 
	public Graph(Context context, AttributeSet attrs, int defStyle) {
 
		super( context, attrs, defStyle );
		variablecost = 1000;
		fastcost = 500;
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
			
			tabellheight1 += 10;
			drawCostFast(canvas,pBlue);
			if(tabellheight1 >= fastcost){
				tabellheight1 = fastcost;
				turn = 2;
			}
			break;
		case 2:
			tabellheight2 += 10;
			drawCostFast(canvas,pBlue);
			drawCostVariable(canvas,pBlue);
			if(tabellheight2 >= variablecost){
				tabellheight2 = variablecost;
				turn = 3;
			}
		case 3:
			Log.d("AdamTorkelsson", "AdamPaint2");
			drawCostFast(canvas,pBlue);
			drawCostVariable(canvas,pBlue);}
		

		
		


		invalidate();

	}
		int tabellheight1 = 0;
		int tabellheight2 = 0;
		int turn = 1;


		
		
		private void drawCostFast(Canvas canvas, Paint pBlue) {
			pBlue.setColor(Color.GRAY);
			double m = Math.max(variablecost, fastcost);
			double c = canvas.getHeight();
			double fixedheight =c/m;
			int fixedwidth = canvas.getWidth()/5;
			Log.d("Adam", "AdamPaint6" + fixedheight);
			Log.d("Adam", "AdamPaint7" + canvas.getHeight());
			Log.d("Adam", "AdamPaint8" + Math.max(variablecost, fastcost));
			Rect r = new Rect();
			r.set(fixedwidth, (int) (canvas.getHeight()-fixedheight*tabellheight1), fixedwidth*2, canvas.getHeight());
			canvas.drawRect(r, pBlue);
		}

		

	private void drawCostVariable(Canvas canvas, Paint pBlue) {
		pBlue.setColor(Color.GREEN);
		double m = Math.max(variablecost, fastcost);
		double c = canvas.getHeight();
		double fixedheight =c/m;
		int fixedwidth = canvas.getWidth()/5;
		Rect r = new Rect();
		r.set(fixedwidth*3, (int) (canvas.getHeight()-fixedheight*tabellheight2), fixedwidth*4, canvas.getHeight());
		canvas.drawRect(r, pBlue);
		
	}
	
	



}
