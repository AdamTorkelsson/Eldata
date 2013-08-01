package com.example.eldata.rest;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
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

public class GraphTenDays extends View{
	float days = 28;
	Bitmap cloud;
	float x = 0;
	float y = 0;
	int xb = 0;
	int yb = 1;
	Paint paint = new Paint();
	Random rg;
	float k =0;
	int a = 10;
	ArrayList<Float> number;
	ArrayList<Float> number2;
	float[] energyUse;
	int width;
	int difference = 0;
	boolean first = true;
	float medel;
	float min;
	float max;
	DatabaseStatistics ds;
    private static final String PREFS_NAME = "UserInfo";
    
	public GraphTenDays(Context context) {
		super(context);
		//WATCH AS IT IS CHANGED TO INT
		first = true;
	}
	

	public GraphTenDays(Context context, AttributeSet attrs) {
		super( context, attrs );
	//WATCH AS IT IS CHANGED TO INT
		first = true;
	}
 
	public GraphTenDays(Context context, AttributeSet attrs, int defStyle) {
 
		super( context, attrs, defStyle );
		//WATCH AS IT IS CHANGED TO INT
		first = true;
	}

float allMax = 0;



	public void setTime(float medel, float max, float min,float allMax) {
		this.medel =  Math.abs(max - min);
		this.min = min;
		this.max = max;
		this.allMax = max;
		this.maxTemp = allMax;
	}

	double fixedheight;
	double fixedwidth;
	float sizeSeven;
	float sizeThree;
	float sizeSix;
	double c = 0;
	double m = 0;
	@Override
	protected void onDraw(Canvas canvas) {

		
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		Log.d("AdamTorkelsson", "AdamPaint1");
		Paint pBlue = new Paint();
		pBlue.setStrokeWidth(5);
		this.m = allMax;
		
		if(this.first){
			
		
			this.fixedwidth = canvas.getWidth()/10;

			this.sizeSeven = setTextSize(String.format("%.0f",Math.max(max, Math.max(min, medel))),fixedwidth,8);
			this.sizeThree = setTextSize("kr",fixedwidth,2);
			this.sizeSix = setTextSize("Variable",fixedwidth,6);
			this.first = false;
			
			this.c = canvas.getHeight() -sizeSeven - sizeSix - 20;
			
		}
		this.fixedheight =c/m;
		
		switch (turn){
		case 1:
			
			tabellheight1 += max/50;
			drawMax(canvas,pBlue);
			if(tabellheight1 >= max){
				tabellheight1 = max;
				turn = 2;
			}
			break;
		case 3:
			tabellheight3 += max/50;
			drawMax(canvas,pBlue);
			drawMin(canvas,pBlue);
			drawMedel(canvas,pBlue);
			if(tabellheight3 >= medel){
				tabellheight3 = medel;
				turn = 4;
			}
			break;
			
		case 2:
			tabellheight2 += max/50;
			drawMax(canvas,pBlue);
			drawMin(canvas,pBlue);
			
			if(tabellheight2 >= min){
				tabellheight2 = min;
				turn = 3;
			}
			break;
		case 4:
			


			if(tabellheight3 != medel){
				tabellheight3 += medeldiff/20;

				if(Math.abs(tabellheight3 -medel) < Math.abs((medeldiff*1.5)/20)){
					tabellheight3 = medel;
				}
				}
			
			
			
			if(tabellheight1 != max){
				tabellheight1 += maxdiff/20;
				if(Math.abs(tabellheight1 -max) < Math.abs((maxdiff*1.5)/20)){
					tabellheight1 = max;
				}
			}
			
			if(tabellheight2 != min){
				tabellheight2 += mindiff/20;
				if(Math.abs(tabellheight2 -min) < 
						Math.abs((mindiff*1.5)/20)){
					tabellheight2 = min;
				}
			}
	
			

		
			
			drawMax(canvas,pBlue);
			drawMin(canvas,pBlue);
			drawMedel(canvas,pBlue);
			
			if(tabellheight2 == min &&
					tabellheight3 == medel &&
					tabellheight1 == max){
				drawText(canvas,pBlue);
				}
			
			}

		invalidate();

	}
	
	float medeldiff = max / 50;
	float maxdiff = max / 50;
	float mindiff = max / 50;
	float maxTemp;
	
public void drawNew(float medel, float max, float min){
	if(("" + min).length() > ("" + max).length())
		this.sizeSeven = setTextSize(String.format("%.0f", min),fixedwidth,8);
	else{
		this.sizeSeven = setTextSize(String.format("%.0f", max),fixedwidth,8);
	}
			medeldiff =  Math.abs(max - min) - this.medel;
			maxdiff =  max - this.max;
			mindiff = min - this.min;
		
		maxTemp =  Math.max(max, Math.max(medel, min));
		maxNumberDiff = maxTemp - allMax;
		
		
		this.medel = Math.abs(max - min);
		this.max = max;
		this.min = min;
		
		
	}
float maxNumberDiff;




private float setTextSize(String s, double i , int width){
	Paint p = new Paint();
	while(s.length() < 2){
		s = s + " ";}
	float textsize = 40;
	i = i*(width*2)/10;
	float step = (float) 0.1;
	
	while(i > p.measureText(s)){
		textsize =textsize+step;
		p.setTextSize(textsize);
		Log.d("", "letafela" + p.measureText(s));
		}
	while(i < p.measureText(s)){
		textsize =textsize-step;
		Log.d("", "letafelaa" + p.measureText(s));
		p.setTextSize(textsize);
	}
	
	return textsize;
	
}
float lasttext;
		private void drawText(Canvas canvas, Paint pBlue) {
			Log.d("", "LetaFel3");
			
	
			pBlue.setColor(Color.BLACK);
			pBlue.setTextSize(sizeSeven);
			lasttext = pBlue.measureText(String.format("%.0f", min));
			
			canvas.drawText("" + String.format("%.0f", min), (float) (fixedwidth*4), (float) (canvas.getHeight() - fixedheight*min - 20), pBlue);
			canvas.drawText("" + String.format("%.0f", medel), (float) (fixedwidth*7), (float) (canvas.getHeight() - fixedheight*medel- 20), pBlue);
			canvas.drawText("" + String.format("%.0f", max), (float) (fixedwidth*1), (float) (canvas.getHeight() - fixedheight*max- 20), pBlue);
		
			pBlue.setTextSize(sizeThree);
			pBlue.setTextAlign(Align.RIGHT);
			
			canvas.drawText("kr", (float) (fixedwidth*6) , (float) (canvas.getHeight() - fixedheight*min- 20), pBlue);
			canvas.drawText("kr", (float) (fixedwidth*9), (float) (canvas.getHeight() - fixedheight*medel- 20), pBlue);
			canvas.drawText("kr", (float) (fixedwidth*3), (float) (canvas.getHeight() - fixedheight*max- 20), pBlue);
			
			pBlue.setTextAlign(Align.LEFT);
			pBlue.setTextSize(sizeSix);
			
			
			canvas.drawText("Fast", (float) (fixedwidth*4), (float) (canvas.getHeight() - fixedheight*min-sizeSeven- 20), pBlue);
			canvas.drawText("Skillnad", (float) (fixedwidth*7), (float) (canvas.getHeight() - fixedheight*medel-sizeSeven- 20), pBlue);
			canvas.drawText("Rörlig", (float) (fixedwidth*1), (float) (canvas.getHeight() - fixedheight*max-sizeSeven- 20), pBlue);
			
			
			
			
		}
		float tabellheight1 = 0;
		float tabellheight2 = 0;
		float tabellheight3 = 0;
		int turn = 1;


		
		
		private void drawMax(Canvas canvas, Paint pBlue) {
			pBlue.setColor(Color.parseColor("#127990"));
			

			Rect r = new Rect();
			r.set((int) fixedwidth*1, (int) (canvas.getHeight()- 20 - fixedheight*tabellheight1),(int) fixedwidth*3, canvas.getHeight());
			canvas.drawRect(r, pBlue);
		}

		

	private void drawMin(Canvas canvas, Paint pBlue) {
		pBlue.setColor(Color.parseColor("#72336b"));

		Rect r = new Rect();
		r.set((int) fixedwidth*4, (int) (canvas.getHeight()- 20-fixedheight*tabellheight2),(int) fixedwidth*6, canvas.getHeight());
		canvas.drawRect(r, pBlue);
		
	}
	
	private void drawMedel(Canvas canvas, Paint pBlue) {
		pBlue.setColor(Color.parseColor("#779438"));

		Rect r = new Rect();
		r.set((int) fixedwidth*7, (int) (canvas.getHeight()- 20-fixedheight*tabellheight3),(int) fixedwidth*9, canvas.getHeight());
		canvas.drawRect(r, pBlue);
		
	}
	



}
