package com.example.locationbasedservice;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

	public class Load extends Activity {

		TableLayout table_layout;
		  int cols = 5;
		  String name,tname;
		  int flag=0,year, month, day, hourOfDay, minute;
		  Date sch_from,sch_to,time_from, time_to,d,t;
		  long time1,time2,time3;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_list);
	        name = getIntent().getExtras().getString("username");
	        tname = getIntent().getExtras().getString("trackname");
	        final Locationdb db = new Locationdb(this);
	        final trackcontroller t_db = new trackcontroller(this);
	        table_layout = (TableLayout) findViewById(R.id.tableLayout1);
	        table_layout.removeAllViews();
	        table_layout.removeAllViewsInLayout();
	        int rows = db.getDatasCount();
	        SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
	        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm",Locale.getDefault());
	        db.openDB();
	        t_db.openDB();
	        Cursor c = db.getData();
            Cursor c1=t_db.getData();
            c1.moveToFirst();
                  
           while (c1.isAfterLast() == false) {
                	//  Toast.makeText(this,"Cursor "+c1.getString(2),Toast.LENGTH_SHORT).show();
                  if(c1.getString(1).equals(name) && c1.getString(2).equals(tname) ){
                	 //  Toast.makeText(this,"username-trackname "+c1.getString(1)+" "+c1.getString(2),Toast.LENGTH_SHORT).show();
                	  try {
  	 					sch_from = df1.parse(c1.getString(3));
  	 					 sch_to = df1.parse(c1.getString(4));
  	 					time_from = df2.parse(c1.getString(5));
  	 					time_to = df2.parse(c1.getString(6));
  		    	         } catch (ParseException e) {
  	 					// TODO Auto-generated catch block
  	 					e.printStackTrace();
  	 				     }
  		     
  		      Calendar cal2 = Calendar.getInstance();
         	  year=sch_from.getYear()+1900;
         	  month=sch_from.getMonth()+1;
         	  day=sch_from.getDate();
         	  hourOfDay=time_from.getHours();
         	  minute=time_from.getMinutes();
              cal2.set(year, month, day, hourOfDay, minute);
              time2=cal2.getTimeInMillis()/60000; 
             //Toast.makeText(this,"  time2 "+time2,Toast.LENGTH_SHORT).show();
              Calendar cal3 = Calendar.getInstance();
         	  year=sch_to.getYear()+1990;
         	  month=sch_to.getMonth()+1;
         	  day=sch_to.getDate();
         	  hourOfDay=time_to.getHours();
         	  minute=time_to.getMinutes();
              cal3.set(year, month, day, hourOfDay, minute);
           // Toast.makeText(this,"  hour "+hourOfDay,Toast.LENGTH_SHORT).show();
   	      //  Toast.makeText(this,"  mins "+minute,Toast.LENGTH_SHORT).show();
              time3=cal3.getTimeInMillis()/60000; 
           //  Toast.makeText(this,"  time3 "+time3,Toast.LENGTH_SHORT).show();
                break;
                 }
                  else
                	  c1.moveToNext();
                }
                //c1.moveToPrevious();
                if(c1.isBeforeFirst()){
                	c1.moveToFirst();
                }
                
              //  Toast.makeText(this,"Rows "+rows,Toast.LENGTH_SHORT).show();
    	    
	        	String[] Fields=new String[]{ "Username","Latitude","Longititude","Address","Date","Time" };
	        	  TableRow row = new TableRow(this);
	        	   row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
	        	     LayoutParams.WRAP_CONTENT));
	        	   for (int j =0; j < 6; j++) {
                         
		        	    TextView tv = new TextView(this);
		        	    tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		        	      LayoutParams.MATCH_PARENT));
		        	   
		        	    tv.setBackgroundColor(Color.LTGRAY);
		        	    tv.setGravity(Gravity.CENTER);
		        	    tv.setTextSize(18);
		        	    tv.setPadding(0, 0, 0, 0);
                        tv.setTextColor(Color.RED);
		        	    tv.setText(Fields[j]);

		        	    row.addView(tv);

		        	   }
		        	   table_layout.addView(row);
		        	//   Toast.makeText(this,"username "+c.getString(1),Toast.LENGTH_SHORT).show();
		        	//   Toast.makeText(this,"username in track "+c1.getString(1),Toast.LENGTH_SHORT).show();
		        	//   Toast.makeText(this,"trackname "+c1.getString(2),Toast.LENGTH_SHORT).show();
	        	  // outer for loop
		      
		      c.moveToFirst();     
	        	while(!c.isAfterLast()) {
	      		// Toast.makeText(this,"inside while"+c.getString(6),Toast.LENGTH_LONG).show();
	        	 if(c.getString(1).equals(name) )
	        	 {	 
	        	//	 Toast.makeText(this,"username "+c.getString(1)+c.getString(5)+c.getString(6),Toast.LENGTH_LONG).show();
	        		 try{
	 					d= df1.parse(c.getString(5));
	 					t=df2.parse(c.getString(6));
	 					} catch (ParseException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				    }
	        		
	 		      
	 			  
	 			  
	        	//	 
	        	  if((d.after(sch_from) || d.equals(sch_from))&&  (d.before(sch_to) ||d.equals(sch_to)))
	        	  {	 
	        	     Calendar cal1 = Calendar.getInstance();
	        		 year=sch_from.getYear()+1900;
		        	 month=sch_from.getMonth()+1;
		        	 day=sch_from.getDate();
		        	 hourOfDay=t.getHours();
	 			     minute=t.getMinutes();
		             cal1.set(year, month, day, hourOfDay, minute);
		             time1=cal1.getTimeInMillis()/60000; 
	        	    if(time1 >= time2)
	        	    {
	        	  //   Toast.makeText(this,"inside time2 ",Toast.LENGTH_SHORT).show();
	        	      Calendar cal11 = Calendar.getInstance();
	        		  year=sch_to.getYear()+1990;
	 	        	  month=sch_to.getMonth()+1;
	 	        	  day=sch_to.getDate();
	 	        	  hourOfDay=t.getHours();
	 			      minute=t.getMinutes();
	 	        	  cal11.set(year, month, day, hourOfDay, minute);
			          time1=cal11.getTimeInMillis()/60000;
	        	     if(time1 <= time3)
	        	      {
	        	    //	Toast.makeText(this,"inside time3 ",Toast.LENGTH_SHORT).show();
	        	    flag=1;
	        	    row = new TableRow(this);
	        	     row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
	        	     LayoutParams.WRAP_CONTENT));
	        	      for (int j = 1; j <=6 ; j++) {
                     

	        	    TextView tv = new TextView(this);
	        	    tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
	        	      LayoutParams.MATCH_PARENT));
	        	   
	        	    tv.setBackgroundColor(Color.TRANSPARENT);
	        	    tv.setGravity(Gravity.CENTER);
	        	    tv.setTextSize(18);
	        	    tv.setPadding(0, 0, 0, 0);

	        	    tv.setText(c.getString(j));

	        	    row.addView(tv);
	        	      }
	        	      table_layout.addView(row);
	        	          }
	        	       }
	        	     }     
	        	   } 
	        	 c.moveToNext();
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
	       				Intent main1 = new Intent(Load.this,Signin.class);
	       				main1.putExtra("username", name);
	       				startActivity(main1);
	       			}
	       	    	
	       	    });
	        	 db.close();
	       }
	   
}

