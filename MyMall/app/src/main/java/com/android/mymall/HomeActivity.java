package com.android.mymall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * Created by Ashwin on 14-02-2018.
 */

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private Context mContext;

    private FirebaseFirestore mFirestore;

    private RecyclerView recyview;
    private RecyclerView recyview1;
    private RecyclerView recyview2;
    private RecyclerView recyview3;
    private RecyclerView recyview4;
    private RecyclerView recyview5;
    private RecyclerView recyview6;
    private RecyclerView recyview7;
    private RecyclerView recyview8;
    private RecyclerView recyview9;
    private RecyclerView recyview10;
    private RecyclerView recyview11;

    private RecyclerViewAdapter adapter;
    private RecyclerViewAdapter_a adapter1;
    private RecyclerViewAdapter_b adapter2;
    private RecyclerViewAdapter_c adapter3;
    private RecyclerViewAdapter_d adapter4;
    private RecyclerViewAdapter2c adapter5;
    private RecyclerViewAdapter2 adapter6;
    private RecyclerViewAdapter2a adapter7;
    private RecyclerViewAdapter2b adapter8;
    private RecyclerViewAdapter3 adapter9;
    private RecyclerViewAdapter4 adapter10;
    private RecyclerViewAdapter5 adapter11;


    private List<model> cinemalist = new ArrayList<>();
    private List<model> shoppinglist = new ArrayList<>();
    private List<model> foodlist = new ArrayList<>();
    private List<model> entertainmentlist = new ArrayList<>();
    private List<model> servicelist = new ArrayList<>();
    private List<model> photolist = new ArrayList<>();
    private List<model> shoppinglistoffers = new ArrayList<>();
    private List<model> foodlistoffers = new ArrayList<>();
    private List<model> Entertainmentofferlist = new ArrayList<>();
    private List<model> movieofferslist = new ArrayList<>();
    private List<model> mainmovieofferslist = new ArrayList<>();

    private List<Banner> banners = new ArrayList<>();

    private String temp;
    private ImageView infoicon;
    private DrawerLayout dlay;
    private ImageView nav_drawer;

    private CircleImageView mcircle1;
    private CircleImageView moutercircle;


    private TextView mtext1;
    private int Flag = 0;
    private android.support.v4.app.FragmentManager manager;

    private BannerSlider bannerSlider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyview = findViewById(R.id.recyclerview);
        recyview1 = findViewById(R.id.recyclerview1);
        recyview2 = findViewById(R.id.recyclerview2);
        recyview3 = findViewById(R.id.recyclerview3);
        recyview4 = findViewById(R.id.recyclerview4);
        recyview5 = findViewById(R.id.recyclerview5);
        recyview6 = findViewById(R.id.recyclerview6);
        recyview7 = findViewById(R.id.recyclerview7);
        recyview8 = findViewById(R.id.recyclerview8);
        recyview9 = findViewById(R.id.recyclerview9);
        recyview10 = findViewById(R.id.recyclerview10);
        recyview11 = findViewById(R.id.recyclerview11);

        mFirestore = FirebaseFirestore.getInstance();
        mContext = getBaseContext();
        infoicon = findViewById(R.id.infoicon);
        dlay = findViewById(R.id.drawer);
        nav_drawer = findViewById(R.id.nav_drawer);

        mcircle1 = findViewById(R.id.listimages);
        moutercircle = findViewById(R.id.outercircle);

        mtext1 = findViewById(R.id.name);
        manager = getSupportFragmentManager();

        bannerSlider = findViewById(R.id.banner_slider1);

        nav_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dlay.openDrawer(GravityCompat.END);

            }
        });

        infoicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirestore.collection("malls").document(temp).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                        if (documentSnapshot.exists()){

                            final String mid = documentSnapshot.getString("mallid");
                            Toast.makeText(mContext,mid, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(HomeActivity.this, MallinfoActivity.class);
                            intent.putExtra("mall_id",mid);
                            startActivity(intent);

                        }

                    }
                });
            }
        });

        getIncomingIntent();
        setmaininfo();
        getmoviestories();
        init();
    }


    public void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intent");

        if (getIntent().hasExtra("mall_id") && getIntent().hasExtra("mall_name")) {
            Log.d(TAG, "getIncomingIntent: found extras");

            String mallid = getIntent().getStringExtra("mall_id");
            String mallname = getIntent().getStringExtra("mall_name");

            if (mallid != null) {

                temp = mallid;
            }
            setname(mallname);
        }
    }

    private void setname(String mallname) {

        TextView name = findViewById(R.id.mname);
        name.setText(mallname);
    }

    private void setmaininfo() {

        mFirestore.collection("malls").document(temp).collection("Cinema").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class);
                        cinemalist.add(model);
                        adapter.notifyDataSetChanged();

                    }

                }
            }
        });
        initRecyclerview();

        mFirestore.collection("malls").document(temp).collection("Shopping").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class).withId(doc.getDocument().getId());
                        shoppinglist.add(model);

                        adapter1.notifyDataSetChanged();

                    }

                }
            }
        });
        initRecyclerview1();


        mFirestore.collection("malls").document(temp).collection("Food").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class);
                        foodlist.add(model);

                        adapter2.notifyDataSetChanged();

                    }

                }
            }
        });
        initRecyclerview2();

        mFirestore.collection("malls").document(temp).collection("Entertainment").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class);
                        entertainmentlist.add(model);

                        adapter3.notifyDataSetChanged();

                    }

                }
            }
        });
        initRecyclerview3();

        mFirestore.collection("malls").document(temp).collection("Services").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class);
                        servicelist.add(model);

                        adapter4.notifyDataSetChanged();

                    }

                }
            }
        });

        initRecyclerview4();

        mFirestore.collection("malls").document(temp).collection("HomePhotos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class);
                        photolist.add(model);

                        adapter5.notifyDataSetChanged();

                    }

                }
            }
        });

        initRecyclerview5();

        mFirestore.collection("malls").document(temp).collection("ShoppingOffers").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class);
                        shoppinglistoffers.add(model);

                        adapter6.notifyDataSetChanged();

                    }

                }
            }
        });
        initRecyclerview6();

        mFirestore.collection("malls").document(temp).collection("FoodOffers").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class);
                        foodlistoffers.add(model);

                        adapter7.notifyDataSetChanged();

                    }

                }
            }
        });
        initRecyclerview7();

        mFirestore.collection("malls").document(temp).collection("EntertainmentOffers").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class);
                        Entertainmentofferlist.add(model);

                        adapter8.notifyDataSetChanged();

                    }

                }
            }
        });

        initRecyclerview8();

        mFirestore.collection("malls").document(temp).collection("CinemaOffers").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class);
                        movieofferslist.add(model);

                        adapter9.notifyDataSetChanged();

                    }

                }
            }
        });

        initRecyclerview9();

//        mFirestore.collection("malls").document(temp).collection("Stories").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
//
//                if (e != null) {
//
//                    Log.d(TAG, "onEvent: " + e.getMessage());
//                }
//
//                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
//
//                    if (doc.getType() == DocumentChange.Type.ADDED) {
//                        model model = doc.getDocument().toObject(model.class);
//                        storylist.add(model);
//
//                        adapter10.notifyDataSetChanged();
//
//                    }
//
//                }
//            }
//        });
//
//        initRecyclerview10();

        mFirestore.collection("malls").document(temp).collection("HomeMainOffers").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class);
                        mainmovieofferslist.add(model);

                        int i = 0;

                        do {

                            banners.add(new RemoteBanner(mainmovieofferslist.get(i).getUrl()));
                            bannerSlider.setBanners(banners);
                            i++;
                        } while (i < mainmovieofferslist.size());

//                        adapter11.notifyDataSetChanged();
                    }

                }
            }
        });
//        initRecyclerview11();
    }

    private void initRecyclerview() {
        Log.d(TAG, "initRecyclerview: init");

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyview2.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(cinemalist, mContext);
        recyview2.setAdapter(adapter);

    }

    private void initRecyclerview1() {
        Log.d(TAG, "initRecyclerview: init");

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyview4.setLayoutManager(layoutManager1);
        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
        snapHelperStart.attachToRecyclerView(recyview4);
        adapter1 = new RecyclerViewAdapter_a(shoppinglist, mContext);
        recyview4.setAdapter(adapter1);

    }

    private void initRecyclerview2() {
        Log.d(TAG, "initRecyclerview: init");

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyview6.setLayoutManager(layoutManager2);
        SnapHelper snapHelperStart1 = new GravitySnapHelper(Gravity.START);
        snapHelperStart1.attachToRecyclerView(recyview6);
        adapter2 = new RecyclerViewAdapter_b(foodlist, mContext);
        recyview6.setAdapter(adapter2);

    }

    private void initRecyclerview3() {
        Log.d(TAG, "initRecyclerview: init");

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyview8.setLayoutManager(layoutManager3);
        SnapHelper snapHelperStart2 = new GravitySnapHelper(Gravity.START);
        snapHelperStart2.attachToRecyclerView(recyview8);
        adapter3 = new RecyclerViewAdapter_c(entertainmentlist, mContext);
        recyview8.setAdapter(adapter3);

    }

    private void initRecyclerview4() {
        Log.d(TAG, "initRecyclerview: init");

        LinearLayoutManager layoutManager4 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyview10.setLayoutManager(layoutManager4);
        adapter4 = new RecyclerViewAdapter_d(servicelist, mContext);
        recyview10.setAdapter(adapter4);

    }

    private void initRecyclerview5() {
        Log.d(TAG, "initRecyclerview: init");

        LinearLayoutManager layoutManager5 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyview11.setLayoutManager(layoutManager5);
        SnapHelper snapHelperStart3 = new GravitySnapHelper(Gravity.START);
        snapHelperStart3.attachToRecyclerView(recyview1);
        adapter5 = new RecyclerViewAdapter2c(photolist, mContext);
        recyview11.setAdapter(adapter5);

    }

    private void initRecyclerview6() {
        Log.d(TAG, "initRecyclerview: init");

        LinearLayoutManager layoutManager6 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyview5.setLayoutManager(layoutManager6);
        SnapHelper snapHelperStart4 = new GravitySnapHelper(Gravity.START);
        snapHelperStart4.attachToRecyclerView(recyview5);
        adapter6 = new RecyclerViewAdapter2(shoppinglistoffers, mContext);
        recyview5.setAdapter(adapter6);

    }

    private void initRecyclerview7() {
        Log.d(TAG, "initRecyclerview: init");

        LinearLayoutManager layoutManager7 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyview7.setLayoutManager(layoutManager7);
        SnapHelper snapHelperStart5 = new GravitySnapHelper(Gravity.START);
        snapHelperStart5.attachToRecyclerView(recyview7);
        adapter7 = new RecyclerViewAdapter2a(foodlistoffers, mContext);
        recyview7.setAdapter(adapter7);

    }

    private void initRecyclerview8() {
        Log.d(TAG, "initRecyclerview: init");

        LinearLayoutManager layoutManager8 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyview9.setLayoutManager(layoutManager8);
        SnapHelper snapHelperStart6 = new GravitySnapHelper(Gravity.START);
        snapHelperStart6.attachToRecyclerView(recyview9);
        adapter8 = new RecyclerViewAdapter2b(Entertainmentofferlist, mContext);
        recyview9.setAdapter(adapter8);

    }

    private void initRecyclerview9() {
        Log.d(TAG, "initRecyclerview: init");

        LinearLayoutManager layoutManager9 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyview3.setLayoutManager(layoutManager9);
        SnapHelper snapHelperStart7 = new GravitySnapHelper(Gravity.START);
        snapHelperStart7.attachToRecyclerView(recyview3);
        adapter9 = new RecyclerViewAdapter3(movieofferslist, mContext);
        recyview3.setAdapter(adapter9);


    }

//    private void initRecyclerview10() {
//        Log.d(TAG, "initRecyclerview: init");
//
//        LinearLayoutManager layoutManager10 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
//        recyview1.setLayoutManager(layoutManager10);
//        adapter10 = new RecyclerViewAdapter4(storylist, mContext);
//        recyview1.setAdapter(adapter10);
//
//    }

//    private void initRecyclerview11() {
//        Log.d(TAG, "initRecyclerview: init");
//
//        LinearLayoutManager layoutManager11 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
//        recyview.setLayoutManager(layoutManager11);
//        SnapHelper snapHelperStart8 = new GravitySnapHelper(Gravity.END);
//        snapHelperStart8.attachToRecyclerView(recyview);
//        recyview.addItemDecoration(new CirclePagerIndicatorDecoration());
//        adapter11 = new RecyclerViewAdapter5(mainmovieofferslist, mContext);
//        recyview.setAdapter(adapter11);
//
//    }

    private void getmoviestories() {

        mFirestore.collection("malls").document(temp).collection("Stories").document("MovieStories").addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                if (documentSnapshot.exists()) {

                    String storyname = documentSnapshot.getString("name");
                    mtext1.setText(storyname);

                    String storyurl = documentSnapshot.getString("url");
                    Glide.with(getBaseContext())
                            .asBitmap()
                            .load(storyurl)
                            .into(mcircle1);

                }

            }
        });
        mFirestore.collection("malls").document(temp).collection("Stories").document("MovieStories").collection("MovieStoriesUrls").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {

                        Flag++;

                        if (Flag != 0) {
                            moutercircle.setBorderColor(getResources().getColor(R.color.red));
                        }

                    }
                }
            }
        });

        mcircle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                moutercircle.setBorderColor(getResources().getColor(R.color.grey));

                Intent intent = new Intent(HomeActivity.this, DisplayStoryActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("mall_id", temp);
                startActivity(intent);

            }
        });
    }

    private void init() {
        MovieBuzzFragment fragment = new MovieBuzzFragment();
        FragmentTransaction transaction = HomeActivity.this.manager.beginTransaction();
        transaction.replace(R.id.moviebuzz_container, fragment);
        manager.popBackStack("init", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction.commit();

        FoodBuzzFragment fragment1 = new FoodBuzzFragment();
        FragmentTransaction transaction1 = HomeActivity.this.manager.beginTransaction();
        transaction1.replace(R.id.foodbuzz_container, fragment1);
        manager.popBackStack("init", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction1.commit();

        ShoppingBuzzFragment fragment2 = new ShoppingBuzzFragment();
        FragmentTransaction transaction2 = HomeActivity.this.manager.beginTransaction();
        transaction2.replace(R.id.shoppingbuzz_container, fragment2);
        manager.popBackStack("init", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction2.commit();

        CategoryShoppingFragment fragment3 = new CategoryShoppingFragment();
        FragmentTransaction transaction3 = HomeActivity.this.manager.beginTransaction();
        transaction3.replace(R.id.category_container, fragment3);
        manager.popBackStack("init", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction3.commit();

        CategoryFoodFragment fragment4 = new CategoryFoodFragment();
        FragmentTransaction transaction4 = HomeActivity.this.manager.beginTransaction();
        transaction4.replace(R.id.food_category_container, fragment4);
        manager.popBackStack("init", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction4.commit();
    }
}

