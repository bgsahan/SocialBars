1 package com.example.lenovo.socialbars; 
2     
3    import android.content.Context; 
4    import android.database.Cursor; 
5    import android.provider.ContactsContract; 
6    import android.view.LayoutInflater; 
7    import android.view.View; 
8    import android.view.ViewGroup; 
9    import android.widget.ArrayAdapter; 
10   import android.widget.ImageView; 
11   import android.widget.SeekBar; 
12   import android.widget.TextView; 
13    
14   import java.util.ArrayList; 
15    
16   /** 
17    * Created by Lenovo on 4.6.2017. 
18    */ 
19    
20   public class CustomAdapter1 extends ArrayAdapter<PersonData> 
21   { 
22   //CustomAdapter1 manual constructor 
23       public CustomAdapter1(Context context, ArrayList<PersonData> data) 
24       { 
25           super(context, 0, data); 
26       } 
27   //*** 
28    
29       @Override 
30       public View getView(int position, View convertView, ViewGroup parent) 
31       { 
32           final PersonData personData = getItem(position); 
33    
34           if (convertView == null) 
35           { 
36               LayoutInflater inflater = LayoutInflater.from(getContext()); 
37               convertView = inflater.inflate(R.layout.row_fragment_main_layout, parent, false); 
38           } 
39    
40   //Initiate and declare all the widgets in row layout 
41           ImageView imageView = (ImageView) convertView.findViewById(R.id.rowImage); 
42           SeekBar seekBar = (SeekBar) convertView.findViewById(R.id.rowBar); 
43           TextView textView2 = (TextView) convertView.findViewById(R.id.rowText2); 
44           TextView textView3 = (TextView) convertView.findViewById(R.id.rowText3); 
45   //*** 
46    
47   //Assign connection between widgets in Row layout and Data Model by TextView and ImageView 
48    
49           //Getting contacts details from phone contactlist 
50    
51           //String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME}; 
52           //Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null, null, null, null); 
53           //cursor.close(); 
54    
55           textView3.setText(personData.getName()); 
56           textView2.setText(String.valueOf(personData.getPhoneNumber())); 
57    
58           seekBar.setProgress((personData.getSocialPoint())); 
59           seekBar.setClickable(false); 
60           seekBar.setFocusable(false); 
61           seekBar.setEnabled(false); 
62           seekBar.setThumb(null); 
63           imageView.setImageResource(personData.getPic()); 
64   //*** 
65    
66           return convertView; 
67       } 
68   } 
69   
