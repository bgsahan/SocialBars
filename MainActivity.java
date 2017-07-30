package com.example.lenovo.socialbars; 
2     
3    import android.Manifest; 
4    import android.app.Activity; 
5    import android.content.pm.PackageManager; 
6    import android.os.Build; 
7    import android.os.Bundle; 
8    import android.widget.Toast; 
9     
10   public class MainActivity extends Activity 
11   { 
12    
13       public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS_ID = 12; 
14       Bundle savedInstanceStateTwin; 
15    
16       @Override 
17       protected void onCreate(Bundle savedInstanceState) 
18       { 
19           super.onCreate(savedInstanceState); 
20           setContentView(R.layout.activity_main_layout); 
21    
22   //// For API above 23, Check if permission given then fragment, If not then ask for permission 
23           if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) 
24           { 
25               if(checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) 
26               { 
27                   requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS_ID); 
28               } 
29               else 
30               { 
31                   if (findViewById(R.id.mainFragmentContainer) != null) 
32                   { 
33                       if (savedInstanceState != null) { 
34                           return; 
35                       } 
36    
37                       MainFragment mainFragment = new MainFragment(); 
38                       getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, mainFragment).commit(); 
39    
40                       //TestFragment testFragment = new TestFragment(); 
41                       //getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, testFragment).commit(); 
42                   } 
43    
44                   savedInstanceStateTwin = savedInstanceState; //onRequestPermissionsResult cycle'a savedInstanceState'i taşıyabilmek için kullanıldı. 
45               } 
46    
47           } 
48   //*** 
49    
50   //For API Below 23 directly call fragment 
51           if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) 
52           { 
53               if (findViewById(R.id.mainFragmentContainer) != null) 
54               { 
55                   if (savedInstanceState != null) { 
56                       return; 
57                   } 
58    
59                   MainFragment mainFragment = new MainFragment(); 
60                   getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, mainFragment).commit(); 
61    
62                   //TestFragment testFragment = new TestFragment(); 
63                   //getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, testFragment).commit(); 
64               } 
65           } 
66   //*** 
67    
68    
69   //Check permission, then MainFragment transaction 
70    
71    
72   //        if (findViewById(R.id.mainFragmentContainer) != null) 
73   //            { 
74   //                if (savedInstanceState != null) { 
75   //                    return; 
76   //                } 
77   //                MainFragment mainFragment = new MainFragment(); 
78   //                getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, mainFragment).commit(); 
79   //            } 
80       } 
81   //*** 
82    
83    
84   //Acting regarding the permission feedback from the user 
85       @Override 
86       public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) 
87       { 
88           switch (requestCode) 
89           { 
90               case MY_PERMISSIONS_REQUEST_READ_CONTACTS_ID: 
91               { 
92                   if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) 
93                   { 
94                       if (findViewById(R.id.mainFragmentContainer) != null) 
95                       { 
96                           if (savedInstanceStateTwin != null) { 
97                               return; 
98                           } 
99    
100                          MainFragment mainFragment = new MainFragment(); 
101                          getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, mainFragment).commit(); 
102   
103                          //TestFragment testFragment = new TestFragment(); 
104                          //getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, testFragment).commit(); 
105                      } 
106   
107                      Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show(); 
108                  } 
109                  else 
110                  { 
111                      Toast.makeText(this, "Permission required for further action", Toast.LENGTH_SHORT).show(); 
112                  } 
113                  return; 
114              } 
115          } 
116   
117      } 
118  //*** 
119  } 
120  
