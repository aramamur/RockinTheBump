package com.example.aramamu1.rockinthebump;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textResponse;
    TextView appttext;
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

    int    uid = 0;
    String uname;
    String udate;
    String uweek;
    String uweight;

    // Use the current date as the default date
    final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);

    String month_string = Integer.toString(month + 1);
    String day_string = Integer.toString(day);
    String year_string = Integer.toString(year);
    String current_date = (month_string +
            "/" + day_string +
            "/" + year_string);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            textResponse = (TextView) findViewById(R.id.textResponse);
            appttext = (TextView) findViewById(R.id.appttext);
            //restore user data
            mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
            uid = mPreferences.getInt(USERID_KEY, uid);
            uname = mPreferences.getString(USERNAME_KEY, uname);
            udate = mPreferences.getString(DELIVERYDATE_KEY, udate);
            uweek = mPreferences.getString(WEEK_KEY, uweek);
            uweight = mPreferences.getString(INITWEIGHT_KEY, uweight);



            if ((uname != null) && !uname.isEmpty()) {
                textResponse.setText("Welcome " + uname + "!"+System.getProperty("line.separator")+ "You are on week " + uweek + ".");


                MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
                Appointment appt = dbHandler.findApptHandler(uid, current_date);

                if (appt != null) {
                    appttext.setText("You have an appointment today regarding:"+System.getProperty("line.separator")+appt.getDescription());

                } else {
                    appttext.setText("No Appointments Today!"+System.getProperty("line.separator")+current_date);
                }

            } else {
                textResponse.setText("Welcome to Rockin the Bump!"+System.getProperty("line.separator")+"Current Date: "+current_date);
                appttext.setText("");
            }

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Email aramamur@umich.edu for help.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
    }

    public void registerUser(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void loginUser(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            textResponse.setText("Welcome to Rockin the Bump!"+System.getProperty("line.separator")+"Current Date: "+current_date);
            appttext.setText("");
            uid = 0;
            //change it so that the shared user data is no longer the person logged in
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.clear();
            preferencesEditor.apply();
            preferencesEditor.putInt(USERID_KEY, 0);
            preferencesEditor.apply();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_wkstatus) {
            if(uid != 0) {
                Intent intent = new Intent(this, WeeklyStatus.class);
                startActivity(intent);
            }
            else{
                textResponse.setText("Please login to access this content!");
            }
        } else if (id == R.id.nav_appointment) {
            if(uid != 0) {
                Intent intent = new Intent(this, TrackAppt.class);
                startActivity(intent);
            }
            else{
                textResponse.setText("Please login to access this content!");
            }
        } else if (id == R.id.nav_health) {
            if(uid != 0) {
                Intent intent = new Intent(this, TrackHealth.class);
                startActivity(intent);
            }
            else{
                textResponse.setText("Please login to access this content!");
            }

        } else if (id == R.id.nav_pictures) {
            if(uid != 0) {
                MyDBHandler db = new MyDBHandler(this, null, null, 1);
                boolean result = db.isPicture(uid);
                if(result == true) {
                    Intent intent = new Intent(this, PictureGallery.class);
                    startActivity(intent);
                }
                else{
                    textResponse.setText("No pictures found to view.  Please take a pic first.");
                }
            }
            else{
                textResponse.setText("Please login to access this content!");
            }

        }
        else if (id == R.id.nav_takepic) {
            if(uid != 0) {

                Intent intent = new Intent(this, TakePictures.class);
                startActivity(intent);
            }
            else{
                textResponse.setText("Please login to access this content!");
            }

        }
        else if (id == R.id.nav_contractions) {
            Intent intent = new Intent(this, ContractionTimer.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_user) {

            if(uid == 1) {
                Intent intent = new Intent(this, SettingsAdmin.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(this, Settings.class);
                startActivity(intent);
            }
        }
        else if (id == R.id.nav_contact) {
            Intent intent = new Intent(this, ContactInfo.class);
            startActivity(intent);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
