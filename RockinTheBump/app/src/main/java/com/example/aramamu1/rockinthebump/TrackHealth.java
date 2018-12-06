package com.example.aramamu1.rockinthebump;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class TrackHealth extends AppCompatActivity {


    EditText currentweight;
    TextView advice;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_health);
        currentweight = (EditText) findViewById(R.id.currentweight);
        advice = (TextView)findViewById(R.id.advice);

        //restore user data
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        uid = mPreferences.getInt(USERID_KEY, uid);
        uname = mPreferences.getString(USERNAME_KEY, uname);
        udate = mPreferences.getString(DELIVERYDATE_KEY, udate);
        uweek = mPreferences.getString(WEEK_KEY, udate);
        initial_weight = mPreferences.getString(INITWEIGHT_KEY, initial_weight);

    }

    public void showAdvice(View view){

        int cweight = Integer.parseInt(currentweight.getText().toString());
        int iweight = Integer.parseInt(initial_weight);
        int cweek = Integer.parseInt(uweek);
        int ideal_weight = 0;
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
                setadvice = "Good job maintaining weight.  Remember as pregnancy progresses you need to have about 300 extra calories a day Your current weight "+Integer.toString(cweight)+".";
            }
            advice.setText(setadvice);
        }



    }



