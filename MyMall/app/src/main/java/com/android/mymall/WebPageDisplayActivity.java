package com.android.mymall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class WebPageDisplayActivity extends AppCompatActivity {
    private static final String TAG = "WebPageDisplayActivity";

    private WebView mwebview;
    private TextView mtext;

    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page_display);

        mwebview = findViewById(R.id.webview);
        mFirestore = FirebaseFirestore.getInstance();
        mtext = findViewById(R.id.movieurltxt);

        getIncomingIntent();
    }
    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intent");
        if (getIntent().hasExtra("moviebuzzfeed_id")) {
            Log.d(TAG, "getIncomingIntent: found extras");

            String movie_feed_id = getIntent().getStringExtra("moviebuzzfeed_id");

            setmoviefeedurl(movie_feed_id);
        }
    }
    private void setmoviefeedurl(String feed_id){
        Toast.makeText(this,feed_id, Toast.LENGTH_SHORT).show();

        if (feed_id != null){

            mFirestore.collection("buzzfeed").document("moviebuzz").collection("moviebuzzfeed").document(feed_id).addSnapshotListener(this,new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                    if (documentSnapshot.exists()){

                        String moviepage_url =  documentSnapshot.getString("weburl");
                        mtext.setText(moviepage_url);

                        mwebview.setWebViewClient(new WebViewClient());
                        mwebview.loadUrl(moviepage_url);


                       WebSettings settings = mwebview.getSettings();
                        settings.setJavaScriptEnabled(true);

                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed() {

        if (mwebview.canGoBack()){

            mwebview.goBack();
        }

        super.onBackPressed();
    }
}
