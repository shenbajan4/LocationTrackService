package com.example.locationbasedservice;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Locationdb extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION =7;

    // Database Name
    private static final String DATABASE_NAME = "location_service1.db";

    // Contacts table name
    private static final String TABLE_DATAS = "tb_location";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String Name= "Name";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LONG = "long";
    private static final String ADDRESS = "addr";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    public Locationdb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DATAS_TABLE = "CREATE TABLE " + TABLE_DATAS + "(" 
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+ Name +" TEXT, "+ KEY_LAT + " TEXT,"
                + KEY_LONG + " TEXT," + ADDRESS + " TEXT,"+ KEY_DATE + " TEXT," + KEY_TIME + " TEXT" + ")";
        db.execSQL(CREATE_DATAS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATAS);

        // Create tables again
        onCreate(db);
    }

   

  
    void addData(Datas data) {
        SQLiteDatabase db = this.getWritableDatabase();
       
        ContentValues values = new ContentValues();
        values.put("Name", data.getuser());
        values.put(KEY_LAT, data.getlat()); 
        values.put(KEY_LONG, data.getlongit());
        values.put(ADDRESS, data.getaddr()); 
        values.put(KEY_DATE, data.getDate()); 
        values.put(KEY_TIME, data.getTime()); 
        
        // Inserting Row
        db.insert(TABLE_DATAS, null, values);
        db.close(); // Closing database connection
    }
    public void delete_byDate(){
    	 SQLiteDatabase db = this.getWritableDatabase();
    	 SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    	 Calendar c=Calendar.getInstance();
    	c.add(Calendar.DATE, -5);
   	     String current=df.format(c.getTime());
   	    
    	 db.delete(TABLE_DATAS, KEY_DATE+"='"+current+"'", null);
    	 db.close();
    	}

    public void openDB() throws SQLException{
		this.getWritableDatabase();
    }
   Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DATAS, new String[] { KEY_ID,"Name",
                KEY_LAT, KEY_LONG, ADDRESS,KEY_DATE, KEY_TIME}, null,null, null, null, null, null);
        if (cursor != null)
        {
        cursor.moveToFirst();
        }
        return cursor;
    }

    public int getDatasCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DATAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }

}