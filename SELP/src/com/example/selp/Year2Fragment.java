package com.example.selp;

import java.util.ArrayList;
import java.util.Collections;
import com.actionbarsherlock.app.SherlockFragment;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Year2Fragment extends SherlockFragment {
	private static final String TAG = "Year2Fragment";
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		return inflater.inflate(R.layout.courses, container, false);
	}

	@Override
	public void onStart() {
		super.onStart();
		
		findViewsById();
		Bundle extras = getSherlockActivity().getIntent().getExtras();  
		connectionCheck = extras.getBoolean("connectionCheck"); 
		courseNames.clear();
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
		final GetList task = new GetList(getSherlockActivity());
		searchList = lister.getList();
		task.execute(lister.getList());
		}
		else //if there is no internet connection, use the saved xml files
		{
			Offline runner = new Offline();
			final GetList task = new GetList(getSherlockActivity());
			searchList = runner.getList();
			task.execute(runner.getList());
		}
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            	selectedCourse = courseNames.get(position);
            	Intent courseInfo = new Intent(getSherlockActivity(), CourseInfo.class);  
            	courseInfo.putExtra("courseName", selectedCourse);  
            	courseInfo.putExtra("connectionCheck", connectionCheck);
            	startActivity(courseInfo);  
            	//overridePendingTransition  (R.anim.right_slide_in,R.anim.left_slide_out);
            	Log.i(TAG,selectedCourse);
            }
		});

	}

	
	private void findViewsById() {
        lv= (ListView) getView().findViewById(R.id.listView);
        searchText = (EditText) getView().findViewById(R.id.searchText);
        searchView = (LinearLayout) getView().findViewById(R.id.searchView);
        clearSearchButton = (Button) getView().findViewById(R.id.clearSearch);
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
				if(courseList.get(i).getYear().contains("2")){
				courseNames.add(courseList.get(i).getName());
				}
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

}
