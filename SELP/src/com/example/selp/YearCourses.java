package com.example.selp;

import java.util.ArrayList;
import java.util.Collections;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class YearCourses extends SherlockActivity {

	ArrayAdapter<String> myarrayAdapter ;
	ListView lv;
	
	ArrayAdapter<String> favAdapter ;
	ListView flv;
	
	EditText searchText;
	int searchTextHeight;
	ArrayList<Course> searchList = new ArrayList<Course>();
	ArrayList<String> searchedCourses = new ArrayList<String>();
	Button clearSearchButton;
	LinearLayout searchView;
	
	ArrayList<String> selectedCourses  = new ArrayList<String>();
	ArrayList<String> courseNames = new ArrayList<String>();
	ArrayList<Course> courseList = new ArrayList<Course>();
	
	Boolean connectionCheck;
	
	//These are used in sorting by time method
	Course courseInfo;
	Times timeInfo;
	ArrayList<Times> timeList = new ArrayList<Times>();
	ArrayList<NameTimeTuple> tuples;
	ArrayList<String> timeSortedCourses;
	ArrayList<String> sortedCourses;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.courses);
		findViewsById();  
		//set the home button on action bar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		//get the connection check
		Bundle extra = getIntent().getExtras();  
		connectionCheck = extra.getBoolean("connectionCheck"); 
		
		Bundle extras = getIntent().getExtras();  
		String selectedYear = extras.getString("yearSelected"); 
		
		
		
		setTitle("Year "+ selectedYear);
		
		//hide the search bar
				LayoutParams lp = (LayoutParams) searchView.getLayoutParams();
				lp.height = 0;
			    searchView.setLayoutParams(lp);
				searchView.setVisibility(View.INVISIBLE);
				searchText.setVisibility(View.INVISIBLE);
				searchText.setBackgroundResource(R.drawable.charback);
				searchText.setTextColor(getResources().getColor(R.color.white));
				
				
				if(connectionCheck){
					Downloader lister = new Downloader();
					courseList = lister.getList();
					timeList = lister.getTimes();
				}
				else
				{
					Offline runner = new Offline();
					courseList = runner.getList();
					timeList = runner.getTimes();
				}
		
		
				for (int i = 0; i<courseList.size(); i++ ){
					if(courseList.get(i).getYear().equals(selectedYear)){
						courseNames.add(courseList.get(i).getName());
						searchList.add(courseList.get(i));
					}
				}
		
		myarrayAdapter= new ArrayAdapter<String>(this, R.layout.my_list_item, courseNames);
        lv.setAdapter(myarrayAdapter);
		
		
		favAdapter= new ArrayAdapter<String>(this, R.layout.my_fav_list, selectedCourses);
		flv.setAdapter(favAdapter);
		
		//listener for closing the search box when the button is pressed
		 clearSearchButton.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	            	
	            	//hide the search bar and clear the text
	            	LayoutParams lp = (LayoutParams) searchView.getLayoutParams();
	        		lp.height = 0;
	        	    searchView.setLayoutParams(lp);
	        	    searchView.setVisibility(View.INVISIBLE);
	        		searchText.setVisibility(View.INVISIBLE);
	        		searchText.setText("");
	        		//Hide the keyboard when the search box in closed
	        		InputMethodManager imm = (InputMethodManager)getSystemService(
	        			      Context.INPUT_METHOD_SERVICE);
	        			imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
	            }
	        });
		
		//search feature for all courses
		searchText.addTextChangedListener(new TextWatcher() {
			  
			@Override
			  public void afterTextChanged(Editable e) {
			    String newChar = e.toString();
			    courseNames.clear();
			    myarrayAdapter.notifyDataSetChanged();
			    favAdapter.notifyDataSetChanged();
			    for (int i = 0; i<searchList.size(); i++)
			    {
			    	if(searchList.get(i).getName().toLowerCase().contains(newChar.toLowerCase()) || searchList.get(i).getAcronym().toLowerCase().contains(newChar.toLowerCase())){
			    		
			    		String searchedC = (searchList.get(i).getName());
			    		courseNames.add(searchList.get(i).getName());
			    		
			    		myarrayAdapter.notifyDataSetChanged();
			    		favAdapter.notifyDataSetChanged();
			    		
			    		for (int k = 0; k<selectedCourses.size();k++){
			    			for (int p = 0; p<courseNames.size();p++){
			    			if ((selectedCourses.contains(searchedC)) && (courseNames.get(p).contains(searchedC))){
			    				courseNames.remove(courseNames.lastIndexOf(searchList.get(i).getName()));
			    				myarrayAdapter.notifyDataSetChanged();
					    		favAdapter.notifyDataSetChanged();
			    			}
			    			myarrayAdapter.notifyDataSetChanged();
				    		favAdapter.notifyDataSetChanged();
			    		}
			    		}
			    		myarrayAdapter.notifyDataSetChanged();
			    		favAdapter.notifyDataSetChanged();
			    		
			    	}
			    }
			    
	    		//Check to see if there are more than 4 element selected, then crop the display to that size
        		if (selectedCourses.size()==0 || selectedCourses.size()==1 || selectedCourses.size()==2 ||selectedCourses.size()==3 ||selectedCourses.size()==4) {
            		LayoutParams lp = (LayoutParams) flv.getLayoutParams();
            		lp.height = 120*selectedCourses.size();
         	        flv.setLayoutParams(lp);
         	        flv.setSelection(favAdapter.getCount() - 1);
         	       
            		}
        		else{
        			LayoutParams lp = (LayoutParams) flv.getLayoutParams();
            		lp.height = 120*4;
         	        flv.setLayoutParams(lp);
         	        flv.setSelection(favAdapter.getCount() - 1);
        		}
        		myarrayAdapter.notifyDataSetChanged();
	    		favAdapter.notifyDataSetChanged();  
			  }
			  

			  @Override
			  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			   //nothing needed here...
			  }

			  @Override
			  public void onTextChanged(CharSequence s, int start, int before, int count) {
			    //nothing needed here...
			  }

			
			});
		

		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            	
            	final int pos = position;
        		
            	runOnUiThread(new Runnable() {
            		  public void run() {
            			  //add selected course to the selected list
            			  selectedCourses.add(courseNames.get(pos));
            			  favAdapter.notifyDataSetChanged();
            			  //remove selected course from options list
            			  courseNames.remove(courseNames.get(pos));
                  		myarrayAdapter.notifyDataSetChanged();
            		  }
            		}); 
            	
            	
            	Toast.makeText(getApplicationContext(),
        				"Course added", Toast.LENGTH_SHORT)
            		      .show();
        		
        		//Check to see if there are more than 4 element selected, then crop the display to that size
        		if (selectedCourses.size()==1 || selectedCourses.size()==2 ||selectedCourses.size()==3 ||selectedCourses.size()==4) {
            		LayoutParams lp = (LayoutParams) flv.getLayoutParams();
            		lp.height = view.getHeight()*selectedCourses.size();
         	        flv.setLayoutParams(lp);
         	        flv.setSelection(favAdapter.getCount() - 1);
         	       
            		}
        		else{
        			LayoutParams lp = (LayoutParams) flv.getLayoutParams();
            		lp.height = view.getHeight()*4;
         	        flv.setLayoutParams(lp);
         	        flv.setSelection(favAdapter.getCount() - 1);
        		}
         	   
        		
        }
	});
		
		
		
		flv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            	
            	
            	final int pos = position;
            	runOnUiThread(new Runnable() {
            		public void run() {
            			//add the course back to the big course list
            			courseNames.add(selectedCourses.get(pos));
            			Collections.sort(courseNames);
            			myarrayAdapter.notifyDataSetChanged();
            			//remove course from selected list
            			selectedCourses.remove(selectedCourses.lastIndexOf(selectedCourses.get(pos)));
            			favAdapter.notifyDataSetChanged();
            		}
            	});

            	
            	Toast.makeText(getApplicationContext(),
            			"Course removed", Toast.LENGTH_SHORT)
            			.show();
        		
        		if (selectedCourses.size()<4){
        			
            		LayoutParams lp = (LayoutParams) flv.getLayoutParams();
            		lp.height = view.getHeight()*selectedCourses.size();
         	        flv.setLayoutParams(lp);
         	       
            		}
            }	  
		});
	}
	
	private void findViewsById() {
        lv= (ListView) findViewById(R.id.listView);
        flv = (ListView) findViewById(R.id.favList);
        searchText = (EditText) findViewById(R.id.searchText);
        searchView = (LinearLayout) findViewById(R.id.searchView);
        clearSearchButton = (Button) findViewById(R.id.clearSearch);
    }
	
	private ArrayList<String> sortByTime(ArrayList<String> selectedCourses){
		timeSortedCourses = new ArrayList<String>();
		sortedCourses = new ArrayList<String>();
		tuples = new ArrayList<NameTimeTuple>();
		
		for (int i = 0; i < selectedCourses.size(); i++ ){
			NameTimeTuple tuple = new NameTimeTuple();
			for (int k = 0; k<courseList.size();k++){
				if (courseList.get(k).getName().equals(selectedCourses.get(i))){
					courseInfo = courseList.get(k);
				}
			}
			for (int j = 0; j<timeList.size();j++){	
					if (timeList.get(j).getCourseID().equals(courseInfo.getAcronym())){
						timeInfo = timeList.get(j);
				}	
			}
			//build the tuples with names and times
			if(timeInfo!= null){
				tuple.setName(courseInfo.getName());
				tuple.setTime(timeInfo.getStartTime());
			}else
			{
				tuple.setName(courseInfo.getName());
				tuple.setTime("--:--");
			}
			//check to see if the tuple all ready exist before adding
			if(!tuples.contains(tuple)){
			tuples.add(tuple);}
			
		}
		//build the time and name list for sorting by time
		for (int i = 0; i<tuples.size(); i++){
			sortedCourses.add(tuples.get(i).getTime() + tuples.get(i).getName());
		}
		Collections.sort(sortedCourses);
		System.out.println("The list of sorted courses with times:" + sortedCourses);
		for (int i = 0; i< sortedCourses.size(); i++)
		{
			
			timeSortedCourses.add(sortedCourses.get(i).substring(5));
		}
		System.out.println("The list of sorted courses with times:" + timeSortedCourses);
		return timeSortedCourses;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.year1courses, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.done:
			Intent timeTable = new Intent(YearCourses.this, TimeTable.class);
			//passing all of the selected data
			timeTable.putStringArrayListExtra("selectedCourses", sortByTime(selectedCourses));
			timeTable.putExtra("connectionCheck", connectionCheck);
			timeTable.putExtra("wayAccessed", 1);
			startActivity(timeTable);
			//clear the lists for editing
			sortedCourses.clear();
			timeSortedCourses.clear();
			break;
		case R.id.search:
			//display the search bar
			LayoutParams lp = (LayoutParams) searchView.getLayoutParams();
			lp.height = LayoutParams.WRAP_CONTENT;
			searchView.setLayoutParams(lp);
			searchText.setHint("eg. 'Database' or 'DBS'");
			searchView.setVisibility(View.VISIBLE);
			searchText.setVisibility(View.VISIBLE);
			searchText.requestFocus();
			InputMethodManager keyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		    keyboard.showSoftInput(searchText, 0);

			break; 
			
		case android.R.id.home:
	    	this.finish();
	    	break;
		}
		return true;
	}
	
	
	
}
