package com.android.mymall;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class LanguageSelectionFragment extends Fragment {
    private static final String TAG = "LanguageSelectionFragment";


    private RecyclerView recyview;
    private RecyclerViewAdapter7 adapter;

    private FirebaseFirestore mFirestore;
    private List<model> languagelist = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_language_selection,null,false);
        recyview = view.findViewById(R.id.recyclerview);
        mFirestore = FirebaseFirestore.getInstance();

        getimages();
        return view;
    }
    private void getimages(){

        mFirestore.collection("/malls/1/Cinema/Theater/LanguageSelection").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null){

                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()){

                    if (doc.getType() == DocumentChange.Type.ADDED){
                        model model = doc.getDocument().toObject(model.class);
                        languagelist.add(model);

                        adapter.notifyDataSetChanged();

                    }

                }
            }
        });
        initRecyclerview();

    }
    private void initRecyclerview(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL ,false);
        recyview.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter7(languagelist,getContext());
        recyview.setAdapter(adapter);
    }
}
