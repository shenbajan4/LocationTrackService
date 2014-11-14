package com.example.locationbasedservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class track extends Activity implements View.OnClickListener{
	
	 private Button home,save;
	 String value;
	 EditText name,from_date,to_date,t1,t2;
	// RadioButton yes,no;
	
	 trackdb track_obj=new trackdb();
	 trackcontroller logic=new trackcontroller(this);
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.track);
			logic.openDB();
			value=getIntent().getStringExtra("username");
			name = (EditText)findViewById(R.id.editText1);
			from_date = (EditText)findViewById(R.id.editText2);
			to_date= (EditText)findViewById(R.id.editText3);
		//	yes=(RadioButton)findViewById(R.id.radioButton1);
		//	no=(RadioButton)findViewById(R.id.radioButton2);
			t1=(EditText)findViewById(R.id.editText4);
			t2=(EditText)findViewById(R.id.editText5);
			home=(Button)findViewById(R.id.button2);
			
			save=(Button)findViewById(R.id.button3);
			home.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						Intent main = new Intent(track.this,tracklist.class);
						main.putExtra("username", value);
						startActivity(main);
					}
				});
		   
		    save.setOnClickListener(this);
		}
				public void onClick(View v) {
				 track_obj.setuser(value);
				 track_obj.settrack(name.getText().toString());
				 track_obj.setfromdate(from_date.getText().toString());
				 track_obj.settodate(to_date.getText().toString());
				// String t1_time=Integer.toString(t1.getCurrentHour())+":"+Integer.toString(t1.getCurrentMinute());
				// String t2_time=Integer.toString(t2.getCurrentHour())+":"+Integer.toString(t2.getCurrentMinute());
				 track_obj.setfromtime(t1.getText().toString());
				 track_obj.settotime(t2.getText().toString());
			/*	 if(yes.isChecked()){
					 track_obj.setrepeat("Yes");
					 //repeat();
				 }
				 else if(no.isChecked())
				 {
					 track_obj.setrepeat("No");
				 }*/
				 logic.addTrack(track_obj);
		          Toast.makeText(track.this,"Saved",Toast.LENGTH_LONG).show();
		          logic.close();	
				}
	}
