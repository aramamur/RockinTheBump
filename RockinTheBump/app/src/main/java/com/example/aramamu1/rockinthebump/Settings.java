package com.example.aramamu1.rockinthebump;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Settings extends AppCompatActivity {

    TextView lst;
    EditText userid;
    EditText username;
    EditText userpswd;
    EditText initweight;


    //store user settings here once we get them- shared preferences
    //preferences object
    private SharedPreferences mPreferences;
    //preferences name
    private String sharedPrefFile =
            "com.example.aramamu1.rockinthebump";
    private String deliverydate;
    private String currentWeek;
    //Shared preferences keys
    private final String USERID_KEY = "userID";
    private final String USERNAME_KEY = "userName";
    private final String DELIVERYDATE_KEY = "deliveryDate";
    private final String WEEK_KEY = "currentWeek";
    private final String INITWEIGHT_KEY = "initialWeight";
    String start_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        lst = (TextView) findViewById(R.id.lst);
        userid = (EditText) findViewById(R.id.userid);
        username = (EditText) findViewById(R.id.username);
        userpswd = (EditText) findViewById(R.id.userpswd);
        initweight = (EditText)findViewById(R.id.initweight);

    }

    /**
     * Handles the button click to create a new date picker fragment and
     * show it.
     *
     * @param view View that was clicked
     */
    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                "datePicker");
    }

    /**
     * Process the date picker result into strings that can be displayed in
     * a Toast.
     *
     * @param year Chosen year
     * @param month Chosen month
     * @param day Chosen day
     */
    public void processDatePickerResult(int year, int month, int day) throws ParseException {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        deliverydate = (month_string +
                "/" + day_string +
                "/" + year_string);

        currentWeek = return_weeks(deliverydate);
    }




    public void addUser (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        int id = Integer.parseInt(userid.getText().toString());
        String name = username.getText().toString();
        String pswd = userpswd.getText().toString();
        String ddate = deliverydate;
        String cweek = currentWeek;
        String weight = initweight.getText().toString();

        if(dbHandler.isUserID(id) == false) {
            User user = new User(id, name, pswd, ddate, weight);
            dbHandler.addHandler(user);
            lst.setText("user updated ");

            //put the health stats in the Health database

            Health health = new Health(id, start_date, Integer.parseInt(initweight.getText().toString()), 0, 0);
            dbHandler.addHealthHandler(health);

            userid.setText("");
            username.setText("");
            userpswd.setText("");
            initweight.setText("");

            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putInt(USERID_KEY, id);
            preferencesEditor.putString(USERNAME_KEY, name);
            preferencesEditor.putString(DELIVERYDATE_KEY, ddate);
            preferencesEditor.putString(WEEK_KEY, cweek);
            preferencesEditor.putString(INITWEIGHT_KEY, weight);
            preferencesEditor.apply();
        }
        else{
            lst.setText("UserID taken, please choose another. ");
        }
    }





    public void deleteUser(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null,
                null, 1);
        boolean result = dbHandler.deleteHandler(
                Integer.parseInt(userid.getText().toString()));
        if (result) {
            userid.setText("");
            username.setText("");
            userpswd.setText("");

            lst.setText("Record Deleted");
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.clear();
            preferencesEditor.apply();
            preferencesEditor.putInt(USERID_KEY, 0);
            preferencesEditor.apply();
        } else {
            lst.setText("No Match Found");
        }



    }



    public String return_weeks(String deliveryDate) throws ParseException {

        Date deliveryDateObj = new SimpleDateFormat("MM/dd/yyyy").parse(deliveryDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(deliveryDateObj);

        //get pregnancy start date
        calendar.add(Calendar.WEEK_OF_YEAR, -40);
        Date startDate = calendar.getTime();
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        start_date = String.valueOf(month)+"/"+String.valueOf(day)+"/"+String.valueOf(year);

        //get current date
        final Calendar c = Calendar.getInstance();
        Date currentDate = c.getTime();

        //get number of weeks
        long diffInMill = Math.abs(currentDate.getTime() - startDate.getTime());
        long days = TimeUnit.DAYS.convert(diffInMill, TimeUnit.MILLISECONDS);
        long weeksinpreg = days/7;
        String dateCon = Long.toString(weeksinpreg);

        return dateCon;
    }
}
