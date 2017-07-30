PersonDataSingleton.java 
1    package com.example.lenovo.socialbars; 
2     
3    import java.util.ArrayList; 
4     
5    /** 
6     * Created by Lenovo on 4.6.2017. 
7     */ 
8     
9    public class PersonDataSingleton 
10   { 
11       private static final PersonDataSingleton ourInstance = new PersonDataSingleton(); 
12    
13   //Declaring PersonData ArrayList 
14       private ArrayList<PersonData> mPersonDataList; 
15   //*** 
16    
17       public static PersonDataSingleton getInstance() { 
18           return ourInstance; 
19       } 
20    
21   //Singleon private constructor method 
22       private PersonDataSingleton() 
23       { 
24           mPersonDataList = new ArrayList<PersonData>(); 
25       } 
26   //*** 
27    
28   //Getters & Setters 
29       public ArrayList<PersonData> getPersonDataList() 
30       { 
31           return mPersonDataList; 
32       } 
33    
34       public void setPersonDataList(ArrayList<PersonData> mPersonDataList) 
35       { 
36           this.mPersonDataList = mPersonDataList; 
37       } 
38   //*** 
39    
40   } 
41   
