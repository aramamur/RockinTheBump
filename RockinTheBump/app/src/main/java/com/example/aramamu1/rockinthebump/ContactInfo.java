package com.example.aramamu1.rockinthebump;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactInfo extends AppCompatActivity {

    TextView textcontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        textcontact = (TextView) findViewById(R.id.textcontact);

        textcontact.setText("Please email:" +System.getProperty("line.separator")+"aramamur@umich.edu for help.");
    }
}
