package com.example.lenovo.socialbars; 
 
import android.content.Context; 
import android.database.sqlite.SQLiteDatabase; 
import android.database.sqlite.SQLiteOpenHelper; 
 
/** 
 * Created by Lenovo on 2.7.2017. 
 */ 
 
public class DatabaseHelper extends SQLiteOpenHelper 
{ 
    //Database information 
    private static  final String DATABASE_NAME = "socialbars.db"; 
    private static final int SCHEMA = 1; 
    static final String DATABASE_TABLE = "socialbars"; 
    static final String COLUMN_LOOKUP = "column_lookup"; 
    static final String COLUMN_SOCIAL_POINT = "column_social_point"; 
    static final String COLUMN_HISTORY_ID = "columh_history_id"; 
//*** 
 
    //Constructor method 
    public DatabaseHelper(Context context) 
    { 
        super(context, DATABASE_NAME, null, SCHEMA); 
    } 
//*** 
 
    @Override 
    public void onCreate(SQLiteDatabase sqLiteDatabase) 
    { 
        sqLiteDatabase.execSQL("CREATE TABLE socialbars (column_lookup TEXT PRIMARY KEY, column_social_point INTEGER, columh_history_id INTEGER);"); 
    } 
 
    @Override 
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) 
    { 
        sqLiteDatabase.execSQL("CREATE TABLE socialbars (column_lookup TEXT PRIMARY KEY, column_social_point INTEGER, columh_history_id INTEGER);"); 
        throw new RuntimeException("Exception occured!"); 
    } 
}
