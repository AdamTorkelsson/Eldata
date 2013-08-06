package com.example.eldata.rest;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GraphUse extends View{
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
    
	public GraphUse(Context context) {
		super(context);
		//WATCH AS IT IS CHANGED TO INT
		first = true;
	}
	

	public GraphUse(Context context, AttributeSet attrs) {
		super( context, attrs );
	//WATCH AS IT IS CHANGED TO INT
		first = true;
	}
 
	public GraphUse(Context context, AttributeSet attrs, int defStyle) {
 
		super( context, attrs, defStyle );
		//WATCH AS IT IS CHANGED TO INT
		first = true;
	}

float allMax = 0;



	public void setTime(float medel, float max, float min,float allMax) {
		this.medel =  medel;
		this.min = min;
		this.max = max;
		this.allMax = allMax;
		Log.d("1", min+ "minimal");
	}

	double fixedheight;
	double fixedwidth;
	float sizeSeven;
	float sizeThree;
	float sizeSix;

	@Override
	protected void onDraw(Canvas canvas) {

		
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		Log.d("AdamTorkelsson", "AdamPaint1");
		Paint pBlue = new Paint();
		pBlue.setStrokeWidth(5);
		if(this.first){
			double m = allMax;
			
			this.fixedwidth = canvas.getWidth()/10;
			
			this.sizeSeven = setTextSize(String.format("%.2f", min),fixedwidth,7);
			this.sizeThree = setTextSize("kwh",fixedwidth,3);
			this.sizeSix = setTextSize("medel",fixedwidth,6);
			this.first = false;
			
			double c = canvas.getHeight() -sizeSeven - sizeSix;
			this.fixedheight =c/m;
		}
		
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
				drawText(canvas,pBlue);}
			}
		
		

		
		


		invalidate();

	}
	Boolean medelboolean = true;
	Boolean minboolean = true;
	Boolean maxboolean = true;
	float medeldiff = max / 50;
	float maxdiff = max / 50;
	float mindiff = max / 50;
	
public void drawNew(float medel, float max, float min){

			medeldiff = medel -this.medel;

			maxdiff =  max - this.max;
		
			mindiff = min - this.min ;
		
		
		this.medel = medel;
		this.max = max;
		this.min = min;
		
	}
private float setTextSize(String s, double i , int width){
	Paint p = new Paint();
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
		private void drawText(Canvas canvas, Paint pBlue) {
			Log.d("", "LetaFel3");
			
	
			pBlue.setColor(Color.BLACK);
			pBlue.setTextSize(sizeSeven);
			float lasttext = pBlue.measureText(String.format("%.2f", min));

			canvas.drawText("" + String.format("%.2f", min), (float) (fixedwidth*4), (float) (canvas.getHeight() - fixedheight*min), pBlue);
			canvas.drawText("" + String.format("%.2f", medel), (float) (fixedwidth*7), (float) (canvas.getHeight() - fixedheight*medel), pBlue);
			canvas.drawText("" + String.format("%.2f", max), (float) (fixedwidth*1), (float) (canvas.getHeight() - fixedheight*max), pBlue);
		
			pBlue.setTextSize(sizeThree);
			
			canvas.drawText("kwh", (float) (fixedwidth*4+lasttext) , (float) (canvas.getHeight() - fixedheight*min), pBlue);
			canvas.drawText("kwh", (float) (fixedwidth*7+lasttext), (float) (canvas.getHeight() - fixedheight*medel), pBlue);
			canvas.drawText("kwh", (float) (fixedwidth*1+lasttext), (float) (canvas.getHeight() - fixedheight*max), pBlue);
			
			pBlue.setTextSize(sizeSix);
			
			
			canvas.drawText("Min", (float) (fixedwidth*4), (float) (canvas.getHeight() - fixedheight*min-sizeSeven), pBlue);
			canvas.drawText("Medel", (float) (fixedwidth*7), (float) (canvas.getHeight() - fixedheight*medel-sizeSeven), pBlue);
			canvas.drawText("Max", (float) (fixedwidth*1), (float) (canvas.getHeight() - fixedheight*max-sizeSeven), pBlue);
			
			
			
			
		}
		float tabellheight1 = 0;
		float tabellheight2 = 0;
		float tabellheight3 = 0;
		int turn = 1;


		
		
		private void drawMax(Canvas canvas, Paint pBlue) {
			pBlue.setColor(Color.parseColor("#127990"));
			

			Rect r = new Rect();
			r.set((int) fixedwidth*1, (int) (canvas.getHeight()-fixedheight*tabellheight1),(int) fixedwidth*3, canvas.getHeight());
			canvas.drawRect(r, pBlue);
		}

		

	private void drawMin(Canvas canvas, Paint pBlue) {
		pBlue.setColor(Color.parseColor("#72336b"));

		Rect r = new Rect();
		r.set((int) fixedwidth*4, (int) (canvas.getHeight()-fixedheight*tabellheight2),(int) fixedwidth*6, canvas.getHeight());
		canvas.drawRect(r, pBlue);
		
	}
	
	private void drawMedel(Canvas canvas, Paint pBlue) {
		pBlue.setColor(Color.parseColor("#779438"));

		Rect r = new Rect();
		r.set((int) fixedwidth*7, (int) (canvas.getHeight()-fixedheight*tabellheight3),(int) fixedwidth*9, canvas.getHeight());
		canvas.drawRect(r, pBlue);
		
	}
	



}
