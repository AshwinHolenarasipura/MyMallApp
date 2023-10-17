package com.android.mymall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieInfoActivity extends AppCompatActivity {
    private static final String TAG = "MovieInfoActivity";

    private TextView share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        share = findViewById(R.id.sharetxt);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")) {
            Log.d(TAG, "getIncomingIntent: found extras");

            String imageurl = getIntent().getStringExtra("image_url");
            String imagename = getIntent().getStringExtra("image_name");

            setImage(imageurl, imagename);
        }
    }

    private void setImage(String imgurl, String imagename) {
        Log.d(TAG, "setImage: setting the image");
        TextView name = findViewById(R.id.moviename);
        TextView name1 = findViewById(R.id.moviename1);
        name.setText(imagename);
        name1.setText(imagename);

        ImageView image = findViewById(R.id.movieimagecontainer);
        ImageView image1 = findViewById(R.id.moviecontainer);
        Glide.with(this)
                .asBitmap()
                .load(imgurl)
                .into(image);

        Glide.with(this)
                .asBitmap()
                .load(imgurl)
                .into(image1);

    }
}
