package com.example.lenovo.socialbars; 
 
import android.content.Context; 
import android.database.Cursor; 
import android.net.Uri; 
import android.provider.ContactsContract; 
import android.view.LayoutInflater; 
import android.view.View; 
import android.view.ViewGroup; 
import android.widget.ArrayAdapter; 
import android.widget.ImageView; 
import android.widget.SeekBar; 
import android.widget.TextView; 
 
import java.util.ArrayList; 
 
/** 
 * Created by Lenovo on 4.6.2017. 
 */ 
 
public class CustomAdapter1 extends ArrayAdapter<PersonData> 
{ 
    //CustomAdapter1 manual constructor 
    public CustomAdapter1(Context context, ArrayList<PersonData> data) 
    { 
        super(context, 0, data); 
    } 
    //*** 
 
 
    @Override 
    public int getCount() 
    { 
        return 50; 
    } 
 
    @Override 
    public View getView(int position, View convertView, ViewGroup parent) 
    { 
        final PersonData personData = getItem(position); 
 
        if (convertView == null) 
        { 
            LayoutInflater inflater = LayoutInflater.from(getContext()); 
            convertView = inflater.inflate(R.layout.row_fragment_main_layout, parent, false); 
        } 
 
        //Initiate and declare all the widgets in row layout 
        ImageView imageView = (ImageView) convertView.findViewById(R.id.rowImage); 
        SeekBar seekBar = (SeekBar) convertView.findViewById(R.id.rowBar); 
        TextView textView2 = (TextView) convertView.findViewById(R.id.rowText2); 
        TextView textView3 = (TextView) convertView.findViewById(R.id.rowText3); 
        //*** 
 
        //Assign connection between widgets in Row layout and Data Model by TextView and ImageView 
 
        //Getting contacts details from phone contactlist 
 
        //String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME}; 
        //Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null, null, null, null); 
        //cursor.close(); 
 
        textView3.setText(personData.getName()); 
        textView2.setText(String.valueOf(personData.getPhoneNumber())); 
 
        seekBar.setProgress((personData.getSocialPoint())); 
        seekBar.setClickable(false); 
        seekBar.setFocusable(false); 
        seekBar.setEnabled(false); 
        seekBar.setThumb(null); 
 
        //TODO: if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) ile düşük API için de kod yaz. 
        //Changing the colour of seekbar in runtime in relation to the social points 
        if (personData.getSocialPoint() <= 30) 
        { 
            seekBar.setProgressDrawable(getContext().getDrawable(R.drawable.seekbar_progress_drawable3)); 
        } 
        else if (personData.getSocialPoint() > 30 && personData.getSocialPoint() < 70) 
        { 
            seekBar.setProgressDrawable(getContext().getDrawable(R.drawable.seekbar_progress_drawable2)); 
        } 
        else if (personData.getSocialPoint() >= 70) 
        { 
            seekBar.setProgressDrawable(getContext().getDrawable(R.drawable.seekbar_progress_drawable)); 
        } 
 
        //*** 
 
        if(personData.getPic() != null) 
        { 
            imageView.setImageURI(Uri.parse(personData.getPic())); 
        } 
        else 
        { 
            imageView.setImageResource(R.mipmap.test_image); 
        } 
 
 
        //imageView.setImageResource(personData.getPic()); 
        //*** 
 
        return convertView; 
    } 
}
