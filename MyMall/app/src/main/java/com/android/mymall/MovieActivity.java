package com.android.mymall;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MovieActivity extends AppCompatActivity {
    private static final String TAG = "MovieActivity";

    private android.support.v4.app.FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        manager = getSupportFragmentManager();

        init();
    }
    private void init(){

        MainMovieOffersFragment fragment = new MainMovieOffersFragment();
        FragmentTransaction transaction = MovieActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.movieimgcontainer, fragment);
        manager.popBackStack("init", android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction.commit();

        LanguageSelectionFragment fragment1 = new LanguageSelectionFragment();
        FragmentTransaction transaction1 = MovieActivity.this.getSupportFragmentManager().beginTransaction();
        transaction1.replace(R.id.languageselectioncontainer, fragment1);
        manager.popBackStack("init", android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction1.commit();

        MovieFeedFragment fragment2 = new MovieFeedFragment();
        FragmentTransaction transaction2 = MovieActivity.this.getSupportFragmentManager().beginTransaction();
        transaction2.replace(R.id.moviefeedcontainer, fragment2);
        manager.popBackStack("init", android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction2.commit();

    }
}
