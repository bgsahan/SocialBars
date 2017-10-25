package com.example.lenovo.socialbars; 
 
import android.app.ListFragment; 
import android.app.LoaderManager; 
import android.content.ContentValues; 
import android.content.Context; 
import android.content.CursorLoader; 
import android.content.Loader; 
import android.content.SharedPreferences; 
import android.database.Cursor; 
import android.database.sqlite.SQLiteDatabase; 
import android.net.Uri; 
import android.os.Bundle; 
import android.provider.BaseColumns; 
import android.provider.CallLog; 
import android.provider.ContactsContract; 
import android.telecom.Call; 
import android.telephony.PhoneNumberUtils; 
import android.util.Log; 
import android.view.LayoutInflater; 
import android.view.View; 
import android.view.ViewGroup; 
import android.widget.ArrayAdapter; 
import android.widget.Button; 
import android.widget.Toast; 
 
import java.util.ArrayList; 
 
/** 
 * Created by Lenovo on 3.6.2017. 
 */ 
public class MainFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> 
{ 
    //ArrayAdapter<PersonData> mAdapter; 
    CustomAdapter1 mCustomAdapter; 
    ArrayList<PersonData> personDataList; 
    //CustomCursorAdapter1 customCursorAdapter1; 
    String[] mProjection; 
    String mSelection; 
    String[] cursorProjection; 
    String[] callLogProjection; 
    Cursor mCursor; 
    String contactName = null; 
    String contactsNumber = null; 
    String normalizedContactNumber; 
    String revisedContactsNumber; 
    String mLookupKey = null; 
    int mContactId; 
    Uri mContactUri; 
    String mThumbNailUri; 
    String mStringContactUri; 
    SQLiteDatabase sqlDbWrite; 
    SQLiteDatabase sqlDbRead; 
    DatabaseHelper db; 
 
    String[] projectionTest; 
    Cursor cursorTest; 
    int testString; 
    private static final String LOG_TAG = "MyLog"; 
 
    Cursor CallLogCursor; 
 
    String lastDate; 
    long currentTimeinMillis; 
    long dayDividerTime; 
    long timeDifference; 
    String timeTest; 
    int daysSince; 
    int subtractValue; 
    int subtractPoint; 
 
    SharedPreferences sharedPref; 
    SharedPreferences.Editor sharedPrefEditor; 
 
    ContentValues values; 
    ContentValues timeValues; 
 
    ArrayList<String> DoubleEntryCheckList; 
 
    @Override 
    public void onCreate(Bundle savedInstanceState) 
    { 
        super.onCreate(savedInstanceState); 
 
        //Setting SharedPrefernces for Last checking date of CallLog Database 
        //lastDate = "1503253053446"; //Dummy Time in Millis to be used in onClick method 
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE); 
        sharedPrefEditor = sharedPref.edit(); 
        lastDate = sharedPref.getString("CallLogLastDate", "1503253053446"); 
        //*** 
 
        //Calculations for Subtracting Social Points Daily 
        dayDividerTime = sharedPref.getLong("DayDividerTime", 1503253053446L); 
        //dayDividerTime = 1505253053446L; //Dummy literal for test purpose 
        currentTimeinMillis= System.currentTimeMillis(); 
        timeDifference = (currentTimeinMillis - dayDividerTime) / 86400000L; //86400000 = 60x60x1000x24 
        timeTest = String.valueOf(timeDifference); 
        daysSince = Integer.valueOf(timeTest); 
        subtractValue = 2; 
        subtractPoint = daysSince * subtractValue; 
        //*** 
 
        //Toast.makeText(getActivity(), "Time Difference " + subtractPoint, Toast.LENGTH_SHORT).show(); 
        //*** 
 
        mProjection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.Contacts.PHOTO_THUMBNAIL_URI}; 
        //mSelection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " IS NOT NULL) GROUP BY (" + ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME; 
        //mCursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, mProjection, null, null, null, null); 
 
        //Singleton içindeki list assign yapıldı. Ve Dummy kod .add() yapıldı. 
        personDataList = PersonDataSingleton.getInstance().getPersonDataList(); 
 
        //Socialbars Database trial 
        db = new DatabaseHelper(getActivity().getApplicationContext()); 
        sqlDbWrite = db.getWritableDatabase(); 
        values = new ContentValues(); 
        timeValues = new ContentValues(); //For Subtracting Social Points 
 
        sqlDbRead = db.getReadableDatabase(); 
        cursorProjection = new String[]{DatabaseHelper.COLUMN_SOCIAL_POINT, DatabaseHelper.COLUMN_LOOKUP}; 
        cursorTest = sqlDbRead.query(DatabaseHelper.DATABASE_TABLE, cursorProjection, null, null, null, null, DatabaseHelper.COLUMN_SOCIAL_POINT + " DESC"); 
 
        callLogProjection = new String[]{CallLog.Calls.NUMBER}; 
        CallLogCursor = getActivity().getContentResolver().query(CallLog.Calls.CONTENT_URI, callLogProjection, CallLog.Calls.DATE + "> ?", new String[] {lastDate}, null, null); 
 
        //Loops for checking the calls since the last CallLogUpdate. 
        //TODO: Nested while loop'ları ayrı bir method içine alıp burada sadece bu method call yapılabilir. 
 
        if (CallLogCursor != null && CallLogCursor.getCount()>0) 
        { 
            int testStringIndex = cursorTest.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SOCIAL_POINT); 
            int testContactNumberIndex = cursorTest.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LOOKUP); 
            int callLogNumberIndex = CallLogCursor.getColumnIndex(CallLog.Calls.NUMBER); 
 
            cursorTest.moveToPosition(-1); 
            while (cursorTest.moveToNext()) 
            { 
                //TEST: fetching Socal Number through Lookup Key and increase it and replace it 
                //cursorTest.moveToFirst(); 
                testString = cursorTest.getInt(testStringIndex); 
                String testContactsNumber = cursorTest.getString(testContactNumberIndex); 
                revisedContactsNumber = testContactsNumber.replaceAll("\\D+", ""); 
 
                //Toast.makeText(getActivity(), testContactsNumber + " > " + revisedContactsNumber, Toast.LENGTH_SHORT).show(); 
 
                CallLogCursor.moveToPosition(-1); 
                while (CallLogCursor.moveToNext()) 
                { 
 
                    String callLogNumber = CallLogCursor.getString(callLogNumberIndex); 
                    //String callLogDate = CallLogCursor.getString(CallLogCursor.getColumnIndex(CallLog.Calls.DATE)); 
                    //Toast.makeText(getActivity(), callLogNumber + " = " + revisedContactsNumber, Toast.LENGTH_SHORT).show(); 
 
                    //if (revisedContactsNumber.equals(callLogNumber)) 
                    if (PhoneNumberUtils.compare(revisedContactsNumber, callLogNumber)) 
                    { 
                        testString = testString + 30; 
 
                        //Eğer social point 100'ün üzerine çıkarsa 100'de sabitliyoruz. 
                        if(testString > 100){ 
                            testString = 100; 
                        } 
                        //*** 
 
                        values.put(DatabaseHelper.COLUMN_SOCIAL_POINT, testString); 
                        sqlDbWrite.update(DatabaseHelper.DATABASE_TABLE, values, DatabaseHelper.COLUMN_LOOKUP + "=?", new String[]{testContactsNumber}); 
 
                    } 
 
                    //Toast.makeText(getActivity(), "callLogDate" + " " + testContactsNumber + " = " + callLogNumber , Toast.LENGTH_SHORT).show(); 
                    //Log.i(LOG_TAG, callLogNumber); 
 
                } 
                //Toast.makeText(getActivity(), testContactsNumber, Toast.LENGTH_SHORT).show(); 
            } 
 
        } 
 
        CallLogCursor.close(); 
        //*** 
 
 
 
    } 
 
    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
    { 
        View resultView = inflater.inflate(R.layout.fragment_main_layout, container, false); 
        //Button button = (Button) resultView.findViewById(R.id.button); 
        //button.setOnClickListener(this); 
        return resultView; 
    } 
 
    @Override 
    public void onActivityCreated(Bundle savedInstanceState) 
    { 
        super.onActivityCreated(savedInstanceState); 
 
        //Loader initializing 
        getLoaderManager().initLoader(0, null, this); 
 
        //mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items); 
 
        //Toast.makeText(getActivity(),personDataList.get(1).getName(),Toast.LENGTH_SHORT).show(); 
    } 
 
    @Override 
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) { 
        return new CursorLoader(getActivity(), ContactsContract.CommonDataKinds.Phone.CONTENT_URI, mProjection, null, null, null); 
    } 
 
    @Override 
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) { 
        mCursor = cursor; 
 
        //Database Double contact entry cheking list 
        DoubleEntryCheckList = new ArrayList<String>(); 
        DoubleEntryCheckList.add("a"); 
 
        //Loops for fetching data from Phone contacts database 
        if(sharedPref.getBoolean("isFirstTime", true)) 
        { 
            int contactNameIndex = mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME); 
            //int contactsNumberIndex = mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER); 
            int contactsNumber2Index = mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER); 
            int mThumbnailUriIndex = mCursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI); 
 
            while (mCursor.moveToNext()) 
            { 
                contactName = mCursor.getString(contactNameIndex); 
                contactsNumber = mCursor.getString(contactsNumber2Index); 
                //mLookupKey = mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)); 
                //mContactId = mCursor.getInt(mCursor.getColumnIndex(ContactsContract.Contacts._ID)); 
                mThumbNailUri = mCursor.getString(mThumbnailUriIndex); 
                //mContactUri = ContactsContract.Contacts.getLookupUri(mContactId, mLookupKey); 
                //mStringContactUri = mContactUri.toString(); 
                //revisedContactsNumber = contactsNumber.replaceAll("\\D+", ""); 
 
                //Toast.makeText(getActivity(), revisedContactsNumber, Toast.LENGTH_SHORT).show(); 
                values.put(DatabaseHelper.COLUMN_LOOKUP, contactsNumber); 
                values.put(DatabaseHelper.COLUMN_SOCIAL_POINT, 50); 
                values.put(DatabaseHelper.COLUMN_HISTORY_ID, 30); 
                sqlDbWrite.replace(DatabaseHelper.DATABASE_TABLE, null, values); 
                //values.put(DatabaseHelper.COLUMN_LOOKUP, "Helly"); 
                //sqlDbWrite.insert(DatabaseHelper.DATABASE_TABLE, null, values); 
 
                //adding the data into singleton to be used in ArrayAdapter 
 
                for (String contact : DoubleEntryCheckList) { 
                    if (!DoubleEntryCheckList.contains(contactName)) { 
                        personDataList.add(new PersonData(contactName, contactsNumber, mThumbNailUri, 50, "01")); 
                        break; 
                    } 
                } 
                DoubleEntryCheckList.add(contactName); 
 
                //Toast.makeText(getActivity(),"contact  number: " + contactsNumber, Toast.LENGTH_SHORT).show(); 
            } 
            Log.i(LOG_TAG, DoubleEntryCheckList.toString()); 
        } 
        else 
        { 
            //TODO: Bu query'yi tekrar yapmak gerekli mi? Üstteki query'yi kullansak? 
 
            int testStringIndex = cursorTest.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SOCIAL_POINT); 
            int testContactsNumberIndex = cursorTest.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LOOKUP); 
            int contactNameIndex = mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME); 
            //int contactsNumberIndex = mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER); 
            int contactsNumber2Index = mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER); 
            int mThumbNailUriIndex = mCursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI); 
 
            cursorTest.moveToPosition(-1); 
            while (cursorTest.moveToNext()) { 
                testString = cursorTest.getInt(testStringIndex); 
                String testContactsNumber = cursorTest.getString(testContactsNumberIndex); 
 
                //Subtracting Social Points if it's been a day since last subtracion 
                if (daysSince > 1) { 
                    testString = testString - subtractPoint; 
 
                    if(testString < 0){ 
                        testString = 0; 
                    } 
 
                    //Toast.makeText(getActivity(), "Subtract Point: " + subtractPoint + " Days Since: " + daysSince + "Social Point: " + testString, Toast.LENGTH_SHORT).show(); 
                    timeValues.put(DatabaseHelper.COLUMN_SOCIAL_POINT, testString); 
                    sqlDbWrite.update(DatabaseHelper.DATABASE_TABLE, timeValues, DatabaseHelper.COLUMN_LOOKUP + "=?", new String[]{testContactsNumber}); 
                    //sqlDbWrite.replace(DatabaseHelper.DATABASE_TABLE, null, timeValues); 
                } 
                //*** 
 
                mCursor.moveToPosition(-1); 
                while (mCursor.moveToNext()) { 
                    contactName = mCursor.getString(contactNameIndex); 
                    contactsNumber = mCursor.getString(contactsNumber2Index); 
                    //normalizedContactNumber = mCursor.getString(contactsNumberIndex); 
                    //mLookupKey = mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)); 
                    //mContactId = mCursor.getInt(mCursor.getColumnIndex(ContactsContract.Contacts._ID)); 
                    mThumbNailUri = mCursor.getString(mThumbNailUriIndex); 
                    //mContactUri = ContactsContract.Contacts.getLookupUri(mContactId, mLookupKey); 
                    //mStringContactUri = mContactUri.toString(); 
                    //revisedContactsNumber = contactsNumber.replaceAll("\\D+", ""); 
 
                    //Toast.makeText(getActivity(), contactsNumber + " " + normalizedContactNumber, Toast.LENGTH_SHORT).show(); 
 
                    if (contactsNumber.equals(testContactsNumber)) { 
                        for (String contact : DoubleEntryCheckList) { 
                            if (!DoubleEntryCheckList.contains(contactName)) { 
                                personDataList.add(new PersonData(contactName, contactsNumber, mThumbNailUri, testString, "01")); 
                                break; 
                            } 
                            //Toast.makeText(getActivity(), contactsNumber + " = " + testContactsNumber , Toast.LENGTH_SHORT).show(); 
                        } 
                        DoubleEntryCheckList.add(contactName); 
 
                        //Log.i(LOG_TAG, contactsNumber); 
                    } 
                } 
            } 
 
            if(daysSince > 1) { 
                Toast.makeText(getActivity(), "Geçen gün: " + daysSince, Toast.LENGTH_SHORT).show(); 
            } 
        } 
 
        //*** 
 
        mCustomAdapter = new CustomAdapter1(getActivity(), personDataList); 
        setListAdapter(mCustomAdapter); 
 
        //make "isFirstTime" key false and close the sharedPreference 
        sharedPrefEditor.putBoolean("isFirstTime", false); 
        sharedPrefEditor.apply(); 
 
        //Setting SharedPreferences for last update Date for CallLog database and Assigning DayDividerTime to SharedPreference to be used in future Subtractions 
        lastDate = String.valueOf(currentTimeinMillis); 
        sharedPrefEditor.putString("CallLogLastDate", lastDate); 
        dayDividerTime = currentTimeinMillis; 
 
        if(daysSince > 1) { 
            sharedPrefEditor.putLong("DayDividerTime", dayDividerTime); 
        } 
 
        sharedPrefEditor.apply(); 
        //Toast.makeText(getActivity(), lastDate, Toast.LENGTH_SHORT).show(); 
        //*** 
    } 
 
    @Override 
    public void onLoaderReset(Loader<Cursor> loader) { 
        mCursor = null; 
 
        mCursor.close(); 
        cursorTest.close(); 
    } 
 
    @Override 
    public void onDestroy() { 
        sqlDbWrite.close(); 
        sqlDbRead.close(); 
 
        mCursor.close(); 
        cursorTest.close(); 
 
        super.onDestroy(); 
    } 
 
}
