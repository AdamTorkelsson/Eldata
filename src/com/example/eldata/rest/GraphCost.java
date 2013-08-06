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

public class GraphCost extends View{
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
	float tabell3;
	float tabell2;
	float tabell1;
	DatabaseStatistics ds;
	
	String tabellvalue;
	String tabellvaluesmall;
	String tabellvaluebig;
	
	String tabell1string= "Fast";
	String tabell2string= "Rörlig";
	String tabell3string= "Skillnad";
	
	float tabellheight1 = 0;
	float tabellheight2 = 0;
	float tabellheight3 = 0;
	
	int turn = 1;

	float allMax = 0;
	double fixedheight;
	double fixedwidth;
	float sizeValue;
	float sizePrefix;
	float sizeTitle;
	double c = 0;
	double unknown = 0;
	float medeldiff = tabell1 / 50;
	float maxdiff = tabell1 / 50;
	float mindiff = tabell1 / 50;
	
	
	
    private static final String PREFS_NAME = "UserInfo";
    
	public GraphCost(Context context) {
		super(context);
		//WATCH AS IT IS CHANGED TO INT
		first = true;
		type = "priceCompare";
	}
	

	public GraphCost(Context context, AttributeSet attrs) {
		super( context, attrs );
	//WATCH AS IT IS CHANGED TO INT
		first = true;
		type = "priceCompare";
	}
 
	public GraphCost(Context context, AttributeSet attrs, int defStyle) {
 
		super( context, attrs, defStyle );
		//WATCH AS IT IS CHANGED TO INT
		first = true;
		type = "priceCompare";
	}




float lasttext;

public void setText(String text){
	
}

	public void setTime( float max, float min,float allMax) {
		isfinished = false;
		this.tabell3 =  Math.abs(max - min);
		this.tabell2 = min;
		this.tabell1 = max;
	
		this.allMax = allMax;
	
	}

	
	
	
	
	
	private void startUpPriceCompare(Canvas canvas){
		isfinished = false;
		tabellvaluesmall = "kr";
		tabellvaluebig = "tkr";
		tabellvalue = "kr";
		
		
		this.fixedwidth = canvas.getWidth()/10;

		this.sizeValue = setTextSize(String.format("%.0f",Math.max(tabell1, Math.max(tabell2, tabell3))),fixedwidth,8);
		this.sizePrefix = setTextSize("kr",fixedwidth,2);
		this.sizeTitle = setTextSize("Variable",fixedwidth,6);
		
		this.first = false;
		
		this.c = canvas.getHeight() -sizeValue - sizeTitle - 20;		
		this.fixedheight =c/unknown;
		
	}
	
	private void startUpPeaks(Canvas canvas){
		isfinished = false;
		tabellvaluesmall = "kwh";
		tabellvaluebig = "Mwh";
		tabellvalue = "kwh";
		
		
		this.fixedwidth = canvas.getWidth()/10;

		this.sizeValue = setTextSize(String.format("%.0f",Math.max(tabell1, Math.max(tabell2, tabell3))),fixedwidth,6);
		this.sizePrefix = setTextSize("Kwh",fixedwidth,3);
		this.sizeTitle = setTextSize("Variable",fixedwidth,6);
		
		this.first = false;
		
		this.c = canvas.getHeight() -sizeValue - sizeTitle - 20;		
		this.fixedheight =c/unknown;
		
	}
	
	String type;
	Paint pBlue;
	@Override
	protected void onDraw(Canvas canvas) {
		
		
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		pBlue = new Paint();
		pBlue.setStrokeWidth(5);
		
		this.unknown = allMax;
	
		if(type == "priceCompare"){
			if(this.first){
	
				startUpPriceCompare(canvas);

				

			}
		onDrawPrice(canvas);}
		else if(type == "peaks"){
			if(this.first){
				startUpPeaks(canvas);
				
			}
			onDrawPrice(canvas);
		}
		else if(type == "ten last"){
			if(this.first){
			
			}
		}

		
	}
	
	
	
private void onDrawPrice(Canvas canvas) {

	switch (turn){
	case 1:
		
		tabellheight1 += tabell1/30;
		drawTabell(canvas,pBlue,tabellheight1,"#127990",1,3);
		if(tabellheight1 >= tabell1){
			tabellheight1 = tabell1;
			turn = 2;
		}
		break;
		
	case 2:
		tabellheight2 += tabell1/30;
		drawTabell(canvas,pBlue,tabellheight1,"#127990",1,3);
		drawTabell(canvas,pBlue,tabellheight2,"#72336b",4,6);
		
		if(tabellheight2 >= tabell2){
			tabellheight2 = tabell2;
			turn = 3;
		}
		break;
	case 3:
		tabellheight3 += tabell1/30;
		drawTabell(canvas,pBlue,tabellheight1,"#127990",1,3);
		drawTabell(canvas,pBlue,tabellheight2,"#72336b",4,6);
		drawTabell(canvas,pBlue,tabellheight3,"#779438",7,9);
		if(tabellheight3 >= tabell3){
			tabellheight3 = tabell3;
			turn = 4;
		}
		break;
		
	case 4:
		


		if(tabellheight3 != tabell3){
			tabellheight3 += medeldiff/20;

			if(Math.abs(tabellheight3 -tabell3) < Math.abs((medeldiff*1.5)/20)){
				tabellheight3 = tabell3;
			}
			}
		
		
		
		if(tabellheight1 != tabell1){
			tabellheight1 += maxdiff/20;
			if(Math.abs(tabellheight1 -tabell1) < Math.abs((maxdiff*1.5)/20)){
				tabellheight1 = tabell1;
			}
		}
		
		if(tabellheight2 != tabell2){
			tabellheight2 += mindiff/20;
			if(Math.abs(tabellheight2 -tabell2) < 
					Math.abs((mindiff*1.5)/20)){
				tabellheight2 = tabell2;
			}
		}

		drawTabell(canvas,pBlue,tabellheight1,"#127990",1,3);
		drawTabell(canvas,pBlue,tabellheight2,"#72336b",4,6);
		drawTabell(canvas,pBlue,tabellheight3,"#779438",7,9);
		
		if(tabellheight2 == tabell2 &&
				tabellheight3 == tabell3 &&
				tabellheight1 == tabell1){
			isfinished = true;
			Log.d("", "Tjaadam");
			drawText(canvas,pBlue);
			}
		
		}

	invalidate();
// TODO Auto-generated method stub
		
	}
boolean isfinished = false;
int size = 8;

public void drawNew( float tabell1, float tabell2, float max , String type){
	isfinished = false;
	if(this.type != type){
		turn = 1;
		tabellheight1 = 0;
		tabellheight2 = 0;
		tabellheight3 = 0;
		
		this.tabell3 = Math.abs(tabell1 - tabell2);
		this.tabell1 = tabell1;
		this.tabell2 = tabell2;
		
		first = true;
		this.type = type;
		
		this.allMax = max;
	
		if(type == "peaks"){
			size = 7;

			tabell1string= "Max";
			tabell2string= "Min";
			tabell3string= "Medel";
		}
		else if(type == "priceCompare"){
			size = 8;
			tabell1string= "Fast";
			tabell2string= "Rörlig";
			tabell3string= "Skillnad";
		}
	}
	else if(tabellheight1 != tabellheight1){
		tabell1 = tabellheight1;
		tabell2 = tabellheight2;
		tabell3 = tabellheight3;
	}


	if(("" + tabell2).length() > ("" + tabell1).length())
		this.sizeValue = setTextSize(String.format("%.0f", tabell2),fixedwidth,size);
	else{
		this.sizeValue = setTextSize(String.format("%.0f", tabell1),fixedwidth,size);
	}
	
			medeldiff =  Math.abs(tabell1 - tabell2) - this.tabell3;
			maxdiff =  tabell1 - this.tabell1;
			mindiff = tabell2 - this.tabell2;
		
			this.tabell3 = Math.abs(tabell1 - tabell2);
			this.tabell1 = tabell1;
			this.tabell2 = tabell2;
		
		
	}

float tabell1Unchanged1;
float tabell1Unchanged2;
float tabell1Unchanged3;



private float setTextSize(String s, double i , int width){
	String newstring;
	Paint p = new Paint();
	while(s.length() < 2){
		s = s + " ";}
	if(s.length()>5){
		for(int j = 0; j < s.length() ;j++){
			
		}
		
		tabellvalue = tabellvaluebig;
	}
	if(s.length()<5){
		tabellvalue = tabellvaluesmall;
	}
	
	float textsize = 40;
	i = i*(width*2)/10;
	float step = (float) 0.1;
	
	while(i > p.measureText(s)){
		textsize =textsize+step;
		p.setTextSize(textsize);
		
		}
	while(i < p.measureText(s)){
		textsize =textsize-step;
		
		p.setTextSize(textsize);
	}
	
	
	return textsize;
	
}

		private void drawText(Canvas canvas, Paint pBlue) {
			
	
			pBlue.setColor(Color.BLACK);
			pBlue.setTextSize(sizeValue);
			lasttext = pBlue.measureText(String.format("%.0f", tabell2));
		
			canvas.drawText("" + String.format("%.0f", tabell1), (float) (fixedwidth*1), (float) (canvas.getHeight() - fixedheight*tabell1- 20), pBlue);
			canvas.drawText("" + String.format("%.0f", tabell2), (float) (fixedwidth*4), (float) (canvas.getHeight() - fixedheight*tabell2 - 20), pBlue);
			canvas.drawText("" + String.format("%.0f", tabell3), (float) (fixedwidth*7), (float) (canvas.getHeight() - fixedheight*tabell3- 20), pBlue);
			
			
		
			pBlue.setTextSize(sizePrefix);
			pBlue.setTextAlign(Align.RIGHT);
			
			canvas.drawText(tabellvalue, (float) (fixedwidth*6) , (float) (canvas.getHeight() - fixedheight*tabell2- 20), pBlue);
			canvas.drawText(tabellvalue, (float) (fixedwidth*9), (float) (canvas.getHeight() - fixedheight*tabell3- 20), pBlue);
			canvas.drawText(tabellvalue, (float) (fixedwidth*3), (float) (canvas.getHeight() - fixedheight*tabell1- 20), pBlue);
			
			pBlue.setTextAlign(Align.LEFT);
			pBlue.setTextSize(sizeTitle);
			
			canvas.drawText(tabell1string, (float) (fixedwidth*1), (float) (canvas.getHeight() - fixedheight*tabell1-sizeValue- 20), pBlue);
			canvas.drawText(tabell2string, (float) (fixedwidth*4), (float) (canvas.getHeight() - fixedheight*tabell2-sizeValue- 20), pBlue);
			canvas.drawText(tabell3string, (float) (fixedwidth*7), (float) (canvas.getHeight() - fixedheight*tabell3-sizeValue- 20), pBlue);
			
			
		
			
		}



		
		
		private void drawTabell(Canvas canvas, Paint pBlue , float tabellheight, String color, int start , int end ) {
			pBlue.setColor(Color.parseColor(color));

			Rect r = new Rect();
			r.set((int) fixedwidth*start
					, (int) (canvas.getHeight()- 20 - fixedheight*tabellheight)
					,(int) fixedwidth*end, canvas.getHeight());
			canvas.drawRect(r, pBlue);
		}


		public boolean isFinished() {
			
			return isfinished;
		}

		

/*	private void drawMin(Canvas canvas, Paint pBlue) {
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
		
	}*/
	



}
