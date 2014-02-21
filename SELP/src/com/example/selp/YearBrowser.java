package com.example.selp;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;


public class YearBrowser extends SherlockFragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.year_browser);
	//set the home button in actionbar
	ActionBar actionBar = getSupportActionBar();
	actionBar.setDisplayHomeAsUpEnabled(true);
	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	actionBar.setTitle("Courses by Year");
	
	
	ActionBar.Tab year1Tab = actionBar.newTab().setText("Year 1");
	ActionBar.Tab year2Tab = actionBar.newTab().setText("Year 2");
	ActionBar.Tab year3Tab = actionBar.newTab().setText("Year 3");
	ActionBar.Tab year4Tab = actionBar.newTab().setText("Year 4");
	ActionBar.Tab year5Tab = actionBar.newTab().setText("Year 5");
	
	Fragment year1Frag = new Year1Fragment();
	Fragment year2Frag = new Year2Fragment();
	Fragment year3Frag = new Year3Fragment();
	Fragment year4Frag = new Year4Fragment();
	Fragment year5Frag = new Year5Fragment();
	
	year1Tab.setTabListener(new YearTabsListener(year1Frag));
	year2Tab.setTabListener(new YearTabsListener(year2Frag));
	year3Tab.setTabListener(new YearTabsListener(year3Frag));
	year4Tab.setTabListener(new YearTabsListener(year4Frag));
	year5Tab.setTabListener(new YearTabsListener(year5Frag));
	
	actionBar.addTab(year1Tab);
	actionBar.addTab(year2Tab);
	actionBar.addTab(year3Tab);
	actionBar.addTab(year4Tab);
	actionBar.addTab(year5Tab);
	}
	
	class YearTabsListener implements ActionBar.TabListener {
		public Fragment fragment;
		
		public YearTabsListener(Fragment fragment){
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
