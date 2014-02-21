package com.example.selp;

import java.util.ArrayList;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends SherlockActivity {
	private static final String TAG = "MainActivity";
	String courseURL = ("http://www.inf.ed.ac.uk/teaching/courses/selp/xml/courses.xml");
	String timeURL = ("http://www.inf.ed.ac.uk/teaching/courses/selp/xml/timetable.xml");
	String venueURL = ("http://www.inf.ed.ac.uk/teaching/courses/selp/xml/venues.xml");

	ArrayList<String> xmlList = new ArrayList<String>();
	public static String lists = "offLineData";
	SharedPreferences courseData;
	Downloader lister = new Downloader();
	Offline runner = new Offline();
	Boolean connectionCheck;
	ArrayList<String> savedCourses = new ArrayList<String>();
	String[] loadedCourses;
	String savedString;
	SharedPreferences savedTimeTable;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		SharedPreferences prefs = getSharedPreferences("savedTimeTable",MODE_PRIVATE); 
		savedString = prefs.getString("savedSelection",null);
		
		if (savedString !=null && savedString.length()>1){
		
		loadedCourses = savedString.split(",");
		
		for (int k = 0; k<loadedCourses.length ; k++)
		{
			savedCourses.add(loadedCourses[k]);
			Log.i(TAG,"Loaded Courses is: " + loadedCourses[k]);
		}
		Log.i(TAG,"Loaded Courses length is: " + loadedCourses.length);
		
		}
		
		
		
		// find all the views by ID
		Button timeTableButton = (Button) findViewById(R.id.timeTable);
		Button browseButton = (Button) findViewById(R.id.browse);
		Button locationButton = (Button) findViewById(R.id.location);
		ImageView edLogo = (ImageView) findViewById(R.id.edLogo);
		courseData = getSharedPreferences(lists, 0);
		
		

		// Check at the start up if there is a internet connection
		if (isOnline()) {
			lister.execute(new String[] { courseURL, timeURL, venueURL });
			connectionCheck = true;

			// Capture button clicks
			timeTableButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// Start Years.class to make user choose a the year they are in
					
					Intent yearSelect = new Intent(MainActivity.this,
							Years.class);
					yearSelect.putExtra("connectionCheck", connectionCheck);
					startActivity(yearSelect);
					overridePendingTransition(R.anim.right_slide_in,
							R.anim.left_slide_out);

					// getting the parsed lists of xmls
					xmlList = lister.getXMLs();
					Log.i(TAG,"The size of the xmlList is: "
							+ xmlList.size());
					// saving these lists for off line use

					SharedPreferences.Editor editor = courseData.edit();
					editor.putString("courseXML", xmlList.get(0));
					editor.putString("timesXML", xmlList.get(1));
					editor.putString("venueXML", xmlList.get(2));
					editor.commit();

				}
			});

			browseButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent browser = new Intent(MainActivity.this,
							BrowseOptions.class);
					browser.putExtra("connectionCheck", connectionCheck);
					startActivity(browser);
					overridePendingTransition(R.anim.right_slide_in,
							R.anim.left_slide_out);

					// getting the parsed lists of xmls
					xmlList = lister.getXMLs();
					Log.i(TAG,"The size of the xmlList is: "
							+ xmlList.size());
					// saving these lists for off line use

					SharedPreferences.Editor editor = courseData.edit();
					editor.putString("courseXML", xmlList.get(0));
					editor.putString("timesXML", xmlList.get(1));
					editor.putString("venueXML", xmlList.get(2));
					editor.commit();

				}
			});
			
			locationButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent location = new Intent(MainActivity.this,
							Location.class);
					startActivity(location);
					overridePendingTransition(R.anim.right_slide_in,
							R.anim.left_slide_out);


				}
			});
			// Directing the edlogo to take you user to inf.ed.ac.uk
			edLogo.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Uri uri = Uri.parse("http://www.inf.ed.ac.uk");
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
					Toast.makeText(getApplicationContext(),
							"Connecting to Edinburgh Informatics",
							Toast.LENGTH_LONG).show();
				}
			});

		}

		else {
			connectionCheck = false;
			// Loading the xmls
			courseData = getSharedPreferences(lists, 0);
			String courseXML = courseData.getString("courseXML", null);
			String timesXML = courseData.getString("timesXML", null);
			String venueXML = courseData.getString("venueXML", null);

			// parsing the xml strings to the offline parser
			runner.execute(new String[] { courseXML, timesXML, venueXML });

			Log.i(TAG,"The length of the courseXML: "
					+ courseXML.length());
			Log.i(TAG,"The length of the timesXML: "
					+ timesXML.length());
			Log.i(TAG,"The length of the venueXML: "
					+ venueXML.length());
			Toast.makeText(getApplicationContext(), "NO INTERNET CONNECTION ",
					Toast.LENGTH_LONG).show();

			// Capture button clicks
			timeTableButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// Start Years.class to make user choose a the year they are in.
					
					Intent yearSelect = new Intent(MainActivity.this,
							Years.class);
					yearSelect.putExtra("connectionCheck", connectionCheck);
					startActivity(yearSelect);
					overridePendingTransition(R.anim.right_slide_in,
							R.anim.left_slide_out);

				}
			});

			browseButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent browseOptions = new Intent(MainActivity.this,
							BrowseOptions.class);
					browseOptions.putExtra("connectionCheck", connectionCheck);
					startActivity(browseOptions);
					overridePendingTransition(R.anim.right_slide_in,
							R.anim.left_slide_out);

				}
			});
			
			locationButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Builder alert = new AlertDialog.Builder(MainActivity.this);
		            alert.setTitle("No Internet Connection");
		            alert.setMessage("Location services only works with an Internet Connection.");
		            alert.setPositiveButton("OK", null);
		            alert.show();
				}
			});
			
			edLogo.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					Toast.makeText(getApplicationContext(),
							"No Internet Connection", Toast.LENGTH_LONG).show();
				}
			});
		}

	}

	// method to check for internet connection aswell as flight mode error
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	public boolean isOnlines() {
		try {
			ConnectivityManager cm = (ConnectivityManager) this
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			return cm.getActiveNetworkInfo().isConnectedOrConnecting();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.load:
			
			if (savedString !=null && savedString.length()>1){
				
				Intent timeTable = new Intent(MainActivity.this, TimeTable.class);
				timeTable.putStringArrayListExtra("selectedCourses", savedCourses);
				timeTable.putExtra("connectionCheck", connectionCheck);
				timeTable.putExtra("wayAccessed", 0);
				startActivity(timeTable);
			}
			else
			{
				Toast.makeText(getApplicationContext(),
	    				"No saved timetable found", Toast.LENGTH_SHORT)
	        		      .show();
			}
			break;
		}
		return true;
	}

}
