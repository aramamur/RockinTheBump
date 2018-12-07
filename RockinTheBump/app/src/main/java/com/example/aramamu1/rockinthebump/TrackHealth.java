package com.example.aramamu1.rockinthebump;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;


public class TrackHealth extends AppCompatActivity {


    EditText currentweight;
    EditText bloodpress;
    EditText fetalhb;
    TextView advice;
    TextView healthresults;
    //store user settings here once we get them
    private SharedPreferences mPreferences;
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
    String uweek;
    String initial_weight;
    String current_date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_health);
        currentweight = (EditText) findViewById(R.id.currentweight);
        bloodpress = (EditText) findViewById(R.id.bloodpress);
        fetalhb = (EditText) findViewById(R.id.fetalhb);
        advice = (TextView)findViewById(R.id.advice);
        healthresults = (TextView)findViewById(R.id.healthresults);

        //restore user data
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        uid = mPreferences.getInt(USERID_KEY, uid);
        uname = mPreferences.getString(USERNAME_KEY, uname);
        udate = mPreferences.getString(DELIVERYDATE_KEY, udate);
        uweek = mPreferences.getString(WEEK_KEY, udate);
        initial_weight = mPreferences.getString(INITWEIGHT_KEY, initial_weight);

        final Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        current_date = String.valueOf(month)+"/"+String.valueOf(day)+"/"+String.valueOf(year);

    }

    public void showAdvice(View view) {

        int cweight = Integer.parseInt(currentweight.getText().toString());
        int iweight = Integer.parseInt(initial_weight);
        int cweek = Integer.parseInt(uweek);
        int ideal_weight = 0;


        if (currentweight.getText().toString().isEmpty() || (currentweight.getText().toString() == null)){
            currentweight.setText("0");
        }
        if (bloodpress.getText().toString().isEmpty() || (bloodpress.getText().toString() == null)){
            bloodpress.setText("0");
        }
        if (fetalhb.getText().toString().isEmpty() || (fetalhb.getText().toString() == null)){
            fetalhb.setText("0");
        }

        //add to the database
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Health health = new Health(uid, current_date, cweight, Integer.parseInt(bloodpress.getText().toString()), Integer.parseInt(fetalhb.getText().toString()) );
        dbHandler.addHealthHandler(health);
        String create = "Health results added";
        healthresults.setText(create);

        //int trimester = (cweek/3)+1;
        String setadvice = null;

        if(cweek<13){
            ideal_weight = iweight+3;
        }
        else
        {
            ideal_weight = iweight + (cweek-13)+3;
        }
            if(cweight<iweight) {
                setadvice = "You should be eating more. Some advice: Eat some more calories throughout the day. Your current weight "+Integer.toString(cweight)+" your ideal weight ( to +10) is  "+Integer.toString(ideal_weight)+".";

            }
            else if(cweight<(ideal_weight-5))
            {
                setadvice = "You need to gain more weight! Some advice: Eat 300 more calories throughout the day. Your current weight "+Integer.toString(cweight)+" your ideal weight ( to +10) is  "+Integer.toString(ideal_weight)+".";
            }
            else if(cweight>(ideal_weight+10)) {
                setadvice = "You are close to the borderline of eating too much. Try to get some exercise like walking and keep track of calories. Your current weight "+Integer.toString(cweight)+" your ideal weight ( to +10) is "+Integer.toString(ideal_weight)+".";
            }
            else {
                setadvice = "Good job maintaining weight.  Remember as pregnancy progresses you need to have about 300 extra calories a day. Your current weight "+Integer.toString(cweight)+".";
            }
            advice.setText(setadvice);
            currentweight.setText("");
            bloodpress.setText("");
            fetalhb.setText("");

        }

        public void loadResults(View view){
            MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
            String status_text = dbHandler.loadHealthHandler(uid);
            if(status_text != null) {
                healthresults.setText(status_text);
            }
            else
            {
                healthresults.setText("No Match Found");
            }
            currentweight.setText("");
            bloodpress.setText("");
            fetalhb.setText("");
        }
    }



