package com.android.mymall;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.transition.TransitionInflater;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.Calendar;

public class FoodActivity extends AppCompatActivity {
    private static final String TAG = "FoodActivity";

    private TextView mday;
    private TextView mopen;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        mday = findViewById(R.id.day);
        mopen = findViewById(R.id.foodopen);
        image = findViewById(R.id.foodimg);

        getIncomingIntent();
        settimeanddayinfo();
    }
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")){
            Log.d(TAG, "getIncomingIntent: found extras");

            String imageurl = getIntent().getStringExtra("image_url");
            String imagename = getIntent().getStringExtra("image_name");

            setImage(imageurl,imagename);
        }
    }
    private void setImage(String imgurl, String imagename){
        Log.d(TAG, "setImage: setting the image");
        TextView name = findViewById(R.id.foodname);
        name.setText(imagename);

        Glide.with(this)
                .asBitmap()
                .load(imgurl)
                .into(image);
    }
    private void settimeanddayinfo(){

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        switch (day){

            case Calendar.SUNDAY:

                mday.setText(R.string.Sunday);

                if(timeOfDay >= 0 && timeOfDay < 10){
                    mopen.setText(R.string.closed);
                    mopen.setTextColor(getResources().getColor(R.color.red));
                }else if(timeOfDay >= 11 && timeOfDay < 24){
                    mopen.setText(R.string.opennow);
                }else if (timeOfDay >=10 && timeOfDay < 11){
                    mopen.setText(R.string.openshortly);
                    mopen.setTextColor(Color.GRAY);
                }
               break;

            case Calendar.MONDAY:

                mday.setText(R.string.Monday);

                if(timeOfDay >= 0 && timeOfDay < 10){
                    mopen.setText(R.string.closed);
                    mopen.setTextColor(getResources().getColor(R.color.red));
                }else if(timeOfDay >= 11 && timeOfDay < 24){
                    mopen.setText(R.string.opennow);
                }else if (timeOfDay >= 10 && timeOfDay < 11){
                    mopen.setText(R.string.openshortly);
                    mopen.setTextColor(Color.GRAY);
                }

                break;

            case Calendar.TUESDAY:

                mday.setText(R.string.Tuesday);

                if(timeOfDay >= 0 && timeOfDay < 10){
                    mopen.setText(R.string.closed);
                    mopen.setTextColor(getResources().getColor(R.color.red));
                }else if(timeOfDay >= 11 && timeOfDay < 24){
                    mopen.setText(R.string.opennow);
                }else if (timeOfDay >=10 && timeOfDay < 11){
                    mopen.setText(R.string.openshortly);
                    mopen.setTextColor(Color.GRAY);
                }
               break;

            case Calendar.WEDNESDAY:

                mday.setText(R.string.Wednesday);

                if(timeOfDay >= 0 && timeOfDay < 10){
                    mopen.setText(R.string.closed);
                    mopen.setTextColor(getResources().getColor(R.color.red));
                }else if(timeOfDay >= 11 && timeOfDay < 24){
                    mopen.setText(R.string.opennow);
                }else if (timeOfDay >=10 && timeOfDay < 11){
                    mopen.setText(R.string.openshortly);
                    mopen.setTextColor(Color.GRAY);
                }

                break;

            case Calendar.THURSDAY:

                mday.setText(R.string.Thursday);

                if(timeOfDay >= 0 && timeOfDay < 10){
                    mopen.setText(R.string.closed);
                    mopen.setTextColor(getResources().getColor(R.color.red));
                }else if(timeOfDay >= 11 && timeOfDay < 24){
                    mopen.setText(R.string.opennow);
                }else if (timeOfDay >=10 && timeOfDay < 11){
                    mopen.setText(R.string.openshortly);
                    mopen.setTextColor(Color.GRAY);
                }
               break;

            case Calendar.FRIDAY:

                mday.setText(R.string.Friday);
                if(timeOfDay >= 0 && timeOfDay < 10){
                    mopen.setText(R.string.closed);
                    mopen.setTextColor(getResources().getColor(R.color.red));
                }else if(timeOfDay >= 11 && timeOfDay < 24){
                    mopen.setText(R.string.opennow);
                }else if (timeOfDay >=10 && timeOfDay < 11){
                    mopen.setText(R.string.openshortly);
                    mopen.setTextColor(Color.GRAY);
                }
                break;

            case Calendar.SATURDAY:

                mday.setText(R.string.saturday);

                if(timeOfDay >= 0 && timeOfDay < 10){
                    mopen.setText(R.string.closed);
                    mopen.setTextColor(getResources().getColor(R.color.red));
                }else if(timeOfDay >= 11 && timeOfDay < 24){
                    mopen.setText(R.string.opennow);
                }else if (timeOfDay >=10 && timeOfDay < 11){
                    mopen.setText(R.string.openshortly);
                    mopen.setTextColor(Color.GRAY);
                }

                break;
        }
    }
}
