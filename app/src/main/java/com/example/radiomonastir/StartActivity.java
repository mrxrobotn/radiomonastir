package com.example.radiomonastir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.analytics.FirebaseAnalytics;

public class StartActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Firebase Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(StartActivity.this, SignInActivity.class);
                StartActivity.this.startActivity(mainIntent);
                StartActivity.this.finish();
            }
        }, 3000);
    }
}