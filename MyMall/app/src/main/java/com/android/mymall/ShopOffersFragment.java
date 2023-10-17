package com.android.mymall;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShopOffersFragment extends Fragment {

    private static final String TAG = "ShopOffersFragment";
    private RecyclerView recyview;
    private RecyclerViewAdapter2 adapter;

    private FirebaseFirestore mFirestore;
    private List<model> shopofferslist = new ArrayList<>();

    private String id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null){
            id = bundle.getString("shopid");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_offers,null,false);

        recyview = view.findViewById(R.id.recyclerview);
        mFirestore = FirebaseFirestore.getInstance();

       getimages();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    private void getimages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        Toast.makeText(getContext(),id, Toast.LENGTH_SHORT).show();

        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

    }
    private void initRecyclerview(){
        Log.d(TAG, "initRecyclerview: init");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext() ,LinearLayoutManager.HORIZONTAL ,false);
        recyview.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter2(shopofferslist,getContext());
        recyview.setAdapter(adapter);
    }
}
