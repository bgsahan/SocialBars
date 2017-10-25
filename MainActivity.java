package com.example.lenovo.socialbars; 
 
import android.Manifest; 
import android.app.Activity; 
import android.content.pm.PackageManager; 
import android.os.Build; 
import android.os.Bundle; 
import android.widget.Toast; 
 
import java.util.ArrayList; 
 
public class MainActivity extends Activity 
{ 
 
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS_ID = 12; 
    Bundle savedInstanceStateTwin; 
 
    String[] permissions = new String[] {Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CALL_LOG}; 
 
    @Override 
    protected void onCreate(Bundle savedInstanceState) 
    { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main_layout); 
 
// For API above 23, Check if permission given then fragment, If not then ask for permission 
 
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) 
        { 
            if (checkPermissions()) 
            { 
                if (findViewById(R.id.mainFragmentContainer) != null) 
                { 
                    if (savedInstanceState != null) { 
                        return; 
                    } 
 
                    MainFragment mainFragment = new MainFragment(); 
                    getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, mainFragment).commit(); 
 
                    //TestFragment testFragment = new TestFragment(); 
                    //getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, testFragment).commit(); 
                } 
 
                savedInstanceStateTwin = savedInstanceState; //onRequestPermissionsResult cycle'a savedInstanceState'i taşıyabilmek için kullanıldı. 
            } 
 
            else 
            { 
                //permission not granted 
            } 
 
            //if(checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) 
            //{ 
            //    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS_ID); 
            //} 
            //else 
            //{ 
            //    if (findViewById(R.id.mainFragmentContainer) != null) 
            //    { 
            //        if (savedInstanceState != null) { 
            //            return; 
            //        } 
 
            //        MainFragment mainFragment = new MainFragment(); 
            //        getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, mainFragment).commit(); 
 
            //TestFragment testFragment = new TestFragment(); 
            //getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, testFragment).commit(); 
            //    } 
 
            //    savedInstanceStateTwin = savedInstanceState; //onRequestPermissionsResult cycle'a savedInstanceState'i taşıyabilmek için kullanıldı. 
            //} 
 
        } 
//*** 
 
//For API Below 23 directly call fragment 
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) 
        { 
            if (findViewById(R.id.mainFragmentContainer) != null) 
            { 
                if (savedInstanceState != null) { 
                    return; 
                } 
 
                MainFragment mainFragment = new MainFragment(); 
                getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, mainFragment).commit(); 
 
                //TestFragment testFragment = new TestFragment(); 
                //getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, testFragment).commit(); 
            } 
        } 
//*** 
 
 
//Check permission, then MainFragment transaction 
 
 
//        if (findViewById(R.id.mainFragmentContainer) != null) 
//            { 
//                if (savedInstanceState != null) { 
//                    return; 
//                } 
//                MainFragment mainFragment = new MainFragment(); 
//                getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, mainFragment).commit(); 
//            } 
    } 
//*** 
 
 
    //Acting regarding the permission feedback from the user 
    @Override 
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) 
    { 
        switch (requestCode) 
        { 
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS_ID: 
            { 
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) 
                { 
                    if (findViewById(R.id.mainFragmentContainer) != null) 
                    { 
                        if (savedInstanceStateTwin != null) { 
                            return; 
                        } 
 
                        MainFragment mainFragment = new MainFragment(); 
                        getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, mainFragment).commit(); 
 
                        //TestFragment testFragment = new TestFragment(); 
                        //getFragmentManager().beginTransaction().add(R.id.mainFragmentContainer, testFragment).commit(); 
                    } 
 
                    //Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show(); 
                } 
                else 
                { 
                    Toast.makeText(this, "Permission required for further action", Toast.LENGTH_SHORT).show(); 
                } 
                return; 
            } 
        } 
 
    } 
//*** 
 
    //Method for checking permissions which is called onCreate cycle 
    private boolean checkPermissions() 
    { 
        int result; 
        ArrayList<String> listPermissionsNeeded = new ArrayList<>(); 
 
        for (String p:permissions) 
        { 
            //zaten bu method >API23 condition'ı içinde call oluyor. 
            result= checkSelfPermission(p); 
            if(result != PackageManager.PERMISSION_GRANTED) 
            { 
                listPermissionsNeeded.add(p); 
            } 
        } 
 
        if (!listPermissionsNeeded.isEmpty()) 
        { 
            //zaten bu method >API23 condition'ı içinde call oluyor. 
            requestPermissions(listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MY_PERMISSIONS_REQUEST_READ_CONTACTS_ID); 
            return false; 
        } 
        return true; 
    } 
}
