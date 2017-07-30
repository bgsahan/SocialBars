1    package com.example.lenovo.socialbars; 
2     
3    import android.content.Context; 
4    import android.database.Cursor; 
5    import android.provider.ContactsContract; 
6    import android.view.LayoutInflater; 
7    import android.view.View; 
8    import android.view.ViewGroup; 
9    import android.widget.CursorAdapter; 
10   import android.widget.ImageView; 
11   import android.widget.SeekBar; 
12   import android.widget.TextView; 
13    
14   /** 
15    * Created by Lenovo on 18.6.2017. 
16    */ 
17    
18   public class CustomCursorAdapter1 extends CursorAdapter 
19   { 
20       LayoutInflater cursorInflater; 
21    
22       public CustomCursorAdapter1(Context context, Cursor cursor, int flags) 
23       { 
24           super(context, cursor, flags); 
25           cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
26       } 
27    
28       @Override 
29       public View newView(Context context, Cursor cursor, ViewGroup parent) 
30       { 
31           return cursorInflater.inflate(R.layout.row_fragment_main_layout2, parent, false); 
32       } 
33    
34       @Override 
35       public void bindView(View view, Context context, Cursor cursor) 
36       { 
37   //Initiate and declare all the widgets in row layout 
38           //ImageView imageView = (ImageView) view.findViewById(R.id.rowImage); 
39           //SeekBar seekBar = (SeekBar) view.findViewById(R.id.rowBar); 
40           TextView textView2 = (TextView) view.findViewById(R.id.rowText5); 
41           TextView textView3 = (TextView) view.findViewById(R.id.rowText4); 
42    
43   //Assign connection between widgets in Row layout and Data Model by TextView and ImageView 
44    
45           //Getting contacts details from phone contactlist 
46           String contactName = null; 
47           String contactsNumber = null; 
48           //String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME}; 
49           //cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null, null, null, null); 
50           contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)); 
51           contactsNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)); 
52    
53           textView3.setText(contactName); 
54           textView2.setText(contactsNumber); 
55    
56           //textView3.setText(String.valueOf(personData.getName())); 
57           //textView2.setText(String.valueOf(personData.getPhoneNumber())); 
58    
59           //seekBar.setProgress((personData.getSocialPoint())); 
60           //seekBar.setClickable(false); 
61           //seekBar.setFocusable(false); 
62           //seekBar.setEnabled(false); 
63           //seekBar.setThumb(null); 
64           //imageView.setImageResource(personData.getPic()); 
65   //*** 
66    
67       } 
68    
69    
70   } 
71   
