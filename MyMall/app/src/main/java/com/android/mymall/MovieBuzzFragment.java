package com.android.mymall;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieBuzzFragment extends Fragment {
    private static final String TAG = "MovieBuzzFragment";

    private RecyclerView recyview;
    private RecyclerViewAdapter_Moviebuzz adapter;

    private FirebaseFirestore mFirestore;
    private List<model> moviebuzzfeed = new ArrayList<>();

    private Context mcontext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_buzz, container, false);

        recyview = view.findViewById(R.id.recyclerview);
        mFirestore = FirebaseFirestore.getInstance();

        getmoviebuzz();
        return view;
    }

    private void getmoviebuzz() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        mFirestore.collection("buzzfeed").document("moviebuzz").collection("moviebuzzfeed").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model1 = doc.getDocument().toObject(model.class).withId(doc.getDocument().getId());
                        moviebuzzfeed.add(model1);

                        adapter.notifyDataSetChanged();

                    }

                }
            }
        });
        initRecyclerview();

    }

    private void initRecyclerview() {
        Log.d(TAG, "initRecyclerview: init");

        LinearLayoutManager layoutManager = new LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false);
        recyview.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter_Moviebuzz(moviebuzzfeed, getContext());
        recyview.setAdapter(adapter);
    }

}