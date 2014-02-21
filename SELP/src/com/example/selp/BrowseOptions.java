package com.example.selp;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class BrowseOptions extends SherlockActivity {

	Boolean connectionCheck;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_options);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		//find the buttons in xml layout
		Button browseAll = (Button) findViewById(R.id.browseAll);
		Button browseSem = (Button) findViewById(R.id.browseSemester);
		Button browseYear = (Button) findViewById(R.id.browseYear);
		final ImageView edLogo = (ImageView) findViewById(R.id.edLogo1);
		
		//get the connectionCheck
		Bundle extra = getIntent().getExtras();  
		connectionCheck = extra.getBoolean("connectionCheck"); 
		
		//capture button clicks
		browseAll.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Start Years.class to make user choose a the year they are in.
				Intent browseAll = new Intent(BrowseOptions.this,BrowseCourses.class);
				browseAll.putExtra("connectionCheck", connectionCheck);
				startActivity(browseAll);
				overridePendingTransition  (R.anim.right_slide_in,R.anim.left_slide_out);

			}
		});
		
		browseSem.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Start Years.class to make user choose a the year they are in.
				Intent browseSem = new Intent(BrowseOptions.this,SemesterBrowser.class);
				browseSem.putExtra("connectionCheck", connectionCheck);
				startActivity(browseSem);
				overridePendingTransition  (R.anim.right_slide_in,R.anim.left_slide_out);

			}
		});
		
		browseYear.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Start Years.class to make user choose a the year they are in.
				Intent browseYear = new Intent(BrowseOptions.this,YearBrowser.class);
				browseYear.putExtra("connectionCheck", connectionCheck);
				startActivity(browseYear);
				overridePendingTransition  (R.anim.right_slide_in,R.anim.left_slide_out);

			}
		});
		
		// Directing the edlogo to take you user to inf.ed.ac.uk
		edLogo.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				if (connectionCheck){

					Uri uri = Uri.parse("http://www.inf.ed.ac.uk");
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
					Toast.makeText(getApplicationContext(),
							"Connecting to Edinburgh Informatics",
							Toast.LENGTH_LONG).show();
				}
				else
				{
					Toast.makeText(getApplicationContext(),
							"No Internet Connection",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.browse_options, menu);
		return true;
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	   
	    case android.R.id.home:
	    	this.finish();
	    	break;
	      
	    }
		return true;
	}

}
