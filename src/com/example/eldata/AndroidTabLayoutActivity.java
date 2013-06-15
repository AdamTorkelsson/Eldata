/*package com.example.eldata;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
 
@SuppressWarnings("deprecation")
public class AndroidTabLayoutActivity extends TabActivity {
    /** Called when the activity is first created. */
  /*   @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testingview);

        TabHost tabHost = getTabHost();
         
        // Tab for Photos
        TabSpec photospec = tabHost.newTabSpec("Numbers");
        // setting Title and Icon for the Tab
        photospec.setIndicator("Numbers", getResources().getDrawable(R.drawable.ic_launcher));
        Intent photosIntent = new Intent(this, StartActivity.class);
        photospec.setContent(photosIntent);
         
        // Tab for Songs
        TabSpec songspec = tabHost.newTabSpec("Graph");        
        songspec.setIndicator("Graph", getResources().getDrawable(R.drawable.ic_launcher));
        Intent songsIntent = new Intent(this, CompareVariableActivity.class);
        songspec.setContent(songsIntent);
         
        // Tab for Videos
        TabSpec videospec = tabHost.newTabSpec("Settings");
        videospec.setIndicator("Settings", getResources().getDrawable(R.drawable.ic_launcher));
        Intent videosIntent = new Intent(this, CompareActivity.class);
        videospec.setContent(videosIntent);
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(videospec); // Adding videos tab
    }
}*/