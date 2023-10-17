package com.android.mymall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashwin on 17-02-2018.
 */

public class MovieFeedFragment extends Fragment {
    private static final String TAG = "MovieFeedFragment";

    private RecyclerView recyview;
    private RecyclerViewAdapter8 adapter;

    private FirebaseFirestore mFirestore;
    private List<model2> moviemainfeedlist = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_feed,null,false);
        recyview = view.findViewById(R.id.recyclerview);
        mFirestore = FirebaseFirestore.getInstance();

        getimages();
        return view;
    }
    private void getimages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        mFirestore.collection("/malls/1/Cinema/Theater/MainFeed").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null){

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()){

                    if (doc.getType() == DocumentChange.Type.ADDED){
                        model2 model = doc.getDocument().toObject(model2.class);
                        moviemainfeedlist.add(model);

                        adapter.notifyDataSetChanged();

                    }

                }
            }
        });
        initRecyclerview();

    }
    private void initRecyclerview(){
        Log.d(TAG, "initRecyclerview: init");

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2, GridLayoutManager.VERTICAL ,false);
        recyview.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter8(moviemainfeedlist,getContext());
        recyview.setAdapter(adapter);
    }

}
