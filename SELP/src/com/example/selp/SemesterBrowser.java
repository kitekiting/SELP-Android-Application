package com.example.selp;


import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;


public class SemesterBrowser extends SherlockFragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.semester_browser);
	//set the home button in actionbar
	ActionBar actionBar = getSupportActionBar();
	actionBar.setDisplayHomeAsUpEnabled(true);
	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	actionBar.setTitle("Semester Courses");
	
	
	ActionBar.Tab semester1Tab = actionBar.newTab().setText("Semester 1");
	ActionBar.Tab semester2Tab = actionBar.newTab().setText("Semester 2");
	
	Fragment Semester1Frag = new Sem1Fragment();
	Fragment Semester2Frag = new Sem2Fragment();
	
	semester1Tab.setTabListener(new SemTabsListener(Semester1Frag));
	semester2Tab.setTabListener(new SemTabsListener(Semester2Frag));
	
	actionBar.addTab(semester1Tab);
	actionBar.addTab(semester2Tab);
	
	}
	
	class SemTabsListener implements ActionBar.TabListener {
		public Fragment fragment;
		
		public SemTabsListener(Fragment fragment){
			this.fragment = fragment;
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.replace(R.id.fragment_container, fragment);
			
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
		
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
