package com.android.mymall;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";

    private RecyclerView recyview;
    private RecyclerViewAdapter9 adapter;

    private FirebaseFirestore mFirestore;
    private List<model1> mainlist = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,null,false);
        recyview = view.findViewById(R.id.recyclerview);
        mFirestore = FirebaseFirestore.getInstance();

        getimages();
        return view;
    }
    private void getimages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

      mFirestore.collection("malls").addSnapshotListener(new EventListener<QuerySnapshot>() {
          @Override
          public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
              if (e != null){

                  Log.d(TAG, "onEvent: " + e.getMessage());
              }

              for (DocumentChange doc : documentSnapshots.getDocumentChanges()){

                  if (doc.getType() == DocumentChange.Type.ADDED){
                      model1 model1 = doc.getDocument().toObject(model1.class).withId(doc.getDocument().getId());
                      mainlist.add(model1);

                      adapter.notifyDataSetChanged();

                  }

              }
          }
      });
        initRecyclerview();

    }
    private void initRecyclerview(){
        Log.d(TAG, "initRecyclerview: init");

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1, GridLayoutManager.VERTICAL ,false);
        recyview.setLayoutManager(layoutManager);
        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.TOP);
        snapHelperStart.attachToRecyclerView(recyview);
        adapter = new RecyclerViewAdapter9(mainlist,getContext());
        recyview.setAdapter(adapter);
    }
}
