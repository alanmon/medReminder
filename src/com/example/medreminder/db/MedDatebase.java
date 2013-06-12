package com.example.medreminder.db;

import java.sql.Date;
import java.sql.Time;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MedDatebase
{
	
	/*Medicine Table*/
	static final String medicineTable="Medicines";
	static final String idMed="idMed";
	static final String medName="medName";
	static final String medType="medType";
	static final String medImage="medImage";
	static final String takePerday="takePerday";
	static final String startDay="startDay";
	static final String medDosage="medDosage";
	static final String medDuration="medDuration";
	static final String medInstruction="medInstruction";
	static final String reminder1="reminder1";

	
	
	/*History Table*/
	static final String historyTable="History";
	static final String id_Med="id_Med";
	static final String time="time";
	

	private Context context = null;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	
	public MedDatebase(Context ctx)
	{
		this.context = ctx;
		DBHelper = new DatabaseHelper(context, DatabaseHelper.dbName, null, DatabaseHelper.DATABASE_VERSION);
	}

	private Cursor getAccessibleHoard() {
	    /**
	     * Listing 8-3: Querying a database
	     */
	    // Specify the result column projection. Return the minimum set
	    // of columns required to satisfy your requirements.
	    String[] result_columns = new String[] { 
	    		idMed, medName, medType, medImage, takePerday,startDay,medDosage, medDuration, medInstruction,reminder1}; 
	    
	    // Specify the where clause that will limit our results.
	    String where = idMed + "=" + 1;
	    
	    // Replace these with valid SQL statements as necessary.
	    String whereArgs[] = null;
	    String groupBy = null;
	    String having = null;
	    String order = null;
	    
	    SQLiteDatabase db = DBHelper.getWritableDatabase();
	    Cursor cursor = db.query(DBHelper.dbName, 
	                             result_columns, where,
	                             whereArgs, groupBy, having, order);
	    //
	    return cursor;
	  }
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		
		/*Database information*/
		static final String dbName="medReminder.db";
		private static final int DATABASE_VERSION = 1;

		
		public DatabaseHelper(Context context, String name, CursorFactory factory, int version) 
		{
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			/*create  parent table*/
			db.execSQL("CREATE TABLE " + medicineTable + " (" 
					+ idMed + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ medName + " TEXT, "
					+ medType + " INTEGER, "
					+ medImage + " TEXT, "
					+ startDay + " DATE, "
					+ takePerday + " INTEGER, "
					+ medDosage + " INTEGER, "
					+ medDuration + " INTEGER, "
					+ medInstruction + " INTEGER, "
					+ reminder1 + " TEXT);");//TODO:: REMINDER IN TEXT, NEED PARSE
					//+ colPhoneNum + " TEXT);");
			
			
			/*create child table*/
			db.execSQL("CREATE TABLE " + historyTable + " ("
					+ id_Med + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ time + " TIMESTAMP);");
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Log the version upgrade.
		      Log.w("TaskDBAdapter", "Upgrading from version " +
		        oldVersion + " to " +
		        newVersion + ", which will destroy all old data");
		      
		   // Upgrade the existing database to conform to the new 
		      // version. Multiple previous versions can be handled by 
		      // comparing oldVersion and newVersion values.

		      // The simplest case is to drop the old table and create a new one.
			 db.execSQL("DROP TABLE IF EXISTS "+medicineTable);
			 db.execSQL("DROP TABLE IF EXISTS "+historyTable);
	 
			 onCreate(db);
			
		}
	}    
/*
	public void open() throws SQLException
	{
		db = DBHelper.getWritableDatabase();
	}
*/ 
	public void close()
	{
		DBHelper.close();
	}    

	/**
	 * insert parent user
	 * @param email
	 * @param username
	 * @param password
	 * @param phoneNum
	 * @param name
	 * @return -1 with error, otherwise, not -1
	 */
	public long insertMed(String med_Name,int med_Type,String med_Image,int perday,String start_day, int med_Dosage, int med_Duration, int med_Instruction, String reminder)
	{
		ContentValues initialValues = new ContentValues();  
		initialValues.put(medName,med_Name); 
        initialValues.put(medType,med_Type);  
        //initialValues.put(medImage, med_Image);  
        initialValues.put(takePerday, perday);
        initialValues.put(startDay, start_day); 
        initialValues.put(medDosage, med_Dosage);
        initialValues.put(medDuration, med_Duration);
        initialValues.put(medInstruction, med_Instruction);
        initialValues.put(reminder1, reminder);
       // return db.insertOrThrow(parentTable, null, initialValues);
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        return db.insert(medicineTable, null, initialValues);  
	}
	
	
	
	
	
/*	public long insertParent(String email, String username, String password, String phoneNum, String name)
	{
		ContentValues initialValues = new ContentValues();  
		initialValues.put(colEmail, email); 
        initialValues.put(colParentUserID, username);  
        initialValues.put(colParentPassword, password);  
        initialValues.put(colPhoneNum, phoneNum);
        initialValues.put(colName, name); 
       // return db.insertOrThrow(parentTable, null, initialValues);
        return db.insert(parentTable, null, initialValues);  
	}

	//addMed(string name,int medType, string medImage, int takePerday, 
	//date startDay, int medDosage, int medDuration, int medInstruction, 
	//reminder1, reminder2, reminder3)
	public long addMedication(String med_name, int med_type, String med_image, integer take_Perday, 
			Date start_date, int med_dosage, int med_duration, int med_instruction,
			Time reminder_1, Time reminder_2, Time reminder_3)
	{
		ContentValues initialValues = new ContentValues(); 
		initialValues.put(medName, med_name);
		initialValues.put(medType, med_type);
		initialValues.put(medImage, med_image);
		initialValues.put(takePerday, take_Perday);
		initialValues.put(startDay, start_date);
		initialValues.put(medDosage, med_dosage);
		initialValues.put(medDuration, med_duration);
		initialValues.put(medInstruction, med_instruction);
		initialValues.put(reminder1, reminder_1);
		initialValues.put(reminder2, reminder_2);
		initialValues.put(reminder3, reminder_3);
		return db.insert(medicineTable, null, initialValues);
	}*/
	
}
