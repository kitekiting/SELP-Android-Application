package com.example.selp;

import java.util.ArrayList;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.TextView;
import android.widget.Toast;

public class CourseInfo extends SherlockActivity {

	ArrayList<Course> courseList = new ArrayList<Course>();
	ArrayList<Times> timeList = new ArrayList<Times>();
	ArrayList<Building> buildingList = new ArrayList<Building>();
	ArrayList<Room> roomsList = new ArrayList<Room>();
	
	Course courseInfo;
	Times timeInfo;
	Building buildingInfo;
	Room roomInfo;
	String degreeString = "";
	
	Boolean connectionCheck;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_info);
		Bundle extras = getIntent().getExtras();  
		String courseName = extras.getString("courseName"); 
		//set the home button in action bar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		//get the connection check
		Bundle extra = getIntent().getExtras();  
		connectionCheck = extra.getBoolean("connectionCheck"); 
		setTitle(courseName);
		
		if(connectionCheck){
		Downloader lister = new Downloader();
		//match the course with all its details
		courseList = lister.getList();
		timeList = lister.getTimes();
		buildingList = lister.getBuildings();
		roomsList = lister.getRooms();
		}
		else
		{
			Offline runner = new Offline();
			//match the course with all its details
			courseList = runner.getList();
			timeList = runner.getTimes();
			buildingList = runner.getBuildings();
			roomsList = runner.getRooms();
		}
		
		TextView location = (TextView)findViewById(R.id.location); 
		TextView room = (TextView)findViewById(R.id.room);
		TextView map = (TextView)findViewById(R.id.map);
		
		
		for (int i = 0; i<courseList.size();i++){
			if (courseList.get(i).getName().equals(courseName)){
				courseInfo = courseList.get(i);
				//checking which degree fields are filled and concatenating them with the degree string
				if(courseInfo.getAI().length()>0){
					degreeString += "Artificial Intelligence\n";
				}
				if(courseInfo.getCG().length()>0){
					degreeString += "Cognitive Science\n";
				}
				if(courseInfo.getCS().length()>0){
					degreeString += "Computer Science\n";
				}
				if(courseInfo.getSE().length()>0){
					degreeString += "Software Engineering\n";
				}
				if(degreeString.isEmpty()){
					degreeString = "--";
				}
			}
		}
		//Safety setting the venue fields incase there are none found. eg. MInf course has no venue
		location.setText("--");
		room.setText("--");
		map.setText("--");
		//match the course Acronym field in courses for the course field in time table
		for (int i = 0; i<timeList.size();i++){	
			if (timeList.get(i).getCourseID().equals(courseInfo.getAcronym())){
				timeInfo = timeList.get(i);
				location.setText(timeInfo.getBuilding());
				room.setText(timeInfo.getRoom());
			}	
		}
		
		//Check if the building and room has a code, then get the description of the building and room
		if(!(location.getText().equals("--")||room.getText().equals("--"))){
			//find the building description
		for (int k = 0; k<buildingList.size(); k++){
			if(buildingList.get(k).getName().equals(timeInfo.getBuilding())){
				buildingInfo = buildingList.get(k);
				location.setText(buildingInfo.getDescription());
				
				//underlining the map url
				SpannableString underlinedMapURL = new SpannableString(buildingInfo.getMap());
				underlinedMapURL.setSpan(new UnderlineSpan(), 0, underlinedMapURL.length(), 0);
				map.setText(underlinedMapURL);
			}
			
		}
		//find the room description
		for (int x = 0; x<roomsList.size(); x++){
			if(roomsList.get(x).getName().equals(timeInfo.getRoom())){
				roomInfo = roomsList.get(x);
				room.setText(roomInfo.getDescription());
			}	
		}
		}
		
		//set the information of the selected course
		TextView lecturer = (TextView)findViewById(R.id.lecturer); 
		lecturer.setText(courseInfo.getLecturer());
		TextView acronym = (TextView)findViewById(R.id.acronym); 
		acronym.setText(courseInfo.getAcronym());
		TextView level = (TextView)findViewById(R.id.level); 
		level.setText(courseInfo.getLevel());
		TextView degree = (TextView)findViewById(R.id.degree);
		degree.setText(degreeString);
		
		TextView url = (TextView)findViewById(R.id.url); 
		SpannableString underlinedURLcourse = new SpannableString(courseInfo.getUrl());
		underlinedURLcourse.setSpan(new UnderlineSpan(), 0, underlinedURLcourse.length(), 0);
		url.setText(underlinedURLcourse);
		
		TextView drps = (TextView)findViewById(R.id.drps); 
		SpannableString underlinedDrpsURL = new SpannableString(courseInfo.getDrps());
		underlinedDrpsURL.setSpan(new UnderlineSpan(), 0, underlinedDrpsURL.length(), 0);
		drps.setText(underlinedDrpsURL);
		
		TextView points = (TextView)findViewById(R.id.points); 
		points.setText(courseInfo.getPoints());
		TextView euclid = (TextView)findViewById(R.id.euclid); 
		euclid.setText(courseInfo.getEuclid());
		TextView year = (TextView)findViewById(R.id.year); 
		year.setText(courseInfo.getYear());
		TextView semester = (TextView)findViewById(R.id.semester); 
		semester.setText(courseInfo.getDevPer());
		
		
		//listeners to redirect the url links to their respected page if there is an internet connection
		if (connectionCheck){
		map.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		 Uri uri = Uri.parse(buildingInfo.getMap());
        		 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        		 startActivity(intent);
        		 Toast.makeText(getApplicationContext(), "Loading Map...",
        				   Toast.LENGTH_LONG).show();
        	}
        });
		//the course page url
		url.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		 Uri uri = Uri.parse(courseInfo.getUrl());
        		 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        		 startActivity(intent);
        		 Toast.makeText(getApplicationContext(), "Loading Course Page",
        				   Toast.LENGTH_LONG).show();
        	}
        });
		//the DRPS page url
		drps.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		 Uri uri = Uri.parse(courseInfo.getDrps());
        		 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        		 startActivity(intent);
        		 Toast.makeText(getApplicationContext(), "Loading DRPS Page",
        				   Toast.LENGTH_LONG).show();
        	}
        });
		}
		else //if there is no internet connection, display these toasts instead
		{
			map.setOnClickListener(new OnClickListener() {
	        	public void onClick(View v) {
	        		 Toast.makeText(getApplicationContext(), "No Internet Connection",
	        				   Toast.LENGTH_LONG).show();
	        	}
	        });
			//the course page url
			url.setOnClickListener(new OnClickListener() {
	        	public void onClick(View v) {
	        		Toast.makeText(getApplicationContext(), "No Internet Connection",
	        				   Toast.LENGTH_LONG).show();
	        	}
	        });
			//the DRPS page url
			drps.setOnClickListener(new OnClickListener() {
	        	public void onClick(View v) {
	        		Toast.makeText(getApplicationContext(), "No Internet Connection",
	        				   Toast.LENGTH_LONG).show();
	        	}
	        });
		}

		
	}
	
	@Override
	public void onBackPressed() 
	{

	    this.finish();
	    overridePendingTransition  (R.anim.left_slide_in, R.anim.right_slide_out);
	    return;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.course_info, menu);
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
