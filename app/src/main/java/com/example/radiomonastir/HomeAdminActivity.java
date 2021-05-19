package com.example.radiomonastir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;


public class HomeAdminActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        mAuth = FirebaseAuth.getInstance();
    }

    public void affichestudio(View view) {
        findViewById(R.id.imageButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAdminActivity.this, StudioActivity.class);
                startActivity(intent);
            }
        });
    }

    public void afficheplanificationadmin(View view) {
        findViewById(R.id.imageButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAdminActivity.this, PlanificationActivity.class);
                startActivity(intent);
            }
        });
    }

    public void affichecellule(View view) {
        findViewById(R.id.imageButton6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAdminActivity.this, CelluleActivity.class);
                startActivity(intent);
            }
        });
    }

    public void affichemaintenanceadmin(View view) {
        findViewById(R.id.imageButton7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAdminActivity.this, MaintenanceAdminActivity.class);
                startActivity(intent);
            }
        });
    }

    public void afficheserveur(View view) {
        findViewById(R.id.imageButton8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAdminActivity.this, ServeurActivity.class);
                startActivity(intent);
            }
        });
    }

    public void affichereformer(View view) {
        findViewById(R.id.imageView4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAdminActivity.this, ReformerActivity.class);
                startActivity(intent);
            }
        });
    }

    public void affichemagasin(View view) {
        findViewById(R.id.imageView5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAdminActivity.this, MagasinActivity.class);
                startActivity(intent);
            }
        });
    }

    public void afficheajoutermemebre(View view) {
        findViewById(R.id.imageView6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAdminActivity.this, GestionTechnicienActivity.class);
                startActivity(intent);
            }
        });
    }

    public void logout(View view) {
        findViewById(R.id.imageView).setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HomeAdminActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        }));
    }
}