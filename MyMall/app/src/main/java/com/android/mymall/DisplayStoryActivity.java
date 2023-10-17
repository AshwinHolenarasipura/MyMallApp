package com.android.mymall;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import jp.shts.android.storiesprogressview.StoriesProgressView;

/**
 * Created by Ashwin on 17-02-2018.
 */

public class DisplayStoryActivity extends AppCompatActivity {
    private static final String TAG = "DisplayStoryActivity";

    private StoriesProgressView storiesProgressView;
    private ImageView mimage;
    private ImageView mnext;
    private ImageView mbefore;
    private RelativeLayout rel;
    private String imageurl;


    private FirebaseFirestore mFirestore;

    private List<model> storylist = new ArrayList<>();

    int counter = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_story);
        Log.d(TAG, "onCreate: started");

        storiesProgressView = findViewById(R.id.stories);
        mimage = findViewById(R.id.storydisplayimage);
        mnext = findViewById(R.id.nav_next);
        mbefore = findViewById(R.id.nav_before);
        rel = findViewById(R.id.rel);

        mFirestore = FirebaseFirestore.getInstance();

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        if (getIntent().hasExtra("mall_id")) {
            Log.d(TAG, "getIncomingIntent: found extras");

            String mallid = getIntent().getStringExtra("mall_id");

            getmoviesstory(mallid);

        }
    }

    private void getmoviesstory(String mall_id) {

        mFirestore.collection("malls").document(mall_id).collection("Stories").document("MovieStories").collection("MovieStoriesUrls").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class);
                        storylist.add(model);


                        imageurl = storylist.get(counter).getUrl();

                        Glide.with(getBaseContext())
                                .asBitmap()
                                .load(imageurl)
                                .into(mimage);
                    }

                }
            }
        });

        storiesProgressView.setStoriesCount(4); // <- set stories
        storiesProgressView.setStoryDuration(3000L); // <- set a story duration
        storiesProgressView.setStoriesListener(new StoriesProgressView.StoriesListener() {
            @Override
            public void onNext() {
                imageurl = storylist.get(++counter).getUrl();
                Glide.with(getBaseContext())
                        .asBitmap()
                        .load(imageurl)
                        .into(mimage);
            }

            @Override
            public void onPrev() {
            }

            @Override
            public void onComplete() {

                Toast.makeText(DisplayStoryActivity.this, "No more", Toast.LENGTH_SHORT).show();
            }
        }); // <- set listener
        storiesProgressView.startStories(); // <- start progress

        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                storiesProgressView.skip();

            }
        });

        mbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (counter != 0) {
                    imageurl = storylist.get(--counter).getUrl();
                    Glide.with(getBaseContext())
                            .asBitmap()
                            .load(imageurl)
                            .into(mimage);
                }
                storiesProgressView.reverse();
            }
        });

        mimage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (view.isPressed()) {

                    storiesProgressView.pause();
                }

                return true;
            }
        });
        mimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                storiesProgressView.resume();
            }
        });

    }

    @Override
    protected void onDestroy() {
        // Very important !
        storiesProgressView.destroy();
        super.onDestroy();
    }
}
