package com.example.selp;

import java.util.ArrayList;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TimeTable extends SherlockActivity{
	private static final String TAG = "TimeTable";
	Course courseInfo;
	Course courseLayout;
	Times timeInfo;
	Times timesLayout;
	Building buildingsInfo;
	Room roomInfo;
	
	
	Boolean connectionCheck;
	
	ArrayList<String> selectedCourses;
	ArrayList<Course> courseList;
	ArrayList<Times> timeList;
	ArrayList<Course> matchedCourses;
	ArrayList<Building> buildingList;
	ArrayList<Room> roomList;
	ArrayList<Times> matchedTimes = new ArrayList<Times>();
	String[] loadedCourses;
	int wayAccessed;
	String savedStringCourses = "";
	public static String saving = "savedTimeTable";
	SharedPreferences savedTimeTable;
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_table);
		savedTimeTable = getSharedPreferences(saving, 0);
		
		//set the home button on action bar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		//get the connection check
		Bundle extra = getIntent().getExtras();  
		connectionCheck = extra.getBoolean("connectionCheck", false); 
		wayAccessed = extra.getInt("wayAccessed");
		
		if(connectionCheck){
		Downloader lister = new Downloader();
		courseList = lister.getList();
		timeList = lister.getTimes();
		buildingList = lister.getBuildings();
		roomList = lister.getRooms();
		}
		else
		{	
			Offline runner = new Offline();
			courseList = runner.getList();
			timeList = runner.getTimes();
			buildingList = runner.getBuildings();
			roomList = runner.getRooms();
		}
		
		Bundle extras = getIntent().getExtras();  
		selectedCourses = extras.getStringArrayList("selectedCourses"); 
		
		//Declaring the layouts for semester 1
		LinearLayout monday = (LinearLayout) findViewById(R.id.monday);
		LinearLayout tuesday = (LinearLayout) findViewById(R.id.tuesday);
		LinearLayout wednesday = (LinearLayout) findViewById(R.id.wednesday);
		LinearLayout thursday = (LinearLayout) findViewById(R.id.thursday);
		LinearLayout friday = (LinearLayout) findViewById(R.id.friday);
		
		//Declaring the layouts for semester 2
		LinearLayout mondays2 = (LinearLayout) findViewById(R.id.mondays2);
		LinearLayout tuesdays2 = (LinearLayout) findViewById(R.id.tuesdays2);
		LinearLayout wednesdays2 = (LinearLayout) findViewById(R.id.wednesdays2);
		LinearLayout thursdays2 = (LinearLayout) findViewById(R.id.thursdays2);
		LinearLayout fridays2 = (LinearLayout) findViewById(R.id.fridays2);
		
		
		for (int i = 0; i < selectedCourses.size(); i++ ){
			savedStringCourses += selectedCourses.get(i) + ",";
			
			for (int k = 0; k<courseList.size();k++){
				if (courseList.get(k).getName().equals(selectedCourses.get(i))){
					courseInfo = courseList.get(k);
					
				}
			}
			
			for (int j = 0; j<timeList.size();j++){	
	
					if (timeList.get(j).getCourseID().equals(courseInfo.getAcronym())){
						timeInfo = timeList.get(j);
						matchedTimes.add(timeInfo);
				
				}	
			}
		}
			Log.i(TAG,"Matched times size: " + matchedTimes.size());
			
			for (int p = 0; p<matchedTimes.size(); p++){
				timesLayout = matchedTimes.get(p);
				
				for (int k = 0; k<courseList.size();k++){
					if (courseList.get(k).getAcronym().equals(timesLayout.getCourseID())){
						courseLayout = courseList.get(k);
						
						
					}
							
				}
				for (int k = 0; k<buildingList.size(); k++){
					if(buildingList.get(k).getName().equals(timesLayout.getBuilding())){
						buildingsInfo = buildingList.get(k);
					}
				}
				
				
				
		if(timesLayout.getSemester().contains("1"))	
		{
			
			if (timesLayout.getDay().equals("Monday")){
				TextView course = new TextView(this);
				course.setTextSize(14);
				course.setTextColor(getResources().getColor(R.color.white));
				course.setGravity(Gravity.LEFT);
				course.setText("Course: " + courseLayout.getName());
				TextView time = new TextView(this);
				time.setTextSize(14);
				time.setTextColor(getResources().getColor(R.color.white));
				time.setGravity(Gravity.LEFT);
				time.setText("Time:    " + timesLayout.getStartTime() + " - " + timesLayout.getEndTime());
				TextView building = new TextView(this);
				building.setTextSize(14);
				building.setTextColor(getResources().getColor(R.color.white));
				building.setGravity(Gravity.LEFT);
				building.setText("Location: " + buildingsInfo.getDescription());
				TextView room = new TextView(this);
				room.setTextSize(14);
				room.setTextColor(getResources().getColor(R.color.white));
				room.setGravity(Gravity.LEFT);				
				room.setText("Room:    " + timesLayout.getRoom());
				monday.addView(course);
				monday.addView(time);
				monday.addView(building);
				monday.addView(room);
				View ruler = new View(this); ruler.setBackgroundColor(getResources().getColor(R.color.white));
				ruler.setPadding(0, 0, 0, 3);
				monday.addView(ruler,
				 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
			}
			if (timesLayout.getDay().equals("Tuesday")){
				TextView course = new TextView(this);
				course.setTextSize(14);
				course.setTextColor(getResources().getColor(R.color.white));
				course.setGravity(Gravity.LEFT);
				course.setText("Course: " + courseLayout.getName());
				TextView time = new TextView(this);
				time.setTextSize(14);
				time.setTextColor(getResources().getColor(R.color.white));
				time.setGravity(Gravity.LEFT);
				time.setText("Time:    " + timesLayout.getStartTime() + " - " + timesLayout.getEndTime());
				TextView building = new TextView(this);
				building.setTextSize(14);
				building.setTextColor(getResources().getColor(R.color.white));
				building.setGravity(Gravity.LEFT);
				building.setText("Location: " + buildingsInfo.getDescription());
				TextView room = new TextView(this);
				room.setTextSize(14);
				room.setTextColor(getResources().getColor(R.color.white));
				room.setGravity(Gravity.LEFT);
				room.setText("Room:    " + timesLayout.getRoom());
				tuesday.addView(course);
				tuesday.addView(time);
				tuesday.addView(building);
				tuesday.addView(room);
				View ruler = new View(this); ruler.setBackgroundColor(getResources().getColor(R.color.white));
				tuesday.addView(ruler,
				 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
			}
			if (timesLayout.getDay().equals("Wednesday")){
				TextView course = new TextView(this);
				course.setTextSize(14);
				course.setTextColor(getResources().getColor(R.color.white));
				course.setGravity(Gravity.LEFT);
				course.setText("Course: " + courseLayout.getName());
				TextView time = new TextView(this);
				time.setTextSize(14);
				time.setTextColor(getResources().getColor(R.color.white));
				time.setGravity(Gravity.LEFT);
				time.setText("Time:    " + timesLayout.getStartTime() + " - " + timesLayout.getEndTime());
				TextView building = new TextView(this);
				building.setTextSize(14);
				building.setTextColor(getResources().getColor(R.color.white));
				building.setGravity(Gravity.LEFT);
				building.setText("Location: " + buildingsInfo.getDescription());
				TextView room = new TextView(this);
				room.setTextSize(14);
				room.setTextColor(getResources().getColor(R.color.white));
				room.setGravity(Gravity.LEFT);
				room.setText("Room:    " + timesLayout.getRoom());
				wednesday.addView(course);
				wednesday.addView(time);
				wednesday.addView(building);
				wednesday.addView(room);
				View ruler = new View(this); ruler.setBackgroundColor(getResources().getColor(R.color.white));
				wednesday.addView(ruler,
				 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
				
			}
			if (timesLayout.getDay().equals("Thursday")){
				TextView course = new TextView(this);
				course.setTextSize(14);
				course.setTextColor(getResources().getColor(R.color.white));
				course.setGravity(Gravity.LEFT);
				course.setText("Course: " + courseLayout.getName());
				TextView time = new TextView(this);
				time.setTextSize(14);
				time.setTextColor(getResources().getColor(R.color.white));
				time.setGravity(Gravity.LEFT);
				time.setText("Time:    " + timesLayout.getStartTime() + " - " + timesLayout.getEndTime());
				TextView building = new TextView(this);
				building.setTextSize(14);
				building.setTextColor(getResources().getColor(R.color.white));
				building.setGravity(Gravity.LEFT);
				building.setText("Location: " + buildingsInfo.getDescription());
				TextView room = new TextView(this);
				room.setTextSize(14);
				room.setTextColor(getResources().getColor(R.color.white));
				room.setGravity(Gravity.LEFT);
				room.setText("Room:    " + timesLayout.getRoom());
				thursday.addView(course);
				thursday.addView(time);
				thursday.addView(building);
				thursday.addView(room);
				View ruler = new View(this); ruler.setBackgroundColor(getResources().getColor(R.color.white));
				thursday.addView(ruler,
				 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
			}
			if (timesLayout.getDay().equals("Friday")){
				TextView course = new TextView(this);
				course.setTextSize(14);
				course.setTextColor(getResources().getColor(R.color.white));
				course.setGravity(Gravity.LEFT);
				course.setText("Course: " + courseLayout.getName());
				TextView time = new TextView(this);
				time.setTextSize(14);
				time.setTextColor(getResources().getColor(R.color.white));
				time.setGravity(Gravity.LEFT);
				time.setText("Time:    " + timesLayout.getStartTime() + " - " + timesLayout.getEndTime());
				TextView building = new TextView(this);
				building.setTextSize(14);
				building.setTextColor(getResources().getColor(R.color.white));
				building.setGravity(Gravity.LEFT);
				building.setText("Location: " + buildingsInfo.getDescription());
				TextView room = new TextView(this);
				room.setTextSize(14);
				room.setTextColor(getResources().getColor(R.color.white));
				room.setGravity(Gravity.LEFT);
				room.setText("Room:    " + timesLayout.getRoom());
				friday.addView(course);
				friday.addView(time);
				friday.addView(building);
				friday.addView(room);
				View ruler = new View(this); ruler.setBackgroundColor(getResources().getColor(R.color.white));
				friday.addView(ruler,
				 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
			}
				
			}
		//Check for semester 2 courses starts here
		if(timesLayout.getSemester().contains("2"))
		{
			
			if (timesLayout.getDay().equals("Monday")){
				TextView course = new TextView(this);
				course.setTextSize(14);
				course.setTextColor(getResources().getColor(R.color.white));
				course.setGravity(Gravity.LEFT);
				course.setText("Course: " + courseLayout.getName());
				TextView time = new TextView(this);
				time.setTextSize(14);
				time.setTextColor(getResources().getColor(R.color.white));
				time.setGravity(Gravity.LEFT);
				time.setText("Time:    " + timesLayout.getStartTime() + " - " + timesLayout.getEndTime());
				TextView building = new TextView(this);
				building.setTextSize(14);
				building.setTextColor(getResources().getColor(R.color.white));
				building.setGravity(Gravity.LEFT);
				building.setText("Location: " + buildingsInfo.getDescription());
				TextView room = new TextView(this);
				room.setTextSize(14);
				room.setTextColor(getResources().getColor(R.color.white));
				room.setGravity(Gravity.LEFT);				
				room.setText("Room:    " + timesLayout.getRoom());
				mondays2.addView(course);
				mondays2.addView(time);
				mondays2.addView(building);
				mondays2.addView(room);
				View ruler = new View(this); ruler.setBackgroundColor(getResources().getColor(R.color.white));
				ruler.setPadding(0, 0, 0, 3);
				mondays2.addView(ruler,
				 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
			}
			if (timesLayout.getDay().equals("Tuesday")){
				TextView course = new TextView(this);
				course.setTextSize(14);
				course.setTextColor(getResources().getColor(R.color.white));
				course.setGravity(Gravity.LEFT);
				course.setText("Course: " + courseLayout.getName());
				TextView time = new TextView(this);
				time.setTextSize(14);
				time.setTextColor(getResources().getColor(R.color.white));
				time.setGravity(Gravity.LEFT);
				time.setText("Time:    " + timesLayout.getStartTime() + " - " + timesLayout.getEndTime());
				TextView building = new TextView(this);
				building.setTextSize(14);
				building.setTextColor(getResources().getColor(R.color.white));
				building.setGravity(Gravity.LEFT);
				building.setText("Location: " + buildingsInfo.getDescription());
				TextView room = new TextView(this);
				room.setTextSize(14);
				room.setTextColor(getResources().getColor(R.color.white));
				room.setGravity(Gravity.LEFT);
				room.setText("Room:    " + timesLayout.getRoom());
				tuesdays2.addView(course);
				tuesdays2.addView(time);
				tuesdays2.addView(building);
				tuesdays2.addView(room);
				View ruler = new View(this); ruler.setBackgroundColor(getResources().getColor(R.color.white));
				tuesdays2.addView(ruler,
				 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
			}
			if (timesLayout.getDay().equals("Wednesday")){
				TextView course = new TextView(this);
				course.setTextSize(14);
				course.setTextColor(getResources().getColor(R.color.white));
				course.setGravity(Gravity.LEFT);
				course.setText("Course: " + courseLayout.getName());
				TextView time = new TextView(this);
				time.setTextSize(14);
				time.setTextColor(getResources().getColor(R.color.white));
				time.setGravity(Gravity.LEFT);
				time.setText("Time:    " + timesLayout.getStartTime() + " - " + timesLayout.getEndTime());
				TextView building = new TextView(this);
				building.setTextSize(14);
				building.setTextColor(getResources().getColor(R.color.white));
				building.setGravity(Gravity.LEFT);
				building.setText("Location: " + buildingsInfo.getDescription());
				TextView room = new TextView(this);
				room.setTextSize(14);
				room.setTextColor(getResources().getColor(R.color.white));
				room.setGravity(Gravity.LEFT);
				room.setText("Room:    " + timesLayout.getRoom());
				wednesdays2.addView(course);
				wednesdays2.addView(time);
				wednesdays2.addView(building);
				wednesdays2.addView(room);
				View ruler = new View(this); ruler.setBackgroundColor(getResources().getColor(R.color.white));
				wednesdays2.addView(ruler,
				 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
				
			}
			if (timesLayout.getDay().equals("Thursday")){
				TextView course = new TextView(this);
				course.setTextSize(14);
				course.setTextColor(getResources().getColor(R.color.white));
				course.setGravity(Gravity.LEFT);
				course.setText("Course: " + courseLayout.getName());
				TextView time = new TextView(this);
				time.setTextSize(14);
				time.setTextColor(getResources().getColor(R.color.white));
				time.setGravity(Gravity.LEFT);
				time.setText("Time:    " + timesLayout.getStartTime() + " - " + timesLayout.getEndTime());
				TextView building = new TextView(this);
				building.setTextSize(14);
				building.setTextColor(getResources().getColor(R.color.white));
				building.setGravity(Gravity.LEFT);
				building.setText("Location: " + buildingsInfo.getDescription());
				TextView room = new TextView(this);
				room.setTextSize(14);
				room.setTextColor(getResources().getColor(R.color.white));
				room.setGravity(Gravity.LEFT);
				room.setText("Room:    " + timesLayout.getRoom());
				thursdays2.addView(course);
				thursdays2.addView(time);
				thursdays2.addView(building);
				thursdays2.addView(room);
				View ruler = new View(this); ruler.setBackgroundColor(getResources().getColor(R.color.white));
				thursdays2.addView(ruler,
				 new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
			}
			if (timesLayout.getDay().equals("Friday")){
				TextView course = new TextView(this);
				course.setTextSize(14);
				course.setTextColor(getResources().getColor(R.color.white));
				course.setGravity(Gravity.LEFT);
				course.setText("Course: " + courseLayout.getName());
				TextView time = new TextView(this);
				time.setTextSize(14);
				time.setTextColor(getResources().getColor(R.color.white));
				time.setGravity(Gravity.LEFT);
				time.setText("Time:    " + timesLayout.getStartTime() + " - " + timesLayout.getEndTime());
				TextView building = new TextView(this);
				building.setTextSize(14);
				building.setTextColor(getResources().getColor(R.color.white));
				building.setGravity(Gravity.LEFT);
				building.setText("Location: " + buildingsInfo.getDescription());
				TextView room = new TextView(this);
				room.setTextSize(14);
				room.setTextColor(getResources().getColor(R.color.white));
				room.setGravity(Gravity.LEFT);
				room.setText("Room:    " + timesLayout.getRoom());
				fridays2.addView(course);
				fridays2.addView(time);
				fridays2.addView(building);
				fridays2.addView(room);
				View ruler = new View(this); ruler.setBackgroundColor(getResources().getColor(R.color.white));
				fridays2.addView(ruler,
						new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, 2));
			}

		}
			}
	}
	
	@Override
	public void onBackPressed()
	{
		if (wayAccessed == 1){ //if the timetables was built by the user, give them the save option
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Do you want to save your timetable?").setPositiveButton("Yes, save and take me home.", dialogClickListener)
		    .setNegativeButton("No, I want to edit it.", dialogClickListener).show();
		}
		else if (wayAccessed == 0) //if the timetables was accessed with the load button, just take them back home
		{
			super.onBackPressed();
		}
	}
	
	//the listener for the dialog box
	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	        switch (which){
	        case DialogInterface.BUTTON_POSITIVE:
	        	
	        	SharedPreferences.Editor editor = savedTimeTable.edit();
				editor.putString("savedSelection", savedStringCourses );
				editor.commit();
				
				//saving the selected courses
				savedTimeTable = getSharedPreferences(saving, 0);
				String loadedString = savedTimeTable.getString("savedSelection", "No saved Time Table");
				Log.i(TAG,loadedString);
				 //display a toast to the user saying his time table was saved
				Toast.makeText(getApplicationContext(),
	    				"Timetable saved", Toast.LENGTH_SHORT)
	        		      .show();
				//this intent quits to the home screen of the app
				Intent quit = new Intent(getApplicationContext(), MainActivity.class);
				quit.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(quit);
				
	            break;

	        case DialogInterface.BUTTON_NEGATIVE:
	        	finish();
	            break;
	        }
	    }
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.time_table, menu);
		return true;
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		case R.id.save:
			//saving the selected courses
			SharedPreferences.Editor editor = savedTimeTable.edit();
			editor.putString("savedSelection", savedStringCourses );
			editor.commit();
			
			//saving the selected courses to the array
			savedTimeTable = getSharedPreferences(saving, 0);
			String loadedString = savedTimeTable.getString("savedSelection", "No saved Time Table");
			Log.i(TAG,loadedString);
			 //display a toast to the user saying his time table was saved
			Toast.makeText(getApplicationContext(),
    				"Timetable saved", Toast.LENGTH_SHORT)
        		      .show();
			
			Intent quit = new Intent(getApplicationContext(), MainActivity.class);
			quit.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(quit);
			
			break;
		case android.R.id.home:
			if (wayAccessed == 1){ //if the timetables was built by the user, give them the save option
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Do you want to save your timetable?").setPositiveButton("Yes, save and take me home.", dialogClickListener)
				    .setNegativeButton("No, I want to edit it.", dialogClickListener).show();
				}
				else if (wayAccessed == 0) //if the timetables was accessed with the load button, just take them back home
				{
					super.onBackPressed();
				}
			break;

		}
		return true;
	}
	
	

}
