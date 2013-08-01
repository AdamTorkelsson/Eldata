package gammalt;

import java.util.ArrayList;
import java.util.Random;

import com.example.eldata.R;
import com.example.eldata.R.layout;
import com.example.eldata.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GraphActivity extends Activity {
float[] adam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.billview);
	//	setContentView(R.layout.graphlayout);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
