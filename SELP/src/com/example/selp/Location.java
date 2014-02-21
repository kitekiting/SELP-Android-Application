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
import android.widget.Button;
import android.widget.Toast;

public class Location extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		
		//set the home button in action bar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		//find all the location buttons
		Button bsq7Button = (Button) findViewById(R.id.bsq7_button);
		Button grgs7Button = (Button) findViewById(R.id.grgs7_button);
		Button bp24Button = (Button) findViewById(R.id.bp24_button);
		Button atButton = (Button) findViewById(R.id.at_button);
		Button cmbButton = (Button) findViewById(R.id.cmb_button);
		Button dhtButton = (Button) findViewById(R.id.dht_button);
		Button dhtltButton = (Button) findViewById(R.id.dhtlt_button);
		Button dsbButton = (Button) findViewById(R.id.dsb_button);
		Button fhButton = (Button) findViewById(R.id.fh_button);
		Button mhButton = (Button) findViewById(R.id.mh_button);
		Button mstButton = (Button) findViewById(R.id.mst_button);
		Button ocButton = (Button) findViewById(R.id.oc_button);
		Button oibButton = (Button) findViewById(R.id.oib_button);
		Button rbButton = (Button) findViewById(R.id.rb_button);
		Button wrbButton = (Button) findViewById(R.id.wrb_button);
		Button plButton = (Button) findViewById(R.id.pl_button);
		Button gs1Button = (Button) findViewById(R.id.gs1_button);
		Button ahButton = (Button) findViewById(R.id.ah_button);
		Button goiButton = (Button) findViewById(R.id.goi_button);
		
		//listeners for the buttons
		bsq7Button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=7+Bristo+Square,+Edinburgh&hl=en&ll=55.945763,-3.187666&spn=0.012208,0.033088&sll=55.945667,-3.186893&sspn=0.012208,0.033088&oq=7+bri&t=h&hnear=7+Bristo+Square,+Edinburgh+EH8+9AL,+United+Kingdom&z=15");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		grgs7Button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=7+George+Square,+Edinburgh,+City+of+Edinburgh+EH8+9JZ,+United+Kingdom&hl=en&ll=55.944634,-3.187537&spn=0.012208,0.033088&sll=55.945763,-3.187666&sspn=0.012208,0.033088&oq=7+g+Square,+Edinburgh&t=h&geocode=FbWlVQMdpFzP_w&hnear=7+George+Square,+Edinburgh,+City+of+Edinburgh+EH8+9JZ,+United+Kingdom&z=15");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		bp24Button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=24+Buccleuch+Place,+Edinburgh&hl=en&ll=55.942567,-3.188138&spn=0.012209,0.033088&sll=55.944634,-3.187537&sspn=0.012208,0.033088&oq=24+bu&t=h&hnear=24+Buccleuch+Pl,+Edinburgh+EH8,+United+Kingdom&z=15");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		atButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=Appleton+Tower,+Crichton+St,+Edinburgh&hl=en&ll=55.945667,-3.186893&spn=0.012208,0.033088&sll=55.942567,-3.188138&sspn=0.012209,0.033088&oq=appl&t=h&z=15&iwloc=A");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		cmbButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=Chrystal+Macmillan+Building,+George+Square,+Edinburgh&hl=en&sll=55.945667,-3.186893&sspn=0.012208,0.033088&oq=Chrystal+Macmillan+Building&t=h&hq=Chrystal+Macmillan+Building,+George+Square,&hnear=Edinburgh,+City+of+Edinburgh,+United+Kingdom&z=14");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		dhtButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=David+Hume+Tower,+Edinburgh&hl=en&ll=55.943192,-3.186636&spn=0.024418,0.066175&sll=55.94418,-3.19084&sspn=0.024417,0.066175&oq=david&t=h&hq=David+Hume+Tower,&hnear=Edinburgh,+City+of+Edinburgh,+United+Kingdom&z=14");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		dhtltButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=David+Hume+Tower,+Edinburgh&hl=en&ll=55.943192,-3.186636&spn=0.024418,0.066175&sll=55.94418,-3.19084&sspn=0.024417,0.066175&oq=david&t=h&hq=David+Hume+Tower,&hnear=Edinburgh,+City+of+Edinburgh,+United+Kingdom&z=14");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		dsbButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=Dugald+Stewart+Building,+Charles+Street,+Edinburgh&hl=en&ll=55.945163,-3.187838&spn=0.024417,0.066175&sll=55.9432,-3.18666&sspn=0.024418,0.066175&oq=duga&t=h&hq=Dugald+Stewart+Building,+Charles+Street,&hnear=Edinburgh,+City+of+Edinburgh,+United+Kingdom&z=14");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		fhButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=Forrest+Hill,+Edinburgh&hl=en&sll=55.945163,-3.187838&sspn=0.024417,0.066175&oq=forre&t=h&hnear=Forrest+Hill,+Edinburgh+EH1+2QL,+United+Kingdom&z=15");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		mhButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=Minto+House,+Chambers+Street,+Edinburgh&hl=en&ll=55.949008,-3.188868&spn=0.012207,0.033088&sll=55.946038,-3.191906&sspn=0.012208,0.033088&oq=minto&t=h&hq=Minto+House,&hnear=Chambers+St,+Edinburgh,+United+Kingdom&z=15&iwloc=A");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		mstButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=Medical+School+(Old+Medical+School),+Edinburgh&hl=en&sll=55.949008,-3.188868&sspn=0.012207,0.033088&oq=medical+school+&t=h&hq=Medical+School+(Old+Medical+School),&hnear=Edinburgh,+City+of+Edinburgh,+United+Kingdom&z=14");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		ocButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=Old+College,+South+Bridge,+Edinburgh&hl=en&sll=55.94523,-3.19056&sspn=0.024416,0.066175&oq=old&t=h&hnear=Old+College,+South+Bridge,+Edinburgh+EH8+9YL,+United+Kingdom&z=15");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		oibButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=High+School+Yards,+Edinburgh&hl=en&ll=55.948791,-3.184447&spn=0.012207,0.033088&sll=55.947509,-3.18648&sspn=0.012208,0.033088&oq=high+sch&t=h&hnear=High+School+Yards,+Edinburgh+EH1+1LZ,+United+Kingdom&z=15");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		rbButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=Hugh+Robson+Building,&hl=en&ll=55.946893,-3.189898&spn=0.024415,0.066175&sll=55.948791,-3.184447&sspn=0.012207,0.033088&oq=hugh+&t=h&hq=Hugh+Robson+Building,&hnear=Edinburgh,+City+of+Edinburgh,+United+Kingdom&z=14&iwloc=A");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		wrbButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=William+Robertson+Wing,+Edinburgh&hl=en&sll=55.950335,-3.266326&sspn=0.097653,0.264702&oq=William+Robe&t=h&hq=William+Robertson+Wing,&hnear=Edinburgh,+City+of+Edinburgh,+United+Kingdom&z=14");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		plButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=Paterson's+Land,+Edinburgh&hl=en&sll=55.94501,-3.19077&sspn=0.024417,0.066175&oq=Paterson's+Land&t=h&hq=Paterson's+Land,&hnear=Edinburgh,+City+of+Edinburgh,+United+Kingdom&z=15");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		gs1Button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=1+George+Square&hl=en&ll=55.944634,-3.187537&spn=0.012208,0.033088&sll=55.95012,-3.17973&sspn=0.012207,0.033088&t=h&hnear=1+George+Square,+Edinburgh,+Midlothian+EH8+9JZ,+United+Kingdom&z=15");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		ahButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=Adam+House,+Chambers+Street,+Edinburgh&hl=en&ll=55.948022,-3.187323&spn=0.012207,0.033088&sll=55.944634,-3.187537&sspn=0.012208,0.033088&oq=adam&t=h&hq=Adam+House,&hnear=Chambers+St,+Edinburgh,+United+Kingdom&z=15");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		goiButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("https://maps.google.co.uk/maps?q=Old+Infirmary+Lane,+Edinburgh&hl=en&sll=53.384727,-1.477454&sspn=0.104024,0.264702&oq=old+infir&t=h&hnear=Old+Infirmary+Ln,+Edinburgh,+City+of+Edinburgh,+United+Kingdom&z=15");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				Toast.makeText(getApplicationContext(), "Loading Map",
						Toast.LENGTH_LONG).show();

			}
		});
		
		
		
		
	 
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
