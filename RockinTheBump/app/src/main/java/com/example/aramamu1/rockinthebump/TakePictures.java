package com.example.aramamu1.rockinthebump;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import	android.net.Uri;

import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;
import 	java.io.File;
import android.os.Environment;


import android.widget.ImageView;


public class TakePictures extends AppCompatActivity {

    public static final String FILE_URI_KEY = "FILE_URI_KEY";
    private ImageView bumppic;
    private File filename;
    Uri imageFileUri;
    private String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if( savedInstanceState != null ){
            String fileUriString = savedInstanceState.getString(FILE_URI_KEY);
            if( fileUriString != null ) {
                imageFileUri = Uri.fromFile(new File(fileUriString));
            }
        }

        setContentView(R.layout.activity_take_pictures);


        bumppic = (ImageView) findViewById(R.id.bumppic);

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
                Environment.DIRECTORY_PICTURES), "Camera");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("Camera", "failed to create directory");
                return null;
            }
        }
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                mediaStorageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( resultCode == RESULT_OK ){
            Bitmap cameraBitmap = BitmapFactory.decodeFile(imageFileUri.getPath());
            ImageView imageView = (ImageView)findViewById(R.id.bumppic);
            imageView.setImageBitmap(cameraBitmap);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String imageFileUriString = imageFileUri.getPath();
        outState.putString(FILE_URI_KEY, imageFileUriString);
    }

}

