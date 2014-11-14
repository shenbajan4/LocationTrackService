package com.example.locationbasedservice;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class tracklist extends Activity implements View.OnClickListener{
	TableLayout table_layout;
	CheckBox ch;
	String trackname=new String();
	int pos;
	//LinearLayout linear_layout,parent;
    int cols = 6;
    int flag=0;
	  String name;
	   //Date sch_from,sch_to,time_from, time_to,d,t;
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.tracklist);
     name = getIntent().getStringExtra("username");
         
      final trackcontroller t_db = new trackcontroller(this);
   //  parent = (LinearLayout) findViewById(R.id.linearlayout1);
      table_layout = (TableLayout) findViewById(R.id.tableLayout1);
     
      	  t_db.openDB();
      	  
            Cursor c1=t_db.getData();
            c1.moveToFirst();
            while (c1.isAfterLast() == false) {
          	//  Toast.makeText(this,"Cursor "+c1.getString(2),Toast.LENGTH_SHORT).show();
            if(c1.getString(1).equals(name)){
          //	  Toast.makeText(this,"Cursor - "+c1.getString(2),Toast.LENGTH_SHORT).show();
             flag=1;  
          	 break;
              }
            else
          	  c1.moveToNext();
            }
          //c1.moveToPrevious();
          if(c1.isBeforeFirst()){
          	c1.moveToFirst();
          }
      
            int rows = t_db.getDatasCount();
          //  Toast.makeText(this,"Row count "+rows+" "+name,Toast.LENGTH_SHORT).show();
      	  // outer for loop
      	  String[] Fields=new String[]{ "Trackname","FromDate","ToDate","FromTime","ToTime" };
      	 
        	//c1 = t_db.getString(null);
      	  c1.moveToFirst();
      	   TableRow row = new TableRow(this);
      	   row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
      	     LayoutParams.WRAP_CONTENT));
      	     for (int j =0; j <5; j++) {
      		    
	        	    TextView tv = new TextView(this);
	        	    tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
	        	      LayoutParams.MATCH_PARENT));
	        	  //  tv.setMovementMethod(LinkMovementMethod.getInstance());
	        	    tv.setBackgroundColor(Color.LTGRAY);
	        	    tv.setGravity(Gravity.CENTER);
	        	    tv.setTextSize(18);
	        	    tv.setPadding(0, 0, 0, 0);
                    tv.setTextColor(Color.RED);
	        	    tv.setText(Fields[j]);
	        	    row.addView(tv);
      	         }
	        	   table_layout.addView(row);

      	  // outer for loop
	  if(flag==1){
      	   for (int i = 0; i < rows; i++) {
      		 row = new TableRow(this);
        	 row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        	     LayoutParams.WRAP_CONTENT));
      	   if(c1.getString(1).equals(name))
    	    {
      	  
      	   ch = new CheckBox(this);
      	   ch.setText(c1.getString(2));
      	   ch.setId(i);
      	   row.addView(ch);
      	   for (int j = 3; j <= cols; j++) {
      	     
      	    TextView tv = new TextView(this);
      	    tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
      	      LayoutParams.MATCH_PARENT));
      	    tv.setBackgroundColor(Color.TRANSPARENT);
      	    tv.setGravity(Gravity.CENTER);
      	    tv.setTextSize(18);
      	    tv.setPadding(0, 0, 0, 0);

      	    tv.setText(c1.getString(j));
      	    
          //  final String repeat=c1.getString(7);
        //    final Cursor cur=c1;
      	    row.addView(tv);
      	    ch.setOnClickListener(new OnClickListener(){
  			      public void onClick(View v) {
  			    // TODO Auto-generated method stub
  			    	Log.i("Track", "before checked");
  			    	CheckBox ch=((CheckBox)v);
  				if(ch.isChecked()){
  				trackname=ch.getText().toString();
  				Log.i("Track", "before load");
  				Intent main = new Intent(tracklist.this,Load.class);
  				main.putExtra("username", name);
  			    main.putExtra("trackname", trackname);
  				startActivity(main);
  				Log.i("Track", "after load");
  				ch.setEnabled(false);
  				}
  				/*if(repeat.equals("Yes"))
  				{	 
  		            repeat(cur.getString(1),cur.getString(2),cur.getString(3),cur.getString(4),cur.getString(5),cur.getString(6));
  				}*/
  			}
  		     });
      	    }
      	   }
      	   c1.moveToNext();

      	   table_layout.addView(row);
      	  }
       }
	  if(flag==0)
	  {
    	   row = new TableRow(this);
    	   row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
    	     LayoutParams.WRAP_CONTENT));
    		   TextView tv = new TextView(this);
        	    tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        	      LayoutParams.MATCH_PARENT));
        	   
        	    tv.setBackgroundColor(Color.TRANSPARENT);
        	    tv.setGravity(Gravity.CENTER);
        	    tv.setTextSize(18);
        	    tv.setPadding(0, 0, 0, 0);

        	    tv.setText("No Record");

        	    row.addView(tv);
        	    table_layout.addView(row);
	  }
        row = new TableRow(this);
   	    row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
   	     LayoutParams.WRAP_CONTENT));
      	Button b = new Button(this);
  	    b.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
        	      LayoutParams.WRAP_CONTENT));
  	    b.setText("Add");
  	    row.addView(b);
  	    table_layout.addView(row);
  	    b.setOnClickListener(this);
  	    
  	    row = new TableRow(this);
  	    row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
  	     LayoutParams.WRAP_CONTENT));
  	    Button b1 = new Button(this);
	    b1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
      	      LayoutParams.WRAP_CONTENT));
	    b1.setText("Back");
	    row.addView(b1);
	    table_layout.addView(row);
	  
	    b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent main1 = new Intent(tracklist.this,Signin.class);
				main1.putExtra("username", name);
				startActivity(main1);
			}
	    	
	    });
       
         
      	 t_db.close();
     }
   
	public void onClick(View v) {
		 
		Intent back = new Intent(tracklist.this,track.class);
		back.putExtra("username", name);
		//main.putExtra("trackname", name.toString());
		startActivity(back);
	}
	
}
