package com.android.mymall;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class ShoppingActivity extends AppCompatActivity {
    private static final String TAG = "ShoppingActivity";

    private RecyclerView recyview;
    private RecyclerView recyview1;
    private RecyclerViewAdapter2_aa adapter;
    private RecyclerViewAdapter2_bb adapter1;

    private FirebaseFirestore mFirestore;
    private List<model> shopofferslist = new ArrayList<>();
    private List<model> shopphotoslist = new ArrayList<>();
    private Context scontext;

    private TextView phoneno;
    private TextView styles;
    private TextView collections;
    private TextView price;
    private TextView floor_level;

    private String mallid;


    //  private android.support.v4.app.FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        mFirestore = FirebaseFirestore.getInstance();
        phoneno = findViewById(R.id.phonenumber);
        styles = findViewById(R.id.styles);
        collections = findViewById(R.id.collectiontxt);
        price = findViewById(R.id.price);
        floor_level = findViewById(R.id.floorlevelnum);

        // manager = getSupportFragmentManager();

        recyview = findViewById(R.id.recyclerview);
        recyview1 = findViewById(R.id.recyclerview1);
        scontext = getBaseContext();

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name") && getIntent().hasExtra("shopping_id") && getIntent().hasExtra("mall_id")) {
            Log.d(TAG, "getIncomingIntent: found extras");

            String imageurl = getIntent().getStringExtra("image_url");
            String imagename = getIntent().getStringExtra("image_name");
            String shoppingid = getIntent().getStringExtra("shopping_id");
            String mallid = getIntent().getStringExtra("mall_id");

            setImage(imageurl, imagename);
            setshopinfo(shoppingid, mallid);
            getphotos(shoppingid, mallid);

        }
    }

    private void setImage(String imgurl, String imagename) {
        Log.d(TAG, "setImage: setting the image");
        TextView name = findViewById(R.id.shopname);
        name.setText(imagename);

        ImageView image = findViewById(R.id.shopimg);
        Glide.with(this)
                .asBitmap()
                .load(imgurl)
                .into(image);
    }

    private void setshopinfo(String shoppingid, String m_id) {

        Toast.makeText(scontext, m_id, Toast.LENGTH_SHORT).show();

        mFirestore.collection("malls").document(m_id).collection("Shopping").document(shoppingid).collection("ShopData").document("Data").addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                if (documentSnapshot.exists()) {

                    String cost = documentSnapshot.getString("cost");
                    String collectionname = documentSnapshot.getString("collectionname");
                    String stylename = documentSnapshot.getString("stylename");
                    String number = documentSnapshot.getString("phoneno");
                    String mfloorlevel = documentSnapshot.getString("floor_level");

                    phoneno.setText(number);
                    collections.setText(collectionname);
                    styles.setText(stylename);
                    price.setText(cost);
                    floor_level.setText(mfloorlevel);
                }
            }
        });
        mFirestore.collection("malls").document(m_id).collection("Shopping").document(shoppingid).collection("ShopOffers").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class);
                        shopofferslist.add(model);

                        adapter.notifyDataSetChanged();

                    }

                }
            }
        });

        initRecyclerview();
    }

    private void getphotos(String shoppingid, String m_id) {
        mFirestore.collection("malls").document(m_id).collection("Shopping").document(shoppingid).collection("ShopPhotos").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class);
                        shopphotoslist.add(model);

                        adapter1.notifyDataSetChanged();

                    }

                }
            }
        });

        initRecyclerview2();

    }

    private void initRecyclerview() {
        Log.d(TAG, "initRecyclerview: init");

        LinearLayoutManager layoutManager = new LinearLayoutManager(scontext, LinearLayoutManager.HORIZONTAL, false);
        recyview.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter2_aa(shopofferslist, scontext);
        recyview.setAdapter(adapter);
    }

    private void initRecyclerview2() {
        Log.d(TAG, "initRecyclerview: init");

        LinearLayoutManager layoutManager = new LinearLayoutManager(scontext, LinearLayoutManager.HORIZONTAL, false);
        recyview1.setLayoutManager(layoutManager);
        adapter1 = new RecyclerViewAdapter2_bb(shopphotoslist, scontext);
        recyview1.setAdapter(adapter1);
    }
}
