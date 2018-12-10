package com.example.aramamu1.rockinthebump;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;

public class TrackAppt extends AppCompatActivity {
    int uid;
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.aramamu1.rockinthebump";
    private final String USERID_KEY = "userID";


    EditText editDesc;
    TextView createstatus;
    String apptDate;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_appt);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        uid = mPreferences.getInt(USERID_KEY, uid);

        editDesc = (EditText) findViewById(R.id.editDesc);
        createstatus = (TextView)findViewById(R.id.createstatus);



    }

    public void addAppointment(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Appointment appt = new Appointment(uid, apptDate, editDesc.getText().toString() );
        dbHandler.addApptHandler(appt);
        String create = "Appointment created "+apptDate+" "+editDesc.getText().toString();
        createstatus.setText(create);
        editDesc.setText("");
    }

    public void deleteAppointment(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        boolean result = dbHandler.deleteApptHandler(apptDate);
        if (result) {
            editDesc.setText("");
            createstatus.setText("Record deleted");
        } else
            createstatus.setText("No Match Found");
    }

    public void updateAppointment(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        boolean result = dbHandler.updateApptHandler(uid, editDesc.getText().toString(), apptDate);
        if (result) {
            editDesc.setText("");
            createstatus.setText("Record Updated");
        } else
            createstatus.setText("No Match Found");
    }

    public void findAppointment(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Appointment appt = dbHandler.findApptHandler(uid, apptDate);
        if (appt != null) {
            createstatus.setText(String.valueOf(String.valueOf(appt.getUserID())+" "+appt.getDate()+" "+appt.getDescription()));

        } else {
            createstatus.setText("No Match Found");
        }
        editDesc.setText("");
    }

    public void loadAppointment(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        String status_text = dbHandler.loadApptHandler(uid);
        if(status_text != null) {
            createstatus.setText(status_text);
        }
        else
        {
            createstatus.setText("No Match Found");
        }
        editDesc.setText("");

    }

    /**
     * Handles the button click to create a new date picker fragment and
     * show it.
     *
     * @param view View that was clicked
     */
    public void showDatePicker(View view) {
        DialogFragment newFragment = new AppointmentDateFragment();
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
        apptDate = (month_string +
                "/" + day_string +
                "/" + year_string);
    }

}
