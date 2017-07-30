package com.example.lenovo.socialbars; 
2     
3    import java.io.Serializable; 
4     
5    /** 
6     * Created by Lenovo on 3.6.2017. 
7     */ 
8     
9    public class PersonData implements Serializable 
10   { 
11   //Fields 
12       private String mName; 
13       private String mPhoneNumber; 
14       private int mPic; 
15       private int mSocialPoint; 
16       private String mHistoryId; 
17   //*** 
18    
19   //Getters & Setters 
20    
21       public String getName() { 
22           return mName; 
23       } 
24    
25       public void setName(String mName) { 
26           this.mName = mName; 
27       } 
28    
29       public String getPhoneNumber() { 
30           return mPhoneNumber; 
31       } 
32    
33       public void setPhoneNumber(String mPhoneNumber) { 
34           this.mPhoneNumber = mPhoneNumber; 
35       } 
36    
37       public int getPic() { 
38           return mPic; 
39       } 
40    
41       public void setPic(int mPic) { 
42           this.mPic = mPic; 
43       } 
44    
45       public String getHistoryId() { 
46           return mHistoryId; 
47       } 
48    
49       public void setHistoryId(String mHistoryId) { 
50           this.mHistoryId = mHistoryId; 
51       } 
52    
53       public int getSocialPoint() 
54       { 
55           return mSocialPoint; 
56       } 
57    
58       public void setSocialPoint(int mSocialPoint) 
59       { 
60           this.mSocialPoint = mSocialPoint; 
61       } 
62   //*** 
63    
64   //Constructor method 
65       public PersonData(String name, String phoneNumber, int pic, int socialPoint, String historyId) 
66       { 
67           mName = name; 
68           mPhoneNumber = phoneNumber; 
69           mPic = pic; 
70           mSocialPoint = socialPoint; 
71           mHistoryId = historyId; 
72       } 
73   //*** 
74   } 
75   
