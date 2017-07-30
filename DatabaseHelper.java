1    package com.example.lenovo.socialbars; 
2     
3    import android.content.Context; 
4    import android.database.sqlite.SQLiteDatabase; 
5    import android.database.sqlite.SQLiteOpenHelper; 
6     
7    /** 
8     * Created by Lenovo on 2.7.2017. 
9     */ 
10    
11   public class DatabaseHelper extends SQLiteOpenHelper 
12   { 
13   //Database information 
14       private static  final String DATABASE_NAME = "socialbars.db"; 
15       private static final int SCHEMA = 1; 
16       static final String DATABASE_TABLE = "socialbars"; 
17       static final String COLUMN_LOOKUP = "column_lookup"; 
18       static final String COLUMN_SOCIAL_POINT = "column_social_point"; 
19       static final String COLUMN_HISTORY_ID = "columh_history_id"; 
20   //*** 
21    
22   //Constructor method 
23       public DatabaseHelper(Context context) 
24       { 
25           super(context, DATABASE_NAME, null, SCHEMA); 
26       } 
27   //*** 
28    
29       @Override 
30       public void onCreate(SQLiteDatabase sqLiteDatabase) 
31       { 
32           sqLiteDatabase.execSQL("CREATE TABLE socialbars (column_lookup TEXT, column_social_point INTEGER, columh_history_id INTEGER);"); 
33       } 
34    
35       @Override 
36       public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) 
37       { 
38           throw new RuntimeException("Exception occured!"); 
39       } 
40   } 
41   
