package com.android.mymall;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Gravity;
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

public class MallinfoActivity extends AppCompatActivity {
    private static final String TAG = "MallinfoActivity";

    private android.support.v4.app.FragmentManager manager;

    private Context mcontext;

    private FirebaseFirestore mfirestore;

    private ImageView mallimage;
    private TextView mallname;

    private RecyclerViewAdapter_availability adapter;
    private RecyclerView recyview;

    private List<model> availability_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mallinfo);

        manager = getSupportFragmentManager();
        mfirestore = FirebaseFirestore.getInstance();
        recyview = findViewById(R.id.recyclerview);

        mallimage = findViewById(R.id.mallinfoimage);
        mallname = findViewById(R.id.mallnametxt);



        getIncomingintent();
    }

    private void getIncomingintent() {

        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        if (getIntent().hasExtra("mall_id")) {
            Log.d(TAG, "getIncomingIntent: found extras");

            String mallid = getIntent().getStringExtra("mall_id");
//            Toast.makeText(getBaseContext(),mallid, Toast.LENGTH_SHORT).show();

            setinfo(mallid);
        }
    }

    private void setinfo(String m_id) {

        mfirestore.collection("malls").document(m_id).addSnapshotListener(this,new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                if (documentSnapshot.exists()) {

                    String mall_image = documentSnapshot.getString("mallurl");
                    String mall_name = documentSnapshot.getString("mallname");

                    Glide.with(getBaseContext())
                            .asBitmap()
                            .load(mall_image)
                            .into(mallimage);

                    mallname.setText(mall_name);
                }
            }
        });
        mfirestore.collection("malls").document(m_id).collection("general_info").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {

                    Log.d(TAG, "onEvent: " + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        model model = doc.getDocument().toObject(model.class);
                        availability_list.add(model);

                        adapter.notifyDataSetChanged();

                    }

                }

            }
        });
        recyclerview();
    }

    private void recyclerview() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false);
        recyview.setLayoutManager(layoutManager);
        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
        snapHelperStart.attachToRecyclerView(recyview);
        adapter = new RecyclerViewAdapter_availability(availability_list,getBaseContext());
        recyview.setAdapter(adapter);

    }

}
