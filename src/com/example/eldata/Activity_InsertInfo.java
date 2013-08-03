package com.example.eldata;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Activity_InsertInfo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity__insert_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity__insert_info, menu);
		return true;
	}

}
