package com.example.radiomonastir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class HomeTechnicienActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_technicien);


    }

    public void logout(View view) {
        findViewById(R.id.imageView18).setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomeTechnicienActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        }));
    }

    public void affichemaintenancetechnicien(View view) {
        findViewById(R.id.imageButton4).setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeTechnicienActivity.this, MaintenanceTechnicienActivity.class);
                startActivity(intent);
            }
        }));
    }

    public void afficheplanificationtechnicien(View view) {
    }
}