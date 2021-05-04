package com.example.radiomonastir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class HomeAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

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

    public void afficheplanification(View view) {
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

    public void affichemaintenance(View view) {
        findViewById(R.id.imageButton7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAdminActivity.this, MaintenanceActivity.class);
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
}