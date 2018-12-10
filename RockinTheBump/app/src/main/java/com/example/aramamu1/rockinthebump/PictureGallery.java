package com.example.aramamu1.rockinthebump;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class PictureGallery extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.aramamu1.rockinthebump";
    //Shared preferences keys
    private final String USERID_KEY = "userID";
    int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_gallery);

        //get the user id
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        uid = mPreferences.getInt(USERID_KEY, uid);
        ArrayList<Picture> photos = loadPictures();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        //how many images per line in grid layout manager
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        GalleryAdapter adapter = new GalleryAdapter(getApplicationContext(), photos);
        recyclerView.setAdapter(adapter);



    }

    private ArrayList<Picture> loadPictures(){
        //get file locations for pictures based on database entry for userid
        ArrayList<Picture> images;
        MyDBHandler db = new MyDBHandler(this, null, null, 1);
        images= db.loadPictureHandler(uid);
        return images;
    }
}
