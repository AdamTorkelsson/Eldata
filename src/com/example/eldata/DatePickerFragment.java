package com.example.eldata;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

public TextView activity_edittext;
public Calendar c2;
public OnDateSetListener f;

public DatePickerFragment() {

}
public DatePickerFragment(OnDateSetListener f){
	this.f = f;
}



@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
    // Use the current date as the default date in the picker
    final Calendar c = Calendar.getInstance();
    c2 = c;
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);

    // Create a new instance of DatePickerDialog and return it
    return new DatePickerDialog(getActivity(), f, year, month, day);
}



	public Calendar getDate() {
		return c2;
		
	}
	



	@Override
	public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
		c2.set(arg1, arg2, arg3);
		
	}



}