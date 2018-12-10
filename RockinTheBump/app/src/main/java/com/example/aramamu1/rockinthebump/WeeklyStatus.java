package com.example.aramamu1.rockinthebump;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WeeklyStatus extends AppCompatActivity {

    //store user settings here once we get them
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.aramamu1.rockinthebump";
    //Shared preferences keys
    private final String USERNAME_KEY = "userName";
    private final String WEEK_KEY = "currentWeek";
    String uname;
    String uweek;
    TextView weeklystatus;
    TextView apptadvice;
    TextView foodadvice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_status);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        uname = mPreferences.getString(USERNAME_KEY, uname);
        uweek = mPreferences.getString(WEEK_KEY, uweek);

        weeklystatus = (TextView) findViewById(R.id.weeklystatus);
        apptadvice = (TextView) findViewById(R.id.apptadvice);
        foodadvice = (TextView) findViewById(R.id.foodadvice);

        displayAdvice(uname, uweek);

    }


    //display advice
    private void displayAdvice(String uname, String uweek){

        int uweekadv = Integer.parseInt(uweek);
        int trimester = (uweekadv/13)+1;

        if((uweekadv>=1) && (uweekadv <=5)){
            weeklystatus.setText("Welcome "+uname+" to the "+trimester+"st trimester!. You are in week "+uweek+". But you are in the beginning stages of preganancy. You have just conceived. ");
            apptadvice.setText("Take a pregnancy test at the 5th week to see if you are pregnant and call the doctor to schedule your first appointment at 8 weeks.");
            foodadvice.setText("You should not be eating any extra calories.  Get healthy veggies, proteins, etc.  Start taking prenatals including folic acid, vitamin D, calcium, iron, and DHA.  Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 6){
            weeklystatus.setText("Welcome "+uname+"!. You are in week "+uweek+". Your baby's heart just started beating. You may start to experience fatigue, nausea, and breast soreness.");
            apptadvice.setText("Your first appointment is at 8 weeks. Only advice on preparations- come up with a list of questions for the doctor.");
            foodadvice.setText("You should not be eating any extra calories.  Get healthy veggies, proteins, etc. Small meals helps with nausea. Try crackers and mint or ginger tea. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 7){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your baby's face and brain are slowly developing. You may experience fatigue, nausea, and breast soreness.");
            apptadvice.setText("Plan on getting tested before your first appointment next week for STD/HIV, rh Negative blood type, CBC, urine, thyroid, and a pap smear.");
            foodadvice.setText("You should not be eating any extra calories.  Get healthy veggies, proteins, etc. Small meals helps with nausea. Try crackers and mint or ginger tea. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 8){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Baby is starting to develop genital parts to determine his/her sex.");
            apptadvice.setText("You should be having your first appointment, where they will confirm the baby on the ultrasound as well as his/her heartbeat. You will continue to have appointments once a month until you reach the third trimester.  This can vary though depending on any pregnancy complications.");
            foodadvice.setText("You should not be eating any extra calories.  Get healthy veggies, proteins, etc. Small meals helps with nausea. Try crackers and mint or ginger tea. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");

        }
        else if(uweekadv == 9){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+".  Your baby is no longer an embroyo.");
            apptadvice.setText("Your next appointment at 12 weeks is a nucheal translucency ultrasound and a blood test to test for genetic defects. These are optional and at this time you can find out the gender of your child.");
            foodadvice.setText("You should not be eating any extra calories. Get healthy veggies, proteins, etc. Small meals helps with nausea. Try crackers and mint or ginger tea. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 10){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". ");
            apptadvice.setText("Your next appointment at 12 weeks, which you should have already setup. Your baby's bones are forming.");
            foodadvice.setText("You should not be eating any extra calories.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 11){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Baby is starting to look human and not like a cell.");
            apptadvice.setText("Next week is a nucheal translucency ultrasound and a blood test to test for genetic defects. These are optional and at this time you can find out the gender of your child.");
            foodadvice.setText("You should not be eating any extra calories.  Get healthy veggies, proteins, etc. Small meals helps with nausea. Try crackers and mint or ginger tea. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 12){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". You should be starting to feel less nauseous if you had morning sickness early on.  Baby has doubled in size. Genetic testing can now show the sex of the baby.");
            apptadvice.setText("If you chose to have the optional tests for genetic defects or to find out the gender early, this is the week those appointments happen.");
            foodadvice.setText("You should not be eating any extra calories.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 13){
            weeklystatus.setText("Welcome "+uname+" to the "+trimester+"nd trimester! You are in week "+uweek+". You should start feeling less tired. Your placenta should be finishing up getting large.");
            apptadvice.setText("You should be continuing with your monthly appointments.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 14){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your baby is now getting hair and can make facial expressions.");
            apptadvice.setText("You should be continuing with your monthly appointments.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 15){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your baby's eyes and ears are developing.");
            apptadvice.setText("You should be continuing with your monthly appointments.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 16){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your baby is sensitive to light, this means you can trying shining a light on your belly to see if baby responds.");
            apptadvice.setText("You should be continuing with your monthly appointments.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 17){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". You may start to feel your baby kick!  Or at least flutters which is the baby kicking- it feels like gas gurgling in your stomach but it is the baby. ");
            apptadvice.setText("You should be continuing with your monthly appointments.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 18){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your baby is developing fingerprints.");
            apptadvice.setText("You should be continuing with your monthly appointments.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 19){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your baby's lungs are forming.");
            apptadvice.setText("You should be continuing with your monthly appointments. You will have a fetal anatomy ultrasound coming up next week.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 20){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your partner may start to feel the baby kick as well if they put a hand to your stomach area.");
            apptadvice.setText("You will have your fetal anatomy ultrasound. If you haven't already, you can find out the sex of the baby during this ultrasound where they look at the structure of the baby.  Drink a liter of water an hour before and don't empty your bladder so that they can get a clear picture.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 21){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your baby's taste buds are forming and may be able to taste your food as it changes the taste of the amniotic fluid.");
            apptadvice.setText("You should be continuing with your monthly appointments.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 22){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your baby can start to hear sounds that you hear.");
            apptadvice.setText("You should be continuing with your monthly appointments.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 23){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". When your baby punches or kicks you may now be able to see your tummy move!");
            apptadvice.setText("You should be continuing with your monthly appointments. You have a glucose and CBC tests coming up next week.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 24){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Plan on starting to count baby kicks.  Setup a quiet time after having dinner or a sugary drink.  Then see if you can feel about 10 kicks in 4 hours.");
            apptadvice.setText("You will need to get a glucose test at this date to check for gestational diabetes.  You will also have an anemia or CBC screening as may pregnant women tend to develop anemia as the pregnancy progresses.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 25){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your baby's nose starts working.");
            apptadvice.setText("You should be continuing with your monthly appointments.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 26){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your baby now has fingernails!");
            apptadvice.setText("You should be continuing with your monthly appointments. You have a vaccination appointment coming up next week.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 27){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your baby has hiccups and you may be able to feel it.");
            apptadvice.setText("Plan on getting a Tdap vaccine to protect your new infant from whooping cough.  By having yourself vaccinated, baby will receive protection for their first two months. Make sure visitors are up to date on Tdap as well.");
            foodadvice.setText("Plan on 200-300 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 28){
            weeklystatus.setText("Welcome "+uname+" to the "+trimester+"rd trimester! You are in week "+uweek+". Your baby can now experience REM sleep.");
            apptadvice.setText("Appointments will now start to increase once every two weeks. If you are Rh negative, you will receive an Rhogam shot.");
            foodadvice.setText("Plan on 300-400 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 29){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your belly is getting cramped as the baby grows. You could start feeling heartburn, not being able to take deep breaths, or feel the need to urinate more frequently as a result.");
            apptadvice.setText("You should be continuing with your bi-weekly appointment.");
            foodadvice.setText("Plan on 300-400 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 30){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your baby is now producing baby's own blood marrow.");
            apptadvice.setText("You should be continuing with your bi-weekly appointment.");
            foodadvice.setText("Plan on 300-400 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 31){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your baby now has all 5 senses! ");
            apptadvice.setText("You should be continuing with your bi-weekly appointment.  You have an ultrasound coming up next week.");
            foodadvice.setText("Plan on 300-400 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 32){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Your baby's skin is now opaque and no longer transparent.");
            apptadvice.setText(" You will have an ultrasound at this point to check fetal position.");
            foodadvice.setText("Plan on 300-400 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 33){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Baby's immune system is developing and baby can differentiate night and day.");
            apptadvice.setText("You should be continuing with your bi-weekly appointment.");
            foodadvice.setText("Plan on 300-400 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 34){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Vernix starts to thicken this week- it is the white coating on babies when they are born.");
            apptadvice.setText("You should be continuing with your bi-weekly appointment.");
            foodadvice.setText("Plan on 300-400 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 35){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Baby should start to position itself face down by now in preparation to descend down the canal.");
            apptadvice.setText("You should be continuing with your bi-weekly appointment. You have a group b stress test coming up next week.");
            foodadvice.setText("Plan on 300-400 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 36){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". Baby may start to drop into the canal- called lightening- that doesn't mean you are giving birth this week. ");
            apptadvice.setText("You will have a group b stress test and if you have it antibiotics will be adminstered during delivery.");
            foodadvice.setText("Plan on 300-400 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if(uweekadv == 37){
            weeklystatus.setText("Welcome "+uname+"! You are in week "+uweek+". If baby is delivered this week baby is considered early term, but the lungs should be developed enough that it is ok. Baby should be about 6 pounds by now.");
            apptadvice.setText("You will probably have a non-stress test to monitor how the baby is doing in terms of heartbeat and activity.");
            foodadvice.setText("Plan on 300-400 extra calories daily.  Get healthy veggies, proteins, etc. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else if((uweekadv >= 38) && (uweekadv<=42)){
            weeklystatus.setText("Welcome "+uname+"! to the "+trimester+". You are in week "+uweek+". You should be delivering soon!");
            apptadvice.setText("You should be having weekly appointments with your doctor at this point.  You will probably have a non-stress test to monitor how the baby is doing in terms of heartbeat and activity. Congrats on delivering soon!  If you are at 42 weeks most likely you will have an induction soon.  It is inadvisable to go past 42 weeks to carry the baby.");
            foodadvice.setText("Get 300-400 calories extra per day. Avoid caffeine, alcohol, fish, processed meats, soft cheeses, and sprouts.");
        }
        else{

            weeklystatus.setText("Welcome "+uname+"!");
            apptadvice.setText("");
            foodadvice.setText("");

        }


    }
}
