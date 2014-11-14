package com.example.locationbasedservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Signin extends Activity {
	 private TextView textValuePassed;
	 private Button home,loc,set;
	 String value;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.signin);
			textValuePassed = (TextView)findViewById(R.id.textView2);
			Bundle bundle = getIntent().getExtras();
			value = (String)bundle.get("username");
			textValuePassed.setText(value);
			home=(Button)findViewById(R.id.button1);
			loc=(Button)findViewById(R.id.button2);
			set=(Button)findViewById(R.id.button3);
			Intent intent = new Intent(Signin.this,TrackService.class);
            intent.putExtra("username",value);
            startService(intent);
			home.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						Intent main = new Intent(Signin.this,MainActivity.class);
						startActivity(main);
					}
				});
		    loc.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
			 
				Intent main = new Intent(Signin.this,tracklist.class);
				main.putExtra("username", value);
				startActivity(main);
			}
		});
		    set.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					//mLocationClient.disconnect();
					 stopService(new Intent(Signin.this,TrackService.class));
					 Toast.makeText(Signin.this,"Disconnected..Please login again",Toast.LENGTH_LONG).show();
					 Intent main = new Intent(Signin.this,MainActivity.class);
					 startActivity(main);
				}
			});
		  
		}
		 
}
