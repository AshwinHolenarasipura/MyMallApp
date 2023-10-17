package com.android.mymall;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * A simple {@link Fragment} subclass.
 */
public class ShoppingBuzzFragment extends Fragment {
    private static final String TAG = "ShoppingBuzzFragment";

    private RecyclerView recyview;
    private RecyclerViewAdapter_Shoppingbuzz adapter;

    private FirebaseFirestore mFirestore;
    private List<model> shoppingbuzzfeed = new ArrayList<>();

    private Context mcontext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_buzz, container, false);

        recyview = view.findViewById(R.id.recyclerview);
        mFirestore = FirebaseFirestore.getInstance();

        getshoppingbuzz();
        return view;
    }
    private void getshoppingbuzz() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        mFirestore.collection("buzzfeed").document("shoppingbuzz").collection("shoppingbuzzfeed").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model1 = doc.getDocument().toObject(model.class);
                        shoppingbuzzfeed.add(model1);

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
        adapter = new RecyclerViewAdapter_Shoppingbuzz(shoppingbuzzfeed, getContext());
        recyview.setAdapter(adapter);
    }



}
