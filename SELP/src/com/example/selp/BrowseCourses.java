package com.example.selp;



import java.util.ArrayList;
import java.util.Collections;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;



public class BrowseCourses extends SherlockActivity {
	private static final String TAG = "BrowseCourses";
	ArrayAdapter<String> myarrayAdapter ;
	ListView lv;
	
	
	EditText searchText;
	int searchTextHeight;
	ArrayList<Course> searchList;
	ArrayList<String> searchedCourses = new ArrayList<String>();
	Button clearSearchButton;
	LinearLayout searchView; 
	String selectedCourse;
	ArrayList<String> courseNames = new ArrayList<String>();
	Bundle courseCarry;
	Animation expand;
	Boolean connectionCheck;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.courses);
		findViewsById();
		//set the home button
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		//get the connection check
		Bundle extras = getIntent().getExtras();  
		connectionCheck = extras.getBoolean("connectionCheck"); 
		
		searchTextHeight = searchText.getHeight();
		
		//hide the search bar 
		LayoutParams lp = (LayoutParams) searchView.getLayoutParams();
		lp.height = 0;
	    searchView.setLayoutParams(lp);
		searchView.setVisibility(View.INVISIBLE);
		searchText.setVisibility(View.INVISIBLE);
		searchText.setBackgroundResource(R.drawable.charback);
		searchText.setTextColor(getResources().getColor(R.color.white));
		
		
		
		//Running the asynctask to update the listview with the courses
		if (connectionCheck)//if there is a internet connection, use the latest xml files from online
		{
		Downloader lister = new Downloader();
		final GetList task = new GetList(this);
		searchList = lister.getList();
		task.execute(lister.getList());
		}
		else //if there is no internet connection, use the saved xml files
		{
			Offline runner = new Offline();
			final GetList task = new GetList(this);
			searchList = runner.getList();
			task.execute(runner.getList());
		}
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            	selectedCourse = courseNames.get(position);
            	Intent courseInfo = new Intent(BrowseCourses.this, CourseInfo.class);  
            	courseInfo.putExtra("courseName", selectedCourse);  
            	courseInfo.putExtra("connectionCheck", connectionCheck);
            	startActivity(courseInfo);  
            	overridePendingTransition  (R.anim.right_slide_in,R.anim.left_slide_out);
            	Log.i(TAG,selectedCourse);
            }
		});
		
		//listener for closing the search box when the button is pressed
		 clearSearchButton.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	            	//hide the search bar
	            	LayoutParams lp = (LayoutParams) searchView.getLayoutParams();
	        		lp.height = 0;
	        	    searchView.setLayoutParams(lp);
	        	    searchView.setVisibility(View.INVISIBLE);
	        		searchText.setVisibility(View.INVISIBLE);
	        		searchText.setText("");
	        		//Hide the keyboard when the search box in closed
	        		InputMethodManager keyboard = (InputMethodManager)getSystemService(
	        			      Context.INPUT_METHOD_SERVICE);
	        			keyboard.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
	            }
	        });
		
		//search feature for all courses
		searchText.addTextChangedListener(new TextWatcher() {
			  @Override
			  public void afterTextChanged(Editable e) {
			    String newChar = e.toString();
			    courseNames.clear();
			    myarrayAdapter.notifyDataSetChanged();
			   Log.i(TAG,"Searching for: " + newChar);
			    
			    for (int i = 0; i<searchList.size(); i++)
			    {
			    	if(searchList.get(i).getName().toLowerCase().contains(newChar.toLowerCase()) || searchList.get(i).getAcronym().toLowerCase().contains(newChar.toLowerCase())){
			    		courseNames.add(searchList.get(i).getName());
			    		myarrayAdapter.notifyDataSetChanged();
			    	}
			    }
			    
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

		
	}

	
	private void findViewsById() {
        lv= (ListView) findViewById(R.id.listView);
        searchText = (EditText) findViewById(R.id.searchText);
        searchView = (LinearLayout) findViewById(R.id.searchView);
        clearSearchButton = (Button) findViewById(R.id.clearSearch);
        
    }
	
	
	
	private class GetList extends AsyncTask<ArrayList<Course>, Void, ArrayList<String>> {
		
		private Activity context;
		
		public GetList (Activity context){
			this.context = context;	
		}
		
		@Override
		protected ArrayList<String> doInBackground(ArrayList<Course>...arrayLists) {
			
			ArrayList<Course> courseList = arrayLists[0];
			
			for (int i = 0; i<courseList.size(); i++ ){
				courseNames.add(courseList.get(i).getName());
			}
			Collections.sort(courseNames);
			return courseNames;
		}
		
		@Override
        protected void onPostExecute(ArrayList<String> courseNames) {
			
			myarrayAdapter= new ArrayAdapter<String>(context, R.layout.my_list_item, courseNames);
			lv.setAdapter(myarrayAdapter);
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
		getSupportMenuInflater().inflate(R.menu.browse_test, menu);
		return true;
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	    case R.id.search:
	    	//show the search bar
	    	LayoutParams lp = (LayoutParams) searchView.getLayoutParams();
			lp.height = LayoutParams.WRAP_CONTENT;
			//lp.height = FILL_PARENT;
			
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
