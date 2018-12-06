package com.example.aramamu1.rockinthebump;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    TextView textResponse;
    EditText userid;
    EditText userpswd;
    //store user settings here once we get them- shared preferences
    //preferences object
    private SharedPreferences mPreferences;
    //preferences name
    private String sharedPrefFile =
            "com.example.aramamu1.rockinthebump";
    //Shared preferences keys
    private final String USERID_KEY = "userID";
    private final String USERNAME_KEY = "userName";
    private final String DELIVERYDATE_KEY = "deliveryDate";
    private final String WEEK_KEY = "currentWeek";
    private final String INITWEIGHT_KEY = "initialWeight";

    int    uid;
    String uname;
    String udate;
    String cweek;
    String weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        textResponse = (TextView) findViewById(R.id.textResponse);
        userid = (EditText) findViewById(R.id.userid);
        userpswd = (EditText) findViewById(R.id.userpswd);

    }

    public void findUser (View view) throws ParseException {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        User user = dbHandler.findHandler(Integer.parseInt(userid.getText().toString()), userpswd.getText().toString());
        if (user != null) {
            uid= user.getID();
            uname = user.getUserName();
            udate = user.getDeliveryDate();
            cweek = return_weeks(udate);
            weight = user.getInitialWeight();
            textResponse.setText("Welcome "+uname+"!");
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putInt(USERID_KEY, uid);
            preferencesEditor.putString(USERNAME_KEY, uname);
            preferencesEditor.putString(DELIVERYDATE_KEY, udate);
            preferencesEditor.putString(WEEK_KEY, cweek);
            preferencesEditor.putString(INITWEIGHT_KEY, weight);
            preferencesEditor.apply();
        } else {
            textResponse.setText("No Match Found"+System.getProperty("line.separator")+"Please register if you don't have an account or contact the admin if you forgot your password.");
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.clear();
            preferencesEditor.apply();
            preferencesEditor.putInt(USERID_KEY, 0);
            preferencesEditor.apply();
        }
    }

    public String return_weeks(String deliveryDate) throws ParseException {

        Date deliveryDateObj = new SimpleDateFormat("MM/dd/yyyy").parse(deliveryDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(deliveryDateObj);

        //get pregnancy start date
        calendar.add(Calendar.WEEK_OF_YEAR, -40);
        Date startDate = calendar.getTime();

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
