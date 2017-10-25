package com.example.lenovo.socialbars; 
 
import java.util.ArrayList; 
 
/** 
 * Created by Lenovo on 4.6.2017. 
 */ 
 
public class PersonDataSingleton 
{ 
    private static final PersonDataSingleton ourInstance = new PersonDataSingleton(); 
 
    //Declaring PersonData ArrayList 
    private ArrayList<PersonData> mPersonDataList; 
//*** 
 
    public static PersonDataSingleton getInstance() { 
        return ourInstance; 
    } 
 
    //Singleon private constructor method 
    private PersonDataSingleton() 
    { 
        mPersonDataList = new ArrayList<PersonData>(); 
    } 
//*** 
 
    //Getters & Setters 
    public ArrayList<PersonData> getPersonDataList() 
    { 
        return mPersonDataList; 
    } 
 
    public void setPersonDataList(ArrayList<PersonData> mPersonDataList) 
    { 
        this.mPersonDataList = mPersonDataList; 
    } 
//*** 
 
}
