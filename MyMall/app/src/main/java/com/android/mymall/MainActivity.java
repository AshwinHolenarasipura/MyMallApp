package com.android.mymall;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private android.support.v4.app.FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        init();

    }
    private void init(){
        MainFragment fragment = new MainFragment();
        FragmentTransaction transaction = MainActivity.this.manager.beginTransaction();
        transaction.replace(R.id.mainfeedimgcontainer, fragment);
        manager.popBackStack("init", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction.commit();
    }
}