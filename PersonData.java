package com.example.lenovo.socialbars; 
 
import java.io.Serializable; 
 
/** 
 * Created by Lenovo on 3.6.2017. 
 */ 
 
public class PersonData implements Serializable 
{ 
    //Fields 
    private String mName; 
    private String mPhoneNumber; 
    private String mPic; 
    private int mSocialPoint; 
    private String mHistoryId; 
    //*** 
 
    //Getters & Setters 
 
    public String getName() { 
        return mName; 
    } 
 
    public void setName(String mName) { 
        this.mName = mName; 
    } 
 
    public String getPhoneNumber() { 
        return mPhoneNumber; 
    } 
 
    public void setPhoneNumber(String mPhoneNumber) { 
        this.mPhoneNumber = mPhoneNumber; 
    } 
 
    public String getPic() { 
        return mPic; 
    } 
 
    public void setPic(String mPic) { 
        this.mPic = mPic; 
    } 
 
    public String getHistoryId() { 
        return mHistoryId; 
    } 
 
    public void setHistoryId(String mHistoryId) { 
        this.mHistoryId = mHistoryId; 
    } 
 
    public int getSocialPoint() 
    { 
        return mSocialPoint; 
    } 
 
    public void setSocialPoint(int mSocialPoint) 
    { 
        this.mSocialPoint = mSocialPoint; 
    } 
    //*** 
 
    //Constructor method 
    public PersonData(String name, String phoneNumber, String pic, int socialPoint, String historyId) 
    { 
        mName = name; 
        mPhoneNumber = phoneNumber; 
        mPic = pic; 
        mSocialPoint = socialPoint; 
        mHistoryId = historyId; 
    } 
    //*** 
}
