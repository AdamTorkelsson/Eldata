package com.example.eldata;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Popuphelp {
	private Context context;
	
	public Popuphelp(Activity activity) {
		context = activity;
	}

	public void newPopup(int text) {
		standardDialog(text);
	}



	

			/**
			 * Creates the different popups and dialogs shown in the application
			 * @param context The context where the dialog is to be shown
			 */


			/**
			 * Contains the tutorial and moves this forward
			 * until the user have finished it or pressed cancel
			 */
	/*		public void tutorialDialog() {
				if(i==1){
					standardDialog(R.string.tutorialdialogview1,"Yes",true);
					i++;}
				else if(i==2){
					standardDialog(R.string.tutorialdialogview2,"Next",true);
					i++;}
				else if(i==3){
					standardDialog(R.string.tutorialdialogview3,"Next",true);
					i++;}
				else if(i==4){
					standardDialog(R.string.tutorialdialogview4,"Next",true);
					i++;}
				else if(i==5){
					standardDialog(R.string.tutorialdialogview5,"Next",true);
					i++;}
				else if(i==6){
					standardDialog(R.string.tutorialdialogview6,"Finish",false);
					i++;}


			}*/

			/**
			 *  A standard dialog, usually to be called when the user
			 * need to do something or be informed of something.
			 * @param stringValue The string to be shown in the dialog, resource value
			 * @param positiveButton The string to be shown on the positive button
			 * @param createloop Should be true if handling the tutorialDialog, else false
			 */
			private void standardDialog(int stringValue){
				//Gets the layout to be set in the dialog
				LayoutInflater li = LayoutInflater.from(context);
				View tutorialView= li.inflate(R.layout.tutorialdialogview, null);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

				//Sets the text inside of the dialog
				TextView textview = (TextView) tutorialView.findViewById(R.id.textView1);
				textview.setText(stringValue);

				//Sets the view of the dialog
				alertDialogBuilder.setView(tutorialView);
				//Sets the name of the buttons of the dialog
				alertDialogBuilder
				.setCancelable(true)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {		
					public void onClick(DialogInterface dialog,int id) {
						//If createloop is set true, which it only should be when called from tutorialDialog, starts the next tutorialDialog
						
							dialog.cancel();
					}

				});

				//Creates the alertDialog and sets it cancelable
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.setCancelable(true);


				//Displays the alertDialog
				alertDialog.show();
				//Sets the background of the buttons

				Button bp = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
				bp.setBackgroundColor(Color.parseColor("#F0F0F0"));
				

			}





		

		
	}


