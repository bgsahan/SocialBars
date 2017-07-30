MainFragment.java 
1    package com.example.lenovo.socialbars; 
2     
3    import android.app.ListFragment; 
4    import android.content.ContentValues; 
5    import android.database.Cursor; 
6    import android.database.sqlite.SQLiteDatabase; 
7    import android.os.Bundle; 
8    import android.provider.BaseColumns; 
9    import android.provider.ContactsContract; 
10   import android.util.Log; 
11   import android.view.LayoutInflater; 
12   import android.view.View; 
13   import android.view.ViewGroup; 
14   import android.widget.ArrayAdapter; 
15   import android.widget.Toast; 
16    
17   import java.util.ArrayList; 
18    
19   /** 
20    * Created by Lenovo on 3.6.2017. 
21    */ 
22    
23   public class MainFragment extends ListFragment 
24   { 
25       //ArrayAdapter<PersonData> mAdapter; 
26       CustomAdapter1 mCustomAdapter; 
27       ArrayList<PersonData> personDataList; 
28       //CustomCursorAdapter1 customCursorAdapter1; 
29       String[] mProjection; 
30       Cursor mCursor; 
31       String contactName = null; 
32       String contactsNumber = null; 
33       String mBaseColumnID = null; 
34       SQLiteDatabase sqlDbWrite; 
35       SQLiteDatabase sqlDbRead; 
36       DatabaseHelper db; 
37    
38       String[] projectionTest; 
39       Cursor cursorTest; 
40       int testString; 
41       private static final String LOG_TAG = "MyLog"; 
42    
43       @Override 
44       public void onCreate(Bundle savedInstanceState) 
45       { 
46           super.onCreate(savedInstanceState); 
47    
48           mProjection = new String[]{BaseColumns._ID, ContactsContract.Contacts.LOOKUP_KEY, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME}; 
49           mCursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, mProjection, null, null, null, null); 
50    
51           //Singleton içindeki list assign yapıldı. Ve Dummy kod .add() yapıldı. 
52           personDataList = PersonDataSingleton.getInstance().getPersonDataList(); 
53    
54           //Socialbars Database trial 
55           db = new DatabaseHelper(getActivity().getApplicationContext()); 
56           sqlDbWrite = db.getWritableDatabase(); 
57           ContentValues values = new ContentValues(); 
58    
59           while(mCursor.moveToNext()) 
60           { 
61               contactName = mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)); 
62               contactsNumber = mCursor.getString(mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)); 
63               mBaseColumnID = mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)); 
64    
65               //Socialbars Database Column Filling 
66               values.put(DatabaseHelper.COLUMN_LOOKUP, mBaseColumnID); 
67               values.put(DatabaseHelper.COLUMN_SOCIAL_POINT, 50); 
68               values.put(DatabaseHelper.COLUMN_HISTORY_ID, 30); 
69               sqlDbWrite.replace(DatabaseHelper.DATABASE_TABLE, null, values); 
70               //values.put(DatabaseHelper.COLUMN_LOOKUP, "Helly"); 
71               //sqlDbWrite.insert(DatabaseHelper.DATABASE_TABLE, null, values); 
72    
73               personDataList.add(new PersonData(contactName, mBaseColumnID, R.mipmap.test_image, 30, "01")); 
74           } 
75    
76           mCursor.close(); 
77           //sqlDbWrite.close(); 
78    
79   //TESTING DATABASE 
80    
81           sqlDbRead = db.getReadableDatabase(); 
82           projectionTest = new String[1]; 
83           projectionTest[0] = DatabaseHelper.COLUMN_SOCIAL_POINT; 
84           String[] whereValues = {"0r2-47314B4D454316"}; 
85           cursorTest = sqlDbRead.query(DatabaseHelper.DATABASE_TABLE, projectionTest, DatabaseHelper.COLUMN_LOOKUP + "=?", whereValues, null, null, null); 
86    
87           //sqlDbRead = db.getReadableDatabase(); 
88           //projectionTest = new String[1]; 
89           //projectionTest[0] = DatabaseHelper.COLUMN_LOOKUP; 
90           //cursorTest = sqlDbRead.query(DatabaseHelper.DATABASE_TABLE, projectionTest, null, null, null, null, null); 
91           cursorTest.moveToFirst(); 
92           while(cursorTest.moveToNext()) 
93           { 
94               //TEST: fetching Socal Number through Lookup Key and increase it and replace it 
95               //cursorTest.moveToFirst(); 
96               testString = cursorTest.getInt(cursorTest.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SOCIAL_POINT)); 
97               testString = testString + 20; 
98               ContentValues replaceValues = new ContentValues(); 
99               replaceValues.put(DatabaseHelper.COLUMN_SOCIAL_POINT, testString); 
100              sqlDbWrite.update(DatabaseHelper.DATABASE_TABLE, replaceValues, DatabaseHelper.COLUMN_LOOKUP + "=?", whereValues); 
101   
102              //Log.i(LOG_TAG, testString); 
103              Toast.makeText(getActivity(), String.valueOf(testString), Toast.LENGTH_SHORT).show(); 
104   
105          } 
106  //*** 
107   
108   
109          //personDataList.add(new PersonData("Lala", 565, R.mipmap.test_image, 30, 01)); 
110          //personDataList.add(new PersonData("Poo", 543, R.mipmap.test_image, 20, 02)); 
111          //personDataList.add(new PersonData("Stake", 456, R.mipmap.test_image, 80, 03)); 
112          //personDataList.add(new PersonData("Mercer", 243, R.mipmap.test_image, 50, 04)); 
113  //*** 
114      } 
115   
116      @Override 
117      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
118      { 
119          View resultView = inflater.inflate(R.layout.fragment_main_layout, container, false); 
120   
121          return resultView; 
122      } 
123   
124      @Override 
125      public void onActivityCreated(Bundle savedInstanceState) 
126      { 
127          super.onActivityCreated(savedInstanceState); 
128   
129          //mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items); 
130   
131          mCustomAdapter = new CustomAdapter1(getActivity(), personDataList); 
132          setListAdapter(mCustomAdapter); 
133   
134          //Toast.makeText(getActivity(),personDataList.get(1).getName(),Toast.LENGTH_SHORT).show(); 
135      } 
136   
137      @Override 
138      public void onDestroy() { 
139          //sqlDbWrite.close(); 
140          //sqlDbRead.close(); 
141          super.onDestroy(); 
142      } 
143  } 
144  
