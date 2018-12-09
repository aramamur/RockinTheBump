package com.example.aramamu1.rockinthebump;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import	android.net.Uri;

import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import 	java.io.File;
import android.os.Environment;



import android.widget.TextView;


public class TakePictures extends AppCompatActivity {

    private File filename;
    public static final String FILE_URI_KEY = "FILE_URI_KEY";
    //store user settings here once we get them
    Uri imageFileUri;
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 0;
    TextView textDesc;
    int uid;

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.aramamu1.rockinthebump";
    //Shared preferences keys
    private final String USERID_KEY = "userID";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_pictures);
        if( savedInstanceState != null ){
            String fileUriString = savedInstanceState.getString(FILE_URI_KEY);
            if( fileUriString != null ) {
                imageFileUri = Uri.fromFile(new File(fileUriString));
            }
        }

        textDesc = (TextView)findViewById(R.id.textDesc);
        //get the user id
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        uid = mPreferences.getInt(USERID_KEY, uid);
    }


    public void storePicture(View view) throws IOException {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        filename = createImageFile();
        imageFileUri = Uri.fromFile(filename);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(cameraIntent, REQUEST_TAKE_PHOTO );
    }



   private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "RockinTheBump");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("RockinTheBump", "failed to create directory");
                return null;
            }
        }
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                mediaStorageDir      //directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        imageFileUri = Uri.fromFile(image);

        //store file in database
       //String imagepath = imageFileUri.getPath();
       MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
       Picture pic = new Picture(uid, mCurrentPhotoPath);
       dbHandler.addPictureHandler(pic);

        return image;
    }

   @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String imageFileUriString = imageFileUri.getPath();
        outState.putString(FILE_URI_KEY, imageFileUriString);
    }


}

