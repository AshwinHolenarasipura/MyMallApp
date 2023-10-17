package com.android.mymall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PhotoDisplayActivity extends AppCompatActivity {
    private static final String TAG = "PhotoDisplayActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_display);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        if (getIntent().hasExtra("image_url")){
            Log.d(TAG, "getIncomingIntent: found extras");

            String imageurl = getIntent().getStringExtra("image_url");

            setImage(imageurl);
        }
    }
    private void setImage(String imgurl){
        Log.d(TAG, "setImage: setting the image");

        ImageView image = findViewById(R.id.photodisplaycontainer);
        Glide.with(this)
                .asBitmap()
                .load(imgurl)
                .into(image);
    }
}
