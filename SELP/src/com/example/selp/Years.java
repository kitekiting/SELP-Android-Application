package com.example.selp;
 

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
 
public class Years extends SherlockActivity {
   
	Boolean connectionCheck;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_years);
        //set the home button in the action bar
        ActionBar actionBar = getSupportActionBar();
    	actionBar.setDisplayHomeAsUpEnabled(true);
        
        
        // Locate the year1_button in activity_main.xml
        Button year1button = (Button) findViewById(R.id.year1_button);
        Button year2button = (Button) findViewById(R.id.year2_button);
        Button year3button = (Button) findViewById(R.id.year3_button);
        Button year4button = (Button) findViewById(R.id.year4_button);
        Button mscbutton = (Button) findViewById(R.id.msc_button);
        
        final String year1 = "1";
        final String year2 = "2";
        final String year3 = "3";
        final String year4 = "4";
        final String year5 = "5";
        
        Bundle extra = getIntent().getExtras();  
		connectionCheck = extra.getBoolean("connectionCheck"); 

     //Capture button clicks
        
        year1button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Start Yearcourses.class
                Intent year1Intent = new Intent(Years.this,
                        YearCourses.class);
                year1Intent.putExtra("yearSelected", year1);
                year1Intent.putExtra("connectionCheck", connectionCheck);
                startActivity(year1Intent);
                overridePendingTransition  (R.anim.right_slide_in,R.anim.left_slide_out);
            }
        });
        
        year2button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Start Yearcourses.class
                Intent year2Intent = new Intent(Years.this,
                        YearCourses.class);
                year2Intent.putExtra("yearSelected", year2); 
                year2Intent.putExtra("connectionCheck", connectionCheck);
                startActivity(year2Intent);
                overridePendingTransition  (R.anim.right_slide_in,R.anim.left_slide_out);
            }
        });
        year3button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Start Yearcourses.class
                Intent year3Intent = new Intent(Years.this,
                        YearCourses.class);
                year3Intent.putExtra("yearSelected", year3);
                year3Intent.putExtra("connectionCheck", connectionCheck);
                startActivity(year3Intent);
                overridePendingTransition  (R.anim.right_slide_in,R.anim.left_slide_out);
            }
        });
        
        year4button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Start Yearcourses.class
                Intent year4Intent = new Intent(Years.this,
                        YearCourses.class);
                year4Intent.putExtra("yearSelected", year4); 
                year4Intent.putExtra("connectionCheck", connectionCheck);
                startActivity(year4Intent);
                overridePendingTransition  (R.anim.right_slide_in,R.anim.left_slide_out);
            }
        });
        mscbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Start Yearcourses.class
                Intent year5Intent = new Intent(Years.this,
                        YearCourses.class);
                year5Intent.putExtra("yearSelected", year5); 
                year5Intent.putExtra("connectionCheck", connectionCheck);
                startActivity(year5Intent);
                overridePendingTransition  (R.anim.right_slide_in,R.anim.left_slide_out);
            }
        });
   
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
        getSupportMenuInflater().inflate(R.menu.years, menu);
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