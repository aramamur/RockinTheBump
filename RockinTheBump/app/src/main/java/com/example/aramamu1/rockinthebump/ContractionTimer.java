package com.example.aramamu1.rockinthebump;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.os.SystemClock;

import android.widget.TextView;


public class ContractionTimer extends AppCompatActivity {

    TextView status;
    TextView contractionStatus;
    TextView timertext;

    long startTime;
    long endTime;

    boolean start = true;
    private Handler contractionTimerHandler = new Handler();


    long secondsContraction;
    private long contractionTimerStart;

    long timerMilli = 0L;
    long updatedTimer = 0L;

    String secondsElapsed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contraction_timer);
        status = (TextView)findViewById(R.id.status);
        contractionStatus = (TextView)findViewById(R.id.contractionStatus);
        timertext = (TextView)findViewById(R.id.timertext);
    }

    public void timeStartContraction(View view) {
        contractionStatus.setText("Contraction started button pushed last.");

        if(start == true){
            //initialize
            start = false;
            contractionTimerStart = SystemClock.uptimeMillis();
            startTime = contractionTimerStart;
            contractionTimerHandler.postDelayed(updateContractionTimerThread, 0);
        }
        else
        {
            //check time in between contractions and time in contractions
            startTime = SystemClock.uptimeMillis();
            long elapsedTime = startTime - endTime;
            long elapsedSeconds = elapsedTime / 1000;
            long elapsedMinutes = elapsedSeconds / 60;
            long minutesDisplay = elapsedMinutes % 60;
            String minutesElapsed = Long.toString(minutesDisplay);
            String status_display = null;

            //if time shows active labor
            if((minutesDisplay>=3) && (minutesDisplay<=5)){

                if((secondsContraction <=60) && (secondsContraction >=45)){
                    status_display = "Go to the hospital now! You are in active labor. Minutes between contractions "+minutesElapsed+". Seconds contractions last "+secondsElapsed+".";

                }
            }
            else {
                status_display = "You are in the early stages of labor. Minutes between contractions "+minutesElapsed+". Seconds contractions last "+secondsElapsed+".";
            }
            status.setText(status_display);
        }

    }

    public void timeEndContraction(View view){

        contractionStatus.setText("Contraction ended button pushed last.");
           endTime = SystemClock.uptimeMillis();
           long elapsedTime = endTime - startTime;
           long elapsedSeconds = elapsedTime / 1000;
           secondsContraction = elapsedSeconds % 60;

           secondsElapsed = Long.toString(secondsContraction);

    }

    private Runnable updateContractionTimerThread = new Runnable() {

        public void run() {

            timerMilli = SystemClock.uptimeMillis() - contractionTimerStart;

            int secs = (int) (timerMilli / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            timertext.setText("" + mins + ":"
                    + String.format("%02d", secs));
            contractionTimerHandler.postDelayed(this, 0);
        }

    };


}
