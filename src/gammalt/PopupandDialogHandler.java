package gammalt;



import com.example.eldata.R;
import com.example.eldata.R.id;
import com.example.eldata.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
/**
 * Creates the different popups and dialogs shown in the application
 */
public class PopupandDialogHandler {
	private Context context;
	int i = 1;

	/**
	 * Creates the different popups and dialogs shown in the application
	 * @param context The context where the dialog is to be shown
	 */
	public PopupandDialogHandler(Context context){
		this.context = context;

	}

	/**
	 * Contains the tutorial and moves this forward
	 * until the user have finished it or pressed cancel
	 */
	/*public void tutorialDialog() {
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
	EditText numberAdd;
	EditText numberFast;
	
	/**
	 *  A standard dialog, usually to be called when the user
	 * need to do something or be informed of something.
	 * @param stringValue The string to be shown in the dialog, resource value
	 * @param positiveButton The string to be shown on the positive button
	 * @param createloop Should be true if handling the tutorialDialog, else false
	 */
	public void standardDialog(int stringValue, String positiveButton){
		//Gets the layout to be set in the dialog
		LayoutInflater li = LayoutInflater.from(context);
		View tutorialView= li.inflate(R.layout.billview, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		
		numberAdd = (EditText) tutorialView.findViewById(R.id.textadd);
		numberFast = (EditText) tutorialView.findViewById(R.id.textfast);
		

		//Sets the view of the dialog
		alertDialogBuilder.setView(tutorialView);
		//Sets the name of the buttons of the dialog
		alertDialogBuilder
		.setCancelable(true)
		.setPositiveButton(positiveButton,
				new DialogInterface.OnClickListener() {		
			public void onClick(DialogInterface dialog,int id) {
				//If createloop is set true, which it only should be when called from tutorialDialog, starts the next tutorialDialog
				Log.d("geh", "heh1");
				/*String temp1 = numberAdd.getText().toString();
				Log.d("geh", "heh0");
				String temp2 = numberFast.getText().toString();
				Log.d("geh", "heh2");*/
				dialog.cancel();
			}

		})
		//Sets the negative button to cancel
		.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				
				dialog.cancel();

			}
		});

		//Creates the alertDialog and sets it cancelable
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.setCancelable(true);


		//Displays the alertDialog
		alertDialog.show();
		//Sets the background of the buttons
		Button bn = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
		bn.setBackgroundColor(Color.parseColor("#FFFFFF"));
		Button bp = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
		bp.setBackgroundColor(Color.parseColor("#FFFFFF"));

	}

	/** 
	 * Lets the user confirm that the chosen location is the correct one
	 * @param m The marker at the chosen location
	 * @param myMap The GoogleMap object where the marker is placed
	 */
	




}
